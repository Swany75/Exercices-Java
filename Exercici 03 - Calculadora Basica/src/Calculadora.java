/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author swany
 */

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {

    private JTextField txtValor1;
    private JTextField txtValor2;
    private JLabel lblResultado;
    private JLabel lblError;

    public Calculadora() {
        setTitle("Calculadora Bàsica");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2, 5, 5));

        // Campos
        txtValor1 = new JTextField();
        txtValor2 = new JTextField();

        add(new JLabel("Valor 1:"));
        add(txtValor1);
        add(new JLabel("Valor 2:"));
        add(txtValor2);

        // Botones
        JButton btnSuma = new JButton("+");
        JButton btnResta = new JButton("-");
        JButton btnMultiplica = new JButton("*");
        JButton btnDivide = new JButton("/");

        add(btnSuma);
        add(btnResta);
        add(btnMultiplica);
        add(btnDivide);

        // Resultado y error
        lblResultado = new JLabel("", JLabel.CENTER);
        lblResultado.setForeground(Color.GREEN);

        lblError = new JLabel("", JLabel.CENTER);
        lblError.setForeground(Color.RED);

        add(lblResultado);
        add(lblError);

        // Eventos
        btnSuma.addActionListener(e -> calcular("+"));
        btnResta.addActionListener(e -> calcular("-"));
        btnMultiplica.addActionListener(e -> calcular("*"));
        btnDivide.addActionListener(e -> calcular("/"));
    }

    private void calcular(String operacion) {
        lblError.setText("");
        lblResultado.setText("");

        try {
            double v1 = Double.parseDouble(txtValor1.getText());
            double v2 = Double.parseDouble(txtValor2.getText());
            double resultado = 0;

            switch (operacion) {
                case "+":
                    resultado = v1 + v2;
                    break;
                case "-":
                    resultado = v1 - v2;
                    break;
                case "*":
                    resultado = v1 * v2;
                    break;
                case "/":
                    if (v2 == 0) {
                        lblError.setText("No es pot dividir per zero.");
                        return;
                    }
                    resultado = v1 / v2;
                    break;
            }

            lblResultado.setText("Resultat: " + resultado);

        } catch (NumberFormatException ex) {
            lblError.setText("Introdueix nombres vàlids.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }
}