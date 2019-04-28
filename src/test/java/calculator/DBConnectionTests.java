package calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class DBConnectionTests {
  static DBConnection db;
  static Statement statement;

  @BeforeEach void initDBConnection() {
    try {
      db = new DBConnection();
      db.setup();
      statement = db.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @AfterEach void closeDBConnection() {
    try {
      db.conn.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("DB setup")
  void setupTest() {
    try {
      ResultSet result = statement.executeQuery("SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      result.first();
      assertEquals(3, result.getInt(1));
      statement.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("SELECT query")
  void selectTest() {
    try {
      ResultSet result = db.selectQuery("SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      result.first();
      assertEquals(3, result.getInt(1));
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("INSERT query")
  void insertTest() {
    try {
      db.reset();
      ArrayList<Integer> keys = db.insertQuery("INSERT INTO players (name) VALUES ('Linus Torvalds')");
      ResultSet result = statement.executeQuery("SELECT name FROM players LIMIT 1");
      result.first();
      assertEquals(1, keys.get(0));
      assertEquals("Linus Torvalds", result.getString(1));
      statement.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("UPDATE query")
  void updateTest() {
    try {
      db.reset();
      statement.executeQuery("INSERT INTO players (name) VALUES ('Linus Torvalds')");
      ResultSet result1 = statement.executeQuery("SELECT name FROM players LIMIT 1");
      result1.first();
      assertEquals("Linus Torvalds", result1.getString(1));

      boolean success = db.updateQuery("UPDATE players SET name = 'Aaron Patterson'");
      ResultSet result2 = statement.executeQuery("SELECT name FROM players LIMIT 1");
      result2.first();
      assertTrue(success);
      assertEquals("Aaron Patterson", result2.getString(1));
      statement.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("CREATE and DROP queries")
  void createAndDropTest() {
    try {
      db.updateQuery("CREATE TABLE test (id INT)");
      ResultSet createResults = statement.executeQuery("SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      createResults.first();
      assertEquals(4, createResults.getInt(1));
      db.updateQuery("DROP SCHEMA PUBLIC CASCADE");
      ResultSet dropResults = statement.executeQuery("SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      dropResults.first();
      assertEquals(0, dropResults.getInt(1));
      statement.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
