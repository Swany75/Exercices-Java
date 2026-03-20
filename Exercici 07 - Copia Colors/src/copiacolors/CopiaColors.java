/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package copiacolors;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

/**
 *
 * @author swany
 */
public class CopiaColors extends JPanel implements MouseListener, MouseMotionListener{

    /**
     * @param args the command line arguments
     */
    
    int rows = 16;
    int cols = 16;
    int max = rows * cols;
    int cellWidth;
    int cellHeight;

    int pxWidth = 1024;
    int pxHeight = 1024;
    
    // COLORS 
    Color[] colors = new Color[max];
    Color savedColor = new Color(0x000000);
    
    public static void main(String[] args) {
        // TODO code application logic here
        (new CopiaColors()).run();
    }

    private void run() {
        genRandomColors();
        JFrame frame = new JFrame("Colors");
        frame.setSize(pxWidth, pxHeight);        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        frame.setVisible(true);
        
    }
    
    private void genRandomColors() {
        
        Random rand = new Random();
        
        for (int i = 0; i < max; i++) {
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);

            colors[i] = new Color(r, g, b);
        }
        
    }
    
    private void paintCell(MouseEvent e) {
        int col = e.getX() / cellWidth;
        int row = e.getY() / cellHeight;
        int index = row * cols + col;

        if (index < 0 || index >= colors.length) return;

        colors[index] = savedColor;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        cellWidth = getWidth() / cols;
        cellHeight = getHeight() / rows;
        
        int index = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                g.setColor(colors[index]);
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
                index++;
            }
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int col = e.getX() / cellWidth;
        int row = e.getY() / cellHeight;
        int index = row * cols + col;

        if (index < 0 || index >= colors.length) return;

        if (SwingUtilities.isRightMouseButton(e)) {
            savedColor = colors[index];
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            colors[index] = savedColor;
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
        if (SwingUtilities.isLeftMouseButton(e)) {
            paintCell(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
