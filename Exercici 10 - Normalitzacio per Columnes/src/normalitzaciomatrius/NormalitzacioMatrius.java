/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package normalitzaciomatrius;

import java.util.Random;

/**
 *
 * @author swany
 */

public class NormalitzacioMatrius {
    
    Random random = new Random();
    int n = random.nextInt(99) + 1;
    int m = random.nextInt(99) + 1;

    double[][] MATRIX;

    public static void main(String[] args) {
        new NormalitzacioMatrius().run();
    }

    private void run() {
        MATRIX = genMatrix();

        printMatrix(MATRIX);

        double[] mitjana = new double[m];
        
        for (int col = 0; col < m; col++) {
            double totalColumna = 0;
            
            for (int row = 0; row < n; row++) {
                totalColumna += MATRIX[row][col];
            }
            
            mitjana[col] = totalColumna / n;
        }
        
        System.out.println("\n\nMitjana final = " + mitjana(mitjana));
    }

    private double[][] genMatrix() {
        double[][] res = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = random.nextInt(19) - 9; // sigue siendo entero pero en double
            }
        }

        return res;
    }

    private void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    private double mitjana(double[] array) {
        double res = 0;
        
        for (int i = 0; i < array.length; i++) {
            res += array[i];
        }
        
        return res / array.length;
    }
}
