package com.ashpex;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class QuizFrame extends javax.swing.JFrame implements java.awt.event.ActionListener {

    JButton btnSlang;
    JButton btnDefinition;
    JButton btnBack;
    QuizFrame() {
        initComponents();

    }

    private void initComponents() {
        JLabel lblQuiz = new JLabel();
        btnSlang = new JButton();
        btnDefinition = new JButton();
        btnBack = new JButton();
        btnSlang.setFont(new java.awt.Font("Arial", 0, 20));
        btnDefinition.setFont(new java.awt.Font("Arial", 0, 20));
        btnDefinition.setText("1. Find Definition");
        btnSlang.addActionListener(this);
        btnDefinition.addActionListener(this);
        btnBack.addActionListener(this);
        btnBack.setText("Back");
        btnBack.setFont(new java.awt.Font("Arial", 0, 20));
        btnSlang.setText("2. Find Slang");
        lblQuiz.setFont(new java.awt.Font("Arial", 0, 20));
        lblQuiz.setText("Quiz");
        lblQuiz.setFont(new java.awt.Font("Arial", 0, 30));
        lblQuiz.setAlignmentX(CENTER_ALIGNMENT);
        lblQuiz.setAlignmentY(-100);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quiz");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2,10,10));
        panel.add(btnDefinition);
        panel.add(btnSlang);
        Dimension dimension = new Dimension(500,200);
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);

        JPanel panel2 = new JPanel();
        panel2.add(btnBack);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createRigidArea(new Dimension(0,100)));
        contentPane.add(lblQuiz);
        contentPane.add(Box.createRigidArea(new Dimension(0,100)));
        contentPane.add(panel);
        contentPane.add(Box.createRigidArea(new Dimension(0,100)));
        contentPane.add(panel2);

        this.setVisible(true);
        this.setSize(800,700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


    }

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if(evt.getSource() == btnSlang) {
            this.dispose();
            try {
                new SlangQuizFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(evt.getSource() == btnDefinition) {
            this.dispose();
            try {
                new DefinitionQuizFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(evt.getSource() == btnBack) {
            this.dispose();
            try {
                new MenuFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
