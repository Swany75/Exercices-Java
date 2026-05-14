/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodesordenacio;

import java.io.Serializable;

/**
 *
 * @autor Tomeu Estrany
 */
import java.io.Serializable;
public class Index implements Serializable {

    private static final long serialVersionUID = 1L;

    private int codi;
    private long posicio;

    public Index(int codi, long posicio) {
        this.codi = codi;
        this.posicio = posicio;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public long getPosicio() {
        return posicio;
    }

    public void setPosicio(long posicio) {
        this.posicio = posicio;
    }

    @Override
    public String toString() {
        return "Índex {" + "codi: " + codi + " posició: " + posicio + '}' ;  //+ "\n" ;
    }
}
