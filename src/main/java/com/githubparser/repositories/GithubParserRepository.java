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

    private List<GithubFileInfo> ScrapGitHubUrl(String url) throws Exception {
		Validators.validGithubURL(url, url + " is an invalid Github repository URL", true);
		List<GithubFileInfo> fileInfoList = new ArrayList<GithubFileInfo>();
		Matcher matcher = this.generateMatcherFromUrlContent(Constants.GITHUB_FILES_SCRAP_REGEX, new URL(url));

		while (matcher.find()) {
			String fileUrl = Constants.GITHUB_ROOT_URL + StringUtils.substringBetween(matcher.group(0), Constants.GITHUB_FILE_SCRAP_START, Constants.GITHUB_FILE_SCRAP_END);
			String fileExtension = FilenameUtils.getExtension(fileUrl);

			if (Validators.nonEmptyString(fileExtension, null, false)) {
				GithubFileInfo githubFileInfo = new GithubFileInfo(fileUrl, FilenameUtils.getFullPath(fileUrl), FilenameUtils.getName(fileUrl), fileExtension);
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
			String[] size;

			try {
				String lines = StringUtils.substringBetween(matcher.group(0), Constants.GITHUB_LINES_SCRAP_START, Constants.GITHUB_LINES_SCRAP_END).trim();
				githubFileInfo.setLines(Integer.parseInt(lines));
				size = StringUtils.substringBetween(matcher.group(0), Constants.SPAN_ENDING_TAG, Constants.DIV_ENDING_TAG).trim().split(" ");
			} catch (Exception e) {
				size = StringUtils.substringBetween(matcher.group(0), Constants.GITHUB_LINES_SCRAP_START, Constants.DIV_ENDING_TAG).trim().split(" ");
			}

			githubFileInfo.setBytes(this.convertGithubSizeToBytes(size));
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