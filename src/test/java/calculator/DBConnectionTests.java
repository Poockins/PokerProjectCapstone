package calculator;

import org.apache.commons.dbutils.DbUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBConnectionTests {

  static DBConnection db;
  static Statement statement;
  static Connection conn;
  static final String DB_PATH = "data/test/poker";

  @BeforeAll
  public static void initDBConnection() {
    try {
      db = new DBConnection(DB_PATH);
      db.dropAllTables();
      db.setup();
      conn = db.getConnection();
      statement = conn
          .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @AfterAll
  public static void teardown() {
    DbUtils.closeQuietly(statement);
    DbUtils.closeQuietly(conn);
  }

  @Test
  @DisplayName("DB setup")
  void setupTest() {
    try {
      ResultSet result = statement.executeQuery(
          "SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      result.first();
      assertEquals(3, result.getInt(1));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("SELECT query")
  void selectTest() {
    try {
      List<Map<String, Object>> result = db.selectQuery(
          "SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      Map<String, Object> row = result.get(0);
      assertEquals(3, (int) (long) row.get("C1"));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("INSERT query")
  void insertTest() {
    try {
      db.reset();
      List<Map<String, Object>> keys = db
          .insertQuery("INSERT INTO players (name) VALUES ('Linus Torvalds')");
      ResultSet result = statement.executeQuery("SELECT name FROM players LIMIT 1");
      result.first();
      Map<String, Object> row = keys.get(0);
      assertEquals(1, row.get("id"));
      assertEquals("Linus Torvalds", result.getString(1));
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

      int updated = db.updateQuery("UPDATE players SET name = 'Aaron Patterson'");
      ResultSet result2 = statement.executeQuery("SELECT name FROM players LIMIT 1");
      result2.first();
      assertEquals(1, updated);
      assertEquals("Aaron Patterson", result2.getString(1));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  @DisplayName("CREATE and DROP queries")
  void createAndDropTest() {
    try {
      db.reset();
      db.setup();
      db.updateQuery("CREATE TABLE test (id INT)");
      ResultSet createResults = statement.executeQuery(
          "SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      createResults.first();
      assertEquals(4, createResults.getInt(1));
      db.updateQuery("DROP SCHEMA PUBLIC CASCADE");
      ResultSet dropResults = statement.executeQuery(
          "SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      dropResults.first();
      assertEquals(0, dropResults.getInt(1));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
