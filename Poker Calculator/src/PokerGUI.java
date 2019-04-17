import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author keith
 */
public class PokerGUI extends JFrame{

    //Variables
    private String title;
    private int width, height;
    private JTabbedPane game;
    private JScrollPane history;
    private JTable communityCard, handOutcome;
    private JComboBox flop1Card, flop2Card, flop3Card, turnCard, riverCard, flop1Suit, 
            flop2Suit, flop3Suit, turnSuit, riverSuit, cardNumberBoxPlayer1A, cardNumberBoxPlayer2A, 
            cardNumberBoxPlayer3A, cardNumberBoxPlayer4A, cardNumberBoxPlayer5A, cardNumberBoxPlayer6A,
            cardNumberBoxPlayer1B, cardNumberBoxPlayer2B,cardNumberBoxPlayer3B, cardNumberBoxPlayer4B, 
            cardNumberBoxPlayer5B, cardNumberBoxPlayer6B,cardSuitBoxPlayer1A, cardSuitBoxPlayer2A, cardSuitBoxPlayer3A, 
            cardSuitBoxPlayer4A, cardSuitBoxPlayer5A, cardSuitBoxPlayer6A, cardSuitBoxPlayer1B, cardSuitBoxPlayer2B, cardSuitBoxPlayer3B, 
            cardSuitBoxPlayer4B, cardSuitBoxPlayer5B, cardSuitBoxPlayer6B, numOfPlayers;
    private JLabel flopCard1Lbl, flopCard2Lbl, flopCard3Lbl, turnCardLbl, riverCardLbl, 
            playerNameLbl, card1LabelLbl, card2LabelLbl, playerWinChanceLbl;
    private JPanel homePanel, playerPanel, handDetails;
    private JButton addPlayer, calculate, newGame, deleteEntry, goToCalculatorButton,
            goToHistoryButton, fold, saveToDatabase;
    
    //Constructor
    protected PokerGUI()    {
        super("Texas Holdem Poker Calculator");
        this.setWindowTitle("Texas Holdem Poker Calculator");
        this.setWindowWidth(1500);
        this.setWindowHeight(1000);
    }
    
    protected PokerGUI(String title, int width, int height) {
        super(title);
        this.setWindowTitle(title);
        this.setWindowWidth(width);
        this.setWindowHeight(height);
    }
    
    //Setters
    private void setWindowTitle(String title) {
        this.title = title;
    }
    
    private void setWindowWidth(int width) {
        if (width < 1500) {
            this.width = 1500;
        } else {
            this.width = width;
        }
    }

    private void setWindowHeight(int height) {
        if (height < 1000) {
            this.height = 1000;
        } else {
            this.height = height;
        }
    }

    //Getters
    protected String getWindowTitle() {
        return this.title;
    }

    protected int getWindowWidth() {
        return this.width;
    }

    protected int getWindowHeight() {
        return this.height;
    }
    
    //Creating GUI, buttons, text field, text area
    private void constructGUI() {

        //Grid layout, GUI perameters
        this.homePanel = new JPanel(new BorderLayout()); 
        this.playerPanel = new JPanel(new GridLayout(1, 1, 1, 1));
        this.handDetails = new JPanel(new GridLayout(1, 1, 1, 1));
        this.game = new JTabbedPane();
        game.add(playerPanel, "Player Panel");
        game.add(handDetails, "History Panel");

        //Create buttons
        this.goToCalculatorButton = new JButton("Poker Odds Calculator");
        this.goToHistoryButton = new JButton("Saved Poker Hands");
        this.addPlayer = new JButton("Add Player");
        this.calculate = new JButton("Calculate");
        this.deleteEntry = new JButton("Delete Entry");
        this.newGame = new JButton("Start Over");
        this.fold = new JButton("Fold Hand");
        this.saveToDatabase = new JButton("Save hand to database");
        
        //JComboBox creation
        String[] numPlayers = {"2", "3", "4", "5", "6"};
        this.numOfPlayers = new JComboBox<>(numPlayers);

        String[] cardNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"};
        this.cardNumberBoxPlayer1A = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer1B = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer2A = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer2B = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer3A = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer3B = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer4A = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer4B = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer5A = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer5B = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer6A = new JComboBox<>(cardNumbers);
        this.cardNumberBoxPlayer6B = new JComboBox<>(cardNumbers);
        this.flop1Card = new JComboBox<>(cardNumbers);
        this.flop2Card = new JComboBox<>(cardNumbers);
        this.flop3Card = new JComboBox<>(cardNumbers);
        this.turnCard = new JComboBox<>(cardNumbers);
        this.riverCard = new JComboBox<>(cardNumbers);
        
        String[] cardSuit = {"Hearts", "Spades", "Diamonds", "Clubs"};
        this.cardSuitBoxPlayer1A = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer1B = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer2A = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer2B = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer3A = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer3B = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer4A = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer4B = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer5A = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer5B = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer6A = new JComboBox<>(cardSuit);
        this.cardSuitBoxPlayer6B = new JComboBox<>(cardSuit);
        this.flop1Suit = new JComboBox<>(cardSuit);
        this.flop2Suit = new JComboBox<>(cardSuit);
        this.flop3Suit = new JComboBox<>(cardSuit);
        this.turnSuit = new JComboBox<>(cardSuit);
        this.riverSuit = new JComboBox<>(cardSuit);
        
        this.goToCalculatorButton.addActionListener((ActionEvent e) ->   {
            this.game();
        });
        
        this.goToHistoryButton.addActionListener((ActionEvent e) -> {
            this.history();
        });
        
        
        String [] columnNames = {"Player", "Win Percentage"};
        Object [] = 
        this.handOutcome = new JTable("Outcome");
        this.history = new JScrollPane(handOutcome);
     
    }
        
