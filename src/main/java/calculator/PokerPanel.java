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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    Player player;
    Hand hand;
    Cards cards;
    Rank rank;
    Suit suit;
    Game game;
    //
    Rank [] cardValue = {Rank.TWO,Rank.THREE,Rank.FOUR,Rank.FIVE,Rank.SIX,Rank.SEVEN,Rank.EIGHT,Rank.NINE,Rank.TEN,Rank.JACK,Rank.QUEEN,Rank.KING};
    Suit [] suitValue = {Suit.SPADES,Suit.DIAMONDS,Suit.CLUBS,Suit.HEARTS};
    JPanel pokerTable = new JPanel();
    JPanel historyPanel = new PokerHistory();
    String numberOfPlayers = "";
    int numPlayers = 1;
    
    
    
    //
    Rank flop1Value,flop2Value,flop3Value,riverValue,turnValue,
            player1Card1Value,player1Card2Value,player2Card1Value,player2Card2Value,
            player3Card1Value,player3Card2Value,player4Card1Value,player4Card2Value,
            player5Card1Value,player5Card2Value,player6Card1Value,player6Card2Value,
            player7Card1Value,player7Card2Value,player8Card1Value,player8Card2Value
            ;
    Suit flop1Suit, flop2Suit, flop3Suit,riverSuit,turnSuit,
            player1Card1Suit,player1Card2Suit,player2Card1Suit,player2Card2Suit,
            player3Card1Suit,player3Card2Suit,player4Card1Suit,player4Card2Suit,
            player5Card1Suit,player5Card2Suit,player6Card1Suit,player6Card2Suit,
            player7Card1Suit,player7Card2Suit,player8Card1Suit,player8Card2Suit;
    Cards flop1,flop2,flop3,river,turn,
            player1Card1,player1Card2,
            player2Card1,player2Card2,
            player3Card1,player3Card2,
            player4Card1,player4Card2,
            player5Card1,player5Card2,
            player6Card1,player6Card2,
            player7Card1,player7Card2,
            player8Card1,player8Card2;
    
    Player player1, player2,player3,player4,player5,player6,player7,player8;
    
    
    //Database inception
    //DBConnection db = new DBConnection();
    //
    
    
        

    
    
