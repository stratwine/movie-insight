package com.github.minsight.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Splitter;

public class FileNameTrimmer {

	public ArrayList<String> trimDirPath(ArrayList<String> pathPrefixedFileNames) {

		ArrayList<String> simpleFileNames = new ArrayList<String>();
		for (String pathPrefixedFileName : pathPrefixedFileNames) {
			String simpleFileName = asSimpleName(pathPrefixedFileName);
			simpleFileNames.add(simpleFileName);
		}
		return simpleFileNames;
	}

	public List<String> trimMetaInfo(List<String> fileNames) {
		List<String> metaInfoTrimmedFileNames = new ArrayList<String>();
		for (String fileName : fileNames) {
			metaInfoTrimmedFileNames.add(trimMetaInfo(fileName));
		}
		return metaInfoTrimmedFileNames;
	}
	@SuppressWarnings("static-access")
	public String trimMetaInfo(String fileName) {

		fileName = fileName.toLowerCase();
		String replaced = fileName.replaceAll("xvid*", "");
		replaced = replaced.replaceAll("\\[.*", "");
		replaced = replaced.replaceAll("dvd.*", "");
		return replaced;
	}

	public String asSimpleName(String pathPrefixedFileName) {

		Iterator<String> pathNamesSplit = Splitter.on(File.separator).split(
				pathPrefixedFileName).iterator();

		String simpleFileName = "";

		// iterate and take the last token
		while (pathNamesSplit.hasNext()) {
			simpleFileName = pathNamesSplit.next();
		}

		return simpleFileName;
	}

}
