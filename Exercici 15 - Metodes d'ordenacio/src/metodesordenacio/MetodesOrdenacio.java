/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodesordenacio;

import java.util.Random;

/**
 *
 * @author swany
 */
public class MetodesOrdenacio {

    /* Variables Globals */
    private Index[] index;
    private final int N = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new MetodesOrdenacio()).run();
    }

    /* Metodes Principals */
    private void run() {

        Timer timer = new Timer();

        Index[] array = genRandArray();
        Index[] arraySelection = array.clone();
        Index[] arrayInsertion = array.clone();
        Index[] arrayBBubble = array.clone();

        selectionSort(arraySelection, timer);
        insertionSort(arrayInsertion, timer);
        bBubbleSort(arrayBBubble, timer);

    }

    private Index[] genRandArray() {
        index = new Index[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            index[i] = new Index(random.nextInt(1000) + 1, i * 100);
        }
        return index;
    }

    private static void selectionSort(Index[] arr, Timer timer) {
        int n = arr.length;
        timer.start();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = (i + 1); j < n; j++) {
                if (arr[j].getCodi() < arr[minIndex].getCodi()) {
                    minIndex = j;
                }
            }

            Index temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        timer.stop();
        System.out.println("Selection Sort: " + timer.getSeconds() + " Miliseconds");

    }

    private static void insertionSort(Index[] arr, Timer timer) {
        int n = arr.length;
        timer.start();

        for (int i = 1; i < n; i++) {
            Index key = arr[i];
            int j = (i - 1);
            
            while ((j >= 0) && (arr[j].getCodi() > key.getCodi())) {
                arr[j+1] = arr[j];
                j--;
            }
            
            arr[j + 1] = key;
        }
        
        timer.stop();
        System.out.println("Insertion Sort: " + timer.getSeconds() + " Miliseconds");

    }

    private static void bBubbleSort(Index[] arr, Timer timer) {
        int n = arr.length;
        timer.start();

        int left = 0;
        int right = n - 1;
        int lastSwap = 0;

        while (left < right) {
            boolean swapped = false;

            // Forward pass: move the largest element to the right
            for (int i = left; i < right; i++) {
                if (arr[i].getCodi() > arr[i + 1].getCodi()) {
                    Index temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;

                    swapped = true;
                    lastSwap = i;
                }
            }
            // Update the right boundary
            right = lastSwap;

            if (!swapped) break; // If no elements were swapped, it's already sorted

            swapped = false;

            // Backward pass: move the smallest element to the left
            for (int i = right; i > left; i--) {
                if (arr[i].getCodi() < arr[i - 1].getCodi()) {
                    Index temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;

                    swapped = true;
                    lastSwap = i;
                }
            }
            // Update the left boundary
            left = lastSwap;
        }

        timer.stop();
        System.out.println("Bidirectional Bubble Sort: " + timer.getSeconds() + " Miliseconds");
    }


}
