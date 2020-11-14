import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    static WebDriver driver = null;
    static WebDriverWait driverWait = null;
    static JavascriptExecutor javascriptExecutor = null;

    public static final int DEFAULT_WEB_DRIVER_WAIT = 30;
    public static final int DEFAULT_IMPLICIT_WAIT = 30;
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;
    public static final int DEFAULT_SET_SCRIPT_TIMEOUT = 30;

    public static void setupChromeBrowser(boolean withHeadlessBrowser) {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.silentOutput", "true");
        ChromeOptions chromeOptions = new ChromeOptions().addArguments("--window-size=1920,1080", "--window-position=0,0");

        if(withHeadlessBrowser) { chromeOptions.addArguments("--headless"); }

        driver = new ChromeDriver(chromeOptions);
        driverWait = new WebDriverWait(driver, DEFAULT_WEB_DRIVER_WAIT);
        javascriptExecutor = (JavascriptExecutor) driver;

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(DEFAULT_SET_SCRIPT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static void closeBrowser() {
        if(driver != null) { driver.quit(); }
    }

    // Usage: FileUtils.copyFile(DriverManager.takeScreenshotAsFile(), screenshotLocation);
    public static File takeScreenshotAsFile() { return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); }
    public static byte[] takeScreenshotAsBytes() { return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES); }

    public static String getCookieByName(String cookieName) { return driver.manage().getCookieNamed(cookieName).getValue(); }
    public static void sleep(long time) { try{ Thread.sleep(time); } catch(InterruptedException e){ e.printStackTrace(); } }
}
