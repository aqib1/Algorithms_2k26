package cb;

import wise.CircuitOpenException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class CircuitBreakerService {
    private final Map<String, CircuitBreaker<Response>> circuitBreakers;

    public CircuitBreakerService(Map<String, CircuitBreaker<Response>> circuitBreakers) {
        this.circuitBreakers = new ConcurrentHashMap<>(circuitBreakers);
    }

    public CircuitBreaker<Response> getCircuitBreaker(String serviceName) {
        var circuitBreaker = circuitBreakers.get(serviceName);
        if (circuitBreaker == null) {
            throw new IllegalArgumentException(
                    "No Circuit Breaker found for: " + serviceName);
        }
        return circuitBreaker;
    }

    public static class CircuitBreaker<T> {
        private final int failureThreshold;
        private final long failureWindowNanos;
        private final long recoveryPeriodNanos;
        private final LongSupplier clock;
        private final Lock lock;
        private final Deque<Long> failures;
        private State state = State.CLOSED;
        private long circuitOpenedInNano = -1;
        private boolean halfOpenProbeInFlight;

        public CircuitBreaker(
                int failureThreshold,
                long failureWindow,
                long recoveryPeriod,
                TimeUnit unit
        ) {
            Objects.requireNonNull(unit, "Please provide required time unit");
            this(
                    failureThreshold,
                    unit.toNanos(failureWindow),
                    unit.toNanos(recoveryPeriod),
                    System::nanoTime
            );
        }


        CircuitBreaker(
                int failureThreshold,
                long failureWindowNanos,
                long recoveryPeriodNanos,
                LongSupplier clock
        ) {

            // validation
            if (failureThreshold <= 0)
                throw new IllegalArgumentException("Failure threshold should be a positive number");
            if (failureWindowNanos <= 0 || recoveryPeriodNanos <= 0)
                throw new IllegalArgumentException("Period should be a positive number");

            this.failureThreshold = failureThreshold;
            this.failureWindowNanos = failureWindowNanos;
            this.recoveryPeriodNanos = recoveryPeriodNanos;
            this.clock = Objects.requireNonNull(clock);
            this.lock = new ReentrantLock();
            this.failures = new ArrayDeque<>();
        }

        public T execute(Supplier<T> request) {
            Objects.requireNonNull(request, "Please provide request to execute");
            var permit = acquirePermission();
            try {
                var response = request.get();
                onSuccess(permit);
                return response;
            } catch (RuntimeException ex) {
                onFailure(permit);
                throw ex;
            }
        }

        private void onFailure(Permit permit) {
            lock.lock();
            try {
                var now = clock.getAsLong();
                if(permit == Permit.PROBE) {
                    openCircuit(now);
                    return;
                }

                if(state != State.CLOSED)
                    return;

                // removed stale failure
                removeStaleFailure(now);
                failures.addLast(now);

                if(failures.size() >= failureThreshold) {
                    openCircuit(now);
                }

            } finally {
                lock.unlock();
            }

        }

        private void removeStaleFailure(long now) {
            while(!failures.isEmpty()) {
                var oldestTime = failures.peekFirst();
                if((now - oldestTime) >= failureWindowNanos) {
                    failures.pollFirst();
                } else {
                    break;
                }
            }
        }

        private void onSuccess(Permit permit) {
            lock.lock();
            try {
                if(permit == Permit.PROBE) {
                    closeCircuit();
                }
            } finally {
                lock.unlock();
            }
        }

        private void openCircuit(long now) {
            state = State.OPEN;
            circuitOpenedInNano = now;
            failures.clear();
            halfOpenProbeInFlight = false;
        }

        private void closeCircuit() {
            state = State.CLOSED;
            circuitOpenedInNano = -1;
            failures.clear();
            halfOpenProbeInFlight = false;
        }

        private Permit acquirePermission() {
            lock.lock();
            try {
                var now = clock.getAsLong();
                if (state == State.OPEN) {
                    var isRecoveryPeriodElapsed = (now - circuitOpenedInNano) >= recoveryPeriodNanos;
                    if (!isRecoveryPeriodElapsed)
                        throw new CircuitOpenException("Circuit is " + state);
                    state = State.HALF_OPEN;
                }
                if (state == State.HALF_OPEN) {
                    if(halfOpenProbeInFlight)
                        throw new CircuitOpenException("Circuit is " + state + "; probe in flight");

                    halfOpenProbeInFlight = true;
                    return Permit.PROBE;
                }
                return Permit.NORMAL;
            } finally {
                lock.unlock();
            }
        }

    }

    private enum Permit {
        PROBE,
        NORMAL
    }

    private enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    public record Response(String payload) {
            public Response() {
                this("200");
            }

    }
}
