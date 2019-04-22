/*
 * 
 * @author rob
 * @version 1.0
 */
package calculator;

import java.sql.Date;

public class Game {

    private Cards[] flop;
    private Cards turn;
    private Cards river;
    private Date createdAt;
    private int id;
    private Hand[] hands;
    private Player[] players;

    public Cards[] getFlop() {
        return flop;
    }

    public void setFlop(Cards[] flop) {
        this.flop = flop;
    }

    public Cards getTurn() {
        return turn;

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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hand[] getHands() {
        return hands;
    }

    public void setHands(Hand[] hands) {
        this.hands = hands;
    }

    public void addHand(Hand hand) {

    }

    public void removeHand(Hand hand) {

    }

    public Player getWinner() {///needs changed
        return players[0];
    }
}