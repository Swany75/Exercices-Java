/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formiga;

/**
 *
 * @author swany
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Formiga extends JFrame implements KeyListener{
    
    /**************************************************************************/
    /* VARIABLES & CONSTANTS **************************************************/
    /**************************************************************************/
    
    int ROWS = 20, COLS = 20, MIDA = 32;;
    
    JLabel[][] board = new JLabel[ROWS][COLS];
    int[][] content = new int[ROWS][COLS];
    
    int row = 10, col = 10;
    int dir = KeyEvent.VK_RIGHT;
    
    // Cell codes
    static final int EMPTY = 0;
    static final int LEAF = 1;
    static final int FORBIDDEN = 2;
    static final int ANT = 3;
    
    // Imatges i Icones

    ImageIcon LEAF_ICON = ImageManager.loadScaledIcon("src/images/hoja.png", MIDA, MIDA);
    ImageIcon FORBIDDEN_ICON = ImageManager.loadScaledIcon("src/images/direccionProhibida.png", MIDA, MIDA);
    ImageIcon EMPTY_ICON = ImageManager.loadScaledIcon("src/images/nada.png", MIDA, MIDA);

    ImageIcon ANT_N = ImageManager.loadScaledIcon("src/images/hormiga_n.png", MIDA, MIDA);
    ImageIcon ANT_S = ImageManager.loadScaledIcon("src/images/hormiga_s.png", MIDA, MIDA);
    ImageIcon ANT_E = ImageManager.loadScaledIcon("src/images/hormiga_e.png", MIDA, MIDA);
    ImageIcon ANT_W = ImageManager.loadScaledIcon("src/images/hormiga_o.png", MIDA, MIDA);

    ImageIcon[] ANT_ICONS = { ANT_N, ANT_S, ANT_E, ANT_W };
    
    // Classes
    Random random = new Random();
    SoundManager sound = new SoundManager();
    
    /**************************************************************************/
    /* MAIN CODE **************************************************************/
    /**************************************************************************/
    
    public static void main(String[] args) {
        // TODO code application logic here
        (new Formiga()).run();
    }
    
    private void run() {
        setTitle("La Formiga que menja fulles");
        setLayout(new GridLayout(ROWS, COLS));
        setIconImage((randAntIcon()).getImage());
        
        content = genInitialMatrix();
        spawnAnt();
        generateBoard();
        
        addKeyListener(this);
        setFocusable(true);

        setSize(ROWS * MIDA, COLS * MIDA);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    /**************************************************************************/
    /* FUNCTIONS & METHODS ****************************************************/
    /**************************************************************************/
    
    
    private ImageIcon randAntIcon() {
        return ANT_ICONS[random.nextInt(ANT_ICONS.length)];
    }

    private int[][] genInitialMatrix() {
        
        int matrix[][] = new int[ROWS][COLS];

        // Cream una matriu amb tot FULLES
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                matrix[r][c] = LEAF;
            }
        }

        // Generam els prohibits
        int prohibits = 0;
        
        while (prohibits < 5) {
            
            int randomRow = random.nextInt(ROWS);
            int randomCol = random.nextInt(COLS);
            
            if (matrix[randomRow][randomCol] == LEAF) {
                if (randomRow != row || randomCol != col) {
                    matrix[randomRow][randomCol] = FORBIDDEN;
                    prohibits++;
                }
            }
        }
        
        return matrix;
    }

    private void generateBoard() {

        getContentPane().removeAll();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {

                JLabel cell = new JLabel();
                cell.setOpaque(true);

                switch (content[r][c]) {
                    /* Recordar important: per usar CASE -> STATIC!! */
                    case LEAF -> cell.setIcon(LEAF_ICON);
                    case FORBIDDEN -> cell.setIcon(FORBIDDEN_ICON);
                    case EMPTY -> cell.setIcon(EMPTY_ICON);
                    case ANT -> cell.setIcon(getAntIcon());

                }

                board[r][c] = cell;
                add(cell);
            }
        }

        revalidate();
        repaint();
        requestFocusInWindow();
    }
    
    private void spawnAnt() {

        row = random.nextInt(ROWS);
        col = random.nextInt(COLS);

        while (content[row][col] == FORBIDDEN) {
            row = random.nextInt(ROWS);
            col = random.nextInt(COLS);
        }

        dir = KeyEvent.VK_RIGHT;
        content[row][col] = ANT;
    }

    private void moveAnt() {

        int newRow = row;
        int newCol = col;

        switch (dir) {
            case KeyEvent.VK_UP -> newRow--;
            case KeyEvent.VK_DOWN -> newRow++;
            case KeyEvent.VK_LEFT -> newCol--;
            case KeyEvent.VK_RIGHT -> newCol++;
        }
        
        // Si arriba al limit, es "teletransporta" a l'altra part del mapa
        if (newRow < 0) newRow = ROWS - 1;
        if (newRow >= ROWS) newRow = 0;
        if (newCol < 0) newCol = COLS - 1;
        if (newCol >= COLS) newCol = 0;
        
        
        // Menja fulla
        if (content[newRow][newCol] == LEAF) {
            sound.reproduce("src/sounds/yum.wav");
        }

        // Prohibit
        if (content[newRow][newCol] == FORBIDDEN) {
            sound.reproduce("src/sounds/bomb.wav");
            return;
        }

        // Mou la formiga
        content[row][col] = EMPTY;
        row = newRow;
        col = newCol;
        content[row][col] = ANT;

        generateBoard();
        checkWin();
    }

    private void setDirection(int keyCode) {

        if (keyCode == KeyEvent.VK_UP ||
            keyCode == KeyEvent.VK_DOWN ||
            keyCode == KeyEvent.VK_LEFT ||
            keyCode == KeyEvent.VK_RIGHT) {

            dir = keyCode;
            generateBoard();
        }
    }

    
    private ImageIcon getAntIcon() {
        return switch (dir) {
            case KeyEvent.VK_UP    -> ANT_N;
            case KeyEvent.VK_DOWN  -> ANT_S;
            case KeyEvent.VK_LEFT  -> ANT_W;
            case KeyEvent.VK_RIGHT -> ANT_E;
            default -> ANT_E;
        };
    }

    
    private void checkWin() {
        
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (content[r][c] == LEAF) {
                    return;
                }
            }
        }
        
        sound.reproduce("src/sounds/perfect.wav");
        
        JLabel msg = new JLabel("GAME OVER", SwingConstants.CENTER);
        msg.setFont(new Font("JetBrains Mono", Font.BOLD, 18));
        
        JOptionPane.showMessageDialog(
            this,
            "You Win · Perfect!",
            "GAME OVER",
            JOptionPane.PLAIN_MESSAGE
        );


        dispose();
    }

    /**************************************************************************/
    /* LISTENERS **************************************************************/
    /**************************************************************************/
    
    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moveAnt();
        } else {
            setDirection(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet.");
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}