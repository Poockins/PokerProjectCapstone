/**
 * Represents a player's hand of two cards.
 *
 * @author rob
 * @author Yuko Takegoshi
 * @version 1.0
 */
package calculator;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.*;

public class Hand {

  private Cards[] cards;
  private int id;
  private Player player;
  private DBConnection db;

  /**
   * Construct a Hand given cards and a player
   *
   * @param cards the cards for the hand
   * @param player the player for the hand
   */
  public Hand(Cards[] cards, Player player) {
    try {
      db = new DBConnection();
      String insertQuery = String
          .format("INSERT INTO hands (cards, player_id) VALUES (ARRAY%s, %i)",
              cardsToString(cards), player.getId());
      ArrayList<Integer> keys = db.insertQuery(insertQuery);
      this.cards = cards;
      this.player = player;
      this.id = keys.get(0);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Construct a Hand given database information
   * @param cards
   * @param player
   * @param id
   */
  public Hand(Cards[] cards, Player player, int id) {
    this.cards = cards;
    this.player = player;
    this.id = id;
  }

  public Cards[] getCards() {
    return cards;
  }

  /**
   * Sets the cards for the Hand
   *
   * @param cards
   * @throws SQLException
   */
  public void setCards(Cards[] cards) throws SQLException {
    boolean status = false;

    try {
      String updateQuery = String
          .format("UPDATE hands SET cards = ARRAY[%s] WHERE id = %i", cardsToString(cards),
              this.id);
      status = db.updateQuery(updateQuery);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    if (status) {
      this.cards = cards;
    } else {
      throw new SQLException("Error setting cards for Hand " + this.id);
    }
  }

  public Player getPlayer() {
    return player;
  }

  /**
   * Sets the player for the Hand
   *
   * @param player
   * @throws SQLException
   */
  public void setPlayer(Player player) throws SQLException {
    boolean status = false;
    try {
      String updateQuery = String
          .format("UPDATE hands SET player_id = %i WHERE id = %i", player.getId(), this.id);
      status = db.updateQuery(updateQuery);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    if (status) {
      this.player = player;
    } else {
      throw new SQLException("Error setting player for Hand " + this.id);
    }
  }

  public int getId() {
    return id;
  }

  /**
   * Attempts to find a Hand given its database id
   *
   * @param id database id
   * @return Hand as found from id, or null if no Hand is found
   */
  public Hand findById(int id) {
    Array foundCards;
    int foundPlayer;
    Player player = null;
    Cards[] cards = new Cards[2];
    String[] cardStrings = new String[0];

    try {
      DBConnection db = new DBConnection();
      ResultSet found = db.selectQuery("SELECT cards, player_id FROM hands WHERE id=" + id);
      found.first();

      foundCards = found.getArray(1);
      cardStrings = (String[]) foundCards.getArray();
      for (int i = 0; i < 2; i++) {
        cards[i] = Cards.stringToCard(cardStrings[i]);
      }

      foundPlayer = found.getInt(2);
      player = Player.findById(foundPlayer);

      db.conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (player != null && cardStrings.length > 0) {
      return new Hand(cards, player);
    } else {
      return null;
    }
  }

  public double calculateWin() {///// Needs changed

    double win = 0.0;
    return win;
  }

  public String toString() {
    return "";
  }

  /**
   * Converts an array of cards to a comma delimited string representation
   *
   * @param cards card to convert to string
   * @return comma delimited string of card
   */
  private String cardsToString(Cards[] cards) {
    List<Cards> CardsList = Arrays.asList(cards);
    return String
        .join(",", CardsList.stream().map(c -> c.toDataString()).collect(Collectors.toList()));
  }
}
