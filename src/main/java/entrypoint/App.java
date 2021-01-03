package entrypoint;

import annotations.*;

/**
 * The entrypoint of this application. Currently is acting as a simple calculator.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "11/12/2020",
        currentRevision = 2, lastModified = "11/14/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"AppTest"})
public class App {
    /**
     * Adds two integers together.
     * @param x The first integer.
     * @param y The second integer.
     * @return An integer equal to adding the first and second parameter together.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"AppTest"})
    public int add(int x, int y) {
        return x + y;
    }

    /**
     * Subtracts one integer from another.
     * @param x The integer to be subtracted from.
     * @param y The amount to subtract from the first integer.
     * @return An integer equal to the first integer minus the second integer.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"AppTest"})
    public int subtract(int x, int y) {
        return x - y;
    }
}
