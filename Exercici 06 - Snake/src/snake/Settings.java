/*.
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Juan
 */

public class Settings extends JFrame {

    private JLabel statusLabel;

    // Controls
    private JComboBox<String> speedCombo;
    private JComboBox<String> gridSizeCombo;
    private JCheckBox squaresCheckbox;
    private JSpinner appleSpinner;
    private JCheckBox randomColorsCheckbox;
    private JButton snakeHeadColorBtn;
    private JButton snakeBodyStartBtn;
    private JButton snakeBodyEndBtn;

    // Working color copies
    private Color selectedHead;
    private Color selectedBodyStart;
    private Color selectedBodyEnd;

    // UI Colors
    private Color BackGroundColor1 = new Color(0xA2D14A);
    private Color BackGroundColor2 = new Color(0xAAD751);
    private Color BorderColor      = new Color(0x588A33);
    private Color SnakeHead        = new Color(0x1D49A5);
    private Color SnakeBody        = new Color(0x315EC4);

    private Font mainFont = new Font("IBM Plex Sans", Font.BOLD, 12);

    private GamePanel     gamePanel;
    private SettingsState state;

    public Settings(GamePanel gamePanel, SettingsState state) {
        this.gamePanel = gamePanel;
        this.state     = state;

        this.selectedHead      = state.headColor;
        this.selectedBodyStart = state.bodyStartColor;
        this.selectedBodyEnd   = state.bodyEndColor;

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
        topPanel.setPreferredSize(new Dimension(600, 250));
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.setBackground(BackGroundColor1);
        topPanel.add(setLeftPanel());
        topPanel.add(setRightPanel());
        return topPanel;
    }

    private JPanel setLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(BackGroundColor2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 10, 5);

        JLabel titleLabel = new JLabel("Game Settings");
        titleLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(titleLabel, gbc);

        // Speed
        gbc.gridy++; gbc.gridwidth = 1;
        JLabel speedLabel = new JLabel("Speed:");
        speedLabel.setForeground(Color.WHITE);
        speedLabel.setFont(mainFont);
        leftPanel.add(speedLabel, gbc);

        gbc.gridx = 1;
        speedCombo = new JComboBox<>(new String[]{"Slow", "Normal", "Fast"});
        speedCombo.setSelectedIndex(state.speed - 1);
        speedCombo.setBackground(SnakeBody);
        speedCombo.setForeground(Color.WHITE);
        leftPanel.add(speedCombo, gbc);

        // Grid Size
        gbc.gridx = 0; gbc.gridy++;
        JLabel gridLabel = new JLabel("Grid Size:");
        gridLabel.setForeground(Color.WHITE);
        gridLabel.setFont(mainFont);
        leftPanel.add(gridLabel, gbc);

        gbc.gridx = 1;
        gridSizeCombo = new JComboBox<>(new String[]{"Small", "Normal", "Large)"});
        gridSizeCombo.setSelectedIndex(state.gridSizeIndex);
        gridSizeCombo.setBackground(SnakeBody);
        gridSizeCombo.setForeground(Color.WHITE);
        leftPanel.add(gridSizeCombo, gbc);

        // Squares visible
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        squaresCheckbox = new JCheckBox("Show Grid Squares");
        squaresCheckbox.setSelected(state.squaresVisible);
        squaresCheckbox.setBackground(BackGroundColor2);
        squaresCheckbox.setForeground(Color.WHITE);
        squaresCheckbox.setFont(mainFont);
        leftPanel.add(squaresCheckbox, gbc);

        // Apple count
        gbc.gridy++; gbc.gridwidth = 1;
        JLabel appleLabel = new JLabel("Apples to Spawn:");
        appleLabel.setForeground(Color.WHITE);
        appleLabel.setFont(mainFont);
        leftPanel.add(appleLabel, gbc);

        gbc.gridx = 1;
        appleSpinner = new JSpinner(new SpinnerNumberModel(state.appleCount, 1, 10, 1));
        leftPanel.add(appleSpinner, gbc);

