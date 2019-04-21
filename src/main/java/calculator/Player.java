/*
 * 
 * @author rob
 * @version 1.0
 */
package calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Player {

    private String name;
    private ArrayList<Cards> cards = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Cards> getCards() {
        return this.cards;
    }

    public void setCards(ArrayList<Cards> cards) {
        this.cards = cards;
    }

    Player(String name) {
        this.name = name;
    }

    public void addCard(Stack<Cards> s) {

        cards.add(s.pop());

    }

}
