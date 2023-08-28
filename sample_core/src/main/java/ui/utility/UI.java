package ui.utility;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ui.selenium.Driver;

public class UI extends Driver{

    private static final Logger logger = LoggerFactory.getLogger(UI.class);

    private final static int DEFAULT_BROWSER_TIMEOUT = 3000;

    public UI() {}

    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            if (element.isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element, int timeOut) {

        try {
            for (int second = 0;second < timeOut; second++) {
                if (element.isDisplayed()) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean isElementPresent(By locatorKey, int timeOut) throws InterruptedException {

        try {
            for (int second = 0;second < timeOut; second++) {
                if (isElementPresent(locatorKey)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean isElementPresent(String locator, String text, int timeOut) throws InterruptedException {

        try {
            for (int second = 0;second < timeOut; second++) {
                if (text.equals(locator)) {
                    return true;
                } else {
                    Thread.sleep(1000);
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public boolean isElementPresent(WebElement locator, String text, int timeOut) throws InterruptedException {
        try {
            for (int second = 0;second < timeOut; second++) {
                if (locator.isDisplayed() && locator.getText().equals(text)) {
                    return true;
                } else {
                    Thread.sleep(1000);
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementSelected(By locatorKey) {
        try {
            driver.findElement(locatorKey).isSelected();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(By locatorKey){
        return driver.findElement(locatorKey).isDisplayed();
    }

    public boolean isElementEnabled(By locatorKey) {
        return driver.findElement(locatorKey).isEnabled();
    }

    public boolean isElementVisibleAndEnabled(By locatorKey){
        try {
            driver.findElement(locatorKey).isDisplayed();
            driver.findElement(locatorKey).isEnabled();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static void click(WebElement element) {
        try {
            logger.info("clicking " + element.getText());
            element.click();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public static void click(WebDriver driver, By locatorKey) {
        try {
            click(driver, locatorKey, 0);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public static void click(WebDriver driver, By locatorKey, int timeOut) {
        try {
            // Element must be visible in order to click it
            waitUntilElementIsVisible(driver, locatorKey, timeOut);

            WebElement we = driver.findElement(locatorKey);
            we.click();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public static boolean doesElementContainText(WebDriver driver, By locatorKey, int timeOut, String findText) {
        try { // If not exist and reach timeout, want to return false (not contain) so test case can fail and continue forward (do not want error)
            waitUntilElementIsVisible(driver, locatorKey, timeOut);
        }
        catch (Exception ex) {
            return false;
        }

        String actualString = driver.findElement(locatorKey).getText();
        return actualString.contains(findText);
    }


    public static boolean doesPageContainText(WebDriver driver, int timeOut /* millisconds */, String findText) {
        boolean foundMatch = false;

        try {
            long endTime = System.currentTimeMillis() + (timeOut);
            while (System.currentTimeMillis() < endTime) {
                String pageSource = driver.getPageSource();
                if (pageSource.contains(findText)) {
                    foundMatch = true;
                    break;
                }
                Thread.sleep(500);
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }

        return foundMatch;
    }


    public static String getElementText(WebDriver driver, By locatorKey, int timeOut /* millisconds */) {
        try { // If not exist and reach timeout, want to return false (not contain) so test case can fail and continue forward (do not want error)
            waitUntilElementIsVisible(driver, locatorKey, timeOut);
        }
        catch (Exception ex) {
            return "";
        }
        return driver.findElement(locatorKey).getText();
    }

    public static void inputText(WebDriver driver, By locatorKey, int timeOut /* millisconds */, String inputText) {
        try {
            waitUntilElementIsVisible(driver, locatorKey, timeOut);  // If not exist and reach timeout, will error and skip steps below (by design)

            WebElement txtSearch = driver.findElement(locatorKey);
            txtSearch.click();
            clearTextField(txtSearch);
            txtSearch.sendKeys(inputText);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public static void inputText(WebElement element, String inputText) {
        try {
            element.click();
            clearTextField(element);
            element.sendKeys(inputText);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    private static void clearTextField(WebElement element) {
        try {
            element.clear();

            if (element.getText().length() > 0) {
                element.sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_UP, Keys.DELETE));

                if (element.getText().length() > 0) {
                    element.sendKeys(Keys.chord(Keys.SHIFT, Keys.ARROW_DOWN, Keys.DELETE));

                    if (element.getText().length() > 0) {
                        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

                        if (element.getText().length() > 0) {
                            element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("error clearing text field:\n" + ExceptionUtils.getStackTrace(e));
        }
    }


    public static Boolean isElementPresent(WebDriver driver, By locatorKey) {
        try {
            return isElementPresent(driver, locatorKey, 0);
        } catch (TimeoutException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    //TODO - fix deprecation
    @SuppressWarnings("deprecation")
    public static boolean isElementPresent(WebDriver driver, By locatorKey, int timeOut /* millisconds */) throws TimeoutException {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
        boolean exists = (driver.findElements(locatorKey).size() !=0);
        driver.manage().timeouts().implicitlyWait(DEFAULT_BROWSER_TIMEOUT, TimeUnit.MILLISECONDS);
        return exists;
    }


    public static Boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }


    public static Boolean isElementVisible(WebDriver driver, By locatorKey) {
        try {
            return isElementPresent(driver, locatorKey, 0);
        } catch (TimeoutException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }


    public static Boolean isElementVisible(WebDriver driver, By locatorKey, int timeOut) {
        try {
            // Element must be visible in order to click it
            waitUntilElementIsVisible(driver, locatorKey, timeOut);

            WebElement we = driver.findElement(locatorKey);
            return we.isDisplayed();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static Boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static boolean isElementEnabled(WebDriver driver, By locatorKey) throws TimeoutException {
        return isElementEnabled(driver, locatorKey, 0);
    }


    public static boolean isElementEnabled(WebDriver driver, By locatorKey, int timeOut) throws TimeoutException {
        // Element must be visible in order to click it
        waitUntilElementIsVisible(driver, locatorKey, timeOut);

        boolean we = driver.findElement(locatorKey).isEnabled();
        return we;
    }


    public static boolean isElementDisabled(WebDriver driver, By locatorKey) throws TimeoutException {
        return !isElementEnabled(driver, locatorKey, 0);
    }


    public static boolean isElementDisabled(WebDriver driver, By locatorKey, int timeOut) throws TimeoutException {
        return !isElementEnabled(driver, locatorKey, timeOut);
    }

    public static boolean isElementEmpty(WebDriver driver, By locatorKey) {
        try {
            Object value = getElementText(driver, locatorKey, 0);
            if (value == null || value.equals("")) {
                return true;
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }


    public static boolean isElementEmpty(WebDriver driver, By locatorKey, int timeOut) {
        try {
            Object value = getElementText(driver, locatorKey, timeOut);
            if (value == null || value.equals("")) {
                return true;
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }





    public static void waitUntilElementIsVisible(WebDriver driver, By locatorKey, int timeOut /* millisconds */) throws TimeoutException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeOut))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(locatorKey).isDisplayed();
            }
        });
    }


    public static void waitUntilElementTextChanges(WebDriver driver, By locatorKey, int timeOut /* millisconds */, String textEquals) throws TimeoutException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeOut))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(locatorKey).getText().contains(textEquals);
            }
        });
    }


    public static void submitForm(WebDriver driver, By locatorKey) {
        driver.findElement(locatorKey).submit();
    }

    public static void selectOption(WebDriver driver, By locatorKey, String option) {
        Select field = new Select(driver.findElement(locatorKey));
        field.selectByVisibleText(option);
    }


    public static String getLinkValue(WebElement element) {
        return element.getAttribute("href");
    }

    public static String getLocationValue(WebElement element) {
        return element.getLocation().toString();
    }

    public static Integer getHeightValue(WebElement element) {
        return element.getSize().getHeight();
    }

    public static Integer getWidthValue(WebElement element) {
        return element.getSize().getWidth();
    }

    public static Dimension getSizeValue(WebElement element) {
        return element.getSize();
    }
    /////////////////////////////////
    ///////////// FOR POM ///////////
    ////////////////////////////////

    public static boolean isElementEnabled(WebDriver driver, WebElement element, int timeOut) throws TimeoutException {
        // Element must be visible in order to click it
        waitUntilElementIsVisible(driver, element, timeOut);

        boolean we = element.isEnabled();
        return we;
    }

    public static void waitUntilElementIsVisible(WebDriver driver, WebElement element, int timeOut /* millisconds */) throws TimeoutException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(timeOut))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed();
            }
        });
    }

}