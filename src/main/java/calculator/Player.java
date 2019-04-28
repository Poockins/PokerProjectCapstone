/**
 * Represents a player
 *
 * @author rob
 * @author Yuko Takegoshi
 * @version 1.0
 */
package calculator;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Player {

  private String name;
  private int id;

  /**
   * Constructor
   *
   * @param name player name
   */
  public Player(String name) {
    try {
      DBConnection db = new DBConnection();
      String query = String.format("INSERT INTO players (name) VALUES (%s)", name);
      ArrayList<Integer> keys = db.insertQuery(query);
      this.name = name;
      this.id = keys.get(0);
      db.conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Constructor given database id and name
   * Assumes that the record already exists as given in the database
   *
   * @param id Primary key database id of player
   * @param name Player name
   */
  public Player(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Gets the player name
   *
   * @return player name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Updates the player name
   *
   * @param name Name of the player
   * @return Nothing
   */
  public void setName(String name) {
    try {
      DBConnection db = new DBConnection();
      String query = String.format("UPDATE players SET name = %s WHERE id = %i", name, this.id);
      db.updateQuery(query);
      this.name = name;
      db.conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Gets the Primary Key id of the Player
   *
   * @return id of the Player
   */
  public int getId() {
    return this.id;
  }

  /**
   * Finds a Player from the database given an id
   *
   * @return Player from found player data
   */
  public Player findById(int id) {
    String foundName = null;
    try {
      DBConnection db = new DBConnection();
      ResultSet found = db.selectQuery("SELECT * FROM players WHERE id=" + id);
      found.first();
      foundName = found.getString(2);
      db.conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    if (foundName != null) {
      return new Player(id, foundName);
    } else {
      return null;
    }
  }
}
