package com.ashpex.Frames;

import com.ashpex.Models.SlangHashMap;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class EditSlangFrame extends javax.swing.JFrame implements java.awt.event.ActionListener  {
    JButton btnEdit = new JButton("Edit");
    JButton btnBack = new JButton("Back");
    SlangHashMap slangHashMap;
    JTextField txtSlang;
    JTextField txtDefinition;
    JRadioButton rb1;
    JRadioButton rb2;
    JRadioButton rb3;

    EditSlangFrame() throws FileNotFoundException {

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() throws FileNotFoundException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ashpex
        slangHashMap = new SlangHashMap();
        btnEdit = new javax.swing.JButton();
        btnEdit.setFont(new Font("Arial", Font.PLAIN, 20));
        btnBack = new javax.swing.JButton();
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        txtSlang = new javax.swing.JTextField("",30);
        txtSlang.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDefinition = new javax.swing.JTextField("",10);
        txtDefinition.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDefinition.setEditable(false);
        Container container = this.getContentPane();

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this);


        btnBack.setText("Back");
        btnBack.addActionListener(this);

        rb1 = new JRadioButton("Delete one definition");
        rb1.setFont(new Font("Arial", Font.PLAIN, 20));
        rb2 = new JRadioButton("Add a definition");
        rb2.setFont(new Font("Arial", Font.PLAIN, 20));
        rb3 = new JRadioButton("Overwrite all definitions");
        rb3.setFont(new Font("Arial", Font.PLAIN, 20));

        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);

        JPanel rbPanel = new JPanel();
        rbPanel.setLayout(new GridLayout(0,3));
        rbPanel.add(rb1);
        rbPanel.add(rb2);
        rbPanel.add(rb3);


        JLabel title = new JLabel("EDIT SLANG");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setPreferredSize(new Dimension(300, 100));

        // Form
        JPanel form = new JPanel();
        JPanel slangPanel = new JPanel();
        form.setBackground(Color.WHITE);
        SpringLayout layout = new SpringLayout();
        slangPanel.setLayout(layout);
        JLabel labelSlang = new JLabel("Slang Word");
        labelSlang.setFont(new Font("Arial", Font.PLAIN, 15));
        labelSlang.setPreferredSize(new Dimension(100, 20));
        slangPanel.add(labelSlang);
        slangPanel.add(txtSlang);
        layout.putConstraint(SpringLayout.WEST, labelSlang, 10, SpringLayout.WEST, slangPanel);
        layout.putConstraint(SpringLayout.NORTH, labelSlang, 10, SpringLayout.NORTH, slangPanel);
        layout.putConstraint(SpringLayout.WEST, txtSlang, 10, SpringLayout.EAST, labelSlang);
        layout.putConstraint(SpringLayout.NORTH, txtSlang, 10, SpringLayout.NORTH, slangPanel);
        layout.putConstraint(SpringLayout.EAST, slangPanel, 10, SpringLayout.EAST, txtSlang);
        layout.putConstraint(SpringLayout.SOUTH, slangPanel, 10, SpringLayout.SOUTH, txtSlang);

        JPanel definitionPanel = new JPanel();
        SpringLayout layoutDefinition = new SpringLayout();
        definitionPanel.setLayout(layoutDefinition);
        JLabel labelDefinition = new JLabel("Definition");
        labelDefinition.setFont(new Font("Arial", Font.PLAIN, 15));
        labelDefinition.setPreferredSize(new Dimension(100, 20));
        definitionPanel.add(labelDefinition);
        definitionPanel.add(txtDefinition);
        layoutDefinition.putConstraint(SpringLayout.WEST, labelDefinition, 10, SpringLayout.WEST, definitionPanel);
        layoutDefinition.putConstraint(SpringLayout.NORTH, labelDefinition, 10, SpringLayout.NORTH, definitionPanel);
        layoutDefinition.putConstraint(SpringLayout.WEST, txtDefinition, 10, SpringLayout.EAST, labelDefinition);
        layoutDefinition.putConstraint(SpringLayout.NORTH, txtDefinition, 10, SpringLayout.NORTH, definitionPanel);
        layoutDefinition.putConstraint(SpringLayout.EAST, definitionPanel, 10, SpringLayout.EAST, txtDefinition);
        layoutDefinition.putConstraint(SpringLayout.SOUTH, definitionPanel, 10, SpringLayout.SOUTH, txtDefinition);

        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(slangPanel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(definitionPanel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(rbPanel);

        // Buttons
        JPanel buttons = new JPanel();
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEdit.setFocusable(false);
        btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(btnBack);
        buttons.add(btnEdit);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(title);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(form);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(buttons);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setTitle("Edit Slang");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            try {
                new MenuFrame();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == btnEdit) {
            String slang = txtSlang.getText();
            if(slang.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter a slang word", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(slangHashMap.containsSlang(slang)) {
                    String definition = slangHashMap.searchDefinitionBasedOnSlang(slang);
                    String newDefinition;
                    txtDefinition.setText(definition);
                    if (rb1.isSelected()) {
                        newDefinition = JOptionPane.showInputDialog(null, "Enter a definition to be deleted", "Delete Definition", JOptionPane.QUESTION_MESSAGE);
                        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the definition?", "Delete Definition", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            slangHashMap.editSlang(slang, newDefinition,1);
                            slangHashMap.save();
                        } else {
                            JOptionPane.showMessageDialog(null, "Definition not deleted", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if (rb2.isSelected()) {
                        newDefinition = JOptionPane.showInputDialog(null, "Enter a definition to be added", "Add Definition", JOptionPane.QUESTION_MESSAGE);
                        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to add this definition?", "Add Definition", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            slangHashMap.editSlang(slang, newDefinition,2);
                            slangHashMap.save();
                            JOptionPane.showMessageDialog(null, "Definition added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Definition not added", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if (rb3.isSelected()) {
                        newDefinition = JOptionPane.showInputDialog(null, "Enter a definition to be overwrite", "Overwrite Definition", JOptionPane.QUESTION_MESSAGE);
                        if(JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite the definition?", "Overwrite", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            slangHashMap.editSlang(slang, newDefinition,3);
                            slangHashMap.save();
                            JOptionPane.showMessageDialog(null, "Definition overwritten successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Definition not overwritten", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Please select an option", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    txtDefinition.setText("");
                    txtSlang.setText("");
                }
                else if(!slangHashMap.containsSlang(slang)){
                    JOptionPane.showMessageDialog(null, "Slang word not found", "Error", JOptionPane.ERROR_MESSAGE);
                    txtDefinition.setText("");
                    txtSlang.setText("");
                }
            }
        }
    }

}

