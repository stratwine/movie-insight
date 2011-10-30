package com.github.minsight.imdb;

import com.github.minsight.gui.ComponentsReferenceHolder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.minsight.model.ImdbEntry;
import javax.swing.SwingWorker;

public class ImdbApiClient extends SwingWorker<List<ImdbEntry>, String> {

     HttpClient httpclient = new DefaultHttpClient();
     List<String> encodedMovieList;
     ComponentsReferenceHolder componentsReferenceHolder;
     private List<ImdbEntry> result;

    public  List<ImdbEntry>getImdbEntriesResult()
    {
       return result;
    }

    public ImdbApiClient()
    {

    }

    public ImdbApiClient(List<String> encodedMovieList, ComponentsReferenceHolder componentsReferenceHolder) {
        this.encodedMovieList=encodedMovieList;
        this.componentsReferenceHolder=componentsReferenceHolder;

    }

    public void setEncodedMovieList(List<String> encodedMovieList) {
        this.encodedMovieList = encodedMovieList;
    }

    public void setHttpclient(HttpClient httpclient) {
        this.httpclient = httpclient;
    }

    public HttpClient getHttpClient() {
        return this.httpclient;
    }

    public ImdbEntry getMovieInfo(String movieName) {

        ImdbEntry movieInfo = null;
        try {
            movieInfo = retrieveMovieInfo(movieName);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("MovieInfo Retrieved:" + movieInfo);
        return movieInfo;
    }

    private ImdbEntry retrieveMovieInfo(String movieName) throws IOException,
            ClientProtocolException, JsonParseException, JsonMappingException {
        HttpGet httpget = new HttpGet("http://www.imdbapi.com/?t=" + movieName);

        System.out.println("executing request " + httpget.getURI());

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = getHttpClient().execute(httpget, responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);
        System.out.println("----------------------------------------");

        ObjectMapper mapper = new ObjectMapper();
        ImdbEntry imdbEntry = mapper.readValue(responseBody, ImdbEntry.class);
        return imdbEntry;
    }

    @Override
    protected List<ImdbEntry> doInBackground() throws Exception {

        List<ImdbEntry> imdbEntries = new ArrayList<ImdbEntry>();

        for (String movieName : this.encodedMovieList) {
            ImdbEntry entry = this.getMovieInfo(movieName);
            imdbEntries.add(entry);
            publish(entry.getTitle());
        }

        return imdbEntries;

    }

    @Override
    protected void process(List<String> messages) {
        for(String message:messages){
            System.out.println("adding progress message"+message);
        componentsReferenceHolder.getProgressBar().setString("Fetching info on\" "+message+"\"\n");

        }

    }

    @Override
    protected void done()
    {
        try {
            componentsReferenceHolder.getProgressBar().setIndeterminate(false);
            componentsReferenceHolder.getProgressBar().setString("Done");
            result = get();
        } catch (InterruptedException ex) {
            Logger.getLogger(ImdbApiClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ImdbApiClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        componentsReferenceHolder.getProgressBar().setVisible(false);
        componentsReferenceHolder.getDirChooser().setVisible(false);
        componentsReferenceHolder.getScanButton().setVisible(false);
        componentsReferenceHolder.getSaveAsFileChooser().setVisible(true);
        componentsReferenceHolder.getSaveButton().setVisible(true);
    }

}
