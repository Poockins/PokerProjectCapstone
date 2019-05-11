/**
 * PokerPanel is the main panel displaying the poker table with its players and hands
 *
 * @author elich
 * @author Yuko Takegoshi
 */

package calculator;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class PokerPanel extends JPanel {

  HashMap<Integer, Player> players = new HashMap<>();

  JPanel[] playerPanels = new JPanel[8];
  JPanel[] playerCardPanels = new JPanel[8];

  //  ArrayList<Hand> hands = new ArrayList();
  Rank[] cardValue = Rank.values();
  Suit[] suitValue = Suit.values();

  ArrayList<HashMap<String, JComboBox>> tableCardComponents = new ArrayList<>();
  HashMap<Integer, ArrayList<JPanel>> playerCardComponents = new HashMap<>();
  JPanel pokerTable = new JPanel();

  //Border used for various components
  Border blackline = BorderFactory.createLineBorder(Color.black);

  public PokerPanel(JPanel contentPanel) {
    SpringLayout layout = new SpringLayout();
    contentPanel.setLayout(layout);
    pokerTable.setLayout(new GridBagLayout());
    pokerTable.setBorder(blackline);

    setupCommunal();

    /**
     *Panels for players
     *Each player has their own panel that is on the screen. Table starts
     *out with 2 players and selecting the number indicator establishes the
     *the new number
     **/

    for (int i = 0; i < 8; i++) {
      JPanel playerPanel = new JPanel(new CardLayout());
      String playerName = String.format("Player %d", i + 1);
      TitledBorder playerTitle = BorderFactory
          .createTitledBorder(blackline, playerName);
      playerTitle.setTitleJustification(TitledBorder.LEFT);
      playerPanel.setBorder(playerTitle);
      playerPanel.setPreferredSize(new Dimension(pokerTable.getPreferredSize().width / 3,
          pokerTable.getPreferredSize().height));
      playerPanel.setName(String.format("%d_player_panel", i));
      JPanel emptyPanel = emptyPanel(i);
      JPanel cardPanel = playerCardPanel(i);
      playerPanel.add(emptyPanel, "empty");
      playerPanel.add(cardPanel, "cards");

      playerPanels[i] = playerPanel;

      repaint();
    }

    //Calculation Panel
    String odds = "", cardsNeeded = "";
    String[][] dataRow = {{odds, cardsNeeded}, {"", ""}};
    String[] columnNames = {"Odds", "Cards"};
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

    //Misc. Components
    JPanel miscPanel = new JPanel();
    miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
    JButton databaseButton = new JButton("Save to Database");
    JButton clearButton = new JButton("Clear Form");
    miscPanel.add(clearButton);
    miscPanel.add(databaseButton);

    for (int i = 0; i < 8; i++) {
      contentPanel.add(playerPanels[i]);
    }

    contentPanel.add(pokerTable);
    contentPanel.add(calcPanel);
    contentPanel.add(miscPanel);

    layout.putConstraint(SpringLayout.WEST, playerPanels[0], 0, SpringLayout.WEST, pokerTable);

    layout.putConstraint(SpringLayout.WEST, playerPanels[1], 5, SpringLayout.EAST,
        playerPanels[0]);

    layout.putConstraint(SpringLayout.EAST, playerPanels[2], 5, SpringLayout.EAST, pokerTable);
    layout.putConstraint(SpringLayout.WEST, playerPanels[2], 5, SpringLayout.EAST,
        playerPanels[1]);

    layout
        .putConstraint(SpringLayout.WEST, playerPanels[3], 5, SpringLayout.WEST, contentPanel);
    layout.putConstraint(SpringLayout.NORTH, playerPanels[3], 5, SpringLayout.SOUTH,
        playerPanels[0]);

    layout.putConstraint(SpringLayout.WEST, pokerTable, 5, SpringLayout.EAST, playerPanels[3]);
    layout
        .putConstraint(SpringLayout.NORTH, pokerTable, 5, SpringLayout.SOUTH, playerPanels[0]);

    layout.putConstraint(SpringLayout.WEST, playerPanels[4], 5, SpringLayout.EAST, pokerTable);
    layout.putConstraint(SpringLayout.NORTH, playerPanels[4], 5, SpringLayout.SOUTH,
        playerPanels[0]);

    layout.putConstraint(SpringLayout.WEST, calcPanel, 5, SpringLayout.WEST, contentPanel);
    layout.putConstraint(SpringLayout.NORTH, calcPanel, 5, SpringLayout.SOUTH, pokerTable);

    layout.putConstraint(SpringLayout.WEST, playerPanels[5], 0, SpringLayout.WEST, pokerTable);
    layout
        .putConstraint(SpringLayout.NORTH, playerPanels[5], 5, SpringLayout.SOUTH, pokerTable);

    layout.putConstraint(SpringLayout.WEST, playerPanels[6], 5, SpringLayout.EAST,
        playerPanels[5]);
    layout
        .putConstraint(SpringLayout.NORTH, playerPanels[6], 5, SpringLayout.SOUTH, pokerTable);

    layout.putConstraint(SpringLayout.WEST, playerPanels[7], 5, SpringLayout.EAST,
        playerPanels[6]);
    layout.putConstraint(SpringLayout.EAST, playerPanels[7], 5, SpringLayout.EAST, pokerTable);
    layout
        .putConstraint(SpringLayout.NORTH, playerPanels[7], 5, SpringLayout.SOUTH, pokerTable);

    layout.putConstraint(SpringLayout.WEST, miscPanel, 5, SpringLayout.EAST, playerPanels[7]);
    layout.putConstraint(SpringLayout.NORTH, miscPanel, 5, SpringLayout.SOUTH, pokerTable);

    layout
        .putConstraint(SpringLayout.EAST, contentPanel, 5, SpringLayout.EAST, playerPanels[4]);
    layout.putConstraint(SpringLayout.SOUTH, contentPanel, 5, SpringLayout.SOUTH,
        playerPanels[6]);


    Cards[] flop = new Cards[3];
    databaseButton.addActionListener((ActionEvent e) -> {
      for (int i = 0; i < 3; i++) {
        Cards card = new Cards((Rank) tableCardComponents.get(i).get("value").getSelectedItem(),
            (Suit) tableCardComponents.get(i).get("suit").getSelectedItem());
        flop[i] = card;
      }

      Cards turn = new Cards((Rank) tableCardComponents.get(3).get("value").getSelectedItem(),
          (Suit) tableCardComponents.get(3).get("suit").getSelectedItem());

      Cards river = new Cards((Rank) tableCardComponents.get(4).get("value").getSelectedItem(),
          (Suit) tableCardComponents.get(4).get("suit").getSelectedItem());

      makeDiaryEntry(flop, turn, river);
    });

    add(contentPanel);
    repaint();
    validate();
  }

  //Method used to create a diary of the table
  void makeDiaryEntry(Cards[] flop, Cards turn, Cards river) {
    Hand[] hands = findHands();

    try {
      Game game = new Game();
      for (Hand hand : hands) {
        hand.setGame(game);
        hand.save();
      }
      game.setFlop(flop);
      game.setTurn(turn);
      game.setRiver(river);
      game.save();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  private JPanel playerCardPanel(int index) {
    JPanel playerCardPanel = new JPanel();
    playerCardPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    ArrayList<JPanel> cardPanels = new ArrayList<>();
    c.gridx = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    for (int i = 0; i < 2; i++) {
      JComboBox<Rank> boxValue = new JComboBox<>(cardValue);
      JComboBox<Suit> boxSuit = new JComboBox<>(suitValue);

      JPanel cardPanel = new JPanel(new GridBagLayout());
      GridBagConstraints cardConstraints = new GridBagConstraints();
      cardPanel.setName(String.format("%d_player_cards_%d", index, i));
      cardConstraints.gridx = 0;
      cardConstraints.gridy = 0;
      cardPanel.add(boxValue, cardConstraints);
      cardConstraints.gridx = 1;
      cardConstraints.gridwidth = 2;
      cardPanel.add(boxSuit);

      cardPanels.add(cardPanel);

      c.gridy = i;
      playerCardPanel.add(cardPanel, c);
    }

    playerCardComponents.put(index, cardPanels);

    JButton removeButton = removeButton(index);
    c.gridy = 2;
    c.fill = GridBagConstraints.NONE;
    playerCardPanel.add(removeButton, c);
    playerCardPanel.setName(String.format("%d_player_card_panel", index));
    playerCardPanels[index] = playerCardPanel;
    return playerCardPanel;
  }

  private JPanel emptyPanel(int index) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JButton addPlayer = addButton(index);

    c.gridx = 1;
    c.gridy = 1;

    panel.add(addPlayer, c);

    return panel;
  }

  private JButton addButton(int index) {
    JButton button = new JButton("Add player");
    button.setName(String.format("%d_player_add", index));
    button.addActionListener((ActionEvent e) -> {
      JButton source = (JButton) e.getSource();
      String name = source.getName();
      int sourceIndex = Integer.parseInt(name.split("_")[0]);
      JPanel parent = playerPanels[sourceIndex];
      CardLayout layout = (CardLayout) parent.getLayout();
      layout.next(parent);
      Player player = new Player(String.format("Player %d", sourceIndex + 1));

      players.put(sourceIndex, player);

      repaint();
      validate();
    });

    return button;
  }

  private JButton removeButton(int index) {
    JButton button = new JButton("Remove player");
    button.setName(String.format("%d_player_remove", index));
    button.addActionListener((ActionEvent e) -> {
      JButton source = (JButton) e.getSource();
      String name = source.getName();
      int sourceIndex = Integer.parseInt(name.split("_")[0]);
      JPanel parent = playerPanels[sourceIndex];
      CardLayout layout = (CardLayout) parent.getLayout();
      layout.next(parent);

      clearPlayerData(sourceIndex);

      repaint();
      validate();
    });

    return button;
  }

  private void setupCommunal() {
    GridBagConstraints tableConstraints = new GridBagConstraints();
    tableConstraints.gridx = 0;
    tableConstraints.gridy = 0;
    tableConstraints.insets = new Insets(2, 1, 1, 1);
    JLabel flopLabel = new JLabel("Flop");
    pokerTable.add(flopLabel, tableConstraints);
    tableConstraints.gridy = 1;

    // Create and add Poker Table components
    int tableGridx = 1;
    for (int i = 0; i < 5; i++) {
      JComboBox<Rank> value = new JComboBox<>(cardValue);
      JComboBox<Suit> suit = new JComboBox<>(suitValue);

      HashMap<String, JComboBox> boxes = new HashMap<>();

      boxes.put("value", value);
      boxes.put("suit", suit);

      tableCardComponents.add(boxes);

      if (i < 3) {
        pokerTable.add(value, tableConstraints);
        tableConstraints.gridx = tableGridx;
        tableGridx++;
        pokerTable.add(suit, tableConstraints);
        tableConstraints.gridx = tableGridx;
        tableGridx++;
      }
    }


    //Start of the river
    tableConstraints.gridy = 2;
    tableConstraints.gridx = 0;
    JLabel turnLabel = new JLabel("Turn");
    pokerTable.add(turnLabel, tableConstraints);
    tableConstraints.gridy = 3;
    pokerTable.add(tableCardComponents.get(4).get("value"), tableConstraints);
    tableConstraints.gridx = 1;
    pokerTable.add(tableCardComponents.get(4).get("suit"), tableConstraints);
    //Start of the turn
    tableConstraints.gridy = 2;
    tableConstraints.gridx = 2;
    JLabel riverLabel = new JLabel("River");
    pokerTable.add(riverLabel, tableConstraints);
    tableConstraints.gridy = 3;
    pokerTable.add(tableCardComponents.get(3).get("value"), tableConstraints);
    tableConstraints.gridx = 3;
    pokerTable.add(tableCardComponents.get(3).get("suit"), tableConstraints);
  }

  private void clearPlayerData(int index) {
    players.remove(index);

    ArrayList<JPanel> cardPanels = playerCardComponents.get(index);
    for (JPanel panel : cardPanels) {
      Component[] components = panel.getComponents();
      for (Component component : components) {
        JComboBox box = (JComboBox) component;
        box.setSelectedIndex(0);
      }
    }
  }

  private Hand[] findHands() {
    Hand[] hands = new Hand[players.size()];
    int handIndex = 0;
    for (HashMap.Entry<Integer, Player> entry : players.entrySet()) {
      Player player = entry.getValue();
      boolean status = player.save();
      if (status) {
        Cards[] cards = new Cards[2];
        ArrayList<JPanel> cardPanels = playerCardComponents.get(entry.getKey());

        for (int i = 0; i < 2; i++) {
          JPanel panel = cardPanels.get(i);
          JComboBox valueBox = (JComboBox) panel.getComponent(0);
          JComboBox suitBox = (JComboBox) panel.getComponent(1);
          Rank value = (Rank) valueBox.getSelectedItem();
          Suit suit = (Suit) suitBox.getSelectedItem();
          cards[i] = new Cards(value, suit);
        }
        Hand hand = new Hand(cards, player);
        hands[handIndex] = hand;
      }
      handIndex++;
    }
    return hands;
  }
}
