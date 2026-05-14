/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package importarfitxerrandomaccess;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author swany
 */
public class ImportarFitxerRandomAccess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new ImportarFitxerRandomAccess()).run();
    }
    
    String FILE = "files/articles.dat";
    IndexsFARam index = new IndexsFARam();
    
    private void run() {

        try (RandomAccessFile raf = new RandomAccessFile(FILE, "rw")) {
        
            readFile(raf);
            listArticles(raf);
            
        
        } catch (IOException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
        
        saveIndexs();
        
    } 
    
    private void readFile(RandomAccessFile raf) throws IOException {
        while (raf.getFilePointer() < raf.length()) {
            long pos = raf.getFilePointer();
            int code = raf.readInt();
                
            String desc = "";
            
            for (int i = 0; i < Article.MIDADESC; i++) {
                desc += raf.readChar();
            }
            desc = desc.trim();
                
            int quantity = raf.readInt();
            boolean deleted = raf.readBoolean();
                
            if (!deleted) {
                Index actualIndex = new Index(code, pos);
                index.addIndex(actualIndex);        
            } else {
                index.addDelPos(pos);
            }
        }      
    }
    
    private void listArticles(RandomAccessFile raf) throws IOException {
        for (int i = 0; i < index.getMidaIndexARam(); i++) {
            Index actualIndex = index.getIndexARam()[i];
            raf.seek(actualIndex.getPosicio());
            int code = raf.readInt();
                
            String desc = "";
            
            for (int j = 0; j < Article.MIDADESC; j++) {
                desc += raf.readChar();
            }
            desc = desc.trim();
                
            int quantity = raf.readInt();
            boolean deleted = raf.readBoolean();
                    
            Article art = new Article(code, desc, quantity, deleted);
            System.out.println(art);   
        }
    }
    
    
    private void saveIndexs() {
        String fileIndx = "files/articles.indx";
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(fileIndx))) {

            oos.writeObject(index);
            System.out.println("[+] Índexs desats correctament a " + fileIndx);

        } catch (IOException e) {
            System.out.println("[!] Error en desar l'arxiu d'índexs: " + e.getMessage());
        }
    }
    
}
