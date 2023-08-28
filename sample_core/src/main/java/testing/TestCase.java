package testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import utility.Config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestCase {

    static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    private String testDescription = "Default Test Description";
    private static String environment = "dev";
    private String url;
    private String browser;
    private RemoteWebDriver driver;
    private String testConfigFile;
    private String testResourceFolder;
    private String testDataFileName;
    private String folder;
    private String testDataFile = folder + testDataFileName;
    private int timerLimit = 0;
    private int timerSleep = 0;
    private boolean useQuery = false;
    private boolean useQueryDateOverride = false;
    private String fromDateDays;
    private String toDateDays;
    private String fromDate;
    private String toDate;
    private boolean logFailures = true;
    private int sourceLimitThreshold;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private Long testElapsedTime;


    public TestCase() {}

    private void setDefaultTestConfig() {
        try {
            logger.info("unable to get config from database ... using default test configuration");

            this.setUseQuery(true);
            this.setUseQueryDateOverride(false);
            this.setFromDateDays("1");
            this.setToDateDays("1");
            this.setFromDate("2021-07-01");
            this.setToDate("2021-07-01");
            this.setTimerSleep(30000);
            this.setTimerLimit(480);
            this.setSourceLimitThreshold(1);
            this.setLogFailures(false);
            this.setBrowser();
        } catch (Exception e) {
            logger.error("error setting default test config\n" + e.toString());
        }
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescriptionn) {
        this.testDescription = testDescriptionn;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowser() {
        return browser;
    }
    public void setBrowser() {
        this.browser = (String) getProperty("browser");
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getTestConfigFile() {
        return testConfigFile;
    }

    public void setTestConfigFile(String testConfigFile) {
        this.testConfigFile = testConfigFile;
    }

    public String getTestDataFileName() {
        return testDataFileName;
    }

    public void setTestDataFileName(String fileName) {
        this.testDataFileName = fileName;
    }

    public void setTestDataFileName() {
        try {
            this.setTestDataFileName(Config.getTestProperty("test.data.file.name", "file_name", testConfigFile));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getTestDataFile() {
        return testDataFile;
    }

    public void setTestDataFile(String testFile) {
        this.testDataFile = testFile;
    }

    public int getTimerLimit() {
        return timerLimit;
    }

    public void setTimerLimit(int timerLimit) {
        this.timerLimit = timerLimit;
    }

    public void setTimerLimit() {
        try {
            this.setTimerLimit(Integer.valueOf(Config.getTestProperty("test.loop.timer.limit", "100", testConfigFile)));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public int getTimerSleep() {
        return timerSleep;
    }

    public void setTimerSleep(int timerSleep) {
        this.timerSleep = timerSleep;
    }

    public void setTimerSleep() {
        try {
            this.setTimerSleep(Integer.valueOf(Config.getTestProperty("test.loop.sleep.timer", "30000", testConfigFile)));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public boolean isUseQuery() {
        return useQuery;
    }

    public void setUseQuery(boolean useQuery) {
        this.useQuery = useQuery;
    }

    public void setUseQuery() {
        try {
            if (Config.getTestProperty("test.use.query.ids", testConfigFile).toLowerCase().contentEquals("true")) {
                this.setUseQuery(true);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public boolean isUseQueryDateOverride() {
        return useQueryDateOverride;
    }

    public void setUseQueryDateOverride(boolean useQueryDateOverride) {
        this.useQueryDateOverride = useQueryDateOverride;
    }

    public void setUseQueryDateOverride() {
        try {
            if (Config.getTestProperty("test.use.query.date.override", testConfigFile).toLowerCase().contentEquals("true")) {
                this.setUseQueryDateOverride(true);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public String getFromDateDays() {
        return fromDateDays;
    }

    public void setFromDateDays(String fromDateDays) {
        this.fromDateDays = fromDateDays;
    }

    public void setFromDateDays() {
        try {
            this.setFromDateDays(Config.getTestProperty("test.query.numberOfDays.from", "1", testConfigFile));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public String getToDateDays() {
        return toDateDays;
    }

    public void setToDateDays(String toDateDays) {
        this.toDateDays = toDateDays;
    }

    public void setToDateDays() {
        try {
            this.setToDateDays(Config.getTestProperty("test.query.numberOfDays.to", "1", testConfigFile));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate() {
        try {
            this.setFromDate(Config.getTestProperty("test.query.date.from.override", testConfigFile));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate() {
        try {
            this.setToDate(Config.getTestProperty("test.query.date.to.override", testConfigFile));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTestResourceFolder() {
        return testResourceFolder;
    }

    public void setTestResourceFolder(String testResourceFolder) {
        this.testResourceFolder = testResourceFolder;
    }

    public void setTestResourceFolder() {
        try {
            this.setTestResourceFolder(ui.utility.Path.TEST_RESOURCES_FOLDER.getValue());
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }


    public int getSourceLimitThreshold() {
        return sourceLimitThreshold;
    }

    public void setSourceLimitThreshold(int limit) {
        this.sourceLimitThreshold = limit;
    }

    public void setSourceLimitThreshold() {
        try {
            logger.error("setting sourceLimitThreshold from config file");
            this.setSourceLimitThreshold(Integer.valueOf(Config.getTestProperty("test.source.limit.threshold", "1" , testConfigFile)));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    public boolean isLogFailures() {
        return logFailures;
    }

    public void setLogFailures(boolean logFailures) {
        this.logFailures = logFailures;
    }

    public void setLogFailures() {
        try {
            if (Config.getTestProperty("test.log.failures", testConfigFile).toLowerCase().contentEquals("false")) {
                this.setLogFailures(false);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setStartTime() {
        this.startTime = LocalDateTime.now();
    }


    public LocalDateTime getStopTime() {
        return stopTime;
    }


    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    public void setStopTime() {
        this.stopTime = LocalDateTime.now();
    }


    public Long getTestElapsedTime() {
        if (this.testElapsedTime == null
                && this.startTime != null
                && this.stopTime != null)
        {
            this.testElapsedTime = this.startTime.until(this.stopTime, ChronoUnit.SECONDS);
            return testElapsedTime;
        } else {
            return testElapsedTime;
        }
    }


    public void setTestElapsedTime(Long testElapsedTime) {
        this.testElapsedTime = testElapsedTime;
    }


    private static String getProperty(String propertyName) {
        String prop;
        /* getting param defaults from local config file */
        prop = Config.getGlobalProperty(propertyName);

        return prop;
    }

    private static boolean getBooleanProperty(String propertyName) {
        String property = getProperty(propertyName);
        if (property.trim().equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, String> parseTag(String tag) {
        Map<String, String> map = new HashMap<>();
        String delims = "[.]";
        String[] tokens = tag.split(delims);
        if (tokens.length > 1) {
            map.put("parameter", tokens[0]);
            map.put("value", tokens[1]);
        }
        return map;
    }

    public static boolean compareDataValueToUiValue(Object label, Object dataValue, Object uiValue) {
        //handling extra and non-keyboard characters
        if (uiValue != null) {
            String str = uiValue.toString().trim();
            uiValue = str;

            //converting a long dash to null to match UI representation to data equivalent
            char[] array= str.toCharArray();
            if (array.length == 1 && array[0] == 8212) {
                uiValue = null;
            }

            //converting empty string to null to match data equivalent
            if (array.length == 0) {
                uiValue = null;
            }
        }

        //handling data extra characters
        if (dataValue != null) {
            String str = dataValue.toString().trim();
            dataValue = str;
        }

        logger.info("comparing {} | {} : {}", label, dataValue, uiValue);
        if (dataValue != null && uiValue != null && dataValue.equals(uiValue)) {
            return true;
        } else if (dataValue == uiValue) {
            return true;
        } else {
            logger.error("Values do not match: data value = {} ; UI value = {}", dataValue, uiValue);
        }

        return false;
    }

    public boolean assertTrueLoop(boolean condition) {
        try {
            int counter = 0;
            int whileCounterLimit = Integer.valueOf(Config.Property.WHILE_COUNTER_LIMIT.getValue());
            while (counter < whileCounterLimit) {
                if (!condition) {
                    logger.info("condition is false (not expected), looping ... {}", counter);
                    counter++;
                } else {
                    logger.info("condition is true (expected)...returning");
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return false;
    }

    public boolean assertFalseLoop(boolean condition) {
        try {
            int counter = 0;
            int whileCounterLimit = Integer.valueOf(Config.Property.WHILE_COUNTER_LIMIT.getValue());
            while (counter < whileCounterLimit) {
                if (condition) {
                    logger.info("condition is false (not expected), looping ... {}", counter);
                    counter++;
                } else {
                    logger.info("condition is true (expected)...returning");
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return true;
    }

    public String parseTestSuiteName(String testClassName) {
        return testClassName.replaceAll("_", " ");
    }

}