package com.github.minsight.io;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FileNameTrimmerTest {

	FileNameTrimmer fileNameTrimmer = new FileNameTrimmer();

	@Test
	public void directoryPrefixesShouldBeRemoved() {

		String simpleName = fileNameTrimmer
				.asSimpleName("/media/something/speed.avi");
		System.out.print(simpleName);
		assertTrue(simpleName.equals("speed.avi"));
	}

	@Test
	public void trimMetaInfo() {
		String trimmed = fileNameTrimmer
				.trimMetaInfo("Harry Potter And The Deathly Hallows Part 1 TS XViD - IMAGiNE.[U...");
		System.out.println(trimmed);
	}

}
