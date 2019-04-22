/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author elich
 */
public class PokerHistory extends JPanel{
    public PokerHistory(){
        String hand = "", flop= "", turn ="",river="",result="";
        String [][] dataRow = {{hand, flop,turn,river,result},{"","","","",""}};
        String [] columnNames = {"Hand","Flop","Turn","River","Result"};
        JPanel calcPanel = new JPanel();
        JTable calcTable = new JTable(dataRow, columnNames); 
        
        JScrollPane sp = new JScrollPane(calcTable);
        sp.setBounds(0, 0, 400, 100);
        calcPanel.add(sp);
        
        JButton clearButton = new JButton("Clear History");
        JButton deleteHistoryButton = new JButton("Delete Entry");
        JButton pokerCalculator = new JButton("Poker Calculator");
        
        calcPanel.add(sp);
        calcPanel.add(clearButton);
        calcPanel.add(deleteHistoryButton);
        //calcPanel.add(pokerCalculator);
        
        
        add(calcPanel);
    }
    
}
