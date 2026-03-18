/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercici2.creacioclassetriangle;

/**
 *
 * @author swany
 */
public class Triangle {
    private int side1, side2, side3;
    
    public Triangle(int s1, int s2, int s3) {
        
        if (s1 < 0 || s2 < 0 || s3 < 0) {
            throw new CE.TriangleException("[!] Error: Les longituds dels costats han de ser positives");
        }

        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1) {
            throw new CE.TriangleException("[!] Error: No es compleix la desigualtat del triangle ");
        }
        
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
        
    }
    
}
