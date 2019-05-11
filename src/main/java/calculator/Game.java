/**
 *
 * @author rob
 * @author Yuko Takegoshi
 *
 * @version 1.0
 */
package calculator;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.lang.reflect.Field;


public class Game {

  private Cards[] flop;
  private Cards turn;
  private Cards river;
  private Date createdAt;
  private int id;
  private Hand[] hands;
  private Player[] players;

  /**
   * Constructor to create a new record with auto-generated id and createdAt
   */
  public Game() {
    try {
      DBConnection db = new DBConnection();
      String query = "INSERT INTO games (created_at) VALUES (CURRENT_TIMESTAMP)";
      List<Map<String, Object>> keys = db.insertQuery(query);
      Map<String, Object> resultRow = keys.get(0);
      this.id = (Integer)resultRow.get("id");
      List<Map<String,Object>> results = db.selectQuery(String.format("SELECT created_at FROM games WHERE id=%i", this.id));
      Map<String, Object> row = results.get(0);
      Date createdAt = (Date)row.get("createdAt");
      this.createdAt = createdAt;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Construct given database information
   *
   * @param flop
   * @param turn
   * @param river
   * @param id
   */
  public Game(Cards[] flop, Cards turn, Cards river, int id, Hand[] hands, Date createdAt) {
    this.flop = flop;
    this.turn = turn;
    this.river = river;
    this.id = id;
    this.hands = hands;
    this.createdAt = createdAt;
  }

  /**
   * Constructor given params from the database
   *
   * @param params Map of database data, keyed by column
   */
  public Game(Map<String, Object> params) {
    //    Dynamically set turn and river to DRY up a bit
    String[] keys = {"turn", "river"};
    for(String key : keys) {
      try {
        Field field = this.getClass().getField(key);
        Cards card = Cards.stringToCard((String)params.get(key));
        field.set(key, card);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    String[] flopStrings = (String[])params.get("flop");
    this.flop = Cards.stringToArray(flopStrings);
    this.id = (Integer)params.get("id");
    this.hands = getHandsFromDB();
    this.createdAt = (Date)params.get("created_at");
  }

  public Cards[] getFlop() {
    return flop;
  }

  public void setFlop(Cards[] flop) {
    this.flop = flop;
  }

  public Cards getTurn() {
    return turn;

  }
  public void setTurn(Cards turn){
      this.turn = turn;
  }
  public Cards getRiver() {
    return river;
  }

  public void setRiver(Cards river) {
    this.river = river;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public int getId() {
    return id;
  }

  public Hand[] getHands() {
    return hands;
  }
  /**
   * Added this setHands deleted original without realizing it existed 
   * @param hands 
   */
  public void setHands(Hand [] hands){
      this.hands = hands;
  }

  public Hand[] getHandsFromDB() {
    ArrayList<Hand> handList = new ArrayList<>();
    try {
      DBConnection db = new DBConnection();
      String query = "SELECT * FROM hands WHERE game_id=?";
      List<Map<String, Object>> results = db.selectQuery(query, this.id);
      for(Map<String, Object> result : results) {
        Hand hand = new Hand(result);
        handList.add(hand);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return handList.toArray(new Hand[handList.size()]);
  }

  public void addHand(Hand hand) {

  }

  public void removeHand(Hand hand) {

  }

  public static Game findById(int id) {
    List<Map<String, Object>> results = new ArrayList<>();
    try {
      DBConnection db = new DBConnection();
      results = db.selectQuery("SELECT * FROM games WHERE id = ?", id);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    if (results.size() > 0) {
      return new Game(results.get(0));
    } else {
      return null;
    }
  }

  public boolean save() throws SQLException {
    int updated = 0;
    DBConnection db = new DBConnection();
    String query = "UPDATE games SET flop=?, turn=?, river=? WHERE id=?";
    String flopParam = String.format("ARRAY[%s]", Cards.arrayToString(this.flop));
    updated = db.updateQuery(query, flopParam, this.turn.toDataString(), this.river.toDataString(), this.id);
    if (updated > 0) {
      return true;
    } else {
      return false;
    }
  }

//  public Player getWinner() {///needs changed
//    return players[0];
//  }
}