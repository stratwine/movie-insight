package com.github.minsight.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.common.annotations.VisibleForTesting;

public class FileNameCleaner {

	public ArrayList<String> withoutDirPath(
			ArrayList<String> pathPrefixedFileNames) {

		ArrayList<String> simpleFileNames = new ArrayList<String>();
		for (String pathPrefixedFileName : pathPrefixedFileNames) {
			String simpleFileName = pathRemoved(pathPrefixedFileName);
			simpleFileNames.add(simpleFileName);
		}
		return simpleFileNames;
	}

	public List<String> withoutMetaInfo(List<String> fileNames) {
		List<String> metaInfoTrimmedFileNames = new ArrayList<String>();
		for (String fileName : fileNames) {
			metaInfoTrimmedFileNames.add(metaInfoRemoved(fileName));
		}
		return metaInfoTrimmedFileNames;
	}

	public List<String> withoutNonWords(List<String> fileNames) {
		List<String> nonWordsReplaced = new ArrayList<String>();
		for (String fileName : fileNames) {
			nonWordsReplaced.add(this.nonWordsReplacedWithSpace(fileName));
		}
		return nonWordsReplaced;
	}

	@VisibleForTesting
	@SuppressWarnings("static-access")
	public String metaInfoRemoved(String fileName) {

		fileName = fileName.toLowerCase();
		String replaced = fileName.replaceAll("xvid*", "");
		replaced = replaced.replaceAll("\\[.*", "");
		replaced = replaced.replaceAll("dvd.*", "");
		replaced = replaced.replaceAll(".avi", "");
		return replaced;
	}

	@VisibleForTesting
	public String pathRemoved(String pathPrefixedFileName) {

		int lastIndexOfFileSeperator = pathPrefixedFileName
				.lastIndexOf(File.separator); // index is 0 based

		if (pathPrefixedFileName.endsWith(File.separator)) {
			return "";
		} else if (lastIndexOfFileSeperator < 1) {
			return pathPrefixedFileName;
		} else {
			String pathRemovedFileName = "";
			pathRemovedFileName = pathPrefixedFileName
					.substring(lastIndexOfFileSeperator + 1);

			return pathRemovedFileName;

		}

	}

	@VisibleForTesting
	public String nonWordsReplacedWithSpace(String fileName) {
		String nonWordCharsReplaced = fileName.replaceAll("\\W", " ");
		return nonWordCharsReplaced;
	}

}
