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
    String numberOfPlayers = "";
    int numPlayers = 1;
    
    //
    String flop1ValueString="",flop1SuitString = "",flop2ValueString="",
            flop2SuitString = "",flop3ValueString="",flop3SuitString="",
            riverValueString="",riverSuitString="",turnValueString="",turnSuitString="";
    String flop1="",flop2="",flop3="",river="",turn="";
    
    String player1Card1Value="",player1Card1Suit="",player1Card2Value="",player1Card2Suit="",
            player2Card1Value="",player2Card1Suit="",player2Card2Value="",player2Card2Suit="",
            player3Card1Value="",player3Card1Suit="",player3Card2Value="",player3Card2Suit="",
            player4Card1Value="",player4Card1Suit="",player4Card2Value="",player4Card2Suit="",
            player5Card1Value="",player5Card1Suit="",player5Card2Value="",player5Card2Suit="",
            player6Card1Value="",player6Card1Suit="",player6Card2Value="",player6Card2Suit="",
            player7Card1Value="",player7Card1Suit="",player7Card2Value="",player7Card2Suit="",
            player8Card1Value="",player8Card1Suit="",player8Card2Value="",player8Card2Suit="";
    
    String player1Card1="",player1Card2="",
            player2Card1="",player2Card2="",
            player3Card1="",player3Card2="",
            player4Card1="",player4Card2="",
            player5Card1="",player5Card2="",
            player6Card1="",player6Card2="",
            player7Card1="",player7Card2="",
            player8Card1="",player8Card2="";
    
    String card1Value="",card1Suit="",card2Value="",card2Suit="";
    String card1="",card2="";
    
    //Database inception
    //DBConnection db = new DBConnection();
    
