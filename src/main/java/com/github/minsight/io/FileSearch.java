package com.github.minsight.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public class FileSearch {

	@SuppressWarnings("unchecked")
	public ArrayList<String> getFileNames(String path) {

		ArrayList<String> fileNames = new ArrayList<String>();

		String[] extensionsToSearch = {"avi"};
		Iterator<File> filesIterator = FileUtils.iterateFiles(new File(path),
				extensionsToSearch, true);

		while (filesIterator.hasNext()) {
			fileNames.add(filesIterator.next().toString());
		}

		return fileNames;
	}

}