        /*
        this.optionsPanel.add(this.readBtn);  
        this.optionsPanel.add(this.searchTxtLbl);  
        this.optionsPanel.add(this.searchTxt);  
        this.optionsPanel.add(this.searchBoxString);   
        this.optionsPanel.add(this.searchBtn);  
        this.optionsPanel.add(this.sortTxtLbl);  
        this.optionsPanel.add(this.sortPortBoxString);  
        this.optionsPanel.add(this.sortTargetBoxString); 
        this.optionsPanel.add(this.sortTypeBoxString);  
        this.optionsPanel.add(this.sortBtn);               

        //JTree Set-up
        this.mainTree = new JTree();
        this.mainTree.setModel(null);
        this.mainTree.getSelectionModel().setSelectionMode(
            TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.treeScrollPane = new JScrollPane(this.mainTree);

        //Tree buttons
        this.treeExpandBtn = new JButton("Expand all");
        this.treeCollapseBtn = new JButton("Collapse all");
        this.treeDetailsBtn = new JButton("More info");

        //Add tree buttons
        this.treeButtonsPanel.add(this.treeExpandBtn);
        this.treeButtonsPanel.add(this.treeCollapseBtn);
        this.treeButtonsPanel.add(this.treeDetailsBtn);
        
        //Parameters of layout
        this.treePanel.add(this.treeScrollPane, BorderLayout.CENTER);
        this.treePanel.add(this.treeButtonsPanel, BorderLayout.SOUTH);

        //Search area
        this.searchResultsTxtArea = new JTextArea();
        this.searchResultsTxtArea.setEditable(false);
        this.searchResultsTxtArea.setFont(new Font("Monospaced", 0, 12));
        this.searchResultsTxtArea.setLineWrap(true);

        //Add to scroll pane
        this.searchScrollPane = new JScrollPane(this.searchResultsTxtArea);

        // Assemble Panel
        this.worldPanel.add(this.treePanel);
        this.worldPanel.add(this.searchScrollPane);

        //Jobs status
        this.jobsStatusTxtArea = new JTextArea();
        this.jobsStatusTxtArea.setEditable(false);
        this.jobsStatusTxtArea.setFont(new Font("Monospaced", 0, 11));
        this.jobsStatusTxtArea.setLineWrap(true);

        //Jobs status logs
        this.jobsScrollPane = new JScrollPane(this.jobsScrollPanel); 
        this.jobsStatusScrollPane = new JScrollPane(this.jobsStatusTxtArea); 
        this.jobsPoolScrollPane = new JScrollPane(this.jobsPoolPanel); 

        //Add to scroll pane
        this.jobsLogsPanel.add(this.jobsStatusScrollPane);
        this.jobsLogsPanel.add(this.jobsPoolScrollPane);
        this.jobsPanel.add(this.jobsScrollPane);
        this.jobsPanel.add(this.jobsLogsPanel);

        //search and status to panel
        this.jobsLogsPanel.add(this.jobsStatusScrollPane);
        this.jobsLogsPanel.add(this.jobsStatusScrollPane);
        this.jobsLogsPanel.add(this.jobsPoolScrollPane);
        this.jobsPanel.add(this.jobsScrollPane);
        this.jobsPanel.add(this.jobsLogsPanel);

        //Layout of main panel
        this.mainPanel.add(this.optionsPanel, BorderLayout.NORTH);
        this.mainPanel.add(this.worldPanel, BorderLayout.WEST);
        this.mainPanel.add(this.jobsPanel, BorderLayout.CENTER);

        //Add boarders with titles
        this.optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        this.treePanel.setBorder(BorderFactory.createTitledBorder("World tree"));
        this.jobsScrollPane.setBorder(BorderFactory.createTitledBorder("Jobs Listing"));
        this.searchScrollPane.setBorder(BorderFactory.createTitledBorder("Search/sort log"));
        this.jobsStatusScrollPane.setBorder(BorderFactory.createTitledBorder("Job status log"));
        this.jobsPoolScrollPane.setBorder(BorderFactory.createTitledBorder("Job resource pool"));
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        //Add boarder color
         this.jobsStatusTxtArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.searchResultsTxtArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        //Add color
        this.jobsScrollPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.jobsScrollPanel.setBackground(Color.WHITE);
        this.jobsPoolPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.jobsPoolPanel.setBackground(Color.WHITE);

        //Action Listeners
        this.sortTargetBoxString.addActionListener((ActionEvent e) -> {
            this.provideProperSortOptions();
        });

        this.readBtn.addActionListener((ActionEvent e) -> {
            this.readFile();
        });

        this.searchBtn.addActionListener((ActionEvent e) -> {
            this.searchWorldContents();
        });

        this.sortBtn.addActionListener((ActionEvent e) -> {
            this.sortWorldContents();
        });

        this.treeExpandBtn.addActionListener((ActionEvent e) -> {
            this.toggleNodes("expandRow");
        });

        this.treeCollapseBtn.addActionListener((ActionEvent e) -> {
            this.toggleNodes("collapseRow");
        });

        this.treeDetailsBtn.addActionListener((ActionEvent e) -> {
            this.displaySelectionDetails();
        });

        //layout of main frame
        this.mainFrame = new JFrame(this.getWindowTitle());
        this.mainFrame.setContentPane(this.mainPanel);
        this.mainFrame.setSize(this.getWindowWidth(), this.getWindowHeight());
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    */
    
    public static void main(String[] args) {
        PokerGUI newGUI = new PokerGUI();
        newGUI.constructGUI();
    }
    
}
