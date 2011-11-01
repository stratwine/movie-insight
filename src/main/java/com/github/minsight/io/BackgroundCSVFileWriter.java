/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.minsight.io;

import au.com.bytecode.opencsv.CSVWriter;
import com.github.minsight.gui.Informant;
import com.github.minsight.model.ImdbEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author stratwine
 */
public class BackgroundCSVFileWriter extends SwingWorker<String, String> {

    File saveAtDirectory;
    List<ImdbEntry> imdbEntries;
    Informant informant;
    String exitMessage;

    public BackgroundCSVFileWriter(File saveAtLocation, List<ImdbEntry> imdbEntries, Informant informant) {
        this.saveAtDirectory = saveAtLocation;
        this.imdbEntries = imdbEntries;
        this.informant = informant;

    }

    public String saveContentAsCSV(List<ImdbEntry> imdbEntries) {

        if (!saveAtDirectory.canWrite()) {
            System.out.println("No write permissions");
            informant.informNoWritePermissions();
            return "NO_WRITE";
        }


        PrintWriter writer = null;
        try {
            writer = writeInCsv(writer, imdbEntries);
            informant.informFileSaved(this.saveAtDirectory.toString());
        } catch (FileNotFoundException ex) {
            informant.informErrorOccured(ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            informant.informErrorOccured(ex);
            ex.printStackTrace();
        } finally {
            //FIXME: check why writer becomes null at times
            if(writer!=null){
            writer.flush();
            writer.close();
            }
        }

        return "WRITTEN";


    }

    private PrintWriter writeInCsv(PrintWriter writer, List<ImdbEntry> imdbEntries) throws IOException, FileNotFoundException {
        File csvFile = new File(saveAtDirectory, "imdbInfo.csv");
        if(csvFile.exists()){csvFile.delete();}
        csvFile.createNewFile();
        writer = new PrintWriter(csvFile);
        CSVWriter csvWriter = new CSVWriter(writer, ',', CSVWriter.NO_QUOTE_CHARACTER);
        csvWriter.writeNext(ImdbEntry.getHeaderContent());
        for (ImdbEntry imdbEntry : imdbEntries) {
            csvWriter.writeNext(imdbEntry.getTextContent());
            publish("Writing info to csv:" + imdbEntry.getTitle());
            System.out.println("Writing to csv"+imdbEntry.getTitle());
        }
        return writer;
    }

    @Override
    protected String doInBackground() throws Exception {
        exitMessage = this.saveContentAsCSV(this.imdbEntries);
        return exitMessage;
    }

    @Override
    protected void process(List<String> messages) {
        for (String message : messages) {
            informant.informProcessingMessage(message);
        }
        
    }

    @Override
    protected void done() {
        String endMessage = null;
        try {
            endMessage=get();
        } catch (InterruptedException ex) {
            Logger.getLogger(BackgroundCSVFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(BackgroundCSVFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(endMessage.equals("WRITTEN")){
        informant.informFileSaved(this.saveAtDirectory.toString());
        }
 else if(endMessage.equals("NO_WRITE"))
        {
            informant.informStaticMessage("No write permissions on the directory");
        }
 }

}
