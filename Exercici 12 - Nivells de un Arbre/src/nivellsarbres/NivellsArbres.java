/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nivellsarbres;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Juan
 */
public class NivellsArbres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new NivellsArbres()).run();
    }
    Arbre A = new Arbre("A");
    Arbre B = new Arbre("B");
    Arbre C = new Arbre("C");
    Arbre D = new Arbre("D");
    Arbre E = new Arbre("E");
    Arbre F = new Arbre("F");
    Arbre G = new Arbre("G");
    Arbre H = new Arbre("H");
    Arbre I = new Arbre("I");
    Arbre J = new Arbre("J");
    Arbre K = new Arbre("K");
    Arbre L = new Arbre("L");
    Arbre M = new Arbre("M");
    Arbre N = new Arbre("N");
    Arbre O = new Arbre("O");
    Arbre P = new Arbre("P");
    Arbre Q = new Arbre("Q");
    Arbre R = new Arbre("R");
    Arbre S = new Arbre("S");

    
    
    private void run() {
        // Afegir els fills
        A.childrens.add(B); A.childrens.add(C); A.childrens.add(D);
        B.childrens.add(E); B.childrens.add(F); B.childrens.add(G);
        D.childrens.add(H); D.childrens.add(I);
        E.childrens.add(J); E.childrens.add(K);
        G.childrens.add(L); G.childrens.add(M); G.childrens.add(N);
        I.childrens.add(O); I.childrens.add(P);
        O.childrens.add(Q); O.childrens.add(R); O.childrens.add(S);
        
        printPerLevels(A);
        
    }

    private void printPerLevels(Arbre arrel) {
        Queue<Arbre> queue = new LinkedList<>();
        queue.add(arrel);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
            
            int vertex = queue.size();
            StringBuilder sb = new StringBuilder();
            sb.append("Nivell ").append(level).append(": ");
            
            for (int i = 0; i < vertex; i++) {
                Arbre actual = queue.poll();
                sb.append(actual.id);
                if (i < vertex - 1) sb.append(", ");
                
                for (Arbre children: actual.childrens) {
                    queue.add(children);
                }
            }
            
            System.out.println(sb.toString());
            level++;
        }
    }
    
    class Arbre {

        String id;
        List<Arbre> childrens;

        Arbre(String id) {
            this.id = id;
            this.childrens = new ArrayList<>();
        }

    }

}
