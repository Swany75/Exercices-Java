/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notepad;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author swany
 */

public class Notepad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // TODO code application logic here
       (new Notepad()).run();
    }
    
    // Components
    private JFrame frame;
    private JTextArea editor;
    private JScrollPane scroll;
    private JMenuBar menuBar;
    private JMenu menuFitxer;
    private JMenu menuEdicio;
    private JMenuItem itemSortir;
    private JMenuItem itemCopiar;
    private JMenuItem itemAferrar;

    // New Components
    private JMenuItem itemNou;
    private JMenuItem itemObrir;
    private JMenuItem itemGuardar;
    private JMenuItem itemGuardarCom;
    
    // Variables
    private Path fitxerActual;
    
    
    private void run() {
        setFrame();
        setEditor();
        setMenu();
        setLayouts();
        setListeners();

        frame.setVisible(true);
        editor.requestFocus();
    }
    
    // METHODS /////////////////////////////////////////////////////////////////
 
        private void setFrame() {
        frame = new JFrame("Notepad");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
    }

    private void setEditor() {
        editor = new JTextArea();
        editor.setLineWrap(true);
        editor.setWrapStyleWord(true);

        scroll = new JScrollPane(editor);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void setMenu() {
        menuBar = new JMenuBar();

        // Menús
        menuFitxer = new JMenu("Fitxer");
        menuEdicio = new JMenu("Edició");

        // Items Fitxer
        itemNou = new JMenuItem("Nou Fitxer");
        itemObrir = new JMenuItem("Obrir Fitxer");
        itemGuardar = new JMenuItem("Guardar Fitxer");
        itemGuardarCom = new JMenuItem("Guardar Fitxer Com...");
        itemSortir = new JMenuItem("Sortir");

        menuFitxer.add(itemNou);
        menuFitxer.add(itemObrir);
        menuFitxer.add(itemGuardar);
        menuFitxer.add(itemGuardarCom);
        menuFitxer.addSeparator();
        menuFitxer.add(itemSortir);

        // Items Edicio
        itemCopiar = new JMenuItem("Copiar");
        itemAferrar = new JMenuItem("Aferrar");
        menuEdicio.add(itemCopiar);
        menuEdicio.add(itemAferrar);

        // Añadir menús a la barra
        menuBar.add(menuFitxer);
        menuBar.add(menuEdicio);

        frame.setJMenuBar(menuBar);
    }

    private void setLayouts() {
        frame.add(scroll, BorderLayout.CENTER);
    }
 
    // LISTENERS ///////////////////////////////////////////////////////////////
    
    private void setListeners() {

        // Files
        itemSortir.addActionListener(e -> System.exit(0));
        itemNou.addActionListener(e -> nouFitxer());
        itemObrir.addActionListener(e -> obrirFitxer());
        itemGuardar.addActionListener(e -> guardarFitxer());
        itemGuardarCom.addActionListener(e -> guardarFitxerCom());

        // Edition
        itemCopiar.addActionListener(e -> editor.copy());
        itemAferrar.addActionListener(e -> editor.paste());

        // Habilitar/inhabilitar copiar según selección
        menuEdicio.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                boolean textSeleccionat = editor.getSelectionStart() != editor.getSelectionEnd();
                itemCopiar.setEnabled(textSeleccionat);
            }
            
            @Override
            public void menuDeselected(MenuEvent e) {}
            
            @Override
            public void menuCanceled(MenuEvent e) {}
        });
    }
    
    // FILES ///////////////////////////////////////////////////////////////////
    
    private void nouFitxer() {
        editor.setText("");
        fitxerActual = null;
    }

    private void obrirFitxer() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            fitxerActual = file.toPath();

            try {
                String text = Files.readString(fitxerActual);
                editor.setText(text);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error al leer el archivo");
            }
        }
    }

    private void guardarFitxer() {
        if (fitxerActual == null) {
            guardarFitxerCom();
            return;
        }

        try {
            Files.write(fitxerActual, editor.getText().getBytes());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar el archivo");
        }
    }

    private void guardarFitxerCom() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            fitxerActual = file.toPath();

            try {
                Files.write(fitxerActual, editor.getText().getBytes());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error al guardar el archivo");
            }
        }
    }
}
