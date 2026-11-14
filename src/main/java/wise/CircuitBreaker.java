
package wise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class CircuitBreaker<T> {

    public enum State {
        CLOSED,
        OPEN,
        HALF_OPEN
    }

    /*
     * Identifies the type of permission granted to a request.
     * NORMAL: request admitted while CLOSED.
     * PROBE:  the single trial request admitted while HALF_OPEN.
     */
    private enum Permit {
        NORMAL,
        PROBE
    }

    private final int failureThreshold;
    private final long failureWindowNanos;
    private final long recoveryPeriodNanos;
    private final LongSupplier clock;
    private final Lock lock = new ReentrantLock();
    private final Deque<Long> failures = new ArrayDeque<>();
    private State state = State.CLOSED;
    private long lastFailureTime = -1;

    /*
     * Ensures only one trial request executes in HALF_OPEN.
     */
    private boolean halfOpenProbeInFlight = false;


    public CircuitBreaker(
            int failureThreshold,
            long failureWindow,
            long recoveryPeriod,
            TimeUnit unit
    ) {
        this(
                failureThreshold,
                unit.toNanos(failureWindow),
                unit.toNanos(recoveryPeriod),
                System::nanoTime
        );
    }


    // Package-private constructor for deterministic tests.
    CircuitBreaker(
            int failureThreshold,
            long failureWindowNanos,
            long recoveryPeriodNanos,
            LongSupplier clock
    ) {
        if (failureThreshold <= 0) {
            throw new IllegalArgumentException(
                    "Failure threshold must be positive"
            );
        }

        if (failureWindowNanos <= 0 ||
                recoveryPeriodNanos <= 0) {
            throw new IllegalArgumentException(
                    "Time windows must be positive"
            );
        }

        this.failureThreshold = failureThreshold;
        this.failureWindowNanos = failureWindowNanos;
        this.recoveryPeriodNanos = recoveryPeriodNanos;
        this.clock = Objects.requireNonNull(clock);
    }


    public T execute(Supplier<T> request) {

        Objects.requireNonNull(request);

        // Step 1: Check whether the request is allowed.
        Permit permit = acquirePermission();
        try {
            // Step 2: Execute outside the lock.
            T response = request.get();
            // Step 3: Handle successful execution.
            onSuccess(permit);
            return response;

        } catch (RuntimeException exception) {
            // Step 4: Handle failed execution.
            onFailure(permit);
            throw exception;
        }
    }


    private Permit acquirePermission() {
        lock.lock();
        try {
            long now = clock.getAsLong();
            // Handle OPEN state.
            if (state == State.OPEN) {
                boolean recoveryPeriodElapsed =
                        now - lastFailureTime >= recoveryPeriodNanos;
                if (!recoveryPeriodElapsed) {
                    throw new CircuitOpenException(
                            "Circuit breaker is OPEN"
                    );
                }

                // Allow transition to HALF_OPEN.
                state = State.HALF_OPEN;
            }

            // Handle HALF_OPEN state.
            if (state == State.HALF_OPEN) {
                if (halfOpenProbeInFlight) {
                    throw new CircuitOpenException(
                            "Circuit breaker is HALF_OPEN; probe in progress"
                    );
                }

                // Reserve the single trial request.
                halfOpenProbeInFlight = true;
                return Permit.PROBE;
            }

            // Normal request while CLOSED.
            return Permit.NORMAL;

        } finally {
            lock.unlock();
        }
    }


    private void onSuccess(Permit permit) {
        lock.lock();
        try {
            // Only a successful HALF_OPEN probe changes the state.
            if (permit == Permit.PROBE &&
                    state == State.HALF_OPEN &&
                    halfOpenProbeInFlight) {
                state = State.CLOSED;
                halfOpenProbeInFlight = false;
                failures.clear();
                lastFailureTime = -1;
            }

        } finally {
            lock.unlock();
        }
    }


    private void onFailure(Permit permit) {
        lock.lock();
        try {
            long now = clock.getAsLong();
            // Failed HALF_OPEN trial.
            if (permit == Permit.PROBE) {
                if (state == State.HALF_OPEN &&
                        halfOpenProbeInFlight) {
                    openCircuit(now);
                }
                return;
            }

            // Only count normal failures while CLOSED.
            if (state != State.CLOSED) {
                return;
            }

            // Remove failures outside the sliding window.
            removeExpiredFailures(now);

            // Record the new failure.
            failures.addLast(now);
            lastFailureTime = now;

            // Check whether the threshold has been reached.
            if (failures.size() >= failureThreshold) {
                openCircuit(now);
            }

        } finally {
            lock.unlock();
        }
    }


    private void removeExpiredFailures(long now) {
        while (!failures.isEmpty()) {
            long oldestFailure = failures.peekFirst();
            // The window is [now - failureWindow, now].
            // A failure exactly on the lower boundary expires.
            if (now - oldestFailure >= failureWindowNanos) {
                failures.removeFirst();
            } else {
                break;
            }
        }
    }


    private void openCircuit(long now) {
        state = State.OPEN;
        lastFailureTime = now;
        halfOpenProbeInFlight = false;
        failures.clear();
    }


    public State getState() {
        lock.lock();
        try {
            return state;
        } finally {
            lock.unlock();
        }
    }
}
