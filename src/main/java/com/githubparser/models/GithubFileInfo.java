package com.githubparser.models;

import java.net.MalformedURLException;
import java.net.URL;
import com.githubparser.utils.Validators;

public class GithubFileInfo {
    private String fileUrl;
    private String filePath;
    private String fileName;
    private String fileExtension;
    private int lines;
    private float bytes;

    public GithubFileInfo() {
        this.setLines(0);
        this.setBytes(0);
    }

    public GithubFileInfo(String fileUrl, String filePath, String fileName, String fileExtension, int lines, float bytes) {
        this.setFileUrl(fileUrl);
        this.setFilePath(filePath);
        this.setFileName(fileName);
        this.SetFileExtension(fileExtension);
        this.setLines(lines);
        this.setBytes(bytes);
    }

    public GithubFileInfo(String fileUrl, String filePath, String fileName, String fileExtension) {
        this.setFileUrl(fileUrl);
        this.setFilePath(filePath);
        this.setFileName(fileName);
        this.SetFileExtension(fileExtension);
    }

    public URL getFileUrl() throws MalformedURLException {
        return Validators.nonEmptyString(this.fileUrl, null, false) ? new URL(this.fileUrl) : null;
    }

    public void setFileUrl(String fileUrl) {
        Validators.validURL(fileUrl, "invalid file URL", true);
        this.fileUrl = fileUrl;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        Validators.nonEmptyString(filePath, "invalid file path", true);
        this.filePath = filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        Validators.nonEmptyString(fileName, "invalid file name", true);
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void SetFileExtension(String fileExtension) {
        Validators.nonEmptyString(fileExtension, "invalid file extension", true);
        this.fileExtension = fileExtension;
    }

    public int getLines() {
        return this.lines;
    }

    public void setLines(int lines) {
        Validators.nonNegativeInt(lines, "invalid lines value", true);
        this.lines = lines;
    }

    public float getBytes() {
        return this.bytes;
    }

    public void setBytes(float bytes) {
        Validators.nonNegativeFloat(bytes, "invalid bytes value", true);
        this.bytes = bytes;
    }
}