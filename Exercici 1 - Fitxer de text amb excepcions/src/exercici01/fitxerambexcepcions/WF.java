/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercici01.fitxerambexcepcions;

/**
 *
 * @author swany
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WF { // Write File
    
    private String filename;
    private FileWriter fileWriter;
    private BufferedWriter writer;
    private final boolean append;

    public WF(String fname, boolean appendMode) {
        filename = fname;
        append = appendMode;
    }

    public void open() {
        try {
            fileWriter = new FileWriter(filename, append);
            writer = new BufferedWriter(fileWriter);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeLine(String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (writer != null) writer.close();
            if (fileWriter != null) fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}