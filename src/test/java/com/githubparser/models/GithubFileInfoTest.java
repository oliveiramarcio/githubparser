package com.githubparser.models;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import com.githubparser.utils.Constants;

public class GithubFileInfoTest {
    @Test
    public void testFirstConstructor() throws MalformedURLException {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        assertEquals(null, githubFileInfo.getFileUrl());
        assertEquals(null, githubFileInfo.getFilePath());
        assertEquals(null, githubFileInfo.getFileName());
        assertEquals(null, githubFileInfo.getFileExtension());
        assertEquals(0, githubFileInfo.getLines());
        assertEquals(0.0f, githubFileInfo.getBytes(), Constants.TESTS_FLOAT_DELTA);
    }

    @Test
    public void testSecondConstructor() throws MalformedURLException {
        String fileUrl = "https://www.google.com/index.html";
        String filePath = "/home/my-folder";
        String fileName = "index.html";
        String fileExtension = "html";
        int lines = 100;
        float bytes = 500.0f;
        GithubFileInfo githubFileInfo = new GithubFileInfo(fileUrl, filePath, fileName, fileExtension, lines, bytes);
        assertEquals(new URL(fileUrl), githubFileInfo.getFileUrl());
        assertEquals(filePath, githubFileInfo.getFilePath());
        assertEquals(fileName, githubFileInfo.getFileName());
        assertEquals(fileExtension, githubFileInfo.getFileExtension());
        assertEquals(lines, githubFileInfo.getLines());
        assertEquals(bytes, githubFileInfo.getBytes(), Constants.TESTS_FLOAT_DELTA);
    }

    @Test
    public void testThirdConstructor() throws MalformedURLException {
        String fileUrl = "https://www.google.com/index.html";
        String filePath = "/home/my-folder";
        String fileName = "index.html";
        String fileExtension = "html";
        GithubFileInfo githubFileInfo = new GithubFileInfo(fileUrl, filePath, fileName, fileExtension);
        assertEquals(new URL(fileUrl), githubFileInfo.getFileUrl());
        assertEquals(filePath, githubFileInfo.getFilePath());
        assertEquals(fileName, githubFileInfo.getFileName());
        assertEquals(fileExtension, githubFileInfo.getFileExtension());
        assertEquals(0, githubFileInfo.getLines());
        assertEquals(0, githubFileInfo.getBytes(), Constants.TESTS_FLOAT_DELTA);
    }

    @Test
    public void testSetGetValidFileUrl() throws MalformedURLException {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        String fileUrl = "https://www.google.com/index.html";
        githubFileInfo.setFileUrl(fileUrl);
        assertEquals(new URL(fileUrl), githubFileInfo.getFileUrl());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullFileUrl() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setFileUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyFileUrl() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setFileUrl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidFileUrl() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        String fileUrl = "google";
        githubFileInfo.setFileUrl(fileUrl);
    }

    @Test
    public void testSetGetValidFilePath() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        String filePath = "/home/my-folder";
        githubFileInfo.setFilePath(filePath);
        assertEquals(filePath, githubFileInfo.getFilePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullValidFilePath() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setFilePath(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyValidFilePath() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setFilePath("");
    }

    @Test
    public void testSetGetValidFileName() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        String fileName = "myfile.txt";
        githubFileInfo.setFileName(fileName);
        assertEquals(fileName, githubFileInfo.getFileName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullValidFileName() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setFileName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyValidFileName() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setFileName("");
    }

    @Test
    public void testSetGetValidFileExtension() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        String fileExtension = "txt";
        githubFileInfo.SetFileExtension(fileExtension);
        assertEquals(fileExtension, githubFileInfo.getFileExtension());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNullValidFileExtension() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.SetFileExtension(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyValidFileExtension() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.SetFileExtension("");
    }

    @Test
    public void testSetGetValidLines() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        int lines = 50;
        githubFileInfo.setLines(lines);
        assertEquals(lines, githubFileInfo.getLines());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidLines() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setLines(-1);
    }

    @Test
    public void testSetGetValidBytes() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        float bytes = 200.0f;
        githubFileInfo.setBytes(bytes);
        assertEquals(bytes, githubFileInfo.getBytes(), Constants.TESTS_FLOAT_DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidBytes() {
        GithubFileInfo githubFileInfo = new GithubFileInfo();
        githubFileInfo.setBytes(-1);
    }
}