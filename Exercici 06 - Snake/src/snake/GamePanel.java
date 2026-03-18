/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.*;
import java.awt.event.*;
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
    
    int SCREEN_WIDTH = 600;
    int SCREEN_HEIGHT = 600;
    int UNIT_SIZE = 64;
    int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    int DELAY = 100;

    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;

    char direction = 'R';
    boolean running = false;
    
    Timer timer;
    Random random;
    
    // Images
    Image appleImage;
    
    // Fonts
    private Font pixelFont;
    
    
    // Colors
    private Color BackGroundColor1 = new Color(0xA2D14A);
    private Color BackGroundColor2 = new Color(0xAAD751);
    private Color BorderColor = new Color(0x588A33);
    private Color SnakeHead = new Color(0x1D49A5);
    private Color SnakeBody = new Color(0x315EC4);
    
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
                
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/Apple.png"));
        appleImage = icon.getImage().getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_SMOOTH);
        
        loadFonts();
        startGame();

    }
    
    public void startGame() {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        
        for(int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void setRestartButton() {
        restartButton = new JButton();
        int btnSize = 100; 

        int posX = (SCREEN_WIDTH - btnSize) / 2;
        // Bajamos el botón un poco (sumamos 15 al centro) para que no choque con GAME OVER
        int posY = ((SCREEN_HEIGHT - btnSize) / 2) + 15; 

        restartButton.setBounds(posX, posY, btnSize, btnSize);

        // ... (el resto del código del try-catch se mantiene igual)
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
    
    public void restartGame() {
        restartButton.setVisible(false);
        topPanel.setScore(0);
        if(timer != null) timer.stop();
        startGame();
        this.requestFocusInWindow();
        repaint();
    }
    
    private void loadFonts() {
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/PressStart/PressStart2P-Regular.ttf"));
        } catch (Exception e) {
            System.out.println("[!] Error while loading fonts: " + e.getMessage());
            pixelFont = new Font("Monospaced", Font.BOLD, 20);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
   
    public void draw(Graphics g) {
        if(running) {
            
            // Efecte tablero de ajedrez
            g.setColor(BackGroundColor2);
            for (int row = 0; row < SCREEN_HEIGHT / UNIT_SIZE; row++) {
                for (int col = 0; col < SCREEN_WIDTH / UNIT_SIZE; col++) {
                    if ((row + col) % 2 == 0) {
                        g.fillRect(col * UNIT_SIZE, row * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }
            }

            // Dibujar Manzana
            g.drawImage(appleImage, appleX, appleY, this);

            // Dibujar Serpiente
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(SnakeHead);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    Color colorInicio = new Color(0x234EAD);
                    Color colorFin = new Color(0x507EF6);

                    float proporcion = (float) i / bodyParts;

                    int red = (int) (colorInicio.getRed() * (1 - proporcion) + colorFin.getRed() * proporcion);
                    int green = (int) (colorInicio.getGreen() * (1 - proporcion) + colorFin.getGreen() * proporcion);
                    int blue = (int) (colorInicio.getBlue() * (1 - proporcion) + colorFin.getBlue() * proporcion);

                    g.setColor(new Color(red, green, blue));

                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
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
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            topPanel.setScore(applesEaten);
            newApple();
        }
    }

    public void checkCollisions() {
        // Checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Checks if head touches the borders
        if (x[0] < 0) {
            running = false;
        }
        if (x[0] >= SCREEN_WIDTH) {
            running = false;
        }
        if (y[0] < 0) {
            running = false;
        }
        if (y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }

    }

    public void gameOver(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Texto: GAME OVER (Lo subimos un poco más: -100)
        g2d.setColor(Color.red);
        g2d.setFont(new Font("IBM Plex Sans", Font.BOLD, 70));
        FontMetrics metrics = getFontMetrics(g2d.getFont());
        g2d.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2, (SCREEN_HEIGHT / 2) - 100);

        // Mostrar el botón (que ahora está en centro + 15)
        if (!restartButton.isVisible()) {
            restartButton.setVisible(true);
        }

        // Texto: Score (Lo bajamos un poco más: +130)
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

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT ->
                    setDirection('R', 'L');
                case KeyEvent.VK_RIGHT ->
                    setDirection('L', 'R');
                case KeyEvent.VK_UP ->
                    setDirection('D', 'U');
                case KeyEvent.VK_DOWN ->
                    setDirection('U', 'D');
            }
        }

        private void setDirection(char A, char B) {
            if (direction != A) {
                direction = B;
            }
        }

    }
}
