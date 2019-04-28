/*
 * 
 * @author rob
 * @version 1.0
 */
package calculator;

public class Hand {

    private Cards[] cards;
    private int id;
    private Player player;

    //private Game game;
    public Cards[] getCards() {
        return cards;
    }

    public void setCards(Cards[] cards) {
        this.cards = cards;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public double calculateWin() {///// Needs changed

        double win = 0.0;
        return win;
    }

    public String toString() {
        return "";
    }
}