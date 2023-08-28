package ui.selenium;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {

    static final Logger logger = LoggerFactory.getLogger(Driver.class);

    public Driver() {}

    public static RemoteWebDriver driver;


    public static RemoteWebDriver browserDriver(String browser) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        return driver;
    }

    public static void pause() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
    }

    public static void quitBrowser(WebDriver driver) {
        // This closes all browser sessions and the driver too
        if (driver != null) {
            driver.quit();
        }
    }

    public static void closeBrowserWindow(WebDriver driver) {
        // This closes just the active browser session that has focus, or the driver if no browsers are open
        if (driver != null) {
            driver.close();
        }
    }

    public static void executeJavaScript(WebDriver driver, String script, Object args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, args);
    }

}