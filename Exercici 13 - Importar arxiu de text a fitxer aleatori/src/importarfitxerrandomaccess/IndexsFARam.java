/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package importarfitxerrandomaccess;

/**
 *
 * @autor Tomeu Estrany
 */
import java.io.Serializable;

public class IndexsFARam implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Index[] indexARam;
    
    private final long[] esborrats; // modificat ----------------
    private int midaEsborrats;      // es podrien posar en una classe separada amb els seus mètodes
    
    private int midaIndexARam; // Mida actual de l'array d'índexs

    private final int MAXINDEX = 10000; // nombre màxim d'index

    public IndexsFARam() {
        indexARam = new Index[MAXINDEX]; // Inicialitzar l'array amb  MAXINDEX
        esborrats = new long[MAXINDEX]; // Inicialitzar l'array amb  MAXINDEX
        midaIndexARam = 0;
        midaEsborrats = 0;
    }
    
    public void addDelPos(long pos) {
        if (midaEsborrats < MAXINDEX) {
            esborrats[midaEsborrats] = pos;
            midaEsborrats++;
        } else {
            System.out.println("[!] Error: L'array d'esborrats es ple");
        }
    }
    
    public void addIndex(Index newIndex) {
        if (midaIndexARam >= MAXINDEX) {
            System.out.println("[!] Error: L'array d'index es ple");
            return;
        }
        
        int i = midaIndexARam - 1;
        while (i >= 0 && indexARam[i].getCodi() > newIndex.getCodi()) {
            indexARam[i + 1] = indexARam[i];
            i--;
        }
        
        indexARam[i + 1] = newIndex;
        
        midaIndexARam++;
    }
    
    
    public void listIndexs() {
        for (int i = 0; i < midaIndexARam; i++) {
            System.out.println(indexARam[i]);
        }
    }
    
    public void listDeleted() {
        for (int i = 0; i < midaEsborrats; i++) {
            System.out.println(esborrats[i]);
        }
    }
    
    public Index[] getIndexARam() {
        return indexARam;
    }
    
    public int getMidaIndexARam() {
        return midaIndexARam;
    }
    
}