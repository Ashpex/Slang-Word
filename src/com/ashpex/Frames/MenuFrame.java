package com.ashpex.Frames;

import com.ashpex.Models.SlangHashMap;
import com.ashpex.Models.SlangWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class MenuFrame extends JFrame implements ActionListener{
    private static final String[] menuItems = {"File", "Edit", "View", "Help"};
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    SlangWord slangWord;
    SlangHashMap slangHashMapWord;

    public MenuFrame() throws FileNotFoundException {
        slangHashMapWord = new SlangHashMap();
        JLabel label = new JLabel("SLANG WORDS");
        label.setForeground(java.awt.Color.BLUE);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 25));
        label.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        b1 = new JButton("1. Show Slang Words List");
        b1.addActionListener(this);
        b1.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b1.setFocusable(false);

        b2 = new JButton("2. Find Slang Word");
        b2.addActionListener(this);
        b2.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b2.setFocusable(false);

        b3 = new JButton("3. Add Slang Word");
        b3.addActionListener(this);
        b3.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b3.setFocusable(false);

        b4 = new JButton("4. Edit Slang Word");
        b4.addActionListener(this);
        b4.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b4.setFocusable(false);

        b5 = new JButton("5. Random Slang Word");
        b5.addActionListener(this);
        b5.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b5.setFocusable(false);

        b6 = new JButton("6. Delete Slang Word");
        b6.addActionListener(this);
        b6.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b6.setFocusable(false);

        b7 = new JButton("7. Reset Slang Word");
        b7.addActionListener(this);
        b7.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b7.setFocusable(false);

        b8 = new JButton("8. Show History");
        b8.addActionListener(this);
        b8.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b8.setFocusable(false);

        b9 = new JButton("9. Quiz");
        b9.addActionListener(this);
        b9.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 17));
        b9.setFocusable(false);



        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3,10,10));
        panel.add(label);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);

        Dimension dim = new Dimension(600, 500);
        panel.setPreferredSize(dim);
        panel.setMaximumSize(dim);
        panel.setMinimumSize(dim);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPane.add(label);
        contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPane.add(panel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words");
        this.setVisible(true);
        this.setSize(800, 700);
        Dimension dim2 = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim2.width/2-this.getSize().width/2, dim2.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == b1) {
            this.dispose();
            try{
                new ListFrame();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        if (e.getSource() == b2) {
            this.dispose();
            try{
                new FindSlangFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b3) {
            this.dispose();
            try{
                new AddSlangFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b4) {
            this.dispose();
            try{
                new EditSlangFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b5) {
            this.dispose();
            try{
                new RandomSlangFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b6) {
            this.dispose();
            try{
                new DeleteSlangFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b7) {
            try{
                int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the database?", "Reset Database", JOptionPane.YES_NO_OPTION);
                if (options == JOptionPane.YES_OPTION) {
                    try {
                        slangHashMapWord.Reset();
                        JOptionPane.showMessageDialog(null, "Database has been reset");
                        slangHashMapWord.save();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Database has not been reset");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b8) {
            this.dispose();
            try{
                new HistoryFrame();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == b9) {
            this.dispose();
            try{
                new QuizFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
