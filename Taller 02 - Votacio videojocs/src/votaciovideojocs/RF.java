/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package votaciovideojocs;

/**
 *
 * @author swany
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RF {
    private String filename;
    private FileReader fileReader;
    private BufferedReader reader;
    
    public RF(String fname) {
        filename = fname;
    }
    
    public void open() {
        try {
            fileReader = new FileReader(filename);
            reader = new BufferedReader(fileReader);
        } catch (IOException ex) {
            System.out.println("[!] Error: File " + filename + " not found");
            ex.printStackTrace();
        }
    }
    
    public String readLine() {
        String result = null;
        try {
            String line = reader.readLine();
            if (line != null) {
                result = new String(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public void close() {
        try {
            if (reader != null) reader.close();
            if (fileReader != null) fileReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
