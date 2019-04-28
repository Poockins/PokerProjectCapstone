/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Box;
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
        setSize(1300,450);
        setVisible(true);
        
        JLabel createdBy = new JLabel("Created by:");
        createdBy.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        JLabel authorsNames = new JLabel("Vanessa Redman   " + "Robert Edwards   " + "Thomas Corea   " +
                "Yuko Takegoshi   " + "Keith DeMoura   " + "Eli Cheek");
        authorsNames.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
        JLabel welcomeLabel = new JLabel("Welcome to the Poker Odds Calculator!");
        welcomeLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 32));
        JButton calculatorButton = new JButton("Go to Poker Calculator");
        JButton databaseButton = new JButton("Go to Poker Database");

        mainPanel.add(welcomePanel);
        welcomePanel.add(welcomeLabel);
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(100));
        //mainPanel.add(Box.createHorizontalStrut(100));
        mainPanel.add(calculatorButton);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(databaseButton);
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(createdBy);
        mainPanel.add(authorsNames);
        add(mainPanel);
        //add(pokerCalPanel);
        calculatorButton.addActionListener((ActionEvent e) -> {
            //removeAll();
            mainPanel.remove(databaseButton);
            mainPanel.remove(calculatorButton);
            mainPanel.remove(createdBy);
            mainPanel.remove(authorsNames);
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
            mainPanel.remove(createdBy);
            mainPanel.remove(authorsNames);
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

