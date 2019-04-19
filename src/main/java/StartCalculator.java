import calculator.DBConnection;

/**
 * Main/Driver class for poker calculator
 *
 * @author Yuko Takegoshi
 */

import calculator.*;

public class StartCalculator {

  public static void main(String[] args) {
    try {
      DBConnection db = new DBConnection();
      db.setup();
      db.conn.close();
    } catch (Exception ex) {
      ex.printStackTrace(); // TODO: Improve error handling
    }
  }
}
