package utility;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    static final Logger logger = LoggerFactory.getLogger(Config.class);

    /*
    API and global properties enum
     */
    public enum Property {

        //browser
        BROWSER(getGlobalProperty("browser")),

        //while counter limit
        WHILE_COUNTER_LIMIT(getGlobalProperty("whileCounterLimit"));

        String value;

        Property(String val) {
            value = val;
        }

        public String getValue() {
            return value;
        }
    }


    public static String getProperty(String propertyName, String defaultValue, String configFileFolder, String configFileName) {

        Properties prop = new Properties();
        InputStream input = null;
        String propValue = "";

        String fileName = configFileFolder + configFileName;

        try {
            // If file does not exist, then return default value and exit now
            File file = new File(fileName);
            if (! file.exists()) {
                return defaultValue;
            }

            // Otherwise, read in the property value
            input = new FileInputStream(fileName);
            prop.load(input);
            propValue = prop.getProperty(propertyName, defaultValue);

        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }

        return propValue;
    }

    /*
     * Global property handlers
     */


    public static String getGlobalProperty(String propertyName) {
        try {
            return getProperty(propertyName, "", ui.utility.Path.CONFIG_FOLDER_GLOBAL.getValue(), ui.utility.Path.CONFIG_FILENAME_GLOBAL.getValue());
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return "";
    }


    /*
     * Test property handlers
     */
    public static String getTestProperty(String propertyName, String configFileName) {
        try {
            return getProperty(propertyName, "", ui.utility.Path.CONFIG_FOLDER_TEST.getValue(), configFileName);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return "";
    }

    public static String getTestProperty(String propertyName, String defaultValue, String configFileName) {
        try {
            return getProperty(propertyName, defaultValue, ui.utility.Path.CONFIG_FOLDER_TEST.getValue(), configFileName);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return defaultValue;
    }


}
