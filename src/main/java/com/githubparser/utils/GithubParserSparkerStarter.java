package com.githubparser.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import com.githubparser.App;

public class GithubParserSparkerStarter extends SparkStarter {
    private static GithubParserSparkerStarter starter;
    private final String host;
    private final String heartBeatPath;

    private GithubParserSparkerStarter(String host, String heartBeatPath) {
        this.host = host;
        this.heartBeatPath = heartBeatPath;
    }

    public static GithubParserSparkerStarter get(String host, String heartBeatPath) {
        if (GithubParserSparkerStarter.starter == null) {
            GithubParserSparkerStarter.starter = new GithubParserSparkerStarter(host, heartBeatPath);
        }

        return GithubParserSparkerStarter.starter;
    }

    public boolean isRunning() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("http", host, sparkport, heartBeatPath).openConnection();
            return (httpURLConnection.getResponseCode() == 200);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void startServer() {
        String[] args = {};
        App.main(args);
    }
}