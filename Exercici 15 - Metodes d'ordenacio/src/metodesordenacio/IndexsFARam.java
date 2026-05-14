/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodesordenacio;

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
}