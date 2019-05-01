/**
 * Main/Driver class for poker calculator
 *
 * @author Yuko Takegoshi
 */

import calculator.*;
import javax.swing.JFrame;

public class StartCalculator {

  public static void main(String[] args) {
    try {
      // Database setup on app start
      DBConnection db = new DBConnection();
      db.setup();
      // End database setup
    } catch (Exception ex) {
      ex.printStackTrace(); // TODO: Improve error handling
    }
    
    WelcomeScreen newGame = new WelcomeScreen();
    newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newGame.setLocation(300,300);
    newGame.setVisible(true);
  }
}
