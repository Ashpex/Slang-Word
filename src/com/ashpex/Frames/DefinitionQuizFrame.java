package com.ashpex.Frames;

import com.ashpex.Models.Quiz;
import com.ashpex.Models.SlangHashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class DefinitionQuizFrame extends javax.swing.JFrame implements java.awt.event.ActionListener {
    JButton b1, b2, b3, b4, btnBack;
    JButton btnNext;
    JLabel lblScore;
    JLabel lblQuestion;
    Quiz quiz;
    int score;
    SlangHashMap slangHashMap;
    DefinitionQuizFrame() throws FileNotFoundException {
        slangHashMap = new SlangHashMap();
        quiz = new Quiz();
        quiz.setQuestions(slangHashMap,1);
        for (int i = 0; i < quiz.getQuestion().length; i++) {
            System.out.println(quiz.questions[i]);
        }
        // A Label
        lblScore = new JLabel("Score: " + score);
        lblScore.setForeground(Color.blue);
        lblScore.setFont(new Font("Arial", Font.PLAIN, 35));
        lblScore.setAlignmentX(CENTER_ALIGNMENT);
        lblScore.setAlignmentY(-100);

        lblQuestion = new JLabel( quiz.questions[0] + " has definition?");
        lblQuestion.setForeground(Color.black);
        lblQuestion.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
        lblQuestion.setAlignmentX(CENTER_ALIGNMENT);
        lblQuestion.setAlignmentY(-100);

        // Add space
        // A Grid Answers
        b1 = new JButton("A." + quiz.questions[1]);
        b1.addActionListener(this);
        b1.setFont(new Font("Arial", Font.PLAIN, 15));
        b2 = new JButton("B." + quiz.questions[2]);
        b2.addActionListener(this);
        b2.setFont(new Font("Arial", Font.PLAIN, 15));
        b3 = new JButton("C." + quiz.questions[3]);
        b3.addActionListener(this);
        b3.setFont(new Font("Arial", Font.PLAIN, 15));
        b4 = new JButton("D. " + quiz.questions[4]);
        b4.addActionListener(this);
        b4.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(2, 2, 10, 10));
        panelCenter.add(b1);
        panelCenter.add(b2);
        panelCenter.add(b3);
        panelCenter.add(b4);
        Dimension size2 = new Dimension(600, 200);
        panelCenter.setMaximumSize(size2);
        panelCenter.setPreferredSize(size2);
        panelCenter.setMinimumSize(size2);
        // Button back

        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        btnNext = new JButton("Next");
        btnNext.addActionListener(this);
        btnNext.setFont(new Font("Arial", Font.PLAIN, 20));


        JPanel buttonPane = new JPanel();
        buttonPane.add(btnBack);
        buttonPane.add(btnNext);

        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 100)));
        con.add(lblScore);
        con.add(Box.createRigidArea(new Dimension(0, 30)));
        con.add(lblQuestion);
        con.add(Box.createRigidArea(new Dimension(0, 50)));
        con.add(panelCenter);
        con.add(Box.createRigidArea(new Dimension(0, 50)));
        con.add(buttonPane);
        this.setTitle("Question Quiz");
        this.setVisible(true);
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == b1) {
            this.answer(1);
        } else if (e.getSource() == b2) {
            this.answer(2);
        } else if (e.getSource() == b3) {
            this.answer(3);
        } else if (e.getSource() == b4) {
            this.answer(4);
        } else if (e.getSource() == btnBack) {
            this.dispose();
            JOptionPane.showMessageDialog(null, "Your score is " + score);
            new QuizFrame();
        }
        else if (e.getSource() == btnNext) {
            this.nextQuestion();
        }
    }

    private void nextQuestion() {
        // TODO Auto-generated method stub
        quiz = new Quiz();
        quiz.setQuestions(slangHashMap,1);
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);
        b1.setBackground(null);
        b2.setBackground(null);
        b3.setBackground(null);
        b4.setBackground(null);
        lblQuestion.setText(quiz.questions[0] + " has definition?");
        b1.setText("A." + quiz.questions[1]);
        b2.setText("B." + quiz.questions[2]);
        b3.setText("C." + quiz.questions[3]);
        b4.setText("D. " + quiz.questions[4]);

    }

    public void answer(int ans) {
        if (quiz.checkAnswer(ans)) {
            // default title and icon
            JOptionPane.showMessageDialog(this, "Correct Answer. You have earned 10 points", "Correct", JOptionPane.INFORMATION_MESSAGE);
            score += 10;
            lblScore.setText("Score: " + score);

        } else {
            if (ans == 1)
                b1.setBackground(Color.red);
            else if (ans == 2)
                b2.setBackground(Color.red);
            else if (ans == 3)
                b3.setBackground(Color.red);
            else if (ans == 4)
                b4.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Wrong Answer", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (quiz.checkAnswer(1)) {
            b1.setBackground(Color.green);
        } else {
            b1.setEnabled(false);
        }
        if (quiz.checkAnswer(2)) {
            b2.setBackground(Color.green);
        } else {
            b2.setEnabled(false);
        }
        if (quiz.checkAnswer(3)) {
            b3.setBackground(Color.green);
        } else {
            b3.setEnabled(false);
        }
        if (quiz.checkAnswer(4)) {
            b4.setBackground(Color.green);
        } else {
            b4.setEnabled(false);
        }
    }


}
