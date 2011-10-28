package com.github.minsight.io;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SearchAndTrimIT {

	@Test
	public void searchAndTrim() {
		FileSearch fileSearch = new FileSearch();
		ArrayList<String> pathPrefixedfileNames = fileSearch
				.getFileNames("/media/fone");

		FileNameCleaner fileNameTrimmer = new FileNameCleaner();
		ArrayList<String> simpleNames = fileNameTrimmer
				.trimDirPath(pathPrefixedfileNames);
		List<String> simpleNamesTwo = fileNameTrimmer.trimMetaInfo(simpleNames);
		List<String> nonWordsReplaced = fileNameTrimmer
				.withoutNonWords(simpleNamesTwo);
		System.out.println(nonWordsReplaced);
	}

}