        return leftPanel;
    }

    private JPanel setRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(BackGroundColor2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 10, 5);

        JLabel titleLabel = new JLabel("Color Settings");
        titleLabel.setFont(new Font("IBM Plex Sans", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(titleLabel, gbc);

        // Random colors
        gbc.gridy++;
        randomColorsCheckbox = new JCheckBox("Random Snake Colors");
        randomColorsCheckbox.setSelected(state.randomColors);
        randomColorsCheckbox.setBackground(BackGroundColor2);
        randomColorsCheckbox.setForeground(Color.WHITE);
        randomColorsCheckbox.setFont(mainFont);
        randomColorsCheckbox.addActionListener(e -> toggleColorButtons());
        rightPanel.add(randomColorsCheckbox, gbc);

        // Head color
        gbc.gridy++; gbc.gridwidth = 1;
        JLabel headLabel = new JLabel("Snake Head:");
        headLabel.setForeground(Color.WHITE);
        headLabel.setFont(mainFont);
        rightPanel.add(headLabel, gbc);

        gbc.gridx = 1;
        snakeHeadColorBtn = new JButton("Pick Color");
        snakeHeadColorBtn.setBackground(selectedHead);
        snakeHeadColorBtn.setForeground(Color.WHITE);
        snakeHeadColorBtn.addActionListener(e -> pickColor("head"));
        rightPanel.add(snakeHeadColorBtn, gbc);

        // Body start
        gbc.gridx = 0; gbc.gridy++;
        JLabel bodyStartLabel = new JLabel("Body Start:");
        bodyStartLabel.setForeground(Color.WHITE);
        bodyStartLabel.setFont(mainFont);
        rightPanel.add(bodyStartLabel, gbc);

        gbc.gridx = 1;
        snakeBodyStartBtn = new JButton("Pick Color");
        snakeBodyStartBtn.setBackground(selectedBodyStart);
        snakeBodyStartBtn.setForeground(Color.WHITE);
        snakeBodyStartBtn.addActionListener(e -> pickColor("bodyStart"));
        rightPanel.add(snakeBodyStartBtn, gbc);

        // Body end
        gbc.gridx = 0; gbc.gridy++;
        JLabel bodyEndLabel = new JLabel("Body End:");
        bodyEndLabel.setForeground(Color.WHITE);
        bodyEndLabel.setFont(mainFont);
        rightPanel.add(bodyEndLabel, gbc);

        gbc.gridx = 1;
        snakeBodyEndBtn = new JButton("Pick Color");
        snakeBodyEndBtn.setBackground(selectedBodyEnd);
        snakeBodyEndBtn.setForeground(Color.WHITE);
        snakeBodyEndBtn.addActionListener(e -> pickColor("bodyEnd"));
        rightPanel.add(snakeBodyEndBtn, gbc);

        toggleColorButtons();
        return rightPanel;
    }

    // CENTER SEPARATOR ////////////////////////////////////////////////////////

    private JSeparator setCenterSeparator() {
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setForeground(BackGroundColor1);
        sep.setBackground(BackGroundColor1);
        sep.setPreferredSize(new Dimension(600, 10));
        return sep;
    }

    // BOTTOM PANEL ////////////////////////////////////////////////////////////

    private JPanel setBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(600, 100));
        bottomPanel.setLayout(new GridLayout(2, 1));
        bottomPanel.setBackground(BackGroundColor1);
        bottomPanel.add(setScreenPanel());
        bottomPanel.add(setButtonsPanel());
        return bottomPanel;
    }

    private JPanel setScreenPanel() {
        JPanel screenPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight(), arc = 20;
                g2.setColor(BorderColor);
                g2.fillRoundRect(0, 0, w, h, arc, arc);
                g2.setColor(BackGroundColor1.darker());
                g2.fillRoundRect(4, 4, w - 8, h - 8, arc - 5, arc - 5);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        screenPanel.setOpaque(false);
        screenPanel.setPreferredSize(new Dimension(700, 60));
        screenPanel.setLayout(new GridBagLayout());

        statusLabel = new JLabel("Adjust settings and press Save Changes.");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(mainFont);
        screenPanel.add(statusLabel);
        return screenPanel;
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
        styleButton(btn);
        btn.addActionListener(e -> this.dispose());
        return btn;
    }

    private JButton btnDefault() {
        JButton btn = new JButton("Restore Defaults");
        styleButton(btn);
        btn.addActionListener(e -> restoreDefaults());
        return btn;
    }

    private JButton btnSave() {
        JButton btn = new JButton("Save Changes");
        styleButton(btn);
        btn.addActionListener(e -> applySettings());
        return btn;
    }

    private void styleButton(JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(mainFont);
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = c.getWidth(), h = c.getHeight(), arc = 20;
                g2.setColor(SnakeBody);
                g2.fillRoundRect(0, 0, w - 1, h - 1, arc, arc);
                g2.setColor(SnakeHead);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(1, 1, w - 3, h - 3, arc, arc);
                super.paint(g2, c);
                g2.dispose();
            }
        });
    }

    // LOGIC ///////////////////////////////////////////////////////////////////

    private void applySettings() {
        state.speed          = speedCombo.getSelectedIndex() + 1;
        state.gridSizeIndex  = gridSizeCombo.getSelectedIndex();
        state.squaresVisible = squaresCheckbox.isSelected();
        state.appleCount     = (int) appleSpinner.getValue();
        state.randomColors   = randomColorsCheckbox.isSelected();

        if (!state.randomColors) {
            state.headColor      = selectedHead;
            state.bodyStartColor = selectedBodyStart;
            state.bodyEndColor   = selectedBodyEnd;
        } else {
            Random rnd = new Random();
            state.headColor      = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            state.bodyStartColor = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            state.bodyEndColor   = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }

        int unitSize = switch (state.gridSizeIndex) {
            case 0  -> 30;
            case 2  -> 18;
            default -> 24;
        };

        gamePanel.setGameSpeed(state.speed);
        gamePanel.setGameResolution(600, 600, unitSize);
        gamePanel.setSquaresVisible(state.squaresVisible);
        gamePanel.setAppleCount(state.appleCount);
        gamePanel.setSnakeColors(state.headColor, state.bodyStartColor, state.bodyEndColor);

        this.dispose();
    }

    private void restoreDefaults() {
        state.speed          = 2;
        state.gridSizeIndex  = 1;
        state.squaresVisible = true;
        state.appleCount     = 1;
        state.randomColors   = false;
        state.headColor      = new Color(0x1D49A5);
        state.bodyStartColor = new Color(0x315EC4);
        state.bodyEndColor   = new Color(0x507EF6);

        selectedHead      = state.headColor;
        selectedBodyStart = state.bodyStartColor;
        selectedBodyEnd   = state.bodyEndColor;

        speedCombo.setSelectedIndex(1);
        gridSizeCombo.setSelectedIndex(1);
        squaresCheckbox.setSelected(true);
        appleSpinner.setValue(1);
        randomColorsCheckbox.setSelected(false);
        snakeHeadColorBtn.setBackground(selectedHead);
        snakeBodyStartBtn.setBackground(selectedBodyStart);
        snakeBodyEndBtn.setBackground(selectedBodyEnd);
        toggleColorButtons();

        gamePanel.setDefaultValues();
        statusLabel.setText("Defaults restored. Press Save Changes to apply.");
    }

    private void toggleColorButtons() {
        boolean random = randomColorsCheckbox.isSelected();
        snakeHeadColorBtn.setEnabled(!random);
        snakeBodyStartBtn.setEnabled(!random);
        snakeBodyEndBtn.setEnabled(!random);
    }

    private void pickColor(String target) {
        Color initial = switch (target) {
            case "head"      -> selectedHead;
            case "bodyStart" -> selectedBodyStart;
            case "bodyEnd"   -> selectedBodyEnd;
            default          -> Color.WHITE;
        };
        Color chosen = JColorChooser.showDialog(this, "Pick a color", initial);
        if (chosen == null) return;
        switch (target) {
            case "head"      -> { selectedHead = chosen;      snakeHeadColorBtn.setBackground(chosen); }
            case "bodyStart" -> { selectedBodyStart = chosen; snakeBodyStartBtn.setBackground(chosen); }
            case "bodyEnd"   -> { selectedBodyEnd = chosen;   snakeBodyEndBtn.setBackground(chosen);   }
        }
    }
}
