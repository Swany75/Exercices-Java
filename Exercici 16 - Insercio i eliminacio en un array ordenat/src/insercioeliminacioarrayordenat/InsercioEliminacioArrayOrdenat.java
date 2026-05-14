/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package insercioeliminacioarrayordenat;

import java.util.Random;

/**
 *
 * @author swany
 */

public class InsercioEliminacioArrayOrdenat {

    /* Variables Globals */
    private Index[] index;
    private final int N = 1000;
    private int currentSize = 0;

    public static void main(String[] args) {
        (new InsercioEliminacioArrayOrdenat()).run();
    }

    private void run() {
        Timer timer = new Timer();

        // 1. Generar array (omplim només 10 per provar inserció/esborrat)
        index = genRandArray(10);
        
        System.out.println("--- Array Original (Random) ---");
        printArray();

        // 2. Ordenar inicialment (necessari per a la lògica d'inserció/esborrat)
        System.out.println("\n--- Ordenant amb Bidirectional Bubble Sort ---");
        bBubbleSort(index, timer);
        printArray();

        // 3. Inserir un nou element
        System.out.println("\n--- Inserint element amb Codi 500 ---");
        Index newElement = new Index(500, 50000);
        insertIndex(newElement);
        printArray();

        // 4. Esborrar l'element
        System.out.println("\n--- Esborrant element amb Codi 500 ---");
        deleteIndex(500);
        printArray();
    }

    private Index[] genRandArray(int elementsToFill) {
        index = new Index[N];
        Random random = new Random();
        currentSize = elementsToFill;
        for (int i = 0; i < currentSize; i++) {
            index[i] = new Index(random.nextInt(1000) + 1, i * 100);
        }
        return index;
    }

    // SELECTION SORT
    private static void selectionSort(Index[] arr, Timer timer) {
        int n = arr.length;
        if(timer != null) timer.start();

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == null) continue;
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] != null && arr[j].getCodi() < arr[minIndex].getCodi()) {
                    minIndex = j;
                }
            }
            Index temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        if(timer != null) {
            timer.stop();
            System.out.println("Selection Sort: " + timer.getSeconds() + " ms");
        }
    }

    // INSERTION SORT (Corregit per acceptar 'size' i 'timer' opcional)
    private static void insertionSort(Index[] arr, int size, Timer timer) {
        if(timer != null) timer.start();

        for (int i = 1; i < size; i++) {
            Index key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getCodi() > key.getCodi()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        if(timer != null) {
            timer.stop();
            System.out.println("Insertion Sort: " + timer.getSeconds() + " ms");
        }
    }

    // BIDIRECTIONAL BUBBLE SORT
    private static void bBubbleSort(Index[] arr, Timer timer) {
        if(timer != null) timer.start();
        int left = 0;
        int right = 0;
        
        // Determinem 'right' basant-nos en elements no nuls
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) right = i;
        }

        while (left < right) {
            boolean swapped = false;
            int lastSwap = left;

            for (int i = left; i < right; i++) {
                if (arr[i].getCodi() > arr[i + 1].getCodi()) {
                    Index temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                    lastSwap = i;
                }
            }
            right = lastSwap;
            if (!swapped) break;

            swapped = false;
            for (int i = right; i > left; i--) {
                if (arr[i].getCodi() < arr[i - 1].getCodi()) {
                    Index temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    swapped = true;
                    lastSwap = i;
                }
            }
            left = lastSwap;
        }
        if(timer != null) {
            timer.stop();
            System.out.println("Bidirectional Bubble Sort: " + timer.getSeconds() + " ms");
        }
    }

    public void insertIndex(Index newIdx) {
        if (currentSize < N) {
            index[currentSize] = newIdx;
            currentSize++;
            // Re-ordenem l'array perquè l'element quedi al seu lloc
            insertionSort(index, currentSize, null);
            System.out.println("[+] Element insertat.");
        } else {
            System.out.println("[!] Array ple.");
        }
    }

    public void deleteIndex(int code) {
        int posFound = -1;
        for (int i = 0; i < currentSize; i++) {
            if (index[i] != null && index[i].getCodi() == code) {
                posFound = i;
                break;
            }
        }

        if (posFound != -1) {
            for (int i = posFound; i < currentSize - 1; i++) {
                index[i] = index[i + 1];
            }
            index[currentSize - 1] = null;
            currentSize--;
            System.out.println("[-] Element " + code + " esborrat.");
        } else {
            System.out.println("[?] No trobat.");
        }
    }

    private void printArray() {
        for (int i = 0; i < currentSize; i++) {
            if (index[i] != null) System.out.println(index[i]);
        }
    }
}