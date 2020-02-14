package com.githubparser.utils;

import java.io.IOException;
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
}