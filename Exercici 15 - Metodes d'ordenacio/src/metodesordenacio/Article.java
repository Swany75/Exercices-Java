/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodesordenacio;

/**
 *
 * @autor Tomeu Estrany
 */

public class Article {
    private int codi;
    private String descripcio;
    private int quantitat;
    private boolean esborrat;
    
    /**
     * Mida del registre 
     * private int codi; 4 
     * private String descripcio; 30 * 2
     * private int quantitat; 4 
     * private boolean esborrat; 1 
     * MIDAREGISTRE = 69
     */
    public static final int MIDAREGISTRE = 69; // Mida del registre
    public static final int MIDADESC = 30;     // MIda descripció

    public Article(int codi, String descripcio, int quantitat, boolean esborrat) {
        this.codi = codi;
        this.descripcio = descripcio;
        this.quantitat = quantitat;
        this.esborrat = esborrat;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public boolean getEsborrat() {
        return esborrat;
    }

    public void setEsborrat(boolean esborrat) {
        this.esborrat = esborrat;
    }

    @Override
    public String toString() {
        return "Article {" + "codi: " + codi + " descripció: " + descripcio + " quantitat: " + quantitat + " esborrat: " + esborrat + '}';
    }

}
