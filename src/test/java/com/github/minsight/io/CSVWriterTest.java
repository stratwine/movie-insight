/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.minsight.io;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;

/**
 *
 * @author stratwine
 */
public class CSVWriterTest {

    @Test
    public void mustWriteCSVFromBean() throws IOException{
       StringWriter writer = new StringWriter();
       CSVWriter csvWriter = new CSVWriter(writer, ',', CSVWriter.NO_QUOTE_CHARACTER);
       csvWriter.writeNext(new String[]{"test1","test2","test3"});
       csvWriter.writeNext(new String[]{"testa","testb","56"});
       writer.flush();
       writer.close();

       System.out.println(writer.toString());
    }

}
