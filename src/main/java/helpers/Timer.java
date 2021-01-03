package helpers;

import annotations.*;

/**
 * A helper class that provides simple stopwatch functionality.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/31/2020",
        currentRevision = 1, lastModified = "12/31/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class Timer {
    private boolean isRunning = false;
    private long startTimeInNanoseconds = 0;
    private long totalTimeInNanoseconds = 0;

    public boolean isRunning() { return isRunning; }

    /**
     * Starts the timer.
     * @throws IllegalStateException Cannot start the timer when the timer is already running.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public void start() throws IllegalStateException {
        if(isRunning) { throw new IllegalStateException("Timer is already running."); }

        startTimeInNanoseconds = System.nanoTime();
        isRunning = true;
    }

    /**
     * Stops the timer.
     * @throws IllegalStateException Cannot stop the timer when the timer is not running.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public void stop() throws IllegalStateException {
        if (!isRunning) { throw new IllegalStateException("Timer has not been started."); }

        totalTimeInNanoseconds = System.nanoTime() - startTimeInNanoseconds;
        isRunning = false;
    }

    public double getTotalTimeInSeconds() {
        if (isRunning) {
            throw new IllegalStateException("Timer must be stopped in order to get total time.");
        }
        return convertNanosecondsToSeconds(totalTimeInNanoseconds);
    }

    public double getTotalTimeInMilliseconds() {
        if (isRunning) {
            throw new IllegalStateException("Timer must be stopped in order to get total time.");
        }
        return convertNanosecondsToMilliseconds(totalTimeInNanoseconds);
    }

    private static double convertNanosecondsToSeconds(long nanoseconds) { return Math.round( (((double) nanoseconds / 1.0E9D) *  100)) / (double) 100; }
    private static double convertNanosecondsToMilliseconds(long nanoseconds) { return Math.round( (((double) nanoseconds / 1.0E6D) * 100)) / (double) 100; }
}
