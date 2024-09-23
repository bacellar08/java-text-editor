package io.github.bacellar08.java;

import io.github.bacellar08.java.component.CustomMenuBar;

import javax.swing.*;
import java.awt.*;

public class TextEditor {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Java Li");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        CustomMenuBar menuBar = new CustomMenuBar(textArea, frame);

        frame.setJMenuBar(menuBar.getMenuBar());

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(1024,768);
        frame.setVisible(true);

    }
}