//    PlayerPanelFactory playerFactory = new PlayerPanelFactory();
    Border blackline = BorderFactory.createLineBorder(Color.black);
                        //Remove Throws IO Exception
    public PokerPanel(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        pokerTable.setLayout(new GridBagLayout());
        pokerTable.setBorder(blackline);
        
        
        
    //    Container contentPane = this.getTopLevelAncestor();
        GridBagConstraints tableConstraints = new GridBagConstraints();
        //Poker Table Components
        //Flop Card1
        JLabel flopLabel = new JLabel("Flop");
        JComboBox flop1ValueBox = new JComboBox(cardValue);
        JComboBox flop1SuitBox = new JComboBox(suitValue);
        //Flop Card2
        JComboBox flop2ValueBox = new JComboBox(cardValue);
        JComboBox flop2SuitBox = new JComboBox(suitValue);
        //Flop Card3
        JComboBox flop3ValueBox = new JComboBox(cardValue);
        JComboBox flop3SuitBox = new JComboBox(suitValue);
        //River
        JLabel riverLabel = new JLabel("River");
        JComboBox riverValueBox = new JComboBox(cardValue);
        JComboBox riverSuitBox = new JComboBox(suitValue);
        //Turn
        JLabel turnLabel = new JLabel("Turn");
        JComboBox turnValueBox = new JComboBox(cardValue);
        JComboBox turnSuitBox = new JComboBox(suitValue);
        
        //The poker Table adds in its components and are laid out using gridbag 
        //
        tableConstraints.gridx = 0;
        tableConstraints.gridy = 0;
        tableConstraints.insets = new Insets(2,1,1,1);
        pokerTable.add(flopLabel,tableConstraints);
        tableConstraints.gridy = 1;
        pokerTable.add(flop1ValueBox,tableConstraints);
        tableConstraints.gridx=1;
        pokerTable.add(flop1SuitBox,tableConstraints);
        tableConstraints.gridx = 2;
        pokerTable.add(flop2ValueBox,tableConstraints);
        tableConstraints.gridx = 3;
        pokerTable.add(flop2SuitBox,tableConstraints);
        tableConstraints.gridx = 4;
        pokerTable.add(flop3ValueBox,tableConstraints);
        tableConstraints.gridx = 5;
        pokerTable.add(flop3SuitBox,tableConstraints);
        //Start of the river
        tableConstraints.gridy=2;
        tableConstraints.gridx = 0;
        pokerTable.add(riverLabel,tableConstraints);
        tableConstraints.gridy=3;
        pokerTable.add(riverValueBox,tableConstraints);
        tableConstraints.gridx = 1;
        pokerTable.add(riverSuitBox,tableConstraints);
        //Start of the turn
        tableConstraints.gridy=2;
        tableConstraints.gridx=2;
        pokerTable.add(turnLabel,tableConstraints);
        tableConstraints.gridy=3;
        pokerTable.add(turnValueBox,tableConstraints);
        tableConstraints.gridx=3;
        pokerTable.add(turnSuitBox,tableConstraints);
        
        
        //Panels for players
        //Each player has their own panel that is on the screen. Table starts 
        //out with no players and pressing the add button will bring up their options
        
        JPanel player1Panel = new JPanel();
        TitledBorder player1Title = BorderFactory.createTitledBorder(blackline, "Player 1");
        player1Title.setTitleJustification(TitledBorder.LEFT);
        player1Panel.setBorder(player1Title);
        JButton addPlayer1 = new JButton("Add");
        addPlayer1.setVisible(false);
        player1Panel.add(addPlayer1);
        repaint();

        JPanel player2Panel = new JPanel();
        TitledBorder player2Title = BorderFactory.createTitledBorder(blackline, "Player2");
        player2Title.setTitleJustification(TitledBorder.LEFT);
        player2Panel.setBorder(player2Title);
        JButton addPlayer2 = new JButton("Add");
        addPlayer2.setVisible(false);
        player2Panel.add(addPlayer2);
        repaint();

        JPanel player3Panel = new JPanel();
        TitledBorder player3Title = BorderFactory.createTitledBorder(blackline, "Player3");
        player3Title.setTitleJustification(TitledBorder.LEFT);
        player3Panel.setBorder(player3Title);
        JButton addPlayer3 = new JButton("Add");
        addPlayer3.setVisible(false);
        player3Panel.add(addPlayer3);
        repaint();
        
        JPanel player4Panel = new JPanel();
        TitledBorder player4Title = BorderFactory.createTitledBorder(blackline, "Player4");
        player4Title.setTitleJustification(TitledBorder.LEFT);
        player4Panel.setBorder(player4Title);
        JButton addPlayer4 = new JButton("Add");
        addPlayer4.setVisible(false);
        player4Panel.add(addPlayer4);
        repaint();

        JPanel player5Panel = new JPanel();
        TitledBorder player5Title = BorderFactory.createTitledBorder(blackline, "Player5");
        player5Title.setTitleJustification(TitledBorder.LEFT);
        player5Panel.setBorder(player5Title);
        JButton addPlayer5 = new JButton("Add");
        addPlayer5.setVisible(false);
        player5Panel.add(addPlayer5);
        repaint();

        JPanel player6Panel = new JPanel();
        TitledBorder player6Title = BorderFactory.createTitledBorder(blackline, "Player6");
        player6Title.setTitleJustification(TitledBorder.LEFT);
        player6Panel.setBorder(player6Title);
        JButton addPlayer6 = new JButton("Add");
        addPlayer6.setVisible(false);
        player6Panel.add(addPlayer6);
        repaint();

        JPanel player7Panel = new JPanel();
        TitledBorder player7Title = BorderFactory.createTitledBorder(blackline, "Player7");
        player7Title.setTitleJustification(TitledBorder.LEFT);
        player7Panel.setBorder(player7Title);
        JButton addPlayer7 = new JButton("Add");
        addPlayer7.setVisible(false);
        player7Panel.add(addPlayer7);
        repaint();
        
        JPanel player8Panel = new JPanel();
        TitledBorder player8Title = BorderFactory.createTitledBorder(blackline, "Player8");
        player8Title.setTitleJustification(TitledBorder.LEFT);
        player8Panel.setBorder(player8Title);
        JButton addPlayer8 = new JButton("Add");
        addPlayer8.setVisible(false);
        player8Panel.add(addPlayer8);
        repaint();
        
        
        //Calculation Panel
        String odds = "", cardsNeeded= "";
        String [][] dataRow = {{odds, cardsNeeded},{"",""}};
        String [] columnNames = {"Odds","Cards"};
        JPanel calcPanel = new JPanel();
        calcPanel.setBorder(blackline);
        JTable calcTable = new JTable(dataRow, columnNames);  
        int tableWidth = calcPanel.getWidth();
        int tableHeight = calcPanel.getHeight();
        calcTable.setPreferredScrollableViewportSize(calcTable.getPreferredSize());
        calcTable.setSize(tableWidth, tableHeight);
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
        miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.X_AXIS));
        JButton databaseButton = new JButton("Save to Database");
        JButton clearButton = new JButton("Clear Form");
        miscPanel.add(clearButton);
        miscPanel.add(databaseButton);
        
        
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
        add(player1Panel,c);
        c.gridx = 3;
        add(player2Panel,c);
        c.gridx = 4;
        add(player3Panel,c);
        c.gridy=2;
        c.gridx=2;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 3;
        add(pokerTable,c);        
        c.gridx=1;
        c.gridwidth=1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(player4Panel,c);
        c.gridx=5;
        add(player5Panel,c);
        c.gridx=2;
        c.gridy=3;
        add(player6Panel,c);
        c.gridx=3;
        add(player7Panel,c);
        c.gridx=4;
        add(player8Panel,c);
        c.gridx=3;
        c.gridy=4;
        add(miscPanel,c);
        //intializes the number of players in the calculator
        playerBox.setSelectedIndex(0);
        numberOfPlayers = playerBox.getSelectedItem().toString();
        numPlayers = Integer.parseInt(numberOfPlayers);
        
        
        //Button Controls
        //The addPlayer# buttons all will replaces the contents of their panel 
        //with the new contents of the the player factory
        addPlayer1.addActionListener((ActionEvent e) -> {
            player1Panel.removeAll();
            player1Panel.add(playerPanelFactory("Player1",1));
            //welcomeScreen.get
            repaint();
            validate();
        });
        addPlayer2.addActionListener((ActionEvent e) -> {
            player2Panel.removeAll();
            player2Panel.add(playerPanelFactory("Player2",2));
            getParent().repaint();
     //       contentPane.repaint();
            validate();
        });
        addPlayer3.addActionListener((ActionEvent e) -> {
            player3Panel.removeAll();
            player3Panel.add(playerPanelFactory("Player3",3));
            getParent().repaint();
    //        contentPane.repaint();
            validate();
        });
        addPlayer4.addActionListener((ActionEvent e) -> {
            player4Panel.removeAll();
            player4Panel.add(playerPanelFactory("Player4",4));
            repaint();
            validate();
        });
        addPlayer5.addActionListener((ActionEvent e) -> {
            player5Panel.removeAll();
            player5Panel.add(playerPanelFactory("Player5",5));
            repaint();
            validate();
        });
        addPlayer6.addActionListener((ActionEvent e) -> {
            player6Panel.removeAll();
            player6Panel.add(playerPanelFactory("Player6",6));
            repaint();
            validate();
        });
        addPlayer7.addActionListener((ActionEvent e) -> {
            player7Panel.removeAll();
            player7Panel.add(playerPanelFactory("Player7",7));
            repaint();
            validate();
        });
        addPlayer8.addActionListener((ActionEvent e) -> {
            player8Panel.removeAll();
            player8Panel.add(playerPanelFactory("Player8",8));
            repaint();
            validate();
        });
        databaseButton.addActionListener((ActionEvent e) -> {
            makeDiaryEntry(numPlayers);
        });
        
        //Listeners for ComboBoxes 
        flop1ValueBox.addActionListener((ActionEvent e)-> {
            flop1Value=(Rank)flop1ValueBox.getSelectedItem();
            
        });
        flop1SuitBox.addActionListener((ActionEvent e)-> {
            flop1Suit=(Suit)flop1SuitBox.getSelectedItem();
        });
        flop2ValueBox.addActionListener((ActionEvent e)-> {
            flop2Value=(Rank)flop2ValueBox.getSelectedItem();        
        });
        flop2SuitBox.addActionListener((ActionEvent e)-> {
            flop2Suit=(Suit)flop2SuitBox.getSelectedItem();
        });
        flop3ValueBox.addActionListener((ActionEvent e)-> {
            flop3Value=(Rank)flop3ValueBox.getSelectedItem();        
        });
        flop3SuitBox.addActionListener((ActionEvent e)-> {
            flop3Suit=(Suit)flop3SuitBox.getSelectedItem();        
        });
        riverValueBox.addActionListener((ActionEvent e)-> {
            riverValue=(Rank)riverValueBox.getSelectedItem();        
        });
        riverSuitBox.addActionListener((ActionEvent e)-> {
            riverSuit=(Suit)riverSuitBox.getSelectedItem();        
        });
        turnValueBox.addActionListener((ActionEvent e)-> {
            turnValue=(Rank)turnValueBox.getSelectedItem();        
        });
        turnSuitBox.addActionListener((ActionEvent e)-> {
            turnSuit=(Suit)turnSuitBox.getSelectedItem();  
        });
        //Switch used to toggle the invisible buttons on the screen
        playerBox.addActionListener((ActionEvent e)-> {
            numberOfPlayers = playerBox.getSelectedItem().toString();
            int numPlayers = Integer.parseInt(numberOfPlayers);
            
            switch(numPlayers){
                case 1:
                    addPlayer1.doClick();
                    player1 = new Player("Player1");
                    break;
                case 2:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    break;
                case 3:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    addPlayer3.doClick();
                    break;
                case 4:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    addPlayer3.doClick();
                    addPlayer4.doClick();
                    break;
                case 5:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    addPlayer3.doClick();
                    addPlayer4.doClick();
                    addPlayer5.doClick();
                    break;
                case 6:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    addPlayer3.doClick();
                    addPlayer4.doClick();
                    addPlayer5.doClick();
                    addPlayer6.doClick();
                    break;
                case 7:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    addPlayer3.doClick();
                    addPlayer4.doClick();
                    addPlayer5.doClick();
                    addPlayer6.doClick();
                    addPlayer7.doClick();
                    break;
                case 8:
                    addPlayer1.doClick();
                    addPlayer2.doClick();
                    addPlayer3.doClick();
                    addPlayer4.doClick();
                    addPlayer5.doClick();
                    addPlayer6.doClick();
                    addPlayer7.doClick();
                    addPlayer8.doClick();
                    
                    break;
                default:
                    return;
            }
        });
        databaseButton.addActionListener((ActionEvent e)->{
            makeDiaryEntry(numPlayers);
        });
      
        repaint();
        validate();
    }
    //Method used to create a diary of the table
    void makeDiaryEntry(int num){
        flop1=new Cards(flop1Value,flop1Suit);
        flop2=new Cards(flop2Value,flop2Suit);
        flop3=new Cards(flop3Value,flop3Suit);
        river=new Cards(riverValue,riverSuit);
        turn=new Cards(turnValue,turnSuit);
        Cards [] player1Cards = new Cards[2];//All of the commented out player hands can be removed to work with Yuko's Database wrappers
        Cards [] player2Cards = new Cards[2];
        Cards [] player3Cards = new Cards[2];
        Cards [] player4Cards = new Cards[2];
        Cards [] player5Cards = new Cards[2];
        Cards [] player6Cards = new Cards[2];
        Cards [] player7Cards = new Cards[2];
        Cards [] player8Cards = new Cards[2];
        
        switch(num){
                case 1:
                    player1Card1 = new Cards(player1Card1Value,player1Card1Suit);
                    player1Card2 = new Cards(player1Card2Value,player1Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    break;
                case 2:
                    player1Card1 =new Cards(player1Card1Value,player1Card1Suit);
                    player1Card2 =new Cards( player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    
                    break;
                case 3:
                    player1Card1 =new Cards( player1Card1Value,player1Card1Suit);
                    player1Card2 = new Cards(player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player3Card1 =new Cards( player3Card1Value,player3Card1Suit);
                    player3Card2 =new Cards( player3Card2Value,player3Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player3Cards[1] =player3Card1;
                    player3Cards[2] = player3Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    break;
                case 4:
                    player1Card1 =new Cards( player1Card1Value,player1Card1Suit);
                    player1Card2 =new Cards( player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player3Card1 =new Cards( player3Card1Value,player3Card1Suit);
                    player3Card2 =new Cards( player3Card2Value,player3Card2Suit);
                    player4Card1 =new Cards( player4Card1Value,player4Card1Suit);
                    player4Card2 =new Cards( player4Card2Value,player4Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player3Cards[1] =player3Card1;
                    player3Cards[2] = player3Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player4Cards[1] =player4Card1;
                    player4Cards[2] = player4Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    break;
                case 5:
                    player1Card1 =new Cards( player1Card1Value,player1Card1Suit);
                    player1Card2 =new Cards( player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player3Card1 =new Cards( player3Card1Value,player3Card1Suit);
                    player3Card2 =new Cards( player3Card2Value,player3Card2Suit);
                    player4Card1 =new Cards( player4Card1Value,player4Card1Suit);
                    player4Card2 =new Cards( player4Card2Value,player4Card2Suit);
                    player5Card1 =new Cards( player5Card1Value,player5Card1Suit);
                    player5Card2 =new Cards( player5Card2Value,player5Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player3Cards[1] =player3Card1;
                    player3Cards[2] = player3Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player4Cards[1] =player4Card1;
                    player4Cards[2] = player4Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player5Cards[1] =player5Card1;
                    player5Cards[2] = player5Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    break;
                case 6:
                    player1Card1 =new Cards( player1Card1Value,player1Card1Suit);
                    player1Card2 =new Cards( player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player3Card1 =new Cards( player3Card1Value,player3Card1Suit);
                    player3Card2 =new Cards( player3Card2Value,player3Card2Suit);
                    player4Card1 =new Cards( player4Card1Value,player4Card1Suit);
                    player4Card2 =new Cards( player4Card2Value,player4Card2Suit);
                    player5Card1 =new Cards( player5Card1Value,player5Card1Suit);
                    player5Card2 =new Cards( player5Card2Value,player5Card2Suit);
                    player6Card1 =new Cards( player6Card1Value,player6Card1Suit);
                    player6Card2 =new Cards( player6Card2Value,player6Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player3Cards[1] =player3Card1;
                    player3Cards[2] = player3Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player4Cards[1] =player4Card1;
                    player4Cards[2] = player4Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player5Cards[1] =player5Card1;
                    player5Cards[2] = player5Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player6Cards[1] =player6Card1;
                    player6Cards[2] = player6Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    break;
                case 7:
                    player1Card1 =new Cards( player1Card1Value,player1Card1Suit);
                    player1Card2 =new Cards( player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player3Card1 =new Cards( player3Card1Value,player3Card1Suit);
                    player3Card2 =new Cards( player3Card2Value,player3Card2Suit);
                    player4Card1 =new Cards( player4Card1Value,player4Card1Suit);
                    player4Card2 =new Cards( player4Card2Value,player4Card2Suit);
                    player5Card1 =new Cards( player5Card1Value,player5Card1Suit);
                    player5Card2 =new Cards( player5Card2Value,player5Card2Suit);
                    player6Card1 =new Cards( player6Card1Value,player6Card1Suit);
                    player6Card2 =new Cards( player6Card2Value,player6Card2Suit);
                    player7Card1 =new Cards( player7Card1Value,player7Card1Suit);
                    player7Card2 =new Cards( player7Card2Value,player7Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player3Cards[1] =player3Card1;
                    player3Cards[2] = player3Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player4Cards[1] =player4Card1;
                    player4Cards[2] = player4Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player5Cards[1] =player5Card1;
                    player5Cards[2] = player5Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player6Cards[1] =player6Card1;
                    player6Cards[2] = player6Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player7Cards[1] =player7Card1;
                    player7Cards[2] = player7Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    break;
                case 8:
                    player1Card1 =new Cards( player1Card1Value,player1Card1Suit);
                    player1Card2 =new Cards( player1Card2Value,player1Card2Suit);
                    player2Card1 =new Cards( player2Card1Value,player2Card1Suit);
                    player2Card2 =new Cards( player2Card2Value,player2Card2Suit);
                    player3Card1 =new Cards( player3Card1Value,player3Card1Suit);
                    player3Card2 =new Cards( player3Card2Value,player3Card2Suit);
                    player4Card1 =new Cards( player4Card1Value,player4Card1Suit);
                    player4Card2 =new Cards( player4Card2Value,player4Card2Suit);
                    player5Card1 =new Cards( player5Card1Value,player5Card1Suit);
                    player5Card2 =new Cards( player5Card2Value,player5Card2Suit);
                    player6Card1 =new Cards( player6Card1Value,player6Card1Suit);
                    player6Card2 =new Cards( player6Card2Value,player6Card2Suit);
                    player7Card1 =new Cards( player7Card1Value,player7Card1Suit);
                    player7Card2 =new Cards( player7Card2Value,player7Card2Suit);
                    player8Card1 =new Cards(player8Card1Value,player8Card1Suit);
                    player8Card2 =new Cards(player8Card2Value,player8Card2Suit);
                    player1Cards[1] =player1Card1;
                    player1Cards[2] = player1Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player2Cards[1] =player2Card1;
                    player2Cards[2] = player2Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player3Cards[1] =player3Card1;
                    player3Cards[2] = player3Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player4Cards[1] =player4Card1;
                    player4Cards[2] = player4Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player5Cards[1] =player5Card1;
                    player5Cards[2] = player5Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player6Cards[1] =player6Card1;
                    player6Cards[2] = player6Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player7Cards[1] =player7Card1;
                    player7Cards[2] = player7Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    player8Cards[1] =player8Card1;
                    player8Cards[2] = player8Card2;
                    //Hand player1Hand = new Hand(player1Cards,player1);
                    
                    break;
                default:
                    return;
            }
        //Once all of the strings have something in them then they can be added to the diary here. 
        
        
    }
    JPanel playerPanelFactory(String name, int num){
        setName(name);
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
        //internal Button listeners
        boxValue1.addActionListener((ActionEvent e)-> {
               // card1Value=boxValue1.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 2:
                        player2Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 3:
                        player3Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 4:
                        player4Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 5:
                        player5Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 6:
                        player6Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 7:
                        player7Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    case 8:
                        player8Card1Value=(Rank)boxValue1.getSelectedItem();
                        break;
                    default:
                        return;
                }
        });
        boxSuit1.addActionListener((ActionEvent e)-> {
               // card1Suit=boxSuit1.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 2:
                        player2Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 3:
                        player3Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 4:
                        player4Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 5:
                        player5Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 6:
                        player6Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 7:
                        player7Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    case 8:
                        player8Card1Suit=(Suit)boxSuit1.getSelectedItem();
                        break;
                    default:
                        return;
                }
        });
        boxValue2.addActionListener((ActionEvent e)-> {
               // card2Value=boxValue2.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 2:
                        player2Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 3:
                        player3Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 4:
                        player4Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 5:
                        player5Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 6:
                        player6Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 7:
                        player7Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    case 8:
                        player8Card2Value=(Rank)boxValue2.getSelectedItem();
                        break;
                    default:
                        return;
                }
        });
        boxSuit2.addActionListener((ActionEvent e)-> {
               // card2Suit=boxSuit2.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 2:
                        player2Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 3:
                        player3Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 4:
                        player4Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 5:
                        player5Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 6:
                        player6Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 7:
                        player7Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    case 8:
                        player8Card2Suit=(Suit)boxSuit2.getSelectedItem();
                        break;
                    default:
                        return;
                }
        });
        return player;
    }
    
    
}
//PlayerPanelFactory creates a player panel that has the component of what a 
//player should be able to enter for their hand

