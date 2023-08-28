package testing;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import ui.selenium.Driver;

public interface WebTestCaseBase extends TestCaseBase {

    static final Logger logger = LoggerFactory.getLogger(WebTestCaseBase.class);

    public static int timeout = 3000;

    @BeforeAll
    static void beforeAllWebTests(TestInfo testInfo) throws Exception {
        try {
            setDriver();
        } catch (Exception e) {
            logger.error("error setting driver\n" + ExceptionUtils.getStackTrace(e));
        }
    }

    @BeforeEach
    default void beforeEachWebTest(TestInfo testInfo) {

    }

    @AfterEach
    default void afterEachWebTest(TestInfo testInfo) {

    }

    @AfterAll
    static void afterAllWebTests(TestInfo testInfo) {
        try {
            if (test.getDriver() != null) {
                Driver.quitBrowser(test.getDriver());
            }
        } catch (Exception e) {
            // swallow error
        }
    }

    /*
     * ===============================
     *
     * Helper Methods
     *
     * ===============================
     */


    static void setDriver() {
        logger.info("getting local webdriver");
        RemoteWebDriver d = Driver.browserDriver(test.getBrowser());
        test.setDriver(d);
        test.getDriver().manage().window().maximize();
        assertMinimumBrowserWindowSize();
    }

    private static void assertMinimumBrowserWindowSize() {
        /* checking the widow size is large enough to put elements in default location */
        Dimension windowSize = test.getDriver().manage().window().getSize();
        Assertions.assertTrue(windowSize.getHeight() >= 700, "Browser window height does not meet minimum requirement of 700, found " + windowSize.getHeight());
        Assertions.assertTrue(windowSize.getWidth() >= 1200, "Browser window width does not meet minimum requirement of 1200, found " + windowSize.getWidth());
    }



}