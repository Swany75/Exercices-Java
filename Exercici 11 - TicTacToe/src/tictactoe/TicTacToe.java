/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Juan
 */

public class TicTacToe extends JPanel implements MouseListener, MouseMotionListener {

    int rows = 3, cols = 3;
    int cellWidth, cellHeight;

    int pxWidth = 800;
    int pxHeight = 800;

    char[][] MATRIX = new char[rows][cols];
    char turn;

    boolean gameOver = false;

    // Global Images
    Image imgX;
    Image imgO;

    // Classes
    Random random = new Random();

    // Colors
    Color chessDark = new Color(0x779556);
    Color chessLight = new Color(0xEBECD0);

    public static void main(String[] args) {
        new TicTacToe().run();
    }

    private void run() {

        imgX = new ImageIcon("src/images/X.png").getImage();
        imgO = new ImageIcon("src/images/O.png").getImage();

        // Inicialitzar la Matriu
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                MATRIX[i][j] = '\0';

        turn = random.nextBoolean() ? 'x' : 'o';

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setIconImage(turn == 'x' ? imgX : imgO);

        frame.setSize(pxWidth, pxHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setLocationRelativeTo(null);

        this.addMouseListener(this);

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        cellWidth = getWidth() / cols;
        cellHeight = getHeight() / rows;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                g.setColor((row + col) % 2 == 0 ? chessLight : chessDark);
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);

                if (MATRIX[row][col] == 'x') {
                    g.drawImage(imgX, col * cellWidth, row * cellHeight, cellWidth, cellHeight, null);
                } else if (MATRIX[row][col] == 'o') {
                    g.drawImage(imgO, col * cellWidth, row * cellHeight, cellWidth, cellHeight, null);
                }
            }
        }
    }

    private boolean checkWin(char p) {

        boolean res = false;
        
        for (int i = 0; i < 3; i++) {
            if (MATRIX[i][0] == p && MATRIX[i][1] == p && MATRIX[i][2] == p) { 
                res = true;
            }
            if (MATRIX[0][i] == p && MATRIX[1][i] == p && MATRIX[2][i] == p) {
                res = true;
            }
        }

        if (MATRIX[0][0] == p && MATRIX[1][1] == p && MATRIX[2][2] == p) {
            res = true;
        }
        if (MATRIX[0][2] == p && MATRIX[1][1] == p && MATRIX[2][0] == p) {
            res = true;
        }

        return res;
    }

    private boolean isDraw() {
        boolean res = true;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (MATRIX[r][c] == '\0') {
                    res = false;
                }
            }
        }
        return res;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX() / cellWidth;
        int row = e.getY() / cellHeight;

        boolean outOfBounds = row < 0 || row >= rows || col < 0 || col >= cols;
        boolean cellOccupied = !outOfBounds && MATRIX[row][col] != '\0';

        if (!outOfBounds && !cellOccupied) {

            MATRIX[row][col] = turn;

            if (checkWin(turn)) {
                gameOver = true;
                repaint();
                JOptionPane.showMessageDialog(this, turn + " wins");

            } else if (isDraw()) {
                gameOver = true;
                repaint();
                JOptionPane.showMessageDialog(this, "Empate");

            } else {
                turn = (turn == 'x') ? 'o' : 'x';
                repaint();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}
