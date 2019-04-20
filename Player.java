/*
 * 
 * @author rob
 * @version 1.0
 */
package calculator;

public class Player {

    private String name;
    private int id;
    private Hand[] hands;

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Hand[] getHands() {
        return hands;
    }

    public void setHands(Hand[] hands) {
        this.hands = hands;
    }
}
