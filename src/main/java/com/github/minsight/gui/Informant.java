/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.minsight.gui;

import com.github.minsight.imdb.BackgroundWorker;
import com.github.minsight.io.BackgroundCSVFileWriter;
import javax.swing.JProgressBar;

/**
 *
 * @author stratwine
 */
public class Informant {

   ComponentsReferenceHolder componentsReferenceHolder;
   BackgroundCSVFileWriter backgroundCSVFileWriter;
   BackgroundWorker backgroundWorker;

    public void setBackgroundCSVFileWriter(BackgroundCSVFileWriter backgroundCSVFileWriter) {
        this.backgroundCSVFileWriter = backgroundCSVFileWriter;
    }

    public void setBackgroundWorker(BackgroundWorker backgroundWorker) {
        this.backgroundWorker = backgroundWorker;
    }


   Informant(ComponentsReferenceHolder componentsReferenceHolder)
    {
       this.componentsReferenceHolder=componentsReferenceHolder;
    }

   private JProgressBar getProgressBar()
    {
       return this.componentsReferenceHolder.getProgressBar();
    }

   public void informNoWritePermissions()
    {
      getProgressBar().setIndeterminate(false);
      getProgressBar().setString("Sorry. No Permissions to write file on the directory");
    }

   public void informErrorOccured(Throwable e)
    {
       e.printStackTrace();
       informStaticMessage("Sorry a technical error occured !");
    }

   public void informFileSaved(String location)
    {
        getProgressBar().setVisible(true);
        getProgressBar().setIndeterminate(false);
        getProgressBar().setString("imdbInfo.csv was saved at " + location);
    }

   public void informWritingEntry(String csvEntry)
    {

    }

    public void informProcessingMessage(String message) {

        getProgressBar().setVisible(true);
        getProgressBar().setIndeterminate(true);
        getProgressBar().setString(message);
    }

    public void informStaticMessage(String message)
    {
        getProgressBar().setVisible(true);
        getProgressBar().setIndeterminate(false);
        getProgressBar().setString(message);
    }

    public void informNoReadPermissions()
    {
      getProgressBar().setVisible(true);
      getProgressBar().setIndeterminate(false);
      getProgressBar().setString("Sorry. No read permissions on the directory");
    }

    public void informImdbInfoFetchCompleted()
    {

        
        componentsReferenceHolder.getProgressBar().setVisible(true);
        
        componentsReferenceHolder.getDirChooser().setVisible(true);
        
        componentsReferenceHolder.getScanButton().setVisible(false);
        
        componentsReferenceHolder.getSaveButton().setVisible(true);
        this.informStaticMessage("Completed retrieving imdb info");
        
    }

}
