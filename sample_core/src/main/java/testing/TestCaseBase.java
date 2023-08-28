package testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface TestCaseBase {

    Logger logger = LoggerFactory.getLogger(TestCaseBase.class);

    TestCase test = new TestCase();

    @BeforeAll
    static void beforeAllTests(TestInfo testInfo) throws Exception {
        BaseTestHandler.beforeAllHandler(testInfo, test);
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        BaseTestHandler.beforeEachHandler(testInfo, test);
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        BaseTestHandler.afterEachHandler(testInfo, test);
    }

    @AfterAll
    static void afterAllTests(TestInfo testInfo) {
        BaseTestHandler.afterAllHandler(testInfo, test);
    }

}