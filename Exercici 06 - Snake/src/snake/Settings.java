/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author swany
 */
public class Settings extends JFrame {

    public Settings() {
        setTitle("Settings");
        this.setIconImage(new ImageIcon(getClass().getResource("/img/metalGear.png")).getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        setVisible(true);
    }
}