//    PlayerPanelFactory playerFactory = new PlayerPanelFactory();
    Border blackline = BorderFactory.createLineBorder(Color.black);
    
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
        addPlayer1.setVisible(false);
        player1.add(addPlayer1);
        repaint();

        JPanel player2 = new JPanel();
        TitledBorder player2Title = BorderFactory.createTitledBorder(blackline, "Player2");
        player2Title.setTitleJustification(TitledBorder.LEFT);
        player2.setBorder(player2Title);
        JButton addPlayer2 = new JButton("Add");
        addPlayer2.setVisible(false);
        player2.add(addPlayer2);
        repaint();

        JPanel player3 = new JPanel();
        TitledBorder player3Title = BorderFactory.createTitledBorder(blackline, "Player3");
        player3Title.setTitleJustification(TitledBorder.LEFT);
        player3.setBorder(player3Title);
        JButton addPlayer3 = new JButton("Add");
        addPlayer3.setVisible(false);
        player3.add(addPlayer3);
        repaint();
        
        JPanel player4 = new JPanel();
        TitledBorder player4Title = BorderFactory.createTitledBorder(blackline, "Player4");
        player4Title.setTitleJustification(TitledBorder.LEFT);
        player4.setBorder(player4Title);
        JButton addPlayer4 = new JButton("Add");
        addPlayer4.setVisible(false);
        player4.add(addPlayer4);
        repaint();

        JPanel player5 = new JPanel();
        TitledBorder player5Title = BorderFactory.createTitledBorder(blackline, "Player5");
        player5Title.setTitleJustification(TitledBorder.LEFT);
        player5.setBorder(player5Title);
        JButton addPlayer5 = new JButton("Add");
        addPlayer5.setVisible(false);
        player5.add(addPlayer5);
        repaint();

        JPanel player6 = new JPanel();
        TitledBorder player6Title = BorderFactory.createTitledBorder(blackline, "Player6");
        player6Title.setTitleJustification(TitledBorder.LEFT);
        player6.setBorder(player6Title);
        JButton addPlayer6 = new JButton("Add");
        addPlayer6.setVisible(false);
        player6.add(addPlayer6);
        repaint();

        JPanel player7 = new JPanel();
        TitledBorder player7Title = BorderFactory.createTitledBorder(blackline, "Player7");
        player7Title.setTitleJustification(TitledBorder.LEFT);
        player7.setBorder(player7Title);
        JButton addPlayer7 = new JButton("Add");
        addPlayer7.setVisible(false);
        player7.add(addPlayer7);
        repaint();
        
        JPanel player8 = new JPanel();
        TitledBorder player8Title = BorderFactory.createTitledBorder(blackline, "Player8");
        player8Title.setTitleJustification(TitledBorder.LEFT);
        player8.setBorder(player8Title);
        JButton addPlayer8 = new JButton("Add");
        addPlayer8.setVisible(false);
        player8.add(addPlayer8);
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
            player1.removeAll();
            player1.add(playerPanelFactory("Player1",1));
            //welcomeScreen.get
            repaint();
            validate();
        });
        addPlayer2.addActionListener((ActionEvent e) -> {
            player2.removeAll();
            player2.add(playerPanelFactory("Player2",2));
            getParent().repaint();
     //       contentPane.repaint();
            validate();
        });
        addPlayer3.addActionListener((ActionEvent e) -> {
            player3.removeAll();
            player3.add(playerPanelFactory("Player3",3));
            getParent().repaint();
    //        contentPane.repaint();
            validate();
        });
        addPlayer4.addActionListener((ActionEvent e) -> {
            player4.removeAll();
            player4.add(playerPanelFactory("Player4",4));
            repaint();
            validate();
        });
        addPlayer5.addActionListener((ActionEvent e) -> {
            player5.removeAll();
            player5.add(playerPanelFactory("Player5",5));
            repaint();
            validate();
        });
        addPlayer6.addActionListener((ActionEvent e) -> {
            player6.removeAll();
            player6.add(playerPanelFactory("Player6",6));
            repaint();
            validate();
        });
        addPlayer7.addActionListener((ActionEvent e) -> {
            player7.removeAll();
            player7.add(playerPanelFactory("Player7",7));
            repaint();
            validate();
        });
        addPlayer8.addActionListener((ActionEvent e) -> {
            player8.removeAll();
            player8.add(playerPanelFactory("Player8",8));
            repaint();
            validate();
        });
        databaseButton.addActionListener((ActionEvent e) -> {
            makeDiaryEntry(numPlayers);
        });
        
        //Listeners for ComboBoxes 
        flop1Value.addActionListener((ActionEvent e)-> {
            flop1ValueString=flop1Value.getSelectedItem().toString();
        });
        flop1Suit.addActionListener((ActionEvent e)-> {
            flop1SuitString=flop1Suit.getSelectedItem().toString();
        });
        flop2Value.addActionListener((ActionEvent e)-> {
            flop2ValueString=flop2Value.getSelectedItem().toString();        
        });
        flop2Suit.addActionListener((ActionEvent e)-> {
            flop2SuitString=flop2Suit.getSelectedItem().toString();
        });
        flop3Value.addActionListener((ActionEvent e)-> {
            flop3ValueString=flop3Value.getSelectedItem().toString();        
        });
        flop3Suit.addActionListener((ActionEvent e)-> {
            flop3SuitString=flop3Suit.getSelectedItem().toString();        
        });
        riverValue.addActionListener((ActionEvent e)-> {
            riverValueString=riverValue.getSelectedItem().toString();        
        });
        riverSuit.addActionListener((ActionEvent e)-> {
            riverSuitString=riverSuit.getSelectedItem().toString();        
        });
        turnValue.addActionListener((ActionEvent e)-> {
            turnValueString=turnValue.getSelectedItem().toString();        
        });
        turnSuit.addActionListener((ActionEvent e)-> {
            turnSuitString=turnSuit.getSelectedItem().toString();  
        });
        //Switch used to toggle the invisible buttons on the screen
        playerBox.addActionListener((ActionEvent e)-> {
            numberOfPlayers = playerBox.getSelectedItem().toString();
            int numPlayers = Integer.parseInt(numberOfPlayers);
            switch(numPlayers){
                case 1:
                    addPlayer1.doClick();
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
        repaint();
        validate();
    }
    //Method used to create a diary of the table
    void makeDiaryEntry(int num){
        flop1=flop1ValueString+" "+flop1SuitString;
        flop2=flop2ValueString+" "+flop2SuitString;
        flop3=flop3ValueString+" "+flop3SuitString;
        river=riverValueString+" "+riverSuitString;
        turn=turnValueString+" "+turnSuitString;
        
        switch(num){
                case 1:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    break;
                case 2:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    break;
                case 3:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    player3Card1 = player3Card1Value+" "+player3Card1Suit;player3Card2 = player3Card2Value+""+player3Card2Suit;
                    break;
                case 4:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    player3Card1 = player3Card1Value+" "+player3Card1Suit;player3Card2 = player3Card2Value+""+player3Card2Suit;
                    player4Card1 = player4Card1Value+" "+player4Card1Suit;player4Card2 = player4Card2Value+""+player4Card2Suit;
                    break;
                case 5:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    player3Card1 = player3Card1Value+" "+player3Card1Suit;player3Card2 = player3Card2Value+""+player3Card2Suit;
                    player4Card1 = player4Card1Value+" "+player4Card1Suit;player4Card2 = player4Card2Value+""+player4Card2Suit;
                    player5Card1 = player5Card1Value+" "+player5Card1Suit;player5Card2 = player5Card2Value+""+player5Card2Suit;
                    break;
                case 6:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    player3Card1 = player3Card1Value+" "+player3Card1Suit;player3Card2 = player3Card2Value+""+player3Card2Suit;
                    player4Card1 = player4Card1Value+" "+player4Card1Suit;player4Card2 = player4Card2Value+""+player4Card2Suit;
                    player5Card1 = player5Card1Value+" "+player5Card1Suit;player5Card2 = player5Card2Value+""+player5Card2Suit;
                    player6Card1 = player6Card1Value+" "+player6Card1Suit;player6Card2 = player6Card2Value+""+player6Card2Suit;
                    break;
                case 7:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    player3Card1 = player3Card1Value+" "+player3Card1Suit;player3Card2 = player3Card2Value+""+player3Card2Suit;
                    player4Card1 = player4Card1Value+" "+player4Card1Suit;player4Card2 = player4Card2Value+""+player4Card2Suit;
                    player5Card1 = player5Card1Value+" "+player5Card1Suit;player5Card2 = player5Card2Value+""+player5Card2Suit;
                    player6Card1 = player6Card1Value+" "+player6Card1Suit;player6Card2 = player6Card2Value+""+player6Card2Suit;
                    player7Card1 = player7Card1Value+" "+player7Card1Suit;player7Card2 = player7Card2Value+""+player7Card2Suit;
                    break;
                case 8:
                    player1Card1 = player1Card1Value+" "+player1Card1Suit;player1Card2 = player1Card2Value+""+player1Card2Suit;
                    player2Card1 = player2Card1Value+" "+player2Card1Suit;player2Card2 = player2Card2Value+""+player2Card2Suit;
                    player3Card1 = player3Card1Value+" "+player3Card1Suit;player3Card2 = player3Card2Value+""+player3Card2Suit;
                    player4Card1 = player4Card1Value+" "+player4Card1Suit;player4Card2 = player4Card2Value+""+player4Card2Suit;
                    player5Card1 = player5Card1Value+" "+player5Card1Suit;player5Card2 = player5Card2Value+""+player5Card2Suit;
                    player6Card1 = player6Card1Value+" "+player6Card1Suit;player6Card2 = player6Card2Value+""+player6Card2Suit;
                    player7Card1 = player7Card1Value+" "+player7Card1Suit;player7Card2 = player7Card2Value+""+player7Card2Suit;
                    player8Card1 = player8Card1Value+" "+player8Card1Suit;player8Card2 = player8Card2Value+""+player8Card2Suit;
                    
                    break;
                default:
                    return;
            }
        //Once all of the strings have a something in them then they can be added to the diary here. 
        
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
                        player1Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 2:
                        player2Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 3:
                        player3Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 4:
                        player4Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 5:
                        player5Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 6:
                        player6Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 7:
                        player7Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    case 8:
                        player8Card1Value=boxValue1.getSelectedItem().toString();
                        break;
                    default:
                        return;
                }
        });
        boxSuit1.addActionListener((ActionEvent e)-> {
               // card1Suit=boxSuit1.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 2:
                        player2Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 3:
                        player3Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 4:
                        player4Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 5:
                        player5Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 6:
                        player6Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 7:
                        player7Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    case 8:
                        player8Card1Suit=boxSuit1.getSelectedItem().toString();
                        break;
                    default:
                        return;
                }
        });
        boxValue2.addActionListener((ActionEvent e)-> {
               // card2Value=boxValue2.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 2:
                        player2Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 3:
                        player3Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 4:
                        player4Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 5:
                        player5Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 6:
                        player6Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 7:
                        player7Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    case 8:
                        player8Card2Value=boxValue2.getSelectedItem().toString();
                        break;
                    default:
                        return;
                }
        });
        boxSuit2.addActionListener((ActionEvent e)-> {
               // card2Suit=boxSuit2.getSelectedItem().toString();
                switch(num){
                    case 1: 
                        player1Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 2:
                        player2Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 3:
                        player3Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 4:
                        player4Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 5:
                        player5Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 6:
                        player6Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 7:
                        player7Card2Value=boxSuit2.getSelectedItem().toString();
                        break;
                    case 8:
                        player8Card2Value=boxSuit2.getSelectedItem().toString();
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

