package web_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import page.Page;
import page.objects.Home;
import testing.WebTestCaseBase;


public class SampleWebTest implements WebTestCaseBase {

    static final Logger logger = LoggerFactory.getLogger(SampleWebTest.class);
    private static RemoteWebDriver driver = null;
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

    //just here for demo/reference
    @BeforeEach
    void beforeEach() {
        try {
            //nothing
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    //just here for demo/reference
    @AfterEach
    void afterEach() {
        try {
            //nothing
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    //just here for demo/reference
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

        logger.info("Starting sample test");

        //sample test steps
        driver.navigate().to(test.getUrl());
        assertTrue(Home.logo.isDisplayed());

    }

}

