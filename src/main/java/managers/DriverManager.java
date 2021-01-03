package managers;

import annotations.*;
import factories.DriverFactory;

import org.openqa.selenium.*;
import java.io.File;

/**
 * A helper class that provides methods that generate data.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/29/2020",
        currentRevision = 2, lastModified = "12/30/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class DriverManager {

    /**
     * Create a Chrome WebDriver with GUI.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static void createChromeDriver() {
        DriverFactory.getInstance().setDriver(DriverFactory.Browser.CHROME, false);
    }

    /**
     * Create a headless Chrome WebDriver.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static void createHeadlessChromeDriver() {
        DriverFactory.getInstance().setDriver(DriverFactory.Browser.CHROME, true);
    }

    /**
     * Create a Firefox WebDriver with GUI.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static void createFirefoxDriver() {
        DriverFactory.getInstance().setDriver(DriverFactory.Browser.FIREFOX, false);
    }

    /**
     * Create a headless Firefox WebDriver.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static void createHeadlessFirefoxDriver() {
        DriverFactory.getInstance().setDriver(DriverFactory.Browser.FIREFOX, true);
    }

    /**
     * Close the currently active WebDriver.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static void closeBrowser() {
        DriverFactory.getInstance().cleanupDriver(DriverFactory.getInstance().getDriver());
    }

    /**
     * Takes a screenshot of the current thread's active WebDriver. Works even when the WebDriver is headless.
     * Usage: <pre>{@code
     * FileUtils.copyFile(DriverManager.takeScreenshotAsFile(), screenshotLocation);
     * }</pre>
     * @return The bytes containing the screenshot taken.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static File takeScreenshotAsFile() {
        return ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
    }

    /**
     * Takes a screenshot of the current thread's active WebDriver. Works even when the WebDriver is headless.
     * @return The bytes containing the screenshot taken.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static byte[] takeScreenshotAsBytes() {
        return ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Get the value of a cookie given it's name.
     * @param cookieName The name of the cookie as shown in the DevConsole under the Application tab.
     * @return The value that the cookie contained.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getCookieByName(String cookieName) {
        return DriverFactory.getInstance().getDriver().manage().getCookieNamed(cookieName).getValue();
    }

    /**
     * A dumb sleep. Avoid using where possible. Mostly for testing purposes.
     * @param timeInMilliseconds The amount of time in milliseconds to wait before continuing code execution.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static void sleep(long timeInMilliseconds) { try{ Thread.sleep(timeInMilliseconds); } catch(InterruptedException e){ e.printStackTrace(); } }
}
