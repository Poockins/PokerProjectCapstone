/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author elich
 */
public class PokerPanel extends JPanel{
    //
    String [] cardValue = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    String [] suitValue = {"Hearts","Clubs","Diamonds","Spades"};
    JPanel pokerTable = new JPanel();
    JPanel historyPanel = new PokerHistory();
    PlayerPanelFactory playerFactory = new PlayerPanelFactory();
    Border blackline = BorderFactory.createLineBorder(Color.black);
    public PokerPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        pokerTable.setLayout(new GridBagLayout());
        pokerTable.setBorder(blackline);
        GridBagConstraints tableConstraints = new GridBagConstraints();
        //Poker Table Components
        //Flop Card1
        JLabel flopLabel = new JLabel("Flop");
        JComboBox flop1Value = new JComboBox(cardValue);
        JComboBox flop1Suit = new JComboBox(suitValue);
        //Flop Card2
        JComboBox flop2Value = new JComboBox(cardValue);
        JComboBox flop2Suit = new JComboBox(suitValue);
        //Flop Card3
        JComboBox flop3Value = new JComboBox(cardValue);
        JComboBox flop3Suit = new JComboBox(suitValue);
        //River
        JLabel riverLabel = new JLabel("River");
        JComboBox riverValue = new JComboBox(cardValue);
        JComboBox riverSuit = new JComboBox(suitValue);
        //Turn
        JLabel turnLabel = new JLabel("Turn");
        JComboBox turnValue = new JComboBox(cardValue);
        JComboBox turnSuit = new JComboBox(suitValue);
        
        //The poker Table adds in its components and are laid out using gridbag 
        //
        tableConstraints.gridx = 0;
        tableConstraints.gridy = 0;
        tableConstraints.insets = new Insets(2,1,1,1);
        pokerTable.add(flopLabel,tableConstraints);
        tableConstraints.gridy = 1;
        pokerTable.add(flop1Value,tableConstraints);
        tableConstraints.gridx=1;
        pokerTable.add(flop1Suit,tableConstraints);
        tableConstraints.gridx = 2;
        pokerTable.add(flop2Value,tableConstraints);
        tableConstraints.gridx = 3;
        pokerTable.add(flop2Suit,tableConstraints);
        tableConstraints.gridx = 4;
        pokerTable.add(flop3Value,tableConstraints);
        tableConstraints.gridx = 5;
        pokerTable.add(flop3Suit,tableConstraints);
        //Start of the river
        tableConstraints.gridy=2;
        tableConstraints.gridx = 0;
        pokerTable.add(riverLabel,tableConstraints);
        tableConstraints.gridy=3;
        pokerTable.add(riverValue,tableConstraints);
        tableConstraints.gridx = 1;
        pokerTable.add(riverSuit,tableConstraints);
        //Start of the turn
        tableConstraints.gridy=2;
        tableConstraints.gridx=2;
        pokerTable.add(turnLabel,tableConstraints);
        tableConstraints.gridy=3;
        pokerTable.add(turnValue,tableConstraints);
        tableConstraints.gridx=3;
        pokerTable.add(turnSuit,tableConstraints);
        
        
        //Panels for players
        //Each player has their own panel that is on the screen. Table starts 
        //out with no players and pressing the add button will bring up their options
        
        JPanel player1 = new JPanel();
        TitledBorder player1Title = BorderFactory.createTitledBorder(blackline, "Player 1");
        player1Title.setTitleJustification(TitledBorder.LEFT);
        player1.setBorder(player1Title);
        JButton addPlayer1 = new JButton("Add");
        player1.add(addPlayer1);
        repaint();

        JPanel player2 = new JPanel();
        TitledBorder player2Title = BorderFactory.createTitledBorder(blackline, "Player2");
        player2Title.setTitleJustification(TitledBorder.LEFT);
        player2.setBorder(player2Title);
        JButton addPlayer2 = new JButton("Add");
        player2.add(addPlayer2);
        repaint();

        JPanel player3 = new JPanel();
        TitledBorder player3Title = BorderFactory.createTitledBorder(blackline, "Player3");
        player3Title.setTitleJustification(TitledBorder.LEFT);
        player3.setBorder(player3Title);
        JButton addPlayer3 = new JButton("Add");
        player3.add(addPlayer3);
        repaint();
        
        JPanel player4 = new JPanel();
        TitledBorder player4Title = BorderFactory.createTitledBorder(blackline, "Player4");
        player4Title.setTitleJustification(TitledBorder.LEFT);
        player4.setBorder(player4Title);
        JButton addPlayer4 = new JButton("Add");
        player4.add(addPlayer4);
        repaint();

        JPanel player5 = new JPanel();
        TitledBorder player5Title = BorderFactory.createTitledBorder(blackline, "Player5");
        player5Title.setTitleJustification(TitledBorder.LEFT);
        player5.setBorder(player5Title);
        JButton addPlayer5 = new JButton("Add");
        player5.add(addPlayer5);
        repaint();

        JPanel player6 = new JPanel();
        TitledBorder player6Title = BorderFactory.createTitledBorder(blackline, "Player6");
        player6Title.setTitleJustification(TitledBorder.LEFT);
        player6.setBorder(player6Title);
        JButton addPlayer6 = new JButton("Add");
        player6.add(addPlayer6);
        repaint();

