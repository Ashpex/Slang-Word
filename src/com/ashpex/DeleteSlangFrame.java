package com.ashpex;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class DeleteSlangFrame extends javax.swing.JFrame implements java.awt.event.ActionListener  {
    JButton btnDelete = new JButton("Delete");
    JButton btnBack = new JButton("Back");
    ListSlang listSlang;
    JTextField txtSlang;
    JTextField txtDefinition;

    DeleteSlangFrame() throws FileNotFoundException {

        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() throws FileNotFoundException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ashpex
        listSlang = new ListSlang();
        btnDelete = new javax.swing.JButton();
        btnDelete.setFont(new Font("Arial", Font.PLAIN, 20));
        btnBack = new javax.swing.JButton();
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        txtSlang = new javax.swing.JTextField("",30);
        txtSlang.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDefinition = new javax.swing.JTextField("",10);
        txtDefinition.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDefinition.setEditable(false);
        Container container = this.getContentPane();

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this);


        btnBack.setText("Back");
        btnBack.addActionListener(this);

        JLabel title = new JLabel("Delete Slang");
        title.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));
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

        // Buttons
        JPanel buttons = new JPanel();
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelete.setFocusable(false);
        btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(btnBack);
        buttons.add(btnDelete);

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
        this.setTitle("Delete Slang");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            new MenuFrame();
        }
        if (e.getSource() == btnDelete) {
            String slang = txtSlang.getText();
            if(slang.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter a slang word", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(listSlang.containsSlang(slang)){
                    String definition = listSlang.searchDefinitionBasedOnSlang(slang);
                    txtDefinition.setText(definition);
                    int options = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this slang?", "Delete Slang", JOptionPane.YES_NO_OPTION);
                    if(options == JOptionPane.YES_OPTION){
                        listSlang.deleteSlang(slang);
                        JOptionPane.showMessageDialog(null, "Slang deleted successfully", "Delete Slang", JOptionPane.INFORMATION_MESSAGE);
                        txtSlang.setText("");
                        txtDefinition.setText("");
                        listSlang.save();
                    } else if(options == JOptionPane.NO_OPTION){
                        txtSlang.setText("");
                        txtDefinition.setText("");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Slang not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}

