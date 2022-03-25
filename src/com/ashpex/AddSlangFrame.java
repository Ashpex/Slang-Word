package com.ashpex;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class AddSlangFrame extends javax.swing.JFrame implements java.awt.event.ActionListener {
    ListSlang listSlang;
    JButton btnAdd;
    JButton btnBack;
    JTextField txtSlang;
    JTextField txtDefinition;
    AddSlangFrame() throws FileNotFoundException {
        initComponents();
    }

    private void initComponents() throws FileNotFoundException {
        listSlang = new ListSlang();
        btnAdd = new javax.swing.JButton();
        btnAdd.setFont(new Font("Arial", Font.PLAIN, 20));
        btnBack = new javax.swing.JButton();
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        txtSlang = new javax.swing.JTextField("",30);
        txtSlang.setFont(new Font("Arial", Font.PLAIN, 20));
        txtDefinition = new javax.swing.JTextField("",10);
        txtDefinition.setFont(new Font("Arial", Font.PLAIN, 20));
        Container container = this.getContentPane();

        btnAdd.setText("Add");
        btnAdd.addActionListener(this);


        btnBack.setText("Back");
        btnBack.addActionListener(this);

        JLabel title = new JLabel("Add Slang");
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
        btnAdd.setFocusable(false);
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(btnBack);
        buttons.add(btnAdd);

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
        this.setTitle("Add Slang");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if(evt.getSource() == btnAdd){
            String slang = txtSlang.getText();
            String definition = txtDefinition.getText();
            if(slang.equals("") || definition.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(listSlang.containsSlang(slang)){
                Object[] options = {"Overwrite", "Add Definition", "Cancel"};
                int n = JOptionPane.showOptionDialog(null, "Slang already exists. Do you want to overwrite it? Press yes to confirm, press no to add definition", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
                if(n == JOptionPane.YES_OPTION){
                    listSlang.addSlang(slang, definition, 1);
                    JOptionPane.showMessageDialog(null, "Slang overwritten");
                }else if(n == JOptionPane.NO_OPTION){
                    listSlang.addSlang(slang, definition, 0);
                    JOptionPane.showMessageDialog(null, "Definition added");
                }
            } else {
                listSlang.addSlang(slang, definition, 0);
                JOptionPane.showMessageDialog(null, "Slang added");
            }
            listSlang.save();
            txtSlang.setText("");
            txtDefinition.setText("");

        }
        else if(evt.getSource() == btnBack){
            this.dispose();
            try {
                new MenuFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
