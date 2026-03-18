/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author swany
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new Calculadora()).run();
    }
    
    // Components
    private JFrame frame;
    private JTextField screen;
    private JPanel keyboard;
    private JButton[] buttons;

    // Colors
    private final Color bgDark = new Color(0x000000);
    private final Color numberBtn = new Color(0x2F2F2F);
    private final Color specialBtn = new Color(0x5A5A5A);
    private final Color operatorBtn = new Color(0xFF9200);
    
    // Calculator Variables
    boolean dotPressed = false;
    private String operator = "";
    private double numX = 0;
    private double memory = 0;
    private boolean newNumber = true;
    
    private void run() {
        setFrame();
        setScreen();
        setKeyboard();
        setLayouts();
        setListeners();
        frame.setVisible(true);
    }
    
    private void setFrame() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 750);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(bgDark);
    }

    private void setScreen() {
        screen = new JTextField("0");
        screen.setForeground(Color.WHITE);
        screen.setBackground(Color.BLACK);
        screen.setPreferredSize(new Dimension(750, 150));
        screen.setFont(new Font("Monospaced", Font.BOLD, 70));
        screen.setFocusable(false);
        screen.setEditable(false);
        screen.setBorder(null);
        screen.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void setKeyboard() {

        keyboard = new JPanel(new GridLayout(5, 4, 5, 5));
        keyboard.setBackground(Color.BLACK);
        keyboard.setPreferredSize(new Dimension(750, 550));

        String[] buttonLabels = {
                "M+", "M-", "MR", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "C", "0", ".", "="
        };

        Color[] bgColors = {
                specialBtn, specialBtn, specialBtn, operatorBtn,
                numberBtn, numberBtn, numberBtn, operatorBtn,
                numberBtn, numberBtn, numberBtn, operatorBtn,
                numberBtn, numberBtn, numberBtn, operatorBtn,
                specialBtn, numberBtn, specialBtn, operatorBtn
        };

        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = createButton(buttonLabels[i], bgColors[i]);
            keyboard.add(buttons[i]);
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Monospaced", Font.BOLD, 40));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
 
    private void setLayouts() {
        frame.add(screen, BorderLayout.NORTH);
        frame.add(keyboard, BorderLayout.CENTER);
    }
    
    private void setListeners() {
        for (JButton btn : buttons) {
            btn.addActionListener(e -> handleKeyPress(btn.getText()));
        }
    }
    
    private void handleKeyPress(String key) {
        switch (key) {
            case "C" -> resetCalculator();
            case "+", "-", "*", "/" -> setOperator(key);
            case "=" -> calculateResult();
            case "." -> addDot();
            case "M+" -> memoryAdd();
            case "M-" -> memorySubtract();
            case "MR" -> memoryRecall();
            default -> appendNumber(key);
        }
    }
    
    private void resetCalculator() {
        screen.setText("0");
        operator = "";
        numX = 0;
        newNumber = true;
        dotPressed = false;
    }
    
    private void appendNumber(String key) {
        if (newNumber) {
            screen.setText(key);
            newNumber = false;
        } else {
            screen.setText(screen.getText() + key);
        }
    }
    
    private void setOperator(String op) {

        if (!operator.isEmpty() && !newNumber) {
            calculateResult();
        } else {
            numX = Double.parseDouble(screen.getText());
        }

        operator = op;
        newNumber = true;
        dotPressed = false;
    }

    private void addDot() {

        if (newNumber) {
            screen.setText("0.");
            newNumber = false;
            dotPressed = true;
            return;
        }
        
        if (!dotPressed) {
            screen.setText(screen.getText() + ".");
            dotPressed = true;
            newNumber = false;
        }
    }
    
    private double getScreenValue() {
        try {
            return Double.parseDouble(screen.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    // Memory Add
    private void memoryAdd() {
        memory += getScreenValue();
    }

    private void memorySubtract() {
        memory -= getScreenValue();
    }
    
    // Memory Recall
    private void memoryRecall() {
        screen.setText(String.valueOf(memory));
        newNumber = true;
        dotPressed = screen.getText().contains(".");
    }
    
    private void calculateResult() {

        if (operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(screen.getText());
        double result = 0;

        switch (operator) {
            case "+" -> result = numX + secondNumber;
            case "-" -> result = numX - secondNumber;
            case "*" -> result = numX * secondNumber;
            case "/" -> {
                if (secondNumber == 0) {
                    screen.setText("Error");
                    newNumber = true;
                    return;
                }
                result = numX / secondNumber;
            }

        }
        
        if (result == (int) result) {
            screen.setText(String.valueOf((int) result));
        } else {
            screen.setText(String.valueOf(result));
        }

        screen.setText(String.valueOf(result));
        newNumber = true;
        dotPressed = false;
        operator = "";
    }
    
}


