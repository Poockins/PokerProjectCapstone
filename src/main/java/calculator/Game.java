/**
 * Game class represents a game with player hands and community cards
 *
 * @author rob
 * @author Yuko Takegoshi
 * @version 1.0
 */
package calculator;

import org.hsqldb.jdbc.JDBCArray;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private final static Logger GAMELOGGER = Logger.
            getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Cards[] flop;
    private Cards turn;
    private Cards river;
    private Date createdAt;
    private int id;
    private Hand[] hands;
    private Player[] players;

    /**
     * Constructor to create a new record with auto-generated id and createdAt
     */
    public Game() {
        try {
            DBConnection db = new DBConnection();
            String query = "INSERT INTO games (created_at) VALUES (CURRENT_TIMESTAMP)";
            List<Map<String, Object>> keys = db.insertQuery(query);
            Map<String, Object> resultRow = keys.get(0);
            this.id = (Integer) resultRow.get("ID");
            List<Map<String, Object>> results = db
                    .selectQuery(String.format("SELECT created_at FROM games WHERE id=%d", this.id));
            Map<String, Object> row = results.get(0);
            Date createdAt = (Date) row.get("CREATED_AT");
            this.createdAt = createdAt;
        } catch (Exception ex) {
            GAMELOGGER.log(Level.FINE, "DB Game auto creation error", ex);
        }
    }

    /**
     * Construct given database information
     */
    public Game(Cards[] flop, Cards turn, Cards river, int id, Hand[] hands, Date createdAt) {
        this.flop = flop;
        this.turn = turn;
        this.river = river;
        this.id = id;
        this.hands = hands;
        this.createdAt = createdAt;
    }

    /**
     * Constructor given params from the database
     *
     * @param params Map of database data, keyed by column
     */
    public Game(Map<String, Object> params) {
        Cards turn = Cards.stringToCard((String) params.get("TURN"));
        Cards river = Cards.stringToCard((String) params.get("RIVER"));
        JDBCArray flop = (JDBCArray) params.get("FLOP");
        try {
            Object[] flopObjects = (Object[]) flop.getArray();
            String[] flopStrings = Arrays.stream(flopObjects)
                    .toArray(String[]::new);
            this.flop = Cards.stringToArray(flopStrings);
            this.turn = turn;
            this.river = river;
            this.id = (Integer) params.get("ID");
            this.createdAt = (Date) params.get("CREATED_AT");
        } catch (SQLException ex) {
            GAMELOGGER.log(Level.FINE, "DB Game creation with params error", ex);
        }
    }

    public Cards[] getFlop() {
        return flop;
    }

    public void setFlop(Cards[] flop) {
        this.flop = flop;
    }

    public Cards getTurn() {
        return turn;

    }

    public void setTurn(Cards turn) {
        this.turn = turn;
    }

    public Cards getRiver() {
        return river;
    }

    public void setRiver(Cards river) {
        this.river = river;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public Hand[] getHands() {
        return hands;
    }

    public Hand[] getHandsFromDB() {
        ArrayList<Hand> handList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            String query = "SELECT * FROM hands WHERE game_id=?";
            List<Map<String, Object>> results = db.selectQuery(query, this.id);
            for (Map<String, Object> result : results) {
                Hand hand = new Hand(result);
                handList.add(hand);
            }
        } catch (Exception ex) {
            GAMELOGGER.log(Level.FINE, "DB get Hands error", ex);
        }

        return handList.toArray(new Hand[handList.size()]);
    }

    public void addHand(Hand hand) {
        ArrayList<Hand> newHands = new ArrayList<>(Arrays.asList(this.hands));
        newHands.add(hand);
        Hand[] converted = new Hand[newHands.size()];
        this.hands = newHands.toArray(converted);
        setPlayers();
    }

    public void removeHand(Hand hand) {

        setPlayers();
    }

    public void setPlayers() {
        this.hands = hands == null ? getHandsFromDB() : hands;
        Player[] players = new Player[this.hands.length];
        for (int i = 0; i < this.hands.length; i++) {
            Hand hand = this.hands[i];
            players[i] = hand.getPlayer();
        }

        this.players = players;
    }

    /**
     * Get the players in the game through their Hands
     *
     * @return players in the game
     */
    public Player[] getPlayers() {
        setPlayers();
        return this.players;
    }

    public static Game findById(int id) {
        List<Map<String, Object>> results = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();
            results = db.selectQuery("SELECT * FROM games WHERE id = ?", id);
        } catch (SQLException ex) {
            GAMELOGGER.log(Level.FINE, "DB find Game by id error", ex);
        }

        if (results.size() > 0) {
            return new Game(results.get(0));
        } else {
            return null;
        }
    }

    public static ArrayList<Game> findAll() {
        ArrayList<Game> found = new ArrayList<>();
        List<Map<String, Object>> results = new ArrayList<>();

        try {
            DBConnection db = new DBConnection();
            results = db.selectQuery("SELECT * FROM games ORDER BY created_at DESC");
        } catch (SQLException ex) {
            GAMELOGGER.log(Level.FINE, "DB find all games error", ex);
        }

        for (Map<String, Object> row : results) {
            Game game = new Game(row);
            found.add(game);
        }

        return found;
    }

    public boolean save() throws SQLException {
        int updated = 0;
        DBConnection db = new DBConnection();
        String query = "UPDATE games SET flop=?, turn=?, river=? WHERE id=?";
        String[] flopParam = Cards.toStringArray(flop);
        updated = db.updateQuery(query, flopParam, this.turn.toDataString(), this.river.toDataString(),
                this.id);
        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String[][] aggregateData() {
        ArrayList<Game> allGames = findAll();
        ArrayList<String[]> data = new ArrayList<>();

        for (Game game : allGames) {
            Cards[] flop = game.getFlop();
            String flopString = Cards.arrayToString(flop);
            String turn = game.getTurn().toString();
            String river = game.getRiver().toString();
            String gameDate = game.getCreatedAt().toString();
            String result = "";

            for (Hand hand : game.getHandsFromDB()) {
                Player player = hand.getPlayer();
                String[] row = {player.getName(), gameDate, hand.toString(), flopString, turn, river, result};
                data.add(row);
            }
        }

        return data.toArray(new String[data.size()][7]);
    }

//  public Player getWinner() {///needs changed
//    return players[0];
//  }
}
