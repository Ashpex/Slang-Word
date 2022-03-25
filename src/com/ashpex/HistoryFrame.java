package com.ashpex;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HistoryFrame extends javax.swing.JFrame implements ActionListener {

    JButton btnBack;
    JButton btnClear;
    JButton btnExit;
    ListSlang listSlang = new ListSlang();
    HistoryFrame() throws FileNotFoundException {
        Container container = this.getContentPane();
        JLabel lblHistory = new JLabel("History");
        lblHistory.setFont(new Font("Arial", Font.BOLD, 20));
        lblHistory.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Table
        JPanel pnlTable = new JPanel();
        pnlTable.setBackground(Color.BLACK);

        ArrayList<String> history = listSlang.getHistory();
        String[] columnNames = {"ID", "Search", "Result", "Date"};
        String regEx = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
        String[][] data = new String[history.size()][4];
        for (int i = 0; i < history.size(); i++) {
            List<String> splitedStringList = splitString(history.get(i), regEx, 1);
            data[i][0] = String.valueOf(i + 1);
            data[i][3] = splitedStringList.get(0);
            data[i][1] = splitedStringList.get(1).split(":")[0];
            data[i][2] = splitedStringList.get(1).split(":")[1];
        }

        JPanel pnlTableContent = new JPanel();
        pnlTableContent.setBackground(Color.BLACK);

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        table.setFont(new Font("Arial", Font.PLAIN, 20));
        DefaultTableCellRenderer headerRender = (DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer();
        headerRender.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));

        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        pnlTable.setLayout(new GridLayout(1, 1));
        pnlTable.add(scrollPane);

        // Buttons
        JPanel pnlButtons = new JPanel();
        btnBack = new JButton("Back");
        btnExit = new JButton("Exit");
        btnClear = new JButton("Clear");
        btnExit.addActionListener(this);
        btnBack.addActionListener(this);
        btnClear.addActionListener(this);
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        btnExit.setFont(new Font("Arial", Font.PLAIN, 20));
        btnClear.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension dimension = new Dimension(700, 50);
        pnlButtons.setPreferredSize(dimension);
        pnlButtons.setMaximumSize(dimension);
        pnlButtons.setMinimumSize(dimension);
        pnlButtons.setLayout(new GridLayout(1, 3));
        pnlButtons.add(btnBack);
        pnlButtons.add(btnClear);
        pnlButtons.add(btnExit);

        // Add to container
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(lblHistory);
        container.add(Box.createRigidArea(new Dimension(0, 50)));
        container.add(pnlTable);
        container.add(Box.createRigidArea(new Dimension(0, 50)));
        container.add(pnlButtons);

        this.setSize(700, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);


    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btnBack) {
            this.dispose();
            try {
                new MenuFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (evt.getSource() == btnExit) {
            this.dispose();
        } else if (evt.getSource() == btnClear) {
            listSlang.clearHistory();
            this.dispose();
            try {
                new HistoryFrame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static java.util.List<String> splitString(String inputString, String regEx, int occurancePosition) {
        List<String> splitedStringList = new LinkedList<String>();
        // Check if inputString is not null
        if (inputString != null && inputString.length() > 0) {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(inputString);
            int count = 0;
            int stringSplitIndex = 0;
            // Iterate while loop for finding regEx
            while (matcher.find()) {
                // increase count if found the matcher string(regEx)
                count++;
                // check if we found expected occurrence of regEx in given
                // inputString
                if (count == occurancePosition) {
                    // assign index position of occurrence regEx.
                    stringSplitIndex = matcher.end();
                    break;
                }
            }
            // add subString of input String into the list from String index
            // position 0 to according to our occurrence position
            splitedStringList.add(inputString.substring(0, stringSplitIndex));
            // add SubString from occurrence index position to end of the
            // inputString.
            splitedStringList.add(inputString.substring(stringSplitIndex));
        }

        return splitedStringList;
    }


}
