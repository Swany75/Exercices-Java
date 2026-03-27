/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vuitreines;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author swany
 */
public class VuitReines extends JPanel implements MouseListener, MouseMotionListener{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new VuitReines()).run();
    }
    
    int rows = 8, cols = 8;
    int max = rows * cols;
    int cellWidth;
    int cellHeight;
    
    int pxWidth = 800;
    int pxHeight = 800;
    
    boolean[][] panel = new boolean[rows][cols];
    boolean[] queens = new boolean[max];
    
    // Images
    Image queen = new ImageIcon(getClass().getResource("/img/queen.png")).getImage();    
    
    // COLORS
    Color[] colors = new Color[max];
    Color chessDark = new Color(0x779556);
    Color chessLight = new Color(0xEBECD0);
    
    private void run() {
        genTableColors();
        JFrame frame = new JFrame("Eight Queens");
        frame.setSize(pxWidth, pxHeight);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        frame.setVisible(true);
        
    }

    private void genTableColors() {
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                
                if ((i + j) % 2 == 0) {
                    colors[index] = chessLight;
                } else {
                    colors[index] = chessDark;
                }
            }
        }
    }
        
    private boolean isSecure(int row, int col) {
        boolean res = true;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (panel[i][j]) {
                    if (i == row || j == col) { res = false; }
                    if (Math.abs(i - row) == Math.abs(j - col)) { res = false; }
                }
            }
        }
        
        return res;
    }
    
    private void checkVictory() {
        int comptador = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (panel[i][j]) {
                    comptador++;
                }
            }
        }

        if (comptador == 8) {
            repaint();
            JOptionPane.showMessageDialog(this, "Enhorabona! Has col·locat les 8 reines.");
            reset();
        }
    }
    
    private void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                panel[i][j] = false;
            }
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        cellWidth = getWidth() / cols;
        cellHeight = getHeight() / rows;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(chessLight);
                } else {
                    g.setColor(chessDark);
                }
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);

                if (panel[row][col]) {
                    g.drawImage(queen, col * cellWidth, row * cellHeight, cellWidth, cellHeight, this);
                }
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / cellWidth;
        int row = e.getY() / cellHeight;
        int index = row * cols + col;

        if (row >= 0 && row < rows && col >= 0 && col < cols) {
        
            if (panel[row][col]) {
                // REQUERIMENT: Si ja hi ha una reina, s'elimina
                panel[row][col] = false;
            } else {
                // REQUERIMENT: Si no n'hi ha, comprovem si es pot posar
                if (isSecure(row, col)) {
                    panel[row][col] = true;
                    checkVictory();
                }
            }
            
            repaint();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
