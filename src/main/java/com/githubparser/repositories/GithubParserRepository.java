package com.githubparser.repositories;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.githubparser.models.GithubFileInfo;
import com.githubparser.utils.Constants;
import com.githubparser.utils.URLDownloader;
import com.githubparser.utils.Validators;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class GithubParserRepository {
    public GithubParserRepository() { }

    public Map<String, List<GithubFileInfo>> GetGithubFilesGroupedByExtension(String githubRepositoryUrl)
            throws Exception {
        return Optional
            .ofNullable(this.ScrapGitHubUrl(githubRepositoryUrl))
            .orElseGet(ArrayList::new).stream()
            .collect(Collectors.groupingBy(GithubFileInfo::getFileExtension));
    }

	public String getFilenameFromUrl(String url)
	{
		String regex = "[ \\w-]+\\.[\\w-]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);

		StringBuilder result = new StringBuilder();
		while (matcher.find()) { 
    		result.append(matcher.group(0));
		}

		return result.toString();
	}

    private List<GithubFileInfo> ScrapGitHubUrl(String url) throws Exception {
		Validators.validGithubURL(url, url + " is an invalid Github repository URL", true);
		List<GithubFileInfo> fileInfoList = new ArrayList<GithubFileInfo>();
		Matcher matcher = this.generateMatcherFromUrlContent(Constants.GITHUB_FILES_SCRAP_REGEX, new URL(url));

		while (matcher.find()) {
			String fileUrl = Constants.GITHUB_ROOT_URL + StringUtils.substringBetween(matcher.group(0), Constants.GITHUB_FILE_SCRAP_START, Constants.GITHUB_FILE_SCRAP_END);
			URL rawURL = new URL(fileUrl.replace(Constants.GITHUB_ROOT_URL, Constants.GITHUB_RAW_CONTENT_URL).replace(Constants.GITHUB_BLOB, ""));

			if (URLDownloader.URLExists(rawURL)) {
				String fileExtension = FilenameUtils.getExtension(fileUrl);
				String fileName = FilenameUtils.getName(fileUrl);

				if (!Validators.nonEmptyString(fileExtension, "", false)) {
					fileExtension = fileName;
				}

				if (!Validators.nonEmptyString(fileName, "", false)) {
					fileName = fileExtension;
				}

				GithubFileInfo githubFileInfo = new GithubFileInfo(fileUrl, FilenameUtils.getFullPath(fileUrl), fileName, fileExtension);
				this.parseGithubFileStats(githubFileInfo);
				fileInfoList.add(githubFileInfo);
			} else {
				fileInfoList.addAll(this.ScrapGitHubUrl(fileUrl));
			}
        }

		return fileInfoList;
	}

	private void parseGithubFileStats(GithubFileInfo githubFileInfo) throws Exception {
		Matcher matcher = this.generateMatcherFromUrlContent(
			Constants.GITHUB_FILE_STATS_SCRAP_REGEX,
			githubFileInfo.getFileUrl());

		while (matcher.find()) {
			int lines = 0;
			String[] sizeInfo = null;
			float size = 0.0f;

			try {
				lines = Integer.parseInt(StringUtils.substringBetween(matcher.group(0), Constants.GITHUB_LINES_SCRAP_START, Constants.GITHUB_LINES_SCRAP_END).trim());
			} catch (Exception e) {
				lines = 0;
			}

			try {
				sizeInfo = StringUtils.substringBetween(matcher.group(0), Constants.SPAN_ENDING_TAG, Constants.DIV_ENDING_TAG).trim().split(" ");
			} catch (Exception ex) {
				sizeInfo = StringUtils.substringBetween(matcher.group(0), Constants.GITHUB_LINES_SCRAP_START, Constants.DIV_ENDING_TAG).trim().split(" ");
			}

			size = (sizeInfo != null && sizeInfo.length > 0) ? this.convertGithubSizeToBytes(sizeInfo) : 0;

			githubFileInfo.setLines(lines);
			githubFileInfo.setBytes(size);
		}
	}

	private Matcher generateMatcherFromUrlContent(String regex, URL url) throws IOException {
		String urlContents = URLDownloader.getURLContent(url);
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL | Pattern.UNIX_LINES);
		return pattern.matcher(urlContents);
	}

	private float convertGithubSizeToBytes(String[] githubSize) {
		float size = Float.parseFloat(githubSize[0]);

		switch (githubSize[1].toUpperCase()) {
			case Constants.KB:
				return (size * 1000);
			case Constants.MB:
				return (size * 1000000);
			case Constants.GB:
				return (size * 1000000000);
			default: // Already in bytes
				return size;
		}
	}
}