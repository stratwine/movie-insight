/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.minsight.gui;

import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;

/**
 *
 * @author stratwine
 */
public class ComponentsReferenceHolder {

    private JProgressBar progressBar;
    private JFileChooser dirChooser;
    private JFileChooser saveAsFileChooser;
    private JButton saveButton;
    private JButton scanButton;
    


    
    public JFileChooser getDirChooser() {
        return dirChooser;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JFileChooser getSaveAsFileChooser() {
        return saveAsFileChooser;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setDirChooser(JFileChooser dirChooser) {
        this.dirChooser = dirChooser;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setSaveAsFileChooser(JFileChooser saveAsFileChooser) {
        this.saveAsFileChooser = saveAsFileChooser;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public void setScanButton(JButton scanButton) {
        this.scanButton = scanButton;
    }

    public JButton getScanButton() {
        return scanButton;
    }


}
