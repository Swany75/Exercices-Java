/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercici01.fitxerambexcepcions;

/**
 *
 * @author swany
 */

import java.util.Scanner;

public class Exercici01FitxerAmbExcepcions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new Exercici01FitxerAmbExcepcions()).run();
    }
    
    private void run() {
        Scanner sc = new Scanner(System.in);
        int intents = 0;

        while (intents < 3) {
            System.out.print("Introduce the path to the file to search: ");
            String fileName = sc.nextLine();

            try {
                checkFile(fileName);
                sc.close();
                return; // Si todo está correcto, salimos

            } catch (CE.FileDoesNotExistException e) {
                System.out.println(e.getMessage());
                intents++; // Solo incrementamos si el fichero no existe

            } catch (CE.EmptyFirstLineException |
                     CE.SeparatorNotFoundException |
                     CE.InvalidFormatException e) {
                System.out.println(e.getMessage());
                sc.close();
                return; // Error de contenido → acabamos programa
            }
        }

        sc.close();
        throw new CE.MaxAttemptsExceededException(
            "S'han superat els intents màxims per introduir el nom del fitxer."
        );
    }
    
    
    private void checkFile(String fileName) 
            throws CE.FileDoesNotExistException,
                   CE.EmptyFirstLineException,
                   CE.SeparatorNotFoundException,
                   CE.InvalidFormatException {

        RF rf = new RF(fileName);
        try {
            rf.open();
        } catch (Exception e) {
            // Si RF no puede abrir el fichero
            throw new CE.FileDoesNotExistException (
                "[!] El fitxer especificat no existeix."
            );
        }

        String firstLine = rf.readLine();
        rf.close();

        // 1️⃣ Línea vacía o nula
        if (firstLine == null || firstLine.trim().isEmpty()) {
            throw new CE.EmptyFirstLineException(
                "[!] La primera línia del fitxer està buida."
            );
        }

        // 2️⃣ Debe contener al menos un espacio
        if (!firstLine.contains(" ")) {
            throw new CE.SeparatorNotFoundException(
                "[!] No s'ha trobat el blanc separador a la primera línia."
            );
        }

        // 3️⃣ Separar en dos partes
        String[] parts = firstLine.trim().split("\\s+");
        if (parts.length != 2) {
            throw new CE.SeparatorNotFoundException(
                "[!] No s'ha trobat el blanc separador a la primera línia."
            );
        }

        // 4️⃣ Validar que la segunda parte sea un entero
        try {
            Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new CE.InvalidFormatException(
                "[!]Error de format del nombre enter."
            );
        }

        // ✅ Todo correcto
        System.out.println("[+] Primera línia correcta: " + firstLine);
    }
}