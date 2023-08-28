package web_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page.Page;
import page.objects.Home;
import testing.TestCaseBase;


public class SampleWebTest implements TestCaseBase {

    static final Logger logger = LoggerFactory.getLogger(SampleWebTest.class);
    private static WebDriver driver = null;
    private static final String lumaUrl = Page.LUMA_MAIN.getValue();


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
    void sampleWebTest1() {

        logger.info("Starting basic sample test");

        //sample test steps
        driver.navigate().to(test.getUrl());
        assertTrue(Home.logo.isDisplayed());

        logger.info("Finished basic sample test");

    }

}