        JPanel player7 = new JPanel();
        TitledBorder player7Title = BorderFactory.createTitledBorder(blackline, "Player7");
        player7Title.setTitleJustification(TitledBorder.LEFT);
        player7.setBorder(player7Title);
        JButton addPlayer7 = new JButton("Add");
        player7.add(addPlayer7);
        repaint();
        
        JPanel player8 = new JPanel();
        TitledBorder player8Title = BorderFactory.createTitledBorder(blackline, "Player8");
        player8Title.setTitleJustification(TitledBorder.LEFT);
        player8.setBorder(player8Title);
        JButton addPlayer8 = new JButton("Add");
        player8.add(addPlayer8);
        repaint();
        
        
        //Calculation Panel
        String odds = "", cardsNeeded= "";
        String [][] dataRow = {{odds, cardsNeeded},{"",""}};
        String [] columnNames = {"Odds","Cards"};
        JPanel calcPanel = new JPanel();
        calcPanel.setBorder(blackline);
        JTable calcTable = new JTable(dataRow, columnNames);  
        calcPanel.setBounds(0, 0, 50, 50);
        JScrollPane sp = new JScrollPane(calcTable);
        
        calcPanel.add(sp);    
        
        
        //Number of Player Panel
        JPanel numPlayerPanel = new JPanel();
        numPlayerPanel.setBorder(blackline);
        String [] numPlayerList = {"2","3","4","5","6","7","8"};
        JLabel playerLabel = new JLabel("Number of Players");
        JComboBox playerBox = new JComboBox(numPlayerList);
        numPlayerPanel.add(playerLabel);
        numPlayerPanel.add(playerBox);
        
        
        //Misc. Components
        JPanel miscPanel = new JPanel();
        miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
        JButton databaseButton = new JButton("Go to database");
        JButton clearButton = new JButton("Clear Form");
       // miscPanel.add(databaseButton);
        miscPanel.add(clearButton);
        
        //Layout Management for the Poker Panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(numPlayerPanel,c);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(calcPanel,c);
        c.gridx = 2;
        c.gridy =1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(player1,c);
        c.gridx = 3;
        add(player2,c);
        c.gridx = 4;
        add(player3,c);
        c.gridy=2;
        c.gridx=2;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 3;
        add(pokerTable,c);        
        c.gridx=1;
        c.gridwidth=1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(player4,c);
        c.gridx=5;
        add(player5,c);
        c.gridx=2;
        c.gridy=3;
        add(player6,c);
        c.gridx=3;
        add(player7,c);
        c.gridx=4;
        add(player8,c);
        c.gridx=6;
        c.gridy=2;
        add(miscPanel,c);
        
        //Button Controls
        //The addPlayer# buttons all will replaces the contents of their panel 
        //with the new contents of the the player factory
        addPlayer1.addActionListener((ActionEvent e) -> {
            player1.removeAll();
            player1.add(playerFactory.PlayerPanelFactory("Player1"));
            repaint();
            validate();
        });
        addPlayer2.addActionListener((ActionEvent e) -> {
            player2.removeAll();
            player2.add(playerFactory.PlayerPanelFactory("Player2"));
            repaint();
            validate();
        });
        addPlayer3.addActionListener((ActionEvent e) -> {
            player3.removeAll();
            player3.add(playerFactory.PlayerPanelFactory("Player3"));
            repaint();
            validate();
        });
        addPlayer4.addActionListener((ActionEvent e) -> {
            player4.removeAll();
            player4.add(playerFactory.PlayerPanelFactory("Player4"));
            repaint();
            validate();
        });
        addPlayer5.addActionListener((ActionEvent e) -> {
            player5.removeAll();
            player5.add(playerFactory.PlayerPanelFactory("Player5"));
            repaint();
            validate();
        });
        addPlayer6.addActionListener((ActionEvent e) -> {
            player6.removeAll();
            player6.add(playerFactory.PlayerPanelFactory("Player6"));
            repaint();
            validate();
        });
        addPlayer7.addActionListener((ActionEvent e) -> {
            player7.removeAll();
            player7.add(playerFactory.PlayerPanelFactory("Player7"));
            repaint();
            validate();
        });
        addPlayer8.addActionListener((ActionEvent e) -> {
            player8.removeAll();
            player8.add(playerFactory.PlayerPanelFactory("Player8"));
            repaint();
            validate();
        });
        repaint();
        validate();
    }
    
}
//PlayerPanelFactory creates a player panel that has the component of what a 
//player should be able to enter for their hand
class PlayerPanelFactory extends JPanel{
    String [] cardValue = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    String [] suitValue = {"Hearts","Clubs","Diamonds","Spades"};
    Border blackline = BorderFactory.createLineBorder(Color.black);
    JPanel PlayerPanelFactory(String name){
        JPanel player = new JPanel();
        player.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JComboBox boxValue1 = new JComboBox(cardValue);
        JComboBox boxSuit1 = new JComboBox(suitValue);
        JComboBox boxValue2 = new JComboBox(cardValue);
        JComboBox boxSuit2 = new JComboBox(suitValue);
        c.gridx=0;
        c.gridy=0;
        player.add(boxValue1,c);
        c.gridx=1;
        player.add(boxSuit1,c);
        c.gridx=0;
        c.gridy=1;
        player.add(boxValue2,c);
        c.gridx=1;
        player.add(boxSuit2,c);
        return player;
    }
}
