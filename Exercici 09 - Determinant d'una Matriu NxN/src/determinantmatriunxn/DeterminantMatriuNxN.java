/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package determinantmatriunxn;

/**
 *
 * @author swany
 */

import java.util.Random;

public class DeterminantMatriuNxN {

    Random random = new Random();
    int n = random.nextInt(99) + 1;

    int[][] MATRIX;

    public static void main(String[] args) {
        new DeterminantMatriuNxN().run();
    }

    private void run() {
        MATRIX = genMatrix();

        printMatrix(MATRIX);

        double det = determinant(MATRIX);

        System.out.println("\nDeterminant = " + det);
    }

    private int[][] genMatrix() {
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = random.nextInt(19) - 9; // -9 a 9
            }
        }

        return m;
    }

    private void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private double determinant(int[][] m) {
        int n = m.length;

        if (n == 1) return det1x1(m);
        if (n == 2) return det2x2(m);
        if (n == 3) return det3x3(m);

        return detNxN(m);
    }

    private int det1x1(int[][] m) {
        return m[0][0];
    }

    private int det2x2(int[][] m) {
        return (m[0][0] * m[1][1]) - (m[0][1] * m[1][0]);
    }

    private int det3x3(int[][] m) {

        int aei = m[0][0] * m[1][1] * m[2][2];
        int bfg = m[0][1] * m[1][2] * m[2][0];
        int cdh = m[0][2] * m[1][0] * m[2][1];

        int ceg = m[0][2] * m[1][1] * m[2][0];
        int bdi = m[0][1] * m[1][0] * m[2][2];
        int afh = m[0][0] * m[1][2] * m[2][1];

        return (aei + bfg + cdh) - (ceg + bdi + afh);
    }

    private double detNxN(int[][] matrix) {
        int n = matrix.length;

        double[][] m = new double[n][n];

        // copiar matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = matrix[i][j];
            }
        }

        double det = 1;
        int sign = 1;

        for (int i = 0; i < n; i++) {

            // pivot 0 → swap filas
            if (m[i][i] == 0) {
                boolean swapped = false;

                for (int k = i + 1; k < n; k++) {
                    if (m[k][i] != 0) {
                        double[] temp = m[i];
                        m[i] = m[k];
                        m[k] = temp;

                        sign *= -1;
                        swapped = true;
                        break;
                    }
                }

                if (!swapped) return 0;
            }

            // eliminación
            for (int j = i + 1; j < n; j++) {
                double factor = m[j][i] / m[i][i];

                for (int k = i; k < n; k++) {
                    m[j][k] -= factor * m[i][k];
                }
            }
        }

        // producto diagonal
        for (int i = 0; i < n; i++) {
            det *= m[i][i];
        }

        return det * sign;
    }
}
