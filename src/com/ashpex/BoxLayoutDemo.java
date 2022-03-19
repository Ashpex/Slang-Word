package com.ashpex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class BoxLayoutDemo extends JFrame {

    private JList<String> list;

    public BoxLayoutDemo() {
        //create the model and add elements
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Tan Hung");
        listModel.addElement("Tan Kien");
        listModel.addElement("Tan Phu");
        listModel.addElement("Tan Thuan Dong");
        listModel.addElement("Tan Thuan Tay");
        listModel.addElement("Tan Quy");
        listModel.addElement("Binh Thuan");
        //create the list
        list = new JList<>(listModel);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = list.getSelectedValuesList();
                    System.out.println(selectedValuesList);
                }
            }
        });

        add(new JScrollPane(list));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ward List");
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public static void main(String[] args) {



        String[] optionsToChoose = {"District 1", "District 2", "District 3", "District 4", "District 5"};

        JFrame jFrame = new JFrame();


        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(80, 50, 140, 20);

        JButton jButtonDone = new JButton("Done");
        jButtonDone.setBounds(50, 100, 90, 20);

        JButton jButtonCancel = new JButton("Cancel");
        jButtonCancel.setBounds(160, 100, 90, 20);

        JLabel jLabel = new JLabel();
        jLabel.setBounds(90, 100, 400, 100);

        BoxLayoutDemo boxLayoutDemo = new BoxLayoutDemo();
        boxLayoutDemo.setLayout(new BoxLayout(boxLayoutDemo.getContentPane(), BoxLayout.Y_AXIS));
        boxLayoutDemo.add(new JComboBox<>(optionsToChoose));
        boxLayoutDemo.add(new JButton("OK"));
        boxLayoutDemo.add(new JButton("Cancel"));
        boxLayoutDemo.setBounds(100, 100, 300, 300);
        jFrame.add(boxLayoutDemo);


        jFrame.add(jButtonDone);
        jFrame.add(jButtonCancel);
        jFrame.add(jComboBox);
        jFrame.add(jLabel);

        jFrame.setLayout(null);
        jFrame.setSize(350, 250);
        jFrame.setVisible(true);

        jButtonDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecteDistrict = "You selected " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
                jLabel.setText(selecteDistrict);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new BoxLayoutDemo();
                    }
                });
                jLabel.setText(selecteDistrict);
            }
        });

        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
