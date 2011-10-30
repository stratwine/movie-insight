/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.minsight.io;

import au.com.bytecode.opencsv.CSVWriter;
import com.github.minsight.model.ImdbEntry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author stratwine
 */
public class CSVFileWriter {

    public void createCSVFileInDirectory(File fileToSave, List<ImdbEntry> imdbEntries) {
        if (fileToSave.canWrite()) {
            PrintWriter writer = null;
            try {
                File csvFile = new File(fileToSave, "imdbInfo.csv");
                csvFile.createNewFile();
                writer = new PrintWriter(csvFile);
                CSVWriter csvWriter = new CSVWriter(writer, ',', CSVWriter.NO_QUOTE_CHARACTER);
                csvWriter.writeNext(new String[]{"Title", "Rating", "Votes"});
                for(ImdbEntry imdbEntry:imdbEntries)
                {
                csvWriter.writeNext(new String[]{imdbEntry.getTitle(),imdbEntry.getRating(),imdbEntry.getVotes()});
                }
                writer.flush();
                writer.close();
                
                System.out.println("Saved");
            }  catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                writer.close();
            }

        } else {
            System.out.println("No permissions");
        }
    

    }}
