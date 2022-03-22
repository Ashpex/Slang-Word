package com.ashpex;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FindSlangFrame extends JFrame implements ActionListener, TableModelListener {
    JButton btnBack, btnFind;
    JTextField txtFind;
    JTable jTable;
    JLabel titleLabel;
    DefaultTableModel model;
    ListSlang listSlang;
    String[][] result;

    final JOptionPane optionPane = new JOptionPane("The only way to close this dialog is by\n"
            + "pressing one of the following buttons.\n" + "Do you understand?", JOptionPane.QUESTION_MESSAGE,
            JOptionPane.YES_NO_OPTION);
    String data[][] = { { "", "", "" } };

    FindSlangFrame(){
        Container container = this.getContentPane();

        //set title
        JLabel title = new JLabel("Find Slang");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Result label
        JLabel lblFind = new JLabel("Enter the word you want to find");
        lblFind.setFont(new Font("Arial", Font.BOLD, 15));
        lblFind.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFind.setForeground(Color.BLACK);

        // Text field
        JPanel form = new JPanel();
        JLabel formLabel = new JLabel("Input the word you want to find");
        txtFind = new JTextField();
        btnFind = new JButton("Find");
        btnBack = new JButton("Back");
        btnFind.addActionListener(this);
        btnFind.setMnemonic(KeyEvent.VK_ENTER);
        form.setLayout(new BorderLayout(10,10));
        form.add(formLabel, BorderLayout.LINE_START);
        form.add(txtFind, BorderLayout.CENTER);
        form.add(btnFind, BorderLayout.LINE_END);
        Dimension dim = new Dimension(800, 50);
        form.setPreferredSize(dim);
        form.setMaximumSize(dim);
        form.setMinimumSize(dim);

        // Table
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);

        String columnNames[] = { "ID", "Slang", "Definition" };
        jTable = new JTable(new DefaultTableModel(columnNames, 0));
        jTable.setRowHeight(30);
        model = (DefaultTableModel) jTable.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable.getModel().addTableModelListener(this);
        JScrollPane scrollPane = new JScrollPane(jTable);

        panel.setLayout(new GridLayout(1, 1));
        panel.add(scrollPane);

        // Bottom panel
        JPanel bottom = new JPanel();
        btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottom.add(btnBack);

        // Add to container
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(title);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(lblFind);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(form);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(panel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(bottom);
        container.add(Box.createRigidArea(new Dimension(0, 10)));

        this.setTitle("Find Slang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        Dimension dim2 = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim2.width/2-this.getSize().width/2, dim2.height/2-this.getSize().height/2);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnFind){
            String word = txtFind.getText();
            if(word.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a word to find", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Object[] options = {"Find definition by slang", "Find slang by definition"};
            int n = JOptionPane.showOptionDialog(null, "Do you want to find by slang or definition?", "Find", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            String[][] data = new String[][]{{""},{""}};
            if(n == 0){
                this.clearTable();
                long startTime = System.currentTimeMillis();
                data[0][0] = word;

                try{
                    String temp = listSlang.searchDefinitionBasedOnSlang(word);
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    JOptionPane.showMessageDialog(null, "Found definition in " + duration + " milliseconds", "Success", JOptionPane.INFORMATION_MESSAGE);
                    data[0][1] = temp;
                    result = data;
                    for (int i = 0; i < result.length; i++) {
                        model.addRow(result[i]);
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "No definition found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //data[0][1] = listSlang.searchDefinitionBasedOnSlang(word);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
/*                if(data[0][1] == null){
                    JOptionPane.showMessageDialog(null, "No definition found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Found definition in " + duration + " milliseconds", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
                result = data;
                for(int i = 0; i < result.length; i++){
                    model.addRow(result[i]);
                }*/
            } else if(n == 1){
                this.clearTable();
                long startTime = System.currentTimeMillis();
                data[0][0] = word;
                data[0][1] = listSlang.searchSlangBasedOnDefinition(word);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                if(data[0][1] == null){
                    JOptionPane.showMessageDialog(null, "No slang found", "Error", JOptionPane.ERROR_MESSAGE);
                    //txtFind.setText("No slang found");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Found slang in " + duration + " milliseconds", "Success", JOptionPane.INFORMATION_MESSAGE);
                //txtFind.setText("Found slang in " + duration + " milliseconds");
                result = data;
                for(int i = 0; i < result.length; i++){
                    model.addRow(result[i]);
                }
            }
        } else if(e.getSource() == btnBack){
            this.dispose();
            new MenuFrame();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }

    void clearTable(){
        for(int i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
    }
}
