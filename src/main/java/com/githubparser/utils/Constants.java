package com.githubparser.utils;

public class Constants {
    public final static String APP_VERSION = "v1.0";
    public final static String APP_PACKAGE = "com.githubparser";
    public final static int DEFAULT_PORT = 4567;
    public final static String GITHUB_ROOT_URL = "https://github.com";
    public final static String REPOSITORY_QUERY_PARAM = "repositoryurl";
    public final static String INVALID_QUERY_PARAMETER_MSG = "Invalid or missing Github repository URL query param (\"" + REPOSITORY_QUERY_PARAM + "\").";
    public final static String GITHUB_FILES_SCRAP_REGEX = "(?s)(?i)<td class=\"content\">(.*?)<\\/td>";
    public final static String GITHUB_FILE_STATS_SCRAP_REGEX = "(?s)(?i)<div class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">(.*?)<\\/div>";
    public final static String GITHUB_FILE_SCRAP_START = "href=\"";
    public final static String GITHUB_FILE_SCRAP_END = "\">";
    public final static String GITHUB_LINES_SCRAP_START = "mt-md-0\">";
    public final static String GITHUB_LINES_SCRAP_END = " lines";
    public final static String SPAN_ENDING_TAG = "</span>";
    public final static String DIV_ENDING_TAG = " </div>";
    public final static String KB = "KB";
    public final static String MB = "MB";
    public final static String GB = "GB";
    public final static float TESTS_FLOAT_DELTA = 0.0f;
}