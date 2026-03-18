/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author swany
 */

public class TopPanel extends JPanel {

    private JTextField screen;
    private JButton settingsButton;
    private Settings settingsWindow;
    private JLabel appleLabel;
    
    private Color topPanelBG = new Color(0x4A752C);
    
    public TopPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(750, 50));

        initComponents();
    }

    private void initComponents() {
        setMenuButton();
        setScreen();
        setAppleImage();
        addComponents();
    }
    
    private void setMenuButton() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/metalGear.png"));

        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        settingsButton = new JButton(icon);
        settingsButton.setPreferredSize(new Dimension(50, 50));
        settingsButton.setFocusPainted(false);
        settingsButton.setBorder(null);
        settingsButton.setBackground(topPanelBG);
        
        // Listeners
        settingsButton.addActionListener(e -> openSettings());
    }

    private void setScreen() {
        screen = new JTextField("0");
        screen.setForeground(Color.WHITE);
        screen.setBackground(topPanelBG);
        screen.setFont(new Font("Monospaced", Font.BOLD, 30));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setBorder(null);
        screen.setEditable(false);
        screen.setFocusable(false);
    }
    
    private void setAppleImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("img/Apple.png"));
        Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        appleLabel = new JLabel(icon);
        appleLabel.setPreferredSize(new Dimension(50, 50));
    }
    
    private void addComponents() {
        add(settingsButton, BorderLayout.WEST);
        add(screen, BorderLayout.CENTER);
        add(appleLabel, BorderLayout.EAST);
    }
    
    public void setScore(int score) {
        screen.setText(String.valueOf(score));
    }
    
    private void openSettings() {
        if (settingsWindow == null || !settingsWindow.isDisplayable()) {
            settingsWindow = new Settings();
        } else {
            settingsWindow.toFront();
        }
    }
}