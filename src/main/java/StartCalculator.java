/**
 * Main/Driver class for poker calculator
 *
 * @author Yuko Takegoshi
 */

public class StartCalculator {

  public static void main(String[] args) {
    try {
      DBConnection db = new DBConnection();
      db.setup();
    } catch (Exception ex) {
      ex.printStackTrace(); // TODO: Improve error handling
    }
  }
}
