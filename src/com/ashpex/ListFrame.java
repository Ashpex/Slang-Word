package com.ashpex;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class ListFrame extends JFrame implements ActionListener, TableModelListener {
    JButton btnBack;
    JTable jTable;
    ListSlang listSlang;
    String[][] data;

    public ListFrame() throws FileNotFoundException {
        Container container = this.getContentPane();
        listSlang = new ListSlang();
        data = listSlang.getData();
        JLabel lblTitle = new JLabel("List of Slang");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(10, 10, 200, 30);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel result = new JLabel("");
        result.setForeground(Color.black);
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        result.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);
        String column[] = { "ID", "Slang Word", "Definition" };
        String data2[][] = listSlang.getListSlang();
        for (int i = 0; i < data2.length; i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = data2[i][0];
            data[i][2] = data2[i][1];
        }
        result.setText("Total: " + data.length);
        jTable = new JTable(data, column);
        jTable.setRowHeight(30);
        jTable.setFont(new Font("Arial", Font.PLAIN, 20));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        jTable.getModel().addTableModelListener(this);

        JScrollPane scrollPane = new JScrollPane(jTable);
        panelTable.setLayout(new BoxLayout(panelTable, BoxLayout.X_AXIS));
        panelTable.add(scrollPane);

        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 20));
        btnBack.addActionListener(this);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(btnBack);

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(lblTitle);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(result);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(panelTable);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(bottomPanel);

        this.setTitle("List of Slang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(800, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == btnBack){
            this.dispose();
            new MenuFrame();
        }
    }

    @Override
    public void tableChanged(javax.swing.event.TableModelEvent e) {
        int row = jTable.getSelectedRow();
        int column = jTable.getSelectedColumn();
        if(row == -1 || column == -1){
            return;
        }
        String Value = (String) jTable.getValueAt(row, column);

        if(column == 2){
            System.out.println("Old slangword: \t" + row + "\t" + data[row][2]);


        }

    }
}
