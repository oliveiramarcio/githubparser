package com.githubparser.utils;

import org.apache.commons.validator.routines.UrlValidator;

public class Validators {
    private static Boolean runValidator(Boolean condition, String message, Boolean throwException) {
        if (!condition) {
            if (throwException) {
                throw new IllegalArgumentException(message);
            }
            
            return false;
        }

        return true;
    }

    public static Boolean validURL(String url, String message, Boolean throwException) {
        return runValidator((url != null && !url.trim().equals("") && new UrlValidator().isValid(url)), message, throwException);
    }

    public static Boolean validGithubURL(String url, String message, Boolean throwException) {
        return runValidator((validURL(url, message, false) && url.startsWith(Constants.GITHUB_ROOT_URL)), message, throwException);
    }

    public static Boolean nonEmptyString(String value, String message, Boolean throwException) {
        return runValidator((value != null && !value.trim().equals("")), message, throwException);
    }

    public static Boolean nonNegativeInt(int number, String message, Boolean throwException) {
        return runValidator((number >= 0), message, throwException);
    }

    public static Boolean nonNegativeFloat(float number, String message, Boolean throwException) {
        return runValidator((number >= 0), message, throwException);
    }
}