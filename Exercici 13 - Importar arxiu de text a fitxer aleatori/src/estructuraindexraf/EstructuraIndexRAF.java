/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package estructuraindexraf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Juan
 */
public class EstructuraIndexRAF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new EstructuraIndexRAF()).run();
    }
    
    
    private void run() {
        String INPUT = "files/articles.txt";
        String OUTPUT = "files/articles.dat";
        StringBuilder SB = new StringBuilder();
        RF reader = new RF(INPUT);
        reader.open();
        
        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT, "rw")) {
            
            String line;
            
            while ((line = reader.readLine()) != null) {

                Article actual = applyFilter(line);
                if (actual != null) {
                    
                    raf.writeInt(actual.getCodi());
                    raf.writeChars(cutDescription(actual.getDescripcio()));
                    raf.writeInt(actual.getQuantitat());
                    raf.writeBoolean(actual.getEsborrat());

                    raf.seek(0);
                    
                    while (raf.getFilePointer() < raf.length()) {
                        int code = raf.readInt();
                        String desc = "";
                        
                        for (int i = 0; i < Article.MIDADESC; i++) {
                            desc += raf.readChar();
                        }
                        
                        int quantity = raf.readInt();
                        boolean deleted = raf.readBoolean();
                        
                        
                        Article newArticle = new Article (code, desc, quantity, deleted);
                        System.out.println(newArticle);
                    }
                    
                }
                
            }
            
        } catch (IOException e) {
            System.out.println("[!] Error: " + e.getMessage());
            
        } finally {
            reader.close();
            
        }
        
    }
    
    private Article applyFilter(String line) {
        
        String[] splited_line = line.split(",");
        Article res;
        
        
        if (splited_line.length == 3) {
            // Trim elimina els espais
            int code = Integer.parseInt(splited_line[0].trim());
            String description = splited_line[1].trim();
            int quantity = Integer.parseInt(splited_line[2].trim());
            res = new Article(code, description, quantity, false);
            
        } else {
            System.out.println("[!] Error (Formato de linea incorrecto): " + line);
            res = null;
        }

        return res;
        
    }
    
    private String cutDescription(String description) {
        if (description.length() > Article.MIDADESC) {
            description = description.substring(0, Article.MIDADESC);
        }
        
        return String.format("%-" + Article.MIDADESC + "s", description);
    }
    
}
