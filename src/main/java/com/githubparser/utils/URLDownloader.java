package com.githubparser.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class URLDownloader {
    public static String getURLContent(URL url) throws IOException {
		Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.toString());

		try {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		} finally {
			scanner.close();
		}
	}

	public static Boolean URLExists(URL url) throws IOException
	{
    	HttpURLConnection.setFollowRedirects(false);
    	HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    	httpURLConnection.setRequestMethod("HEAD");
    	int responseCode = httpURLConnection.getResponseCode();
    	return (responseCode == HttpURLConnection.HTTP_OK);
	}
}