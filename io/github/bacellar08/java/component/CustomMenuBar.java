package io.github.bacellar08.java.component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CustomMenuBar {

    JMenuBar menuBar = new JMenuBar();
    JTextArea textArea;
    File currentFile;
    JFrame frame;

    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");

    JMenuItem novo = new JMenuItem("Novo");
    JMenuItem salvar = new JMenuItem("Salvar");
    JMenuItem salvarComo = new JMenuItem("Salvar Como...");
    JMenuItem abrir = new JMenuItem("Abrir");
    JMenuItem fechar = new JMenuItem("Fechar");

    public CustomMenuBar(JTextArea textArea, JFrame frame) {
        this.frame = frame;
        this.textArea = textArea;

        fileMenu.add(novo);
        fileMenu.add(salvar);
        fileMenu.add(salvarComo);
        fileMenu.add(abrir);
        fileMenu.add(fechar);

        novo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Criar novo arquivo.");
                textArea.setText("");
                textArea.setCaretPosition(0);
                currentFile = null;
            }
        });

        salvarComo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Salvar Como...");
                salvarComo();
            }
        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Salvar arquivo.");
                if(currentFile != null) {
                    salvar(currentFile);
                    return;
                }
                salvarComo();
            }
        });

        abrir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrir arquivo.");
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    currentFile = fileChooser.getSelectedFile();

                    try {

                        BufferedReader reader = new BufferedReader(new FileReader(currentFile));
                        textArea.setText("");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                        frame.setTitle("Java Li - " + currentFile.getName());
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        fechar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fechar editor de texto.");

                if (!textArea.getText().isEmpty()) {
                   int option = JOptionPane.showConfirmDialog(null, "Deseja salvar o arquivo?");

                   if (option == JOptionPane.YES_OPTION) {
                       if (currentFile != null) {
                           salvar(currentFile);

                       } else {
                           salvarComo();
                       }
                   }

                }

                frame.dispose();

            }
        });

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }


    void salvar(File file) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
                currentFile = file;
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    void salvarComo() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            if (currentFile != null && !currentFile.getName().endsWith(".txt")) {
                    currentFile = new File(currentFile.getAbsolutePath() + ".txt");
            }
            salvar(currentFile);
        }
    }
    }



