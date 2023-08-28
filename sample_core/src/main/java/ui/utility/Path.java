package ui.utility;

import utility.Config;

public enum Path {

    /*
    path constants
     */

    CONFIG_FOLDER_GLOBAL("src/main/resources/"),
    CONFIG_FOLDER_TEST("src/test/resources/config/"),
    CONFIG_FILENAME_GLOBAL("config.properties"),
    CONFIG_FILENAME_API("api_config.properties"),
    ROOT("./"),
    LOG_FILE_PATH(Config.getGlobalProperty("main.logfile.path")),
    TEST_RESOURCES_FOLDER(Config.getGlobalProperty("test.resources.path")),
    TEST_OUTPUT_FOLDER(Config.getGlobalProperty("test.outputfile.path")),
    LOGFILE_PATH(Config.getGlobalProperty("main.logfile.path")),
    LOGFILE_NAME(Config.getGlobalProperty("main.logfile.name")),
    ZIPCODE_FILE(Config.getGlobalProperty("zipcode.file")),
    DOWNLOADS_FOLDER(System.getProperty("user.home")+"/Downloads/"),
    TEST_RESOURCES_IMAGE_FOLDER(Config.getGlobalProperty("local.download.path"));

    String value;

    Path(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }

}
