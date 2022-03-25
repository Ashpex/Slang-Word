package com.ashpex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class RandomSlangFrame extends javax.swing.JFrame implements ActionListener {
    JButton btnBack;
    JButton btnRandom;
    ListSlang listSlang = new ListSlang();
    JLabel lblSlang;
    JLabel lblDefinition;
    JLabel lblTitle;
    RandomSlangFrame() throws FileNotFoundException {
        initComponents();
    }

    private void initComponents() {
        Container container = getContentPane();
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Random Slang");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(title);
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setMaximumSize(new Dimension(500, 200));

        JPanel randomPanel = new JPanel();
        lblSlang = new JLabel("Slang Word:");
        lblSlang.setFont(new Font("Arial", Font.BOLD, 30));
        randomPanel.add(lblSlang);
        lblDefinition = new JLabel("Definition:");
        lblDefinition.setFont(new Font("Arial", Font.BOLD, 30));

        randomPanel.add(lblDefinition);
        randomPanel.setBackground(Color.WHITE);

        btnRandom = new JButton("Random");
        btnBack = new JButton("Back");
        btnRandom.setFont(new Font("Arial", Font.PLAIN, 20));
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        btnRandom.addActionListener(this);
        btnBack.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(btnRandom);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnBack);

        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.add(titlePanel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(randomPanel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(buttonPanel);

        this.setTitle("Random Slang");
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btnRandom) {
            SlangWord slangWord = listSlang.RandomSlang();
            lblSlang.setText(slangWord.getSlang()+ ":\n");
            lblDefinition.setText(slangWord.getDefinition());
        }
        if (evt.getSource() == btnBack) {
            this.dispose();
            try {
                new MenuFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
