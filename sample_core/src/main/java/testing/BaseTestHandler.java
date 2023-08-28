package testing;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.TestInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTestHandler {

    static final Logger logger = LoggerFactory.getLogger(BaseTestHandler.class);

    public static void beforeAllHandler(TestInfo testInfo, TestCase test) {
        try {

            logger.info("start: {}", test.getTestDescription());

            test.setStartTime();
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public static void beforeEachHandler(TestInfo testInfo, TestCase test) {

        logger.info("running test: {}", testInfo.getDisplayName());

        Set<String> tags = testInfo.getTags();
        tags.forEach(tag -> {
            Map<String, String> mappedTag = test.parseTag(tag);
            if (mappedTag.size()>0) {
                logger.info("parameter tag is: {}|{}", mappedTag.get("parameter"), mappedTag.get("value"));
            } else {
                logger.info("tag is: {}", tag);
            }
        });
    }

    public static void afterEachHandler(TestInfo testInfo, TestCase test) {
        logger.info("finished test: {}", testInfo.getDisplayName());
    }

    public static void afterAllHandler(TestInfo testInfo, TestCase test) {
        test.setStopTime();
        logger.info("total test elapsed time: " + test.getTestElapsedTime() + " seconds");
        logger.info("end: {}", test.getTestDescription());
    }

}
