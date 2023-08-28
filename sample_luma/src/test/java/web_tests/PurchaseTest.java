package web_tests;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.Page;
import page.objects.Home;
import page.objects.ItemPage;
import page.objects.NewItemPage;
import page.objects.ShippingPage;
import testing.TestCaseBase;
import ui.utility.UI;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
public class PurchaseTest implements TestCaseBase {

    static final Logger logger = LoggerFactory.getLogger(web_tests.SampleWebTest.class);

    private static RemoteWebDriver driver = null;
    private static final String lumaUrl = Page.LUMA_MAIN.getValue();


    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));


    /*
     * ====================================================================
     *
     * Before/After Handlers
     *
     * ====================================================================
     */

    @BeforeAll
    static void beforeAll(TestInfo testInfo) {
        try {
            if (test.getDriver() == null) fail("failed to get web driver");
            driver = test.getDriver();
            test.setUrl(lumaUrl);
            Home.initializePageObjects(driver);
            NewItemPage.initializePageObjects(driver);
            ItemPage.initializePageObjects(driver);
            ShippingPage.initializePageObjects(driver);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @AfterAll
    static void afterAll(TestInfo testInfo) {
        try {
            driver.close();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    /*
     * ====================================================================
     *
     * Test Methods
     *
     * ====================================================================
     */

    @Test
    void purchaseTest() {

        //Simple test to confirm user is able to complete a purchase

        logger.info("Starting purchase test");

        //Navigate to What's New page
        try {
            driver.navigate().to(test.getUrl());
            wait.until(ExpectedConditions.visibilityOf(Home.whatsNewLink));
            UI.click(Home.whatsNewLink);
            assertEquals(NewItemPage.pageTitle, driver.getTitle(), "Page title does not match");
        } catch (Exception e) {
            logger.error("Unable to get page title\n" + ExceptionUtils.getStackTrace(e));
        }

        //Select an item
        wait.until(ExpectedConditions.visibilityOf(NewItemPage.productsGrid));
        NewItemPage.scrollToProductsGrid();
        NewItemPage.selectItem();

        //Select item details
        wait.until(ExpectedConditions.visibilityOf(ItemPage.logo));
        ItemPage.selectSize();
        ItemPage.selectColor();
        UI.click(ItemPage.addToCartButton);
        wait.until(ExpectedConditions.visibilityOf(ItemPage.itemAddedMessage));
        assertTrue(ItemPage.itemAddedMessage.isDisplayed());

        //Navigate to checkout
        UI.click(ItemPage.cartButton);
        wait.until(ExpectedConditions.visibilityOf(ItemPage.cartDropdown));
        wait.until(ExpectedConditions.visibilityOf(ItemPage.checkoutButton));
        UI.click(ItemPage.checkoutButton);

        //Enter shipping information



        logger.info("Finished basic sample test");

    }

}
