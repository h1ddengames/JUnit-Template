package site.hiddengames.factories;

import site.hiddengames.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * A factory that provides WebDrivers for Chrome and Firefox in a thread-safe way.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/29/2020",
        currentRevision = 2, lastModified = "12/30/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class DriverFactory {
    // Publicly and statically available so that if a driver's waits are changed,
    // there is an easy way to change it back to default.
    // Usage:
    // DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    // DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(DriverFactory.DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
    public static final int DEFAULT_WEB_DRIVER_WAIT = 30;
    public static final int DEFAULT_IMPLICIT_WAIT = 30;
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;
    public static final int DEFAULT_SET_SCRIPT_TIMEOUT = 30;

    public enum Browser { CH, CHROME, FF, FIREFOX, IE, EXPLORER }

    ThreadLocal<WebDriver> driverFactory = new ThreadLocal<>();
    ThreadLocal<WebDriverWait> driverWaitFactory = new ThreadLocal<>();

    private static DriverFactory instance;
    private DriverFactory() { }

    // Singleton pattern: only one DriverFactory should exist at any given time.
    // Allows other classes to call this class statically by using DriverFactory.getInstance()
    public static DriverFactory getInstance() {
        if(instance == null) { instance = new DriverFactory(); }
        return instance;
    }

    public WebDriver getDriver() { return driverFactory.get(); }
    public WebDriverWait getDriverWait() { return driverWaitFactory.get(); }

    /**
     * Specify the type of Driver to generate and track.
     * @param browser The type of browser to generate -- Chrome or Firefox.
     * @param withHeadlessBrowser Should the driver be headless -- show no GUI?
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public final void setDriver(Browser browser, boolean withHeadlessBrowser) {
        switch (browser) {
            case CHROME:
            case CH:
                startChrome(withHeadlessBrowser);
                break;
            case FIREFOX:
            case FF:
                startFirefox(withHeadlessBrowser);
                break;
            case EXPLORER:
            case IE:
                startIE();
                break;
            default:
                break;
        }
    }

    /**
     * Close all browser windows and delete the reference to the WebDriver.
     * @param driver The WebDriver to close and delete.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public void cleanupDriver(WebDriver driver) {
        if(driver != null) { driver.quit(); }
        driverFactory.remove();
    }

    private void startChrome(boolean withHeadlessBrowser) {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.silentOutput", "true"); // Stop WebDriver from polluting the console/logs.
        ChromeOptions chromeOptions = new ChromeOptions().addArguments("--window-size=1920,1080", "--window-position=0,0");

        if(withHeadlessBrowser) { chromeOptions.addArguments("--headless"); }

        ChromeDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait driverWait = new WebDriverWait(driver, DEFAULT_WEB_DRIVER_WAIT);

        webDriverTimeoutSetup(driver);

        driverFactory.set(driver);
        driverWaitFactory.set(driverWait);
    }

    private void startFirefox(boolean withHeadlessBrowser) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions().addArguments("-width=1920", "-height=1080");
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.FATAL); // Stop WebDriver from polluting the console/logs.

        if(withHeadlessBrowser) { firefoxOptions.addArguments("-headless"); }

        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        WebDriverWait driverWait = new WebDriverWait(driver, DEFAULT_WEB_DRIVER_WAIT);

        webDriverTimeoutSetup(driver);

        driverFactory.set(new FirefoxDriver(firefoxOptions));
        driverWaitFactory.set(driverWait);
    }

    private void startIE() {
        System.out.println("NOT IMPLEMENTED.");
    }

    private void webDriverTimeoutSetup(WebDriver driver) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(DEFAULT_SET_SCRIPT_TIMEOUT, TimeUnit.SECONDS);
    }
}
