package com.github.minsight.io;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class FileSearchTest {

	@Test
	public void videoFilesFromFileSystemShouldBeFetched() throws IOException {
		String path = "/media/fone";
		FileSearch fileSearch = new FileSearch();
		// Files.touch(new File(path + File.pathSeparatorChar + "test.avi"));
		ArrayList<String> fileNames = fileSearch.getFileNames(path);
		assertTrue(fileNames.size() != 0);
		System.out.println(fileNames);
	}
}
