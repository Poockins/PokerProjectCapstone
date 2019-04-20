/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public enum Suit {
    DIAMONDS(1),
    CLUBS(2),
    HEARTS(3),
    SPADES(4);

    private final int suitValue;

    Suit(final int suitValue) {
        this.suitValue = suitValue;
    }

    public int getSuitValue() {
        return this.suitValue;
    }
}