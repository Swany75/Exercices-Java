/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercici2.creacioclassetriangle;

/**
 *
 * @author swany
 */
public class Exercici2CreacioClasseTriangle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new Exercici2CreacioClasseTriangle()).run();
    }
    
    private void run() {
        try {
            // Excepcio numeros negatius
            // Triangle t1 = new Triangle(-1,2,4);
            // Excepcio suma del (c1 + c2) > c3
            // Triangle t2 = new Triangle(1,1,3);
            // Format correcte
            Triangle t3 = new Triangle(1,1,1);
            System.out.println("[+] Codi Executat correctament");
            
        } catch (CE.TriangleException e) {
            System.out.println(e.getMessage());
        
        }
    }
}
