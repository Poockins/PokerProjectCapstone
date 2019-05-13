/**
 * PokerPanel displays the Calculator portion of the app, allowing users to input cards and calculate probabilities.
 *
 * @author elich
 * @author Yuko Takegoshi
 */

package calculator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class PokerPanel extends JPanel {

  private HashMap<Integer, Player> players = new HashMap<>();

  private JPanel[] playerPanels = new JPanel[8];
  private JPanel[] playerCardPanels = new JPanel[8];

  private Rank[] cardValue = Rank.values();
  private Suit[] suitValue = Suit.values();

  private ArrayList<HashMap<String, JComboBox>> tableCardComponents = new ArrayList<>();
  private HashMap<Integer, ArrayList<JPanel>> playerCardComponents = new HashMap<>();

  // Tracks whether a community panel is active
  private HashMap<Integer, Boolean> communityStatus = new HashMap<>();

  // Container for community panels/cards
  private JPanel pokerTable = new JPanel();

  //Border used for various components
  private Border blackline = BorderFactory.createLineBorder(Color.black);

  private JButton databaseButton = new JButton("Save game to history");

  private JTable calcTable;

  public PokerPanel(JPanel contentPanel) {
    SpringLayout layout = new SpringLayout();
    contentPanel.setLayout(layout);
    pokerTable.setLayout(new GridBagLayout());
    pokerTable.setBorder(blackline);

    setupCommunal();

    createPlayerPanels();

    //Calculation Panel
    JPanel calcPanel = new JPanel(new BorderLayout());
    calcPanel.setPreferredSize((new Dimension(pokerTable.getPreferredSize().width / 3,
        pokerTable.getPreferredSize().height)));
    JButton calculate = new JButton("Calculate");
    calcPanel.add(calculate, BorderLayout.NORTH);
    calcPanel.setBorder(blackline);
    String[] columnNames = {"Player", "Odds"};
    String[][] initial = {{"", ""}};
    calcTable = new JTable(initial, columnNames);
    int tableWidth = calcPanel.getWidth();
    int tableHeight = calcPanel.getHeight();
    calcTable.setPreferredScrollableViewportSize(calcTable.getPreferredSize());
    calcTable.setSize(tableWidth, tableHeight);

    JScrollPane sp = new JScrollPane(calcTable);
    calcPanel.add(sp, BorderLayout.CENTER);
    calculate.addActionListener((ActionEvent e) -> {
      String[][] data = calculateProbabilities();
      String[] columns = {"Player", "Odds"};
      DefaultTableModel newModel = new DefaultTableModel(data, columns);
      calcTable.setModel(newModel);
    });


    //Misc. Components
    JPanel miscPanel = new JPanel();
    miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
    databaseButton.setVisible(false);
    JButton clearButton = new JButton("Clear Form");
    clearButton.addActionListener((ActionEvent e) -> {
      clearForm();
    });

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

  /**
   * Saves the current game board to the database
   */
  private void makeDiaryEntry(Cards[] flop, Cards turn, Cards river) {
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
      JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Game saved");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Creates a panel for an active player, showing their cards and a button to remove them
   *
   * @param index Player index for the panel
   * @return JPanel for active player of index
   */
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

    // Player 1 is always active, don't show a remove button
    if (index > 0) {
      JButton removeButton = removeButton(index);
      c.gridy = 2;
      c.fill = GridBagConstraints.NONE;
      playerCardPanel.add(removeButton, c);
    }

    playerCardPanel.setName(String.format("%d_player_card_panel", index));
    playerCardPanels[index] = playerCardPanel;
    return playerCardPanel;
  }

  /**
   * Creates a panel for an empty player state
   *
   * @param index Index of the player
   * @return JPanel of the player's empty state
   */
  private JPanel emptyPanel(int index) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JButton addPlayer = addButton(index);

    c.gridx = 1;
    c.gridy = 1;

    panel.add(addPlayer, c);

    return panel;
  }

  /**
   * Create a button to add a player
   *
   * @param index Index of player to add
   * @return JButton to add a player to the board
   */
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

  /**
   * Create a button to remove a player
   *
   * @param index Index of the player to remove
   * @return JButton to remove a player
   */
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

  /**
   * Create and set up the community card components
   */
  private void setupCommunal() {
    GridBagConstraints tableConstraints = new GridBagConstraints();
    tableConstraints.gridx = 0;
    tableConstraints.gridy = 0;
    tableConstraints.insets = new Insets(2, 1, 1, 1);
    String[] panelLabels = {"Flop", "Turn", "River"};
    JPanel[] panels = {new JPanel(new CardLayout()), new JPanel(new CardLayout()), new JPanel(new CardLayout())};
    JPanel[] containers = {new JPanel(new GridBagLayout()), new JPanel(new GridBagLayout()), new JPanel(new GridBagLayout())};

    for (int i = 0; i < panels.length; i++) {
      JPanel container = containers[i];
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      JLabel label = new JLabel(panelLabels[i]);
      container.add(label, c);
      JPanel cardPanel = panels[i];
      cardPanel.setName("community_card_panel");
      String buttonLabel = String.format("Add %s", panelLabels[i]);
      JPanel empty = emptyCommunityPanel(buttonLabel, i);
      cardPanel.add(empty, "empty");
      int numCards = i == 0 ? 3 : 1; // flop requires 3 cards
      JPanel active = activeCommunityPanel(numCards, i);
      cardPanel.add(active, "active");
      c.gridy = 1;
      container.add(cardPanel, c);
      communityStatus.put(i, false);
    }

    tableConstraints.fill = GridBagConstraints.HORIZONTAL;
    tableConstraints.gridwidth = 2;
    pokerTable.add(containers[0], tableConstraints);
    tableConstraints.gridy = 1;
    tableConstraints.gridwidth = 1;
    tableConstraints.fill = GridBagConstraints.NONE;
    pokerTable.add(containers[1], tableConstraints);
    tableConstraints.gridx = 1;
    pokerTable.add(containers[2], tableConstraints);
  }

  /**
   * Creates empty state for a community card panel
   *
   * @param label Button label
   * @param index index of community panel
   * @return
   */
  private JPanel emptyCommunityPanel(String label, int index) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JButton button = new JButton(label);
    button.setName(String.format("%d_add_community", index));
    button.addActionListener((ActionEvent e) -> {
      JButton source = (JButton) e.getSource();
      JPanel parent = (JPanel) SwingUtilities.getAncestorNamed("community_card_panel", source);
      CardLayout layout = (CardLayout) parent.getLayout();
      layout.show(parent, "active");
      String name = source.getName();

      int sourceIndex = Integer.parseInt(name.split("_")[0]);

      communityStatus.put(sourceIndex, true);


      boolean canSave = false;
      for (Boolean status : communityStatus.values()) {
        if (status) {
          canSave = true;
        } else {
          canSave = false;
          break;
        }
      }

      if (canSave) {
        databaseButton.setVisible(true);
      }
    });

    c.gridx = 1;
    c.gridy = 1;

    panel.add(button, c);

    return panel;
  }

  /**
   * Creates active state for a community panel
   *
   * @param numCards number of cards to generate for panel
   * @param index    community panel index
   * @return
   */
  private JPanel activeCommunityPanel(int numCards, int index) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2, 1, 1, 1);
    c.gridy = 0;
    int tableGridx = 0;
    for (int i = 0; i < numCards; i++) {
      JComboBox<Rank> value = new JComboBox<>(cardValue);
      JComboBox<Suit> suit = new JComboBox<>(suitValue);

      HashMap<String, JComboBox> boxes = new HashMap<>();

      boxes.put("value", value);
      boxes.put("suit", suit);

      tableCardComponents.add(boxes);
      c.gridx = tableGridx;
      panel.add(value, c);
      tableGridx++;
      c.gridx = tableGridx;
      panel.add(suit, c);
      tableGridx++;
    }

    JButton reset = new JButton("Reset");
    reset.setName(String.format("%d_reset_community", index));
    reset.addActionListener((ActionEvent e) -> {
      JButton source = (JButton) e.getSource();
      JPanel parent = (JPanel) SwingUtilities.getAncestorNamed("community_card_panel", source);
      CardLayout layout = (CardLayout) parent.getLayout();
      layout.show(parent, "empty");
      String name = source.getName();
      int sourceIndex = Integer.parseInt(name.split("_")[0]);
      if (sourceIndex == 0) {
        for (int i = 0; i < 3; i++) {
          HashMap<String, JComboBox> component = tableCardComponents.get(i);
          for (JComboBox box : component.values()) {
            box.setSelectedIndex(0);
          }
        }
      } else {
        HashMap<String, JComboBox> component = tableCardComponents.get(sourceIndex + 2);
        for (JComboBox box : component.values()) {
          box.setSelectedIndex(0);
        }
      }

      communityStatus.put(sourceIndex, false);
      databaseButton.setVisible(false);
    });

    c.gridy = 1;
    c.gridx = 0;
    c.gridwidth = tableGridx;
    c.anchor = GridBagConstraints.PAGE_END;
    panel.add(reset, c);

    return panel;
  }

  /**
   * Creates the panels for every player on the board with empty and active states
   */
  private void createPlayerPanels() {
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

      // Player 1 is always active
      if (i == 0) {
        Player player = new Player("Player 1");
        players.put(0, player);
      } else {
        JPanel emptyPanel = emptyPanel(i);
        playerPanel.add(emptyPanel, "empty");
      }

      JPanel cardPanel = playerCardPanel(i);
      playerPanel.add(cardPanel, "cards");

      playerPanels[i] = playerPanel;

      repaint();
    }
  }

  /**
   * Clears everything on the board, resets back to original state
   */
  private void clearForm() {
    resetPlayerCards(0); // Player 1 is always active
    for (int i = 1; i < playerPanels.length; i++) {
      CardLayout panelLayout = (CardLayout) playerPanels[i].getLayout();
      panelLayout.show(playerPanels[i], "empty");
      clearPlayerData(i);
    }

    clearTable();
    repaint();
    validate();
  }

  /**
   * Clears a player from the board, resetting their cards and removing the Player object
   *
   * @param index Player index to clear
   */
  private void clearPlayerData(int index) {
    players.remove(index);
    resetPlayerCards(index);
  }

  /**
   * Reset all cards on the table
   */
  private void clearTable() {
    for (HashMap<String, JComboBox> component : tableCardComponents) {
      for (JComboBox box : component.values()) {
        box.setSelectedIndex(0);
      }
    }
  }

  /**
   * Resets cards for a given player back to default.
   *
   * @param index player index to reset
   */
  private void resetPlayerCards(int index) {
    ArrayList<JPanel> cardPanels = playerCardComponents.get(index);
    for (JPanel panel : cardPanels) {
      Component[] components = panel.getComponents();
      for (Component component : components) {
        JComboBox box = (JComboBox) component;
        box.setSelectedIndex(0);
      }
    }
  }

  /**
   * Create Hands from the currently selected cards
   *
   * @return Hands representing those on the board
   */
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

  /**
   * Creates a table friendly representation of the current odds
   *
   * @return data of players and their current odds
   */
  private String[][] calculateProbabilities() {
    Hand[] hands = findHands();
    ArrayList<String[]> data = new ArrayList<>();
    Cards[] flop = getFlop();
    Cards turn = getTurn();
    Cards river = getRiver();

    for (Hand hand : hands) {
      NumberFormat numberFormat = NumberFormat.getNumberInstance();
      numberFormat.setMinimumFractionDigits(2);
      String probability = numberFormat.format(hand.calculateWin(flop, turn, river)) + "%";
      String[] row = {hand.getPlayer().getName(), probability};
      data.add(row);
    }

    return data.toArray(new String[data.size()][2]);
  }

  /**
   * Gets the current flop if the panel is active
   *
   * @return flop Cards array
   */
  private Cards[] getFlop() {
    Cards[] flop = new Cards[3];
    if (communityStatus.get(0)) {
      for (int i = 0; i < 3; i++) {
        Rank rank = (Rank) tableCardComponents.get(i).get("value").getSelectedItem();
        Suit suit = (Suit) tableCardComponents.get(i).get("suit").getSelectedItem();
        flop[i] = new Cards(rank, suit);
      }
    } else {
      return null;
    }

    return flop;
  }

  /**
   * Gets the current turn card if the panel is active.
   *
   * @return turn card
   */
  private Cards getTurn() {
    if (communityStatus.get(1)) {
      Rank rank = (Rank) tableCardComponents.get(1).get("value").getSelectedItem();
      Suit suit = (Suit) tableCardComponents.get(1).get("suit").getSelectedItem();
      return new Cards(rank, suit);
    } else {
      return null;
    }
  }

  /**
   * Gets the current river card if the panel is active.
   *
   * @return river card
   */
  private Cards getRiver() {
    if (communityStatus.get(2)) {
      Rank rank = (Rank) tableCardComponents.get(2).get("value").getSelectedItem();
      Suit suit = (Suit) tableCardComponents.get(2).get("suit").getSelectedItem();
      return new Cards(rank, suit);
    } else {
      return null;
    }
  }
}
