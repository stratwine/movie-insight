package com.github.minsight.imdb;

import com.github.minsight.gui.Informant;
import com.github.minsight.io.FileNameCleaner;
import com.github.minsight.io.FileSearch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


import com.github.minsight.model.ImdbEntry;
import javax.swing.SwingWorker;

public class BackgroundWorker extends SwingWorker<List<ImdbEntry>, String> {

     ImdbClient imdbClient = new ImdbClient();
     List<String> encodedMovieList;
     FileSearch fileSearch;
     Informant informant;
     private List<ImdbEntry> result;



    public BackgroundWorker(Informant informant, FileSearch fileSearch) {
    this.fileSearch=fileSearch;
    this.informant=informant;
    }

    
    

    public void populateMovieList()
    {
        List<String> pathPrefixedfileNames;
        try {
            fileSearch.execute();
            pathPrefixedfileNames = fileSearch.get();
            FileNameCleaner cleaner = new FileNameCleaner();
            this.encodedMovieList=cleaner.cleanAndEncode(pathPrefixedfileNames);
        } catch (InterruptedException ex) {
            informant.informErrorOccured(ex);
        } catch (ExecutionException ex) {
            informant.informErrorOccured(ex);
        }

    }




    @Override
    protected List<ImdbEntry> doInBackground() throws Exception {

        List<ImdbEntry> imdbEntries = new ArrayList<ImdbEntry>();
        this.populateMovieList();
        for (String movieName : this.encodedMovieList) {
            ImdbEntry entry = imdbClient.getImdbInfo(movieName);
            imdbEntries.add(entry);
            publish("Fetching info on: "+entry.getTitle());
        }

        return imdbEntries;

    }

    @Override
    protected void process(List<String> messages) {
        for(String message:messages){
         informant.informProcessingMessage(message);
        }

    }

    @Override
    protected void done()
    {
        try {
            informant.informImdbInfoFetchCompleted();
            result = get();
        } catch (InterruptedException ex) {
            informant.informErrorOccured(ex);
        } catch (ExecutionException ex) {
            informant.informErrorOccured(ex);
        }

    }

    public void setEncodedMovieList(List<String> encodedMovieList) {
        this.encodedMovieList = encodedMovieList;
    }

    public  List<ImdbEntry>getImdbEntriesResult()
    {
       return result;
    }

}
