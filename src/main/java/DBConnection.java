/**
 * DBConnection is used for database functionality throughout the app
 *
 * @author Yuko Takegoshi
 * @version 1.0
 */
package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    static final String DB_PATH = "data/poker";
    Connection conn; // Persist database connection until explicitly shut down

    public DBConnection() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");

        conn = DriverManager.getConnection("jdbc:hsqldb:file:" + DB_PATH, "SA", "");
    }

    /**
     * Initializes a database and table structure if they do not yet exist
     */
    public void setup() throws SQLException {
        createGamesTable();
        createPlayersTable();
        createHandsTable();
    }

    /**
     * Initializes the games table if it doesn't already exist
     *
     * @return Nothing
     * @exception SQLException on creation failure
     */
    private void createGamesTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS games (" +
                "id INT IDENTITY PRIMARY KEY, " +
                "flop VARCHAR(30) ARRAY, " +
                "turn VARCHAR(30), " +
                "river VARCHAR(30), " +
                "created_at TIMESTAMP);";

        Statement st = conn.createStatement();
        st.executeUpdate(query);

    }

    /**
     * Initializes the players table if it doesn't already exist
     *
     * @return Nothing
     * @exception SQLException on creation failure
     */
    private void createPlayersTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS players (" +
                "id INT IDENTITY PRIMARY KEY, " +
                "name VARCHAR(256) )";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    /**
     * Initializes the hands table if it doesn't already exist
     *
     * @return Nothing
     * @exception SQLException on creation failure
     */
    private void createHandsTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS hands (" +
                "id INT IDENTITY PRIMARY KEY, " +
                "cards VARCHAR(30) ARRAY, " +
                "player_id INT, " +
                "game_id INT, " +
                "FOREIGN KEY(player_id) REFERENCES players, " +
                "FOREIGN KEY(game_id) REFERENCES games)";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }
}