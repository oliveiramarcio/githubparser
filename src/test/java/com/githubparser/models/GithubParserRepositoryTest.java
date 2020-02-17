package com.githubparser.models;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.githubparser.utils.Constants;
import com.githubparser.utils.GithubParserSparkerStarter;
import com.githubparser.utils.URLDownloader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GithubParserRepositoryTest {
    @BeforeClass
    public static void ensureAppIsRunning() {
        GithubParserSparkerStarter.get(Constants.LOCALHOST_ROOT_URL, "/").startSparkAppIfNotRunning(4567);
    }

    @Test
    public void testURLs() throws MalformedURLException, IOException {
        assertTrue(URLDownloader.URLExists(new URL(Constants.LOCALHOST_ROOT_URL)));
        assertTrue(URLDownloader.URLExists(new URL(Constants.LOCALHOST_SWAGGER_JSON_URL)));
        assertTrue(URLDownloader.URLExists(new URL(Constants.LOCALHOST_INTEGRATION_TEST_PARSER_URL)));
    }

    @Test
    public void testFirstConstructor() throws IOException {
        JsonObject expectedJsonObject = JsonParser.parseString(Constants.INTEGRATION_TEST_EXPECTED_JSON).getAsJsonObject();
        assertTrue(expectedJsonObject.isJsonObject());

        String returnedJson = URLDownloader.getURLContent(new URL(Constants.LOCALHOST_INTEGRATION_TEST_PARSER_URL));        
        JsonObject returnedJsonObject = JsonParser.parseString(returnedJson).getAsJsonObject();
        assertTrue(returnedJsonObject.isJsonObject());
    
        assertEquals(expectedJsonObject, returnedJsonObject);
    }
}