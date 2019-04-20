package calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBConnectionTests {
  static DBConnection db;

  @BeforeEach void initDBConnection() {
    try {
      db = new DBConnection();
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
      db.setup();
      Statement statement = db.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet result = statement.executeQuery("SELECT COUNT(TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'");
      result.first();
      assertEquals(3, result.getInt(1));
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Test
  void emptyTest() {
    
  }
}
