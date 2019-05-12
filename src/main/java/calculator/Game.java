/**
 * Game class represents a game with player hands and community cards
 *
 * @author rob
 * @author Yuko Takegoshi
 * @version 1.0
 */

package calculator;

import org.hsqldb.jdbc.JDBCArray;

import java.sql.SQLException;
import java.util.*;


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
      this.id = (Integer) resultRow.get("ID");
      List<Map<String, Object>> results = db
          .selectQuery(String.format("SELECT created_at FROM games WHERE id=%d", this.id));
      Map<String, Object> row = results.get(0);
      Date createdAt = (Date) row.get("CREATED_AT");
      this.createdAt = createdAt;
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Construct given database information
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
    Cards turn = Cards.stringToCard((String) params.get("TURN"));
    Cards river = Cards.stringToCard((String) params.get("RIVER"));
    JDBCArray flop = (JDBCArray) params.get("FLOP");
    try {
      Object[] flopObjects = (Object[]) flop.getArray();
      String[] flopStrings = Arrays.stream(flopObjects)
          .toArray(String[]::new);
      this.flop = Cards.stringToArray(flopStrings);
      this.turn = turn;
      this.river = river;
      this.id = (Integer) params.get("ID");
      this.createdAt = (Date) params.get("CREATED_AT");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
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

  public void setTurn(Cards turn) {
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

  public Hand[] getHandsFromDB() {
    ArrayList<Hand> handList = new ArrayList<>();
    try {
      DBConnection db = new DBConnection();
      String query = "SELECT * FROM hands WHERE game_id=?";
      List<Map<String, Object>> results = db.selectQuery(query, this.id);
      for (Map<String, Object> result : results) {
        Hand hand = new Hand(result);
        handList.add(hand);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return handList.toArray(new Hand[handList.size()]);
  }

  public void addHand(Hand hand) {
    ArrayList<Hand> newHands = new ArrayList<>(Arrays.asList(this.hands));
    newHands.add(hand);
    Hand[] converted = new Hand[newHands.size()];
    this.hands = newHands.toArray(converted);
    setPlayers();
  }

  public void removeHand(Hand hand) {
    setPlayers();
  }

  public void setPlayers() {
    this.hands = hands == null ? getHandsFromDB() : hands;
    Player[] players = new Player[this.hands.length];
    for (int i = 0; i < this.hands.length; i++) {
      Hand hand = this.hands[i];
      players[i] = hand.getPlayer();
    }

    this.players = players;
  }

  /**
   * Get the players in the game through their Hands
   *
   * @return players in the game
   */
  public Player[] getPlayers() {
    setPlayers();
    return this.players;
  }

  /**
   * Find a Game given its database id
   *
   * @param id game database id
   * @return found Game
   */
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

  /**
   * Finds all Games in the database
   *
   * @return all found games
   */
  public static ArrayList<Game> findAll() {
    ArrayList<Game> found = new ArrayList<>();
    List<Map<String, Object>> results = new ArrayList<>();

    try {
      DBConnection db = new DBConnection();
      results = db.selectQuery("SELECT * FROM games ORDER BY created_at DESC");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    for (Map<String, Object> row : results) {
      Game game = new Game(row);
      found.add(game);
    }

    return found;
  }

  /**
   * Saves the Game to the database
   *
   * @return success/failure status
   * @throws SQLException
   */

  public boolean save() throws SQLException {
    int updated;
    DBConnection db = new DBConnection();
    String query = "UPDATE games SET flop=?, turn=?, river=? WHERE id=?";
    String[] flopParam = Cards.toDataStringArray(flop);
    updated = db.updateQuery(query, flopParam, this.turn.toDataString(), this.river.toDataString(),
        this.id);

    if (updated > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Deletes the Game and associated Hands from the database.
   *
   * @return success status
   * @throws SQLException
   */
  public boolean delete() throws SQLException {
    int updated;
    DBConnection db = new DBConnection();
    String query = "DELETE FROM games WHERE id = ?";
    updated = db.updateQuery(query, this.id);

    if (updated > 0) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * Aggregates data for all games into a table-friendly format
   *
   * @return table-friendly data containing general game details
   */
  public static Object[][] aggregateData() {
    ArrayList<Game> allGames = findAll();
    ArrayList<Object[]> data = new ArrayList<>();

    for (Game game : allGames) {
      Cards[] flop = game.getFlop();
      String flopString = Cards.arrayToString(flop);
      String turn = game.getTurn().toString();
      String river = game.getRiver().toString();
      Date gameDate = game.getCreatedAt();
      Object[] row = {gameDate, flopString, turn, river};
      data.add(row);
    }

    return data.toArray(new Object[data.size()][7]);
  }

  /**
   * Aggregates details for a given Game into a table-friendly format
   * Returns one row per player/hand in a game
   *
   * @return data of game details for inserting into a table
   */
  public String[][] aggregateDetails() {
    Hand[] hands = getHandsFromDB();
    ArrayList<String[]> data = new ArrayList<>();
    for (Hand hand : hands) {
      String preFlop = Double.toString(hand.calculateWin());
      String flop = Double.toString(hand.calculateWin(this.flop));
      String turn = Double.toString(hand.calculateWin(this.flop, this.turn));
      String river = Double.toString(hand.calculateWin(this.flop, this.turn, this.river));
      String[] row = {hand.getPlayer().getName(), hand.toString(), preFlop, flop, turn, river};
      data.add(row);
    }

    return data.toArray(new String[data.size()][7]);
  }
}