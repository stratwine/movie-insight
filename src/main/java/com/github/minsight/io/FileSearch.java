package com.github.minsight.io;

import com.github.minsight.gui.Informant;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;

/**
 * TODO: Either use informant and add more messages on file search
 * Or remove informant
 * @author stratwine
 */
public class FileSearch extends SwingWorker<List<String>,String>{

    String pathToSearch;
    ArrayList<String> fileNamesFound;
    Informant informant;

    public ArrayList<String> getFileNamesFound() {
        return fileNamesFound;
    }

    public void setFileNamesFound(ArrayList<String> fileNamesFound) {
        this.fileNamesFound = fileNamesFound;
    }

    public String getPathToSearch() {
        return pathToSearch;
    }

    public void setPathToSearch(String pathToSearch) {
        this.pathToSearch = pathToSearch;
    }

    public FileSearch(String locationToSearch, Informant informant) {
        this.pathToSearch = locationToSearch;
        this.informant=informant;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<String> getFileNames() {


        ArrayList<String> fileNames = new ArrayList<String>();
        File searchAtLocation = new File(this.getPathToSearch());
        if (!searchAtLocation.canRead()) {
            informant.informNoReadPermissions();
            return fileNames; //FIXME: Handle it better
        }
        String[] extensionsToSearch = {"avi"};
        Iterator<File> filesIterator = FileUtils.iterateFiles(new File(this.getPathToSearch()),
                extensionsToSearch, true);
        while (filesIterator.hasNext()) {
            String fname=filesIterator.next().toString();
            fileNames.add(fname);
            publish("Found: "+fname);
        }
        return fileNames;
    }

    @Override
    protected List<String> doInBackground() throws Exception {
        return getFileNames();
    }


    @Override
    protected void process(List<String> messages) {
        for(String message:messages){
         informant.informProcessingMessage(message);
        }

    }


}
