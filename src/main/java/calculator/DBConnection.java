/**
 * DBConnection is used for database functionality throughout the app
 *
 * @author Yuko Takegoshi
 * @version 1.0
 */

package calculator;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.hsqldb.jdbc.JDBCDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBConnection {

  static final String DB_PATH = "data/poker";
  public QueryRunner runner;
  private MapListHandler handler = new MapListHandler();
  private JDBCDataSource dataSource;

  /**
   * Constructor
   */
  public DBConnection() {
    this.dataSource = new JDBCDataSource();
    dataSource.setDatabase("jdbc:hsqldb:file:" + DB_PATH);
    dataSource.setUser("sa");
    dataSource.setPassword("");
    runner = new QueryRunner(dataSource);
  }

  public DBConnection(String dbPath) {
    this.dataSource = new JDBCDataSource();
    dataSource.setDatabase("jdbc:hsqldb:file:" + dbPath);
    dataSource.setUser("sa");
    dataSource.setPassword("");
    runner = new QueryRunner(dataSource);
  }

  public Connection getConnection() throws SQLException {
    return this.dataSource.getConnection();
  }

  /**
   * Initializes a database and table structure if they do not yet exist
   *
   * @return Nothing
   */
  public void setup() throws SQLException {
    createGamesTable();
    createPlayersTable();
    createHandsTable();
  }

  /**
   * Empties all data in the database
   *
   * @return Nothing
   */
  public void reset() throws SQLException {
    runner.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
  }

  public void dropAllTables() throws SQLException {
    runner.execute("DROP SCHEMA PUBLIC CASCADE");
  }

  /**
   * Wraps a SELECT SQL command
   *
   * @param query SELECT query to run
   * @return the results from the query as a list of maps
   */
  public List<Map<String, Object>> selectQuery(String query, Object... params) throws SQLException {
    List<Map<String, Object>> results = runner.query(query, handler, params);

    return results;
  }

  /**
   * Wraps a CREATE, DROP, or UPDATE SQL command
   *
   * @param query  SQL query to run
   * @param params any replacement parameters
   * @return number of rows updated
   */
  public int updateQuery(String query, Object... params) throws SQLException {
    int result = runner.update(query, params);

    return result;
  }

  /**
   * Wraps an INSERT SQL query
   *
   * @param query  INSERT query to run
   * @param params Replacement parameters for query
   * @return List containing map of auto-generated keys
   */
  public List<Map<String, Object>> insertQuery(String query, Object... params) throws SQLException {
    List<Map<String, Object>> results = runner.insert(query, handler, params);

    return results;
  }

  /**
   * Initializes the games table if it doesn't already exist
   *
   * @return Nothing
   * @throws SQLException on creation failure
   */
  private void createGamesTable() throws SQLException {
    String query = "CREATE TABLE IF NOT EXISTS games (" +
        "id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
        "flop VARCHAR(30) ARRAY, " +
        "turn VARCHAR(30), " +
        "river VARCHAR(30), " +
        "created_at TIMESTAMP);";

    updateQuery(query);
  }

  /**
   * Initializes the players table if it doesn't already exist
   *
   * @return Nothing
   * @throwsSQLException on creation failure
   */
  private void createPlayersTable() throws SQLException {
    String query = "CREATE TABLE IF NOT EXISTS players (" +
        "id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
        "name VARCHAR(256));";

    updateQuery(query);
  }

  /**
   * Initializes the hands table if it doesn't already exist
   *
   * @return Nothing
   * @throws SQLException on creation failure
   */
  private void createHandsTable() throws SQLException {
    String query = "CREATE TABLE IF NOT EXISTS hands (" +
        "id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
        "cards VARCHAR(30) ARRAY, " +
        "player_id INT, " +
        "game_id INT, " +
        "FOREIGN KEY(player_id) REFERENCES players, " +
        "FOREIGN KEY(game_id) REFERENCES games);";

    updateQuery(query);
  }
}
