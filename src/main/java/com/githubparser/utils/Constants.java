package com.githubparser.utils;

public class Constants {
    public final static String APP_VERSION = "v1.0";
    public final static String APP_PACKAGE = "com.githubparser";
    public final static int DEFAULT_PORT = 4567;
    public final static String GITHUB_ROOT_URL = "https://github.com";
    public final static String GITHUB_RAW_CONTENT_URL = "https://raw.githubusercontent.com";
    public final static String GITHUB_BLOB = "blob/";
    public final static String REPOSITORY_QUERY_PARAM = "repositoryurl";
    public final static String INVALID_QUERY_PARAMETER_MSG = "Invalid or missing Github repository URL query param (\"" + REPOSITORY_QUERY_PARAM + "\").";
    public final static String GITHUB_FILES_SCRAP_REGEX = "(?s)(?i)<td class=\"content\">(.*?)<\\/td>";
    public final static String GITHUB_FILE_STATS_SCRAP_REGEX = "(?s)(?i)<div class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">(.*?)<\\/div>";
    public final static String GITHUB_FILE_SCRAP_START = "href=\"";
    public final static String GITHUB_FILE_SCRAP_END = "\">";
    public final static String GITHUB_LINES_SCRAP_START = "mt-md-0\">";
    public final static String GITHUB_LINES_SCRAP_END = " lines";
    public final static String SPAN_ENDING_TAG = "file-info-divider\"></span>";
    public final static String DIV_ENDING_TAG = " </div>";
    public final static String KB = "KB";
    public final static String MB = "MB";
    public final static String GB = "GB";
    public final static float TESTS_FLOAT_DELTA = 0.0f;
    public final static String LOCALHOST_ROOT_URL = "http://localhost:" + HerokuUtils.getHerokuAssignedPort();
    public final static String LOCALHOST_SWAGGER_JSON_URL = LOCALHOST_ROOT_URL + "/v1.0/swagger.json";
    public final static String LOCALHOST_INTEGRATION_TEST_PARSER_URL = LOCALHOST_ROOT_URL + "/github/parse?repositoryurl=https://github.com/oliveiramarcio/utc-wingpanel";
    public final static String INTEGRATION_TEST_EXPECTED_JSON = "{\"vapi\":[{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/vapi/libgtop-2.0.vapi\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/vapi/\",\"fileName\":\"libgtop-2.0.vapi\",\"fileExtension\":\"vapi\",\"lines\":1319,\"bytes\":67200}],\"build\":[{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/meson.build\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/\",\"fileName\":\"meson.build\",\"fileExtension\":\"build\",\"lines\":32,\"bytes\":937}],\"gitignore\":[{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/.gitignore\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/\",\"fileName\":\".gitignore\",\"fileExtension\":\"gitignore\",\"lines\":2,\"bytes\":8}],\"md\":[{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/README.md\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/\",\"fileName\":\"README.md\",\"fileExtension\":\"md\",\"lines\":38,\"bytes\":590}],\"vala\":[{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/DisplayWidget.vala\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/\",\"fileName\":\"DisplayWidget.vala\",\"fileExtension\":\"vala\",\"lines\":19,\"bytes\":587},{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/PopoverWidget.vala\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/\",\"fileName\":\"PopoverWidget.vala\",\"fileExtension\":\"vala\",\"lines\":21,\"bytes\":631},{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/main.vala\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/\",\"fileName\":\"main.vala\",\"fileExtension\":\"vala\",\"lines\":63,\"bytes\":1590}],\"png\":[{\"fileUrl\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/data/screenshot_0.png\",\"filePath\":\"https://github.com/oliveiramarcio/utc-wingpanel/blob/master/data/\",\"fileName\":\"screenshot_0.png\",\"fileExtension\":\"png\",\"lines\":0,\"bytes\":67100}]}";
}