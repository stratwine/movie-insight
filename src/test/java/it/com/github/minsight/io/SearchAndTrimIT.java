package com.github.minsight.io;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.minsight.encoder.UrlEncoderUtils;
import com.github.minsight.imdb.ImdbApiClient;
import com.github.minsight.model.ImdbEntry;

public class SearchAndTrimIT {

	@Test
	public void searchAndTrim() {
		FileSearch fileSearch = new FileSearch();
		ArrayList<String> pathPrefixedfileNames = fileSearch
				.getFileNames("/media/fone");

		FileNameCleaner fileNameTrimmer = new FileNameCleaner();

		ArrayList<String> simpleNames = fileNameTrimmer
				.withoutDirPath(pathPrefixedfileNames);
		List<String> simpleNamesTwo = fileNameTrimmer
				.withoutMetaInfo(simpleNames);
		List<String> nonWordsReplaced = fileNameTrimmer
				.withoutNonWords(simpleNamesTwo);
		System.out.println("Non words replaced" + nonWordsReplaced);

		List<String> encodedMovieList = new UrlEncoderUtils()
				.getEncoded(nonWordsReplaced);

		ImdbApiClient client = new ImdbApiClient();
		//TODO: complete test
	}

}
