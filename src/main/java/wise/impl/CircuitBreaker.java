package wise.impl;

import wise.CircuitOpenException;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.LongSupplier;
import java.util.Objects;
import java.util.Deque;
import java.util.function.Supplier;

public class CircuitBreaker<T> {

    private final int failureThreshold;
    private final long failureWindowNanos;
    private final long recoveryPeriodNanos;
    private final LongSupplier clock;
    private final Deque<Long> failures;
    private final Lock lock;
    private State state = State.CLOSED;
    private long circuitOpenedInNanos = -1;
    private boolean halfOpenProbeInFlight;

    public CircuitBreaker(
            int failureThreshold,
            long failureWindow,
            long recoveryPeriod,
            TimeUnit unit
    ) {
        Objects.requireNonNull(unit, "TimeUnit cannot be null");
        this(
                failureThreshold,
                unit.toNanos(failureWindow),
                unit.toNanos(recoveryPeriod),
                System::nanoTime
        );
    }

    public CircuitBreaker(
            int failureThreshold,
            long failureWindowNanos,
            long recoveryPeriodNanos,
            LongSupplier clock
    ) {
        if (failureThreshold <= 0)
            throw new IllegalArgumentException("Failure threshold should be positive");
        if (failureWindowNanos <= 0 || recoveryPeriodNanos <= 0)
            throw new IllegalArgumentException("Period should be positive");

        this.failureThreshold = failureThreshold;
        this.failureWindowNanos = failureWindowNanos;
        this.recoveryPeriodNanos = recoveryPeriodNanos;
        this.clock = Objects.requireNonNull(clock);
        this.failures = new ArrayDeque<>();
        this.lock = new ReentrantLock();
    }

    public T execute(Supplier<T> request) {
        Objects.requireNonNull(request);
        var permit = requirePermission();
        try {
            var response = request.get();
            onSuccess(permit);
            return response;
        } catch (RuntimeException e) {
            onFailure(permit);
            throw e;
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

            // remove expired durations
            removeExpiredNanos(now);
            failures.addLast(now);

            if(failures.size() >= failureThreshold) {
                openCircuit(now);
            }

        } finally {
            lock.unlock();
        }
    }

    private void removeExpiredNanos(long now) {
        while(!failures.isEmpty()) {
            var oldestNano = failures.getFirst();
            if(now - oldestNano >= failureWindowNanos) {
                failures.removeFirst();
            } else {
                break;
            }
        }
    }

    private void onSuccess(Permit permit) {
        lock.lock();
        try {
            if(permit == Permit.PROBE)
                closeCircuit();
        } finally {
            lock.unlock();
        }
    }

    private void openCircuit(long time) {
        state = State.OPEN;
        circuitOpenedInNanos = time;
        halfOpenProbeInFlight = false;
        failures.clear();
    }

    private void closeCircuit() {
        state = State.CLOSED;
        circuitOpenedInNanos = -1;
        halfOpenProbeInFlight = false;
        failures.clear();
    }

    private Permit requirePermission() {
        lock.lock();
        var now = clock.getAsLong();
        try {
            if (state == State.OPEN) {
                var recoveryPeriodElapsed = (now - circuitOpenedInNanos) >= recoveryPeriodNanos;
                if (!recoveryPeriodElapsed)
                    throw new CircuitOpenException("Circuit is " + state);

                state = State.HALF_OPEN;
            }

            if (state == State.HALF_OPEN) {
                if (halfOpenProbeInFlight) {
                    throw new CircuitOpenException("Circuit is " + state + "; probe in flight.");
                }

                halfOpenProbeInFlight = true;
                return Permit.PROBE;
            }
            return Permit.NORMAL;
        } finally {
            lock.unlock();
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
}