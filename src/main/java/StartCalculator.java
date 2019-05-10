
/**
 * Main/Driver class for poker calculator
 *
 * @author Yuko Takegoshi
 */
import calculator.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class StartCalculator {

    private final static Logger STARTLOGGER = Logger.getLogger("Start Calc");
    private static Handler fileHandler;

    public static void main(String[] args) {
        try {
            fileHandler = new FileHandler("startLogger.log", true);
            STARTLOGGER.addHandler(fileHandler);
        } catch (IOException exception) {
            STARTLOGGER.log(Level.FINER, "FileHandler error.", exception);
        }
        try {
            // Database setup on app start
            DBConnection db = new DBConnection();
            db.setup();
            // End database setup
        } catch (SQLException ex) {
            STARTLOGGER.log(Level.FINE, "DB error.", ex);
        }

        WelcomeScreen newGame = new WelcomeScreen();
        newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newGame.setLocation(300, 300);
        newGame.setVisible(true);
    }
}
