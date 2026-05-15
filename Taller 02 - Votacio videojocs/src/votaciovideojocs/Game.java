/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package votaciovideojocs;

/**
 *
 * @author swany
 */
public class Game {
    
    // Mida del registre (4+60+30+30+4+4 = 132 bytes)
    public static final int MIDA_REG = 132;
    
    // Atributs privats
    private int code;
    private String name;
    private String genre;
    private String platform;
    private int year;
    private int votes;
    
    // Constructor
    public Game(int c, String n, String g, String p, int y, int v) {
        this.code = c;
        this.name = n;
        this.genre = g;
        this.platform = p;
        this.year = y;
        this.votes = v;
    }
    
    // Getters i Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
    
    // Mètode toString corregit
    @Override
    public String toString() {
        return String.format("Codi: %d | Vots: %d | %-20s | %-10s | %-10s | %d", 
                code, votes, name.trim(), genre.trim(), platform.trim(), year);
    }
    
}
