package com.github.minsight.encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlEncoderUtils {

	public List<String> getEncoded(List<String> movieNames) {
		ArrayList<String> encodedMovieList = new ArrayList<String>();

		for (String movieNameWithSpaces : movieNames) {
			try {
				encodedMovieList.add(URLEncoder.encode(movieNameWithSpaces,
						"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return encodedMovieList;
	}
}
