/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.minsight.imdb;

import com.github.minsight.model.ImdbEntry;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Interacts with www.imdbapi.com and fetches info
 * @author stratwine
 */
public class ImdbClient {

        HttpClient httpClient = new DefaultHttpClient();
     

    public ImdbEntry getImdbInfo(String movieName) throws IOException,
            ClientProtocolException, JsonParseException, JsonMappingException {

        if(movieName==null)
        {
            return new ImdbEntry();
        }


        if(movieName.isEmpty())
        {
            return new ImdbEntry();
        }

        HttpGet httpget = new HttpGet("http://www.imdbapi.com/?t=" + movieName);

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpClient.execute(httpget, responseHandler);
        ObjectMapper mapper = new ObjectMapper();
        ImdbEntry imdbEntry = mapper.readValue(responseBody, ImdbEntry.class);
        return imdbEntry;
    }

}
