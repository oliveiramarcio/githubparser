package com.githubparser.utils;

public class HerokuUtils {
    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return Constants.DEFAULT_PORT;
    }
}