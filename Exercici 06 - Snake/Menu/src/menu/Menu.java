/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menu;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author swany
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new Menu()).run();
    }
    
    private JFrame frame;
    private TopPanel topPanel;    
    
    private JPanel gamePanel;
    
    private void run() {
        setFrame();
        topPanel = new TopPanel();
        setGamePanel();
        
        setLayouts();
        frame.setVisible(true);
    }
    
    private void setFrame() {
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
    }
    
    private void setGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setForeground(Color.RED);
        gamePanel.setBackground(new Color(0x578A34));
    }
    
    private void setLayouts() {
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);
    }
    
}
