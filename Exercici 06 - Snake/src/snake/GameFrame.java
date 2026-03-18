/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Juan
 */
public class GameFrame extends JFrame{
    
    private TopPanel topPanel;
    private GamePanel gamePanel;  
    
    GameFrame() {
        
        this.setLayout(new BorderLayout());

        topPanel = new TopPanel();
        gamePanel = new GamePanel(topPanel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);

        this.setTitle("Snake");
        this.setIconImage(new ImageIcon(getClass().getResource("/img/snake.png")).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow(); 

    }
       
}