/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Juan
 */
public class GamePanel extends JPanel implements ActionListener {

    // Global Variables & Constants
    private TopPanel topPanel;
    private JButton restartButton;

    int SCREEN_WIDTH  = 600;
    int SCREEN_HEIGHT = 600;
    int UNIT_SIZE     = 24;
    int GAME_UNITS    = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    int DELAY         = 70;
    int appleCount    = 1;

    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int bodyParts  = 6;
    int applesEaten;

    // Multiple apples
    ArrayList<Integer> appleX = new ArrayList<>();
    ArrayList<Integer> appleY = new ArrayList<>();

    char    direction     = 'R';
    boolean running       = false;
    boolean squaresVisible = true;

    Timer  timer;
    Random random;

    // Images
    Image appleImage;

    // Fonts
    private Font pixelFont;

    // Colors
    private Color BackGroundColor1 = new Color(0xA2D14A);
    private Color BackGroundColor2 = new Color(0xAAD751);
    private Color BorderColor      = new Color(0x588A33);
    private Color SnakeHead        = new Color(0x1D49A5);
    private Color SnakeBodyStart   = new Color(0x315EC4);
    private Color SnakeBodyEnd     = new Color(0x507EF6);

    GamePanel(TopPanel topPanel) {
        random = new Random();
        this.topPanel = topPanel;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(BackGroundColor1);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setBorder(BorderFactory.createLineBorder(BorderColor, 5));

        setRestartButton();

        loadAppleImage();
        loadFonts();
        startGame();
    }

    // IMAGE ///////////////////////////////////////////////////////////////////

    private void loadAppleImage() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/Apple.png"));
        appleImage = icon.getImage().getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_SMOOTH);
    }

    // GAME LIFECYCLE //////////////////////////////////////////////////////////

    public void startGame() {
        bodyParts   = 6;
        applesEaten = 0;
        direction   = 'R';

        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        appleX.clear();
        appleY.clear();
        for (int i = 0; i < appleCount; i++) {
            spawnApple();
        }

        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void restartGame() {
        restartButton.setVisible(false);
        topPanel.setScore(0);
        if (timer != null) timer.stop();
        startGame();
        this.requestFocusInWindow();
        repaint();
    }

    // RESTART BUTTON //////////////////////////////////////////////////////////

    public void setRestartButton() {
        restartButton = new JButton();
        int btnSize = 100;

        int posX = (SCREEN_WIDTH  - btnSize) / 2;
        int posY = (SCREEN_HEIGHT - btnSize) / 2 + 15;

        restartButton.setBounds(posX, posY, btnSize, btnSize);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/Reset.png"));
            Image img = icon.getImage().getScaledInstance(btnSize, btnSize, Image.SCALE_SMOOTH);
            restartButton.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            restartButton.setText("RESTART");
        }

        restartButton.setFocusable(false);
        restartButton.setBorderPainted(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setOpaque(false);
        restartButton.setVisible(false);

        restartButton.addActionListener(e -> restartGame());
        this.add(restartButton);
    }

    public void updateRestartButtonPosition() {
        int btnSize = 100;
        int posX = (SCREEN_WIDTH  - btnSize) / 2;
        int posY = (SCREEN_HEIGHT - btnSize) / 2 + 15;
        restartButton.setBounds(posX, posY, btnSize, btnSize);
    }

    // FONTS ///////////////////////////////////////////////////////////////////

    private void loadFonts() {
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/fonts/PressStart/PressStart2P-Regular.ttf"));
        } catch (Exception e) {
            System.out.println("[!] Error while loading fonts: " + e.getMessage());
            pixelFont = new Font("Monospaced", Font.BOLD, 20);
        }
    }

    // PAINT ///////////////////////////////////////////////////////////////////

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {

            // Checkered grid
            if (squaresVisible) {
                g.setColor(BackGroundColor2);
                for (int row = 0; row < SCREEN_HEIGHT / UNIT_SIZE; row++) {
                    for (int col = 0; col < SCREEN_WIDTH / UNIT_SIZE; col++) {
                        if ((row + col) % 2 == 0) {
                            g.fillRect(col * UNIT_SIZE, row * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                        }
                    }
                }
            }

            // Apples
            for (int i = 0; i < appleX.size(); i++) {
                g.drawImage(appleImage, appleX.get(i), appleY.get(i), this);
            }

            // Snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(SnakeHead);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    float proportion = (float) i / bodyParts;
                    int red   = (int) (SnakeBodyStart.getRed()   * (1 - proportion) + SnakeBodyEnd.getRed()   * proportion);
                    int green = (int) (SnakeBodyStart.getGreen() * (1 - proportion) + SnakeBodyEnd.getGreen() * proportion);
                    int blue  = (int) (SnakeBodyStart.getBlue()  * (1 - proportion) + SnakeBodyEnd.getBlue()  * proportion);
                    g.setColor(new Color(red, green, blue));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

        } else {
            gameOver(g);
        }
    }

    // GAME LOGIC //////////////////////////////////////////////////////////////

    /**
     * Spawns one apple in a cell not occupied by the snake.
     */
    private void spawnApple() {
        int ax, ay;
        do {
            ax = random.nextInt(SCREEN_WIDTH  / UNIT_SIZE) * UNIT_SIZE;
            ay = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        } while (isOccupiedBySnake(ax, ay));

        appleX.add(ax);
        appleY.add(ay);
    }

    /**
     * Returns true if the given pixel position coincides with any snake segment.
     */
    private boolean isOccupiedBySnake(int px, int py) {
        for (int i = 0; i < bodyParts; i++) {
            if (x[i] == px && y[i] == py) return true;
        }
        return false;
    }

    public void move() {
        for (int i = bodyParts - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U' -> y[0] = y[0] - UNIT_SIZE;
            case 'D' -> y[0] = y[0] + UNIT_SIZE;
            case 'L' -> x[0] = x[0] - UNIT_SIZE;
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
    }

    public void checkApple() {
        for (int i = 0; i < appleX.size(); i++) {
            if (x[0] == appleX.get(i) && y[0] == appleY.get(i)) {
                bodyParts++;
                applesEaten++;
                topPanel.setScore(applesEaten);

                appleX.remove(i);
                appleY.remove(i);
                spawnApple();
                break;
            }
        }
    }

    public void checkCollisions() {
        // Head hits body
        for (int i = bodyParts - 1; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Head hits border
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(Color.red);
        g2d.setFont(new Font("IBM Plex Sans", Font.BOLD, 70));
        FontMetrics metrics = getFontMetrics(g2d.getFont());
        g2d.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2, (SCREEN_HEIGHT / 2) - 100);

        if (!restartButton.isVisible()) {
            restartButton.setVisible(true);
        }

        g2d.setColor(Color.white);
        g2d.setFont(new Font("IBM Plex Sans", Font.BOLD, 35));
        String scoreText = "Score: " + applesEaten;
        FontMetrics metrics2 = getFontMetrics(g2d.getFont());
        g2d.drawString(scoreText, (SCREEN_WIDTH - metrics2.stringWidth(scoreText)) / 2, (SCREEN_HEIGHT / 2) + 130);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    // KEY ADAPTER /////////////////////////////////////////////////////////////

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT  -> setDirection('R', 'L');
                case KeyEvent.VK_RIGHT -> setDirection('L', 'R');
                case KeyEvent.VK_UP    -> setDirection('D', 'U');
                case KeyEvent.VK_DOWN  -> setDirection('U', 'D');
            }
        }

        private void setDirection(char A, char B) {
            if (direction != A) {
                direction = B;
            }
        }
    }

    // SETTINGS METHODS ////////////////////////////////////////////////////////

    public void setDefaultValues() {
        SCREEN_WIDTH   = 600;
        SCREEN_HEIGHT  = 600;
        UNIT_SIZE      = 24;
        DELAY          = 70;
        appleCount     = 1;
        squaresVisible = true;
        SnakeHead      = new Color(0x1D49A5);
        SnakeBodyStart = new Color(0x315EC4);
        SnakeBodyEnd   = new Color(0x507EF6);
        loadAppleImage();
        repaint();
    }

    public void setSquaresVisible(boolean visible) {
        this.squaresVisible = visible;
        repaint();
    }

    public void setGameResolution(int w, int h, int unitSize) {
        this.SCREEN_WIDTH  = w;
        this.SCREEN_HEIGHT = h;
        this.UNIT_SIZE     = unitSize;
        this.GAME_UNITS    = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        loadAppleImage();
        updateRestartButtonPosition();
        revalidate();
        repaint();
    }

    public void setGameSpeed(int speed) {
        DELAY = switch (speed) {
            case 1  -> 150; // Slow
            case 3  -> 50;  // Fast
            default -> 70;  // Normal
        };
        if (timer != null && timer.isRunning()) {
            timer.setDelay(DELAY);
        }
    }

    public void setAppleCount(int count) {
        this.appleCount = count;
    }

    public void setSnakeColors(Color head, Color bodyStart, Color bodyEnd) {
        this.SnakeHead      = head;
        this.SnakeBodyStart = bodyStart;
        this.SnakeBodyEnd   = bodyEnd;
        repaint();
    }
}