/**
 * Represents a player
 *
 * @author rob
 * @author Yuko Takegoshi
 * @version 1.0
 */

package calculator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

  private String name;
  private int id;

  /**
   * Constructor to create a player with a name.
   *
   * @param name player name
   */
  public Player(String name) {
    this.name = name;
  }

  /**
   * Constructor given database id and name.
   * Assumes that the record already exists as given in the database.
   *
   * @param id   Primary key database id of player
   * @param name Player name
   */
  public Player(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Constructor from map from database
   */
  public Player(Map<String, Object> params) {
    this.id = (Integer) params.get("id");
    this.name = (String) params.get("name");
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
    this.name = name;
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
  public static Player findById(int id) {
    List<Map<String, Object>> found = new ArrayList<>();
    try {
      DBConnection db = new DBConnection();
      found = db.selectQuery("SELECT * FROM players WHERE id = ?", id);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    Map<String, Object> row = found.get(0);

    return new Player(row);
  }

  /**
   * Creates a new Player recprd in the database
   *
   * @return status indicating whether save was successful
   */
  public boolean save() {
    boolean status = false;
    try {
      DBConnection db = new DBConnection();
      List<Map<String, Object>> results = db.insertQuery("INSERT INTO players (name) VALUES (?)", name);
      Map<String, Object> row = results.get(0);
      Integer id = (Integer)row.get("id");
      if (id > 0) {
        status = true;
        this.id = id;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return status;
  }


  /**
   * Updates Player data in the database
   *
   * @return status indicating whether save was successful
   */
  public boolean update() {
    boolean status = false;
    int updated;
    try {
      DBConnection db = new DBConnection();
      updated = db.updateQuery("UPDATE players SET name = ? WHERE id = ?", this.name, this.id);

      if (updated > 0) {
        status = true;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return status;
  }
}
