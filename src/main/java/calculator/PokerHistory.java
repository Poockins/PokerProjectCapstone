/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elich
 */
public class PokerHistory extends JPanel{
    JTable calcTable = new JTable(new DefaultTableModel(new Object[]{"Hand", "Flop","Turn","River","Result"},0)); 
    DefaultTableModel model = (DefaultTableModel)calcTable.getModel();
    public PokerHistory(){
        JPanel historyPanel = new JPanel();       
        JScrollPane sp = new JScrollPane(calcTable);
        sp.setBounds(0, 0, 500, 100);
        historyPanel.add(sp);
        
        JButton clearButton = new JButton("Clear History");
        JButton deleteHistoryButton = new JButton("Delete Entry");
        
        addData();
        
        historyPanel.add(sp);
        historyPanel.add(clearButton);
        historyPanel.add(deleteHistoryButton);
        //calcPanel.add(pokerCalculator);
        
        
        add(historyPanel);
        
    }
    //Method will add data from the database and add it to the table
    void addData(){
        
        //These strings will recieve there assignment from the respective database object
        String flop1 = "";
        String flop2 = "";
        String flop3 = "";        
        String turn = "";
        String river = "";
        String hand = "";
        String result = "";
        
        String flop = flop1+" "+flop2+" "+flop3;
        model.addRow(new Object[]{hand,flop,turn,river,result});
        
    }
    
}
