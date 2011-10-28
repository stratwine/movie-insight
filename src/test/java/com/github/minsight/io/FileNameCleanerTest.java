package com.github.minsight.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FileNameCleanerTest {

	FileNameCleaner fileNameTrimmer = new FileNameCleaner();

	@Test
	public void directoryPrefixesShouldBeRemoved() {

		String simpleName = fileNameTrimmer
				.pathRemoved("/media/something/speed.avi");
		System.out.print("simple name:" + simpleName);
		assertTrue(simpleName.equals("speed.avi"));
	}

	@Test
	public void trimMetaInfo() {
		String trimmed = fileNameTrimmer
				.metaInfoRemoved("Harry Potter And The Deathly Hallows Part 1 TS XViD - IMAGiNE.[U...");
		System.out.println(trimmed);
	}

	@Test
	public void withoutNonWordChars() {
		String spaceReplaced = fileNameTrimmer
				.replaceSpecialCharsWithSpace("this-is-a-test");
		assertTrue(spaceReplaced.equals("this is a test"));
	}

}
