/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package votaciovideojocs;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author swany
 */
public class VotacioVideojocs {

    private static final int MAXREG = 1000;
    private static final String TXT_FILE = "media/jocs.txt";
    private static final String DAT_FILE = "media/jocs.dat";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new VotacioVideojocs()).run();
    }
    
    private void run() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            clearScreen();
            showMenu();
            
            try {
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1 -> {
                        Game[] jocs = readGames();
                        writeGames(jocs);
                        System.out.println("\n[+] Fitxer DAT creat i ordenat correctament.");
                    }
                    
                    case 2 -> {
                        java.io.File f = new java.io.File(DAT_FILE);
                        if (!f.exists()) {
                            System.out.println("[!] Error: El fitxer binari no existeix");
                            System.out.println("[i] Pista: Executa primer la opció 1");
                        } else {
                            showBinaryFile();
                        }
                    }
                    
                    case 3 -> {
                        java.io.File f = new java.io.File(DAT_FILE);
                        if (!f.exists()) {
                            System.out.println("[!] Error: El fitxer binari no existeix de moment.");
                            System.out.println("[i] Pista: Executa primer la opció 1 per generar-lo.");
                        } else {
                            voteGame(sc);
                        }
                    }
                    
                    case 0 -> {
                        System.out.println("\n[+] Sortint de l'aplicació...");
                    }
                    
                    default -> {
                        System.out.println("\n[!] Error: Opció no valida");
                        System.out.println("\n[i] Pista: Introdueix un valor del 0 al 3");
                    }
                }
                
                pressToContinue();
                
            } catch (Exception e) {
                System.out.println("Error: Introdueix un numero valid.");
                option = -1;
            }
        }
    }
    
    private Game[] readGames() {
        RF rf = new RF(TXT_FILE);
        rf.open();
        
        Game[] array = new Game[MAXREG];
        int c = 0;
        
        String line;
        
        while ((line = rf.readLine()) != null && c < 1000) {
            String[] fields = line.split(";");
            
            int codi = Integer.parseInt(fields[0].trim());
            String titol = fields[1].trim();
            if (titol.length() > 30) titol = titol.substring(0, 30);
            String genere = fields[2].trim();
            if (genere.length() > 15) genere = genere.substring(0, 15);
            String plataforma = fields[3].trim();
            if (plataforma.length() > 15) plataforma = plataforma.substring(0, 15);
            int any = Integer.parseInt(fields[4].trim());
            
            array[c] = new Game(codi, titol, genere, plataforma, any, 0);
            c++;
        }
        
        rf.close();
        Game[] res = new Game[c];
        System.arraycopy(array, 0, res, 0, c);
        insertionSort(res);
        return res;
    }
    
    private static void insertionSort(Game[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Game key = arr[i];
            int j = (i - 1);
            
            while ((j >= 0) && (arr[j].getCode() > key.getCode())) {
                arr[j+1] = arr[j];
                j--;
            }
            
            arr[j + 1] = key;
        }
    }

    private void writeGames(Game[] jocs) {
        try (RandomAccessFile raf = new RandomAccessFile(DAT_FILE, "rw")) {
            raf.setLength(0);
            for (int i = 0; i < jocs.length; i++) {
                Game j = jocs[i];

                raf.writeInt(j.getCode());              // 4 bytes
                writeString(raf, j.getName(), 30);      // 60 bytes (30 chars * 2)
                writeString(raf, j.getGenre(), 15);     // 30 bytes (15 chars * 2)
                writeString(raf, j.getPlatform(), 15);  // 30 bytes (15 chars * 2)
                raf.writeInt(j.getYear());              // 4 bytes
                raf.writeInt(j.getVotes());             // 4 bytes

            }
        } catch (Exception e) {
            System.out.println("[!] Error escrivint DAT: " + e.getMessage());
        }
    }
    
    
    private void showBinaryFile() {
        clearScreen();
        
        try (RandomAccessFile raf = new RandomAccessFile(DAT_FILE, "r")) {
        
            while (raf.getFilePointer() < raf.length()) {
                int codi = raf.readInt();
                String nom = readString(raf, 30);
                String genere = readString(raf, 15);
                String plataforma = readString(raf, 15);
                int any = raf.readInt();
                int vots = raf.readInt();
                
                System.out.println(codi + ") " + nom + " " + genere + " " + plataforma + " " + any + " " + vots);
            }
        } catch (IOException e) {
            System.out.println("[!] Error llegint el fitxer DAT: " + e.getMessage());
        }
    }
    
    private void writeString(RandomAccessFile raf, String s, int midaFixa) throws IOException {
        for (int i = 0; i < midaFixa; i++) {
            char c = (i < s.length()) ? s.charAt(i) : ' ';
            raf.writeChar(c);
        }
    }

    private String readString(RandomAccessFile raf, int midaFixa) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < midaFixa; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim(); 
    }

    private void voteGame(Scanner sc) {
        int code = codeToSearch(sc);
        
        if (code == -1) { return; }
        
        try (RandomAccessFile raf = new RandomAccessFile(DAT_FILE, "rw")) {
            int pos = dicotomicSearch(raf, code);
            if (pos == -1) { 
                System.out.println("[!] Error: El codi de joc " + code + " no existeix");
                
            } else {
                long posVots = ((long) pos * Game.MIDA_REG) + 128;
                
                raf.seek(posVots);
                int vots = raf.readInt();
                
                raf.seek(posVots);
                raf.writeInt(vots + 1);
                
                System.out.println("\n[+] Vot registrat correctament");

            }
            
        } catch (IOException e) {
            System.out.println("[!] Error en accedir al fitxer DAT: " + e.getMessage());
        }
        
    }
    
    
   
    private int codeToSearch(Scanner sc) {
        System.out.print("\n[+] Introdueix el codi de videojoc per votar: ");
        
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[!] Error: El codi ha de ser un un numero sencer");
            System.out.println("[i] Pista: Si vols veurer els jocs executa el 2n menu");
            pressToContinue();
            return -1;
        }
    }
    
    private int dicotomicSearch(RandomAccessFile raf, int gameCode) throws IOException {
        int low = 0;
        int high = (int) (raf.length() / Game.MIDA_REG) - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            raf.seek((long) mid * Game.MIDA_REG);
            int actual = raf.readInt();
            if (actual == gameCode) { return mid; }
            if (actual < gameCode) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return -1;
    }
    
    /// Context Methods ////////////////////////////////////////////////////////
    
    private void showMenu() {
        System.out.println("\n╔════ Votació Videojocs ═════════════════════════╗");
        System.out.println("║                                             ║");
        System.out.println("║ 1 ) Importar fitxer TXT i crear fitxer DAT  ║");
        System.out.println("║ 2 ) Mostrar el contingut del RAF            ║");
        System.out.println("║ 3 ) Votar un joc per codi                   ║");
        System.out.println("║ 0 ) Sortir                                  ║");
        System.out.println("║                                             ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.print("\n\n[+] Introdueix una opció: ");
    }
    
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private void pressToContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n[i] Press any key to continue... ");
        sc.nextLine();
    }

}
 