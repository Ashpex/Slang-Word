package com.ashpex;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;

public class ListFrame extends JFrame implements ActionListener, TableModelListener {
    JButton bntBack;
    JTable jTable;
    ListSlang listSlang;

    public ListFrame(){

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource() == bntBack){
            this.dispose();
        }
    }

    @Override
    public void tableChanged(javax.swing.event.TableModelEvent e) {

    }
}
