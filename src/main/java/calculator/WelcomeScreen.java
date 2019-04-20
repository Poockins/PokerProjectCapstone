/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author elich
 */
public class WelcomeScreen extends JFrame{
    JPanel mainPanel = new JPanel();
    JPanel welcomePanel = new JPanel();
    
    JPanel pokerCalPanel = new PokerPanel();
    JPanel historyPanel = new PokerHistory();
    
    public WelcomeScreen(){
        setTitle("Poker Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000,400);
        setVisible(true);
        
        
        JLabel welcomeLabel = new JLabel("Poker Calculator");
        JButton calculatorButton = new JButton("Go to Poker Calculator");
        JButton databaseButton = new JButton("Go to Poker Database");
        
        welcomePanel.add(welcomeLabel);
        mainPanel.add(welcomePanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(calculatorButton);
        mainPanel.add(databaseButton);
        add(mainPanel);
        //add(pokerCalPanel);
        calculatorButton.addActionListener((ActionEvent e) -> {
            //removeAll();
            mainPanel.remove(databaseButton);
            mainPanel.remove(calculatorButton);
            welcomePanel.removeAll();
            welcomePanel.add(pokerCalPanel);
            //mainPanel.remove(welcomePanel);
            //mainPanel.remove(historyPanel);
            //add(mainPanel);
            //mainPanel.add(pokerCalPanel);
           // mainPanel.add(calculatorButton);
            mainPanel.add(databaseButton);
            repaint();
            validate();
        });
        databaseButton.addActionListener((ActionEvent e) -> {
            mainPanel.remove(databaseButton);
            mainPanel.remove(calculatorButton);
            welcomePanel.removeAll();
            welcomePanel.add(historyPanel);
            //mainPanel.remove(welcomePanel);
            //mainPanel.remove(historyPanel);
            //add(mainPanel);
            //mainPanel.add(pokerCalPanel);
           // mainPanel.add(calculatorButton);
            mainPanel.add(calculatorButton);
           // mainPanel.add(databaseButton);
           repaint();
            validate();
        });
        validate();
    }
}

