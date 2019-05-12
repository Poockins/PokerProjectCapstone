/**
 * Represents a player's hand of two cards.
 *
 * @author rob
 * @author Yuko Takegoshi
 * @version 1.0
 */

package calculator;

import org.hsqldb.jdbc.JDBCArray;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Hand {

  private Cards[] cards;
  private int id;
  private Player player;
  private Game game;

  /**
   * Construct a Hand given cards and a player
   *
   * @param cards  the cards for the hand
   * @param player the player for the hand
   */
  public Hand(Cards[] cards, Player player) {
    try {
      DBConnection db = new DBConnection();
      String query = "INSERT INTO hands (cards, player_id) VALUES (?, ?)";
      String[] cardParam = Cards.toStringArray(cards);
      List<Map<String, Object>> results = db.insertQuery(query, cardParam, player.getId());
      Map<String, Object> row = results.get(0);
      this.cards = cards;
      this.player = player;
      this.id = (Integer) row.get("ID");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Construct a Hand given database information
   */
  public Hand(Cards[] cards, Player player, Game game, int id) {
    this.cards = cards;
    this.player = player;
    this.game = game;
    this.id = id;
  }

  /**
   * Construct a Hand given params from the database
   */
  public Hand(Map<String, Object> params) {
    JDBCArray cards = (JDBCArray) params.get("cards");
    try {
      Object[] cardObjects = (Object[]) cards.getArray();
      String[] cardStrings = Arrays.stream(cardObjects)
          .toArray(String[]::new);
      this.cards = Cards.stringToArray(cardStrings);
      int playerId = (Integer) params.get("PLAYER_ID");
      this.player = Player.findById(playerId);
      int gameId = (Integer) params.get("GAME_ID");
      this.game = Game.findById(gameId);
      this.id = (Integer) params.get("ID");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * This hand constructor was used in testing can be removed once final build is solidified
   */
  public Hand(Cards card1, Cards card2, Player player) {
    Cards[] cards = {card1, card2};
    this.cards = cards;
    this.player = player;
  }

  /**
   * Get the cards in the hand
   *
   * @return the cards in the hand
   */
  public Cards[] getCards() {
    return cards;
  }

  /**
   * Sets the cards for the Hand
   */
  public void setCards(Cards[] cards) {
    this.cards = cards;
  }

  /**
   * Get the player of the hand
   *
   * @return player for the hand
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Sets the player for the Hand
   */
  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Gets the database id for the Hand
   *
   * @return database id
   */
  public int getId() {
    return id;
  }

  public Game getGame() {
    return this.game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * Attempts to find a Hand given its database id
   *
   * @param id database id
   * @return Hand as found from id, or null if no Hand is found
   */
  public static Hand findById(int id) {
    Map<String, Object> row;
    List<Map<String, Object>> found = new ArrayList<>();

    try {
      DBConnection db = new DBConnection();
      found = db.selectQuery("SELECT cards, player_id FROM hands WHERE id = ?", id);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    row = found.get(0);
    if (row != null) {
      return new Hand(row);
    } else {
      return null;
    }
  }

  /**
   * Saves the Hand to the database
   *
   * @return boolean status of save success
   */
  public boolean save() {
    boolean status = false;
    int updated;

    try {
      DBConnection db = new DBConnection();
      updated = db.updateQuery("UPDATE hands SET player_id = ?, cards = ?, game_id=? WHERE id = ?",
          player.getId(), Cards.toStringArray(this.cards), game.getId(), this.id);
      if (updated > 0) {
        status = true;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return status;
  }

  public double calculateWin() {///// Needs changed

    double win = 0.0;
    return win;
  }

  public String toString() {
    String handString = player + " has a " + cards[0].toString() + " and " + cards[1].toString();
    return handString;
  }
}
