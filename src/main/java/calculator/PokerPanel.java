/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author elich
 */


public class PokerPanel extends JPanel{
    //
    ArrayList<Player> players = new ArrayList();
    ArrayList<JButton> invisButtons = new ArrayList();
    ArrayList<JButton> addPlayersButtons = new ArrayList();
    ArrayList<JButton> removePlayersButtons = new ArrayList();
    ArrayList<Hand> hands = new ArrayList();
    Rank [] cardValue = {Rank.TWO,Rank.THREE,Rank.FOUR,Rank.FIVE,Rank.SIX,Rank.SEVEN,Rank.EIGHT,Rank.NINE,Rank.TEN,Rank.JACK,Rank.QUEEN,Rank.KING,Rank.ACE};
    Suit [] suitValue = {Suit.HEARTS,Suit.CLUBS,Suit.DIAMONDS,Suit.SPADES};
    
    JPanel pokerTable = new JPanel();
    JPanel historyPanel = new PokerHistory(); 
    String numberOfPlayers = "";
    int numPlayers = 2;
    
    //
    //Border used for various components
    Border blackline = BorderFactory.createLineBorder(Color.black);
    
    public PokerPanel(JPanel contentPanel){
        SpringLayout layout = new SpringLayout();
        contentPanel.setLayout(layout);
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
        
        /**
        *Panels for players
        *Each player has their own panel that is on the screen. Table starts 
        *out with 2 players and selecting the number indicator establishes the 
        *the new number
        **/
        JPanel player1Panel = new JPanel();
        TitledBorder player1Title = BorderFactory.createTitledBorder(blackline, "Player 1");
        player1Title.setTitleJustification(TitledBorder.LEFT);
        player1Panel.setBorder(player1Title);
        player1Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer1 = new JButton("Add");
        JButton removePlayer1 = new JButton("Remove");
        addPlayersButtons.add(addPlayer1);
        removePlayersButtons.add(removePlayer1);
        repaint();

        JPanel player2Panel = new JPanel();
        TitledBorder player2Title = BorderFactory.createTitledBorder(blackline, "Player2");
        player2Title.setTitleJustification(TitledBorder.LEFT);
        player2Panel.setBorder(player2Title);
        player2Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer2 = new JButton("Add");
        JButton removePlayer2 = new JButton("Remove");
        addPlayersButtons.add(addPlayer2);
        removePlayersButtons.add(removePlayer2);
        repaint();

        JPanel player3Panel = new JPanel();
        TitledBorder player3Title = BorderFactory.createTitledBorder(blackline, "Player3");
        player3Title.setTitleJustification(TitledBorder.LEFT);
        player3Panel.setBorder(player3Title);
        player3Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer3 = new JButton("Add");
        JButton removePlayer3 = new JButton("Remove");
        addPlayersButtons.add(addPlayer3);
        removePlayersButtons.add(removePlayer3);
        repaint();
        
        JPanel player4Panel = new JPanel();
        TitledBorder player4Title = BorderFactory.createTitledBorder(blackline, "Player4");
        player4Title.setTitleJustification(TitledBorder.LEFT);
        player4Panel.setBorder(player4Title);
        player4Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer4 = new JButton("Add");
        JButton removePlayer4 = new JButton("Remove");
        addPlayersButtons.add(addPlayer4);
        removePlayersButtons.add(removePlayer4);
        repaint();

        JPanel player5Panel = new JPanel();
        TitledBorder player5Title = BorderFactory.createTitledBorder(blackline, "Player5");
        player5Title.setTitleJustification(TitledBorder.LEFT);
        player5Panel.setBorder(player5Title);
        player5Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer5 = new JButton("Add");
        JButton removePlayer5 = new JButton("Remove");
        addPlayersButtons.add(addPlayer5);
        removePlayersButtons.add(removePlayer5);
        repaint();

        JPanel player6Panel = new JPanel();
        TitledBorder player6Title = BorderFactory.createTitledBorder(blackline, "Player6");
        player6Title.setTitleJustification(TitledBorder.LEFT);
        player6Panel.setBorder(player6Title);
        player6Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer6 = new JButton("Add");
        JButton removePlayer6 = new JButton("Remove");
        addPlayersButtons.add(addPlayer6);
        removePlayersButtons.add(removePlayer6);
        repaint();

        JPanel player7Panel = new JPanel();
        TitledBorder player7Title = BorderFactory.createTitledBorder(blackline, "Player7");
        player7Title.setTitleJustification(TitledBorder.LEFT);
        player7Panel.setBorder(player7Title);
        player7Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer7 = new JButton("Add");
        JButton removePlayer7 = new JButton("Remove");
        addPlayersButtons.add(addPlayer7);
        removePlayersButtons.add(removePlayer7);
        repaint();
        
        JPanel player8Panel = new JPanel();
        TitledBorder player8Title = BorderFactory.createTitledBorder(blackline, "Player8");
        player8Title.setTitleJustification(TitledBorder.LEFT);
        player8Panel.setBorder(player8Title);
        player8Panel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width/3,pokerTable.getPreferredSize().height));
        JButton addPlayer8 = new JButton("Add");
        JButton removePlayer8 = new JButton("Remove");
        addPlayersButtons.add(addPlayer8);
        removePlayersButtons.add(removePlayer8);
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
        miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
        JButton databaseButton = new JButton("Save to Database");
        JButton clearButton = new JButton("Clear Form");
        miscPanel.add(clearButton);
        miscPanel.add(databaseButton);
        
        contentPanel.add(numPlayerPanel);
        contentPanel.add(player1Panel);
        contentPanel.add(player2Panel);
        contentPanel.add(player3Panel);
        contentPanel.add(player4Panel);
        contentPanel.add(player5Panel);
        contentPanel.add(player6Panel);
        contentPanel.add(player7Panel);
        contentPanel.add(player8Panel);
        contentPanel.add(pokerTable);
        contentPanel.add(calcPanel);
        contentPanel.add(miscPanel);
        ///setContent(contentPanel);
        
        
        //Layout Management Constraints
        layout.putConstraint(SpringLayout.NORTH, numPlayerPanel, 5, SpringLayout.NORTH, contentPanel);
        layout.putConstraint(SpringLayout.WEST, numPlayerPanel, 5, SpringLayout.WEST, player2Panel);
        
        layout.putConstraint(SpringLayout.NORTH, player1Panel, 5, SpringLayout.SOUTH, numPlayerPanel);
        
        layout.putConstraint(SpringLayout.WEST, player1Panel, 0, SpringLayout.WEST, pokerTable);
        
        layout.putConstraint(SpringLayout.NORTH, player2Panel, 5, SpringLayout.SOUTH, numPlayerPanel);
        layout.putConstraint(SpringLayout.WEST, player2Panel, 5, SpringLayout.EAST, player1Panel);
        
        layout.putConstraint(SpringLayout.NORTH, player3Panel, 5, SpringLayout.SOUTH, numPlayerPanel);
        layout.putConstraint(SpringLayout.EAST, player3Panel, 5, SpringLayout.EAST, pokerTable);
        layout.putConstraint(SpringLayout.WEST, player3Panel, 5, SpringLayout.EAST, player2Panel);
        
        layout.putConstraint(SpringLayout.WEST, player4Panel, 5, SpringLayout.WEST, contentPanel);
        layout.putConstraint(SpringLayout.NORTH, player4Panel,5, SpringLayout.SOUTH, player1Panel);
        
        layout.putConstraint(SpringLayout.WEST, pokerTable, 5, SpringLayout.EAST, player4Panel);
        layout.putConstraint(SpringLayout.NORTH, pokerTable, 5, SpringLayout.SOUTH, player1Panel);
        
        layout.putConstraint(SpringLayout.WEST, player5Panel, 5, SpringLayout.EAST, pokerTable);
        layout.putConstraint(SpringLayout.NORTH, player5Panel, 5, SpringLayout.SOUTH, player1Panel);
        
        layout.putConstraint(SpringLayout.WEST, calcPanel, 5, SpringLayout.WEST, contentPanel);
        layout.putConstraint(SpringLayout.NORTH, calcPanel, 5, SpringLayout.SOUTH, pokerTable);
        
        layout.putConstraint(SpringLayout.WEST, player6Panel, 0, SpringLayout.WEST, pokerTable);
        layout.putConstraint(SpringLayout.NORTH, player6Panel,5, SpringLayout.SOUTH, pokerTable);
        
        layout.putConstraint(SpringLayout.WEST, player7Panel, 5, SpringLayout.EAST, player6Panel);
        layout.putConstraint(SpringLayout.NORTH, player7Panel, 5, SpringLayout.SOUTH, pokerTable);
        
        layout.putConstraint(SpringLayout.WEST, player8Panel, 5, SpringLayout.EAST, player7Panel);
        layout.putConstraint(SpringLayout.EAST, player8Panel, 5, SpringLayout.EAST, pokerTable);
        layout.putConstraint(SpringLayout.NORTH, player8Panel,5, SpringLayout.SOUTH, pokerTable);
        
        layout.putConstraint(SpringLayout.WEST, miscPanel, 5, SpringLayout.EAST, player8Panel);
        layout.putConstraint(SpringLayout.NORTH, miscPanel, 5, SpringLayout.SOUTH, pokerTable);
        
        layout.putConstraint(SpringLayout.EAST, contentPanel, 5, SpringLayout.EAST, player5Panel);
        layout.putConstraint(SpringLayout.SOUTH, contentPanel, 5, SpringLayout.SOUTH, player7Panel);
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
            repaint();
            validate();
        });
        addPlayer2.addActionListener((ActionEvent e) -> {
            player2Panel.removeAll();
            player2Panel.add(playerPanelFactory("Player2",2));
            repaint();
            validate();
        });
        addPlayer3.addActionListener((ActionEvent e) -> {
            player3Panel.removeAll();
            player3Panel.add(playerPanelFactory("Player3",3));
            repaint();
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
        removePlayer1.addActionListener((ActionEvent e) -> {
            player1Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer2.addActionListener((ActionEvent e) -> {
            player2Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer3.addActionListener((ActionEvent e) -> {
            player3Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer4.addActionListener((ActionEvent e) -> {
            player4Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer5.addActionListener((ActionEvent e) -> {
            player5Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer6.addActionListener((ActionEvent e) -> {
            player6Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer7.addActionListener((ActionEvent e) -> {
            player7Panel.removeAll();
            repaint();
            revalidate();
        });
        removePlayer8.addActionListener((ActionEvent e) -> {
            player8Panel.removeAll();
            repaint();
            revalidate();
        });
        databaseButton.addActionListener((ActionEvent e) -> {
            Cards flop1 = new Cards((Rank)flop1Value.getSelectedItem(), (Suit)flop1Suit.getSelectedItem());
            Cards flop2 = new Cards((Rank)flop2Value.getSelectedItem(), (Suit)flop2Suit.getSelectedItem());
            Cards flop3 = new Cards((Rank)flop3Value.getSelectedItem(), (Suit)flop3Suit.getSelectedItem());
            Cards river = new Cards((Rank)riverValue.getSelectedItem(), (Suit)riverSuit.getSelectedItem());
            Cards turn = new Cards((Rank)turnValue.getSelectedItem(), (Suit)turnSuit.getSelectedItem());
            Cards [] flop = {flop1, flop2, flop3};
            makeDiaryEntry(flop,river,turn);
        });
        addPlayers(0,2);
        
        playerBox.addActionListener((ActionEvent e)-> {
            numPlayers = Integer.parseInt(playerBox.getSelectedItem().toString());            
            int newNumber = numPlayers;
            if(newNumber>players.size()){
                addPlayers(players.size(),newNumber);
            }else if(newNumber<players.size()){
                removePlayers(players.size(),newNumber);
            }
            contentPanel.revalidate();
        });
        
        add(contentPanel);
        repaint();
        validate();
    }
    //Method used to create a diary of the table
    void makeDiaryEntry(Cards [] flop, Cards turn, Cards river){
        Hand[] arrayHands = new Hand[hands.size()];        
        for(int i = 0;i<hands.size();i++){
            arrayHands[i] = hands.get(i);
        }
        System.out.println(arrayHands[0].toString());
        Game game = new Game();
        game.setFlop(flop);
        game.setTurn(turn);
        game.setRiver(river);
        game.setHands(arrayHands);                       
        //Once all of the strings have a something in them then they can be added to the diary here. 
        
    }
    JPanel playerPanelFactory(String name, int num){
        Player player = new Player(name);
        players.add(player);
        setName(name);
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JComboBox boxValue1 = new JComboBox(cardValue);
        JComboBox boxSuit1 = new JComboBox(suitValue);
        JComboBox boxValue2 = new JComboBox(cardValue);
        JComboBox boxSuit2 = new JComboBox(suitValue);
        c.gridx=0;
        c.gridy=0;
        playerPanel.add(boxValue1,c);
        c.gridx=1;
        playerPanel.add(boxSuit1,c);
        c.gridx=0;
        c.gridy=1;
        playerPanel.add(boxValue2,c);
        c.gridx=1;
        playerPanel.add(boxSuit2,c);
        JButton button = makeButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                Cards card1 = new Cards((Rank)boxValue1.getSelectedItem(), (Suit)boxSuit1.getSelectedItem());
                Cards card2 = new Cards((Rank)boxValue2.getSelectedItem(), (Suit)boxSuit2.getSelectedItem());
                Cards [] cards = {card1,card2};
                Hand hand = new Hand(cards,player);
                hands.add(hand);
            }
        });        
        return playerPanel;
    }
    public JButton makeButton(String name){
        JButton button = new JButton(name);
        button.setVisible(false);
        return new JButton(name);
    }
    public void addPlayers(int start,int end){
        for(int i =start;i<end;i++){
                JButton button = addPlayersButtons.get(i);
                button.doClick();
            }        
    }
    public void removePlayers(int start,int end){
        for(int i =start-1;i>=end;i--){
                JButton button = removePlayersButtons.get(i);
                button.doClick();
            }
        for(int i = start-1;i>=end;i--){
            players.remove(players.get(players.size()-1));
        }
    }
    
    
}

