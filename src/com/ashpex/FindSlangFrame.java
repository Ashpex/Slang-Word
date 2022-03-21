package com.ashpex;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindSlangFrame extends JFrame implements ActionListener, TableModelListener {
    JButton btnBack, btnFind;
    JTextField txtFind;
    JTable jTable;
    DefaultTableModel model;
    ListSlang listSlang;
    String[][] result;

    final JOptionPane optionPane = new JOptionPane("The only way to close this dialog is by\n"
            + "pressing one of the following buttons.\n" + "Do you understand?", JOptionPane.QUESTION_MESSAGE,
            JOptionPane.YES_NO_OPTION);
    String data[][] = { { "", "", "" } };

    FindSlangFrame(){
        Container container = new Container();
        JLabel title = new JLabel("Find Slang");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblFind = new JLabel("Find   :");
        lblFind.setFont(new Font("Arial", Font.BOLD, 15));
        lblFind.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblFind.setForeground(Color.BLUE);
        JPanel form = new JPanel();
        JLabel formLabel = new JLabel("Input the word you want to find");
        txtFind = new JTextField();
        btnFind = new JButton("Find");
        btnBack = new JButton("Back");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

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
