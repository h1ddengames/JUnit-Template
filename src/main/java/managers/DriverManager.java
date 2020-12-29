package managers;

import factories.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class DriverManager {
    public static void createChromeDriver() {
        DriverFactory.getInstance().setDriver(DriverFactory.Browser.CHROME, true);
    }

    public static void closeBrowser() {
        DriverFactory.getInstance().cleanupDriver(DriverFactory.getInstance().getDriver());
    }

    // Usage: FileUtils.copyFile(DriverManager.takeScreenshotAsFile(), screenshotLocation);
    public static File takeScreenshotAsFile() {
        return ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
    }

    public static byte[] takeScreenshotAsBytes() {
        return ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String getCookieByName(String cookieName) {
        return DriverFactory.getInstance().getDriver().manage().getCookieNamed(cookieName).getValue();
    }

    public static void sleep(long time) { try{ Thread.sleep(time); } catch(InterruptedException e){ e.printStackTrace(); } }
}
