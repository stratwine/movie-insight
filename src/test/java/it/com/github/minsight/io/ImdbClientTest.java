package com.github.minsight.io;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.github.minsight.imdb.ImdbApiClient;

public class ImdbClientTest {

	@Test
	public void getMovieInfoTest() throws ClientProtocolException, IOException {
		try {
			ImdbApiClient imdbClient = new ImdbApiClient();
			imdbClient.getMovieInfo("speed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
