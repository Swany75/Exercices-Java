/*.
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

    // Colors
    private Color BackGroundColor1 = new Color(0xA2D14A);
    private Color BackGroundColor2 = new Color(0xAAD751);
    private Color BorderColor = new Color(0x588A33);
    private Color SnakeHead = new Color(0x1D49A5);
    private Color SnakeBody = new Color(0x315EC4);
    
    // Fonts
    
    private Font mainFont = new Font("IBM Plex Sans", Font.BOLD, 12);
    
    public Settings() {
        this.setTitle("Settings");
        this.setIconImage(new ImageIcon(getClass().getResource("/img/metalGear.png")).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(setMainPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private JPanel setMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BackGroundColor1);
        
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BorderColor, 5),
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        
        mainPanel.add(setTopPanel(), BorderLayout.NORTH);
        mainPanel.add(setCenterSeparator(), BorderLayout.CENTER);
        mainPanel.add(setBottomPanel(), BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    // TOP PANEL ///////////////////////////////////////////////////////////////
    
    private JPanel setTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1000, 450));
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.setBackground(BackGroundColor1);
        
        topPanel.add(setLeftPanel());
        topPanel.add(setRightPanel());
        
        return topPanel;
    }
    
    private JPanel setLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(350, 350));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.MAGENTA);
        return leftPanel;
    }
    
    private JPanel setRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(350, 350));
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.ORANGE);
        return rightPanel;
    }
    
    // CENTER PANEL ////////////////////////////////////////////////////////////
    
    private JSeparator setCenterSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBackground(BackGroundColor1);
        separator.setPreferredSize(new Dimension(1000, 10));
        return separator;
    }
    
    // BOTTOM PANEL ////////////////////////////////////////////////////////////
    
    private JPanel setBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 100));
        bottomPanel.setLayout(new GridLayout(2,1));
        bottomPanel.setBackground(BackGroundColor1);
        
        bottomPanel.add(setButtonsPanel());
        bottomPanel.add(setScreenPanel());
        
        return bottomPanel;
    }
    
    private JPanel setButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3, 20, 0)); 
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        buttonsPanel.add(btnCancel());
        buttonsPanel.add(btnDefault());
        buttonsPanel.add(btnSave());

        return buttonsPanel;
    }
    
    private JButton btnCancel() {
        JButton btn = new JButton("Cancel Settings");
        btn.setFont(mainFont);
        return btn;
    }
    
    private JButton btnDefault() {
        JButton btn = new JButton("Restore Defaults");
        btn.setFont(mainFont);
        return btn;
    }
    
    private JButton btnSave() {
        JButton btn = new JButton("Save Changes");
        btn.setFont(mainFont);
        return btn;
    }
    
    private JPanel setScreenPanel() {
        JPanel screenPanel = new JPanel();
        screenPanel.setPreferredSize(new Dimension(700, 50));
        screenPanel.setLayout(new BorderLayout());
        screenPanel.setBackground(Color.CYAN);
        return screenPanel;
    }
    
}
