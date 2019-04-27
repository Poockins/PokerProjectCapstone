/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package First;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class GameMain extends JFrame {

    Deck deck;
    Player p1;
    Player p2;
    Player board;

    static ArrayList<String> handResults1 = new ArrayList<>();
    static ArrayList<String> handResults2 = new ArrayList<>();

    GameMain() {

        setTitle(" Poker Program");
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel t = new JPanel();
        JLabel tab = new JLabel();
        JPanel top = new JPanel();
        JPanel buttonPan = new JPanel();
        buttonPan.setLocation(500, 800);

        top.add(buttonPan);
        top.setLocation(0, 700);
        top.add(tab);
        add(top);

        JButton deal = new JButton("Deal");
        buttonPan.add(deal);

        JButton exit = new JButton("EXIT");
        buttonPan.add(exit);

        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\AceSpades.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(60, 80, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);

        JLabel p1Card1 = new JLabel();

        /////////////////////////////////////Player 1 card and name Panels///////////////////////
        JPanel cPan = new JPanel();
        cPan.setSize(150, 175);
        cPan.setLocation(500, 600);
        cPan.add(p1Card1);

        JPanel p1Pan = new JPanel();
        p1Pan.setSize(100, 100);
        p1Pan.setLocation(525, 720);
        JLabel player1Label = new JLabel("Player 1");
        p1Pan.add(player1Label);
        add(p1Pan);
        /////////////////////////////////////end Player 1 stuff///////////////////////////////////

        /////////////////////////////////////Player 2 card and name Panels///////////////////////
        JLabel p2Card1 = new JLabel();
        JLabel p2Card2 = new JLabel();
        JPanel cPan2 = new JPanel();
        cPan2.setSize(150, 175);
        cPan2.setLocation(500, 100);
        cPan2.add(p2Card1);
        cPan2.add(p2Card2);

        JPanel p2Pan = new JPanel();
        p2Pan.setSize(150, 120);
        p2Pan.setLocation(525, 120);
        JLabel player2Label = new JLabel("Player 2");

        p2Pan.add(p2Card1);
        p2Pan.add(p2Card2);
        p2Pan.add(player2Label);
        add(p2Pan);
        /////////////////////////////////////end Player 2 stuff///////////////////////////////////

        /////////////////////////////Board cards and label stuff////////////////////////////////
        JPanel boardPan = new JPanel();
        boardPan.setSize(300, 175);
        boardPan.setLocation(500, 350);
        //boardPan.add(p1Card1);

        JLabel board1 = new JLabel();
        JLabel board2 = new JLabel();
        JLabel board3 = new JLabel();
        JLabel board4 = new JLabel();
        JLabel board5 = new JLabel();

        JPanel boardNamePan = new JPanel();
        boardNamePan.setSize(400, 125);
        boardNamePan.setLocation(375, 350);
        JLabel boardLabel = new JLabel("Community Cards");

        boardNamePan.add(board1);
        boardNamePan.add(board2);
        boardNamePan.add(board3);
        boardNamePan.add(board4);
        boardNamePan.add(board5);
        boardNamePan.add(boardLabel);
        add(boardNamePan);
        ////////////////////////////////////////////////////////////////////////////////////////

        JLabel p1Card2 = new JLabel();
        cPan.add(p1Card2);

        add(cPan);

        //ActionListener to deal new hand button in clicked
        deal.addActionListener((ActionEvent e) -> {

            try {
                handResults1 = new ArrayList<>();
                handResults2 = new ArrayList<>();
                deck = Deck.newShuffledDeck();

                p1 = new Player("p1");
                p2 = new Player("p2");
                board = new Player("board");

                //Cards dealt to players from the deck stack
                p1.addCard(deck.getDeckCards());
                p2.addCard(deck.getDeckCards());
                p1.addCard(deck.getDeckCards());
                p2.addCard(deck.getDeckCards());

                //Display card images to the cards each player is dealt
                ImageIcon im = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + p1.getCards().get(0) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                p1Card1.setIcon(im);
                ImageIcon im2 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + p1.getCards().get(1) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                p1Card2.setIcon(im2);
                im2 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + p2.getCards().get(0) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                p2Card1.setIcon(im2);
                im2 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + p2.getCards().get(1) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                p2Card2.setIcon(im2);

                //Burn card
                System.out.println("Flop Burn card (Just for show): " + deck.getDeckCards().pop());

                //FLOP
                board.addCard(deck.getDeckCards());
                board.addCard(deck.getDeckCards());
                board.addCard(deck.getDeckCards());

                //Burn card
                System.out.println("Turn Burn card (Just for show): " + deck.getDeckCards().pop());

                //TURN
                board.addCard(deck.getDeckCards());

                //Burn card
                System.out.println("River Burn card (Just for show): " + deck.getDeckCards().pop());

                //RIVER
                board.addCard(deck.getDeckCards());

                //Display card images of the cards dealt on the flop, turn, and river
                ImageIcon boardImg1 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + board.getCards().get(0) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                board1.setIcon(boardImg1);
                boardImg1 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + board.getCards().get(1) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                board2.setIcon(boardImg1);
                boardImg1 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + board.getCards().get(2) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                board3.setIcon(boardImg1);
                boardImg1 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + board.getCards().get(3) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                board4.setIcon(boardImg1);
                boardImg1 = new ImageIcon(new ImageIcon("C:\\Users\\rob\\Desktop\\NetBeansProjects\\Poker2\\" + board.getCards().get(4) + ".jpg").getImage().getScaledInstance(60, 80, Image.SCALE_DEFAULT));
                board5.setIcon(boardImg1);

                /**
                 * Print the player's cards and cards on the board to ensure the
                 * right images are displayed
                 *
                 */
                System.out.println("Player 1: " + p1.getCards());
                System.out.println("Player 2: " + p2.getCards());
                System.out.println("Board/community: " + board.getCards());

                /**
                 * Check each player's usable cards to determine their best
                 * available hand. Currently checks for pairs, three of a kind,
                 * four of a kind, straight, and flush.
                 *
                 * Need to combine logic for full house and straight flush
                 */
                p1PairCheck(p1, board);
                p2PairCheck(p2, board);
                p1StraightCheck(p1, board);
                p2StraightCheck(p2, board);
                System.out.println("P1");
                flushCheck(p1, board);
                System.out.println("P2");
                flushCheck(p2, board);

                System.out.println("The winner is...  ");

                System.out.println("P1   \n " + handResults1);
                System.out.println("P2    \n" + handResults2);

            } catch (Exception p) {
                p.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERRRRRRORRRRRR");
            }
        });

        t.add(top);
        add(t);

        setVisible(true);

    }

    /**
     * Pair checking algorithm puts the rank values of usable cards into an
     * ArrayList and checks the frequency that each rank is found. 2 = pair 3 =
     * three of a kind 4 = four of a kind
     */
    private static void p1PairCheck(Player p, Player b) {

        ArrayList<Cards> pairUse1 = new ArrayList<>();
        ArrayList<Integer> vvs = new ArrayList<>();
        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            pairUse1.add(b.getCards().get(i));
        }
        for (int i = 0; i < 2; i++) {
            pairUse1.add(p.getCards().get(i));
        }
        for (int i = 0; i < pairUse1.size(); i++) {
            vvs.add(pairUse1.get(i).getRank().getRankValue());

        }

        Collections.sort(vvs);
        for (int t = 0; t < vvs.size(); t++) {

            if (Collections.frequency(vvs, vvs.get(t)) == 2) {
                results.add("Pair of " + vvs.get(t));

                handResults1.add("Pair of " + vvs.get(t));
                t++;
            }
            if (Collections.frequency(vvs, vvs.get(t)) == 3) {
                results.add("Three of a kind " + vvs.get(t));
                handResults1.add("Three of a kind " + vvs.get(t));
                t++;
                t++;
            }
            if (Collections.frequency(vvs, vvs.get(t)) == 4) {
                results.add("Four of a kind " + vvs.get(t));
                handResults1.add("Four of a kind " + vvs.get(t));
                t++;
                t++;
                t++;
            }

        }

    }

    private static void p2PairCheck(Player p, Player b) {

        ArrayList<Cards> pairUse2 = new ArrayList<>();
        ArrayList<Integer> vvs2 = new ArrayList<>();
        ArrayList<String> results2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            pairUse2.add(b.getCards().get(i));
        }
        for (int i = 0; i < 2; i++) {
            pairUse2.add(p.getCards().get(i));
        }

        for (int i = 0; i < pairUse2.size(); i++) {
            vvs2.add(pairUse2.get(i).getRank().getRankValue());
        }

        Collections.sort(vvs2);
        for (int t = 0; t < vvs2.size(); t++) {

            if (Collections.frequency(vvs2, vvs2.get(t)) == 2) {
                results2.add("Pair of " + vvs2.get(t));
                handResults2.add("Pair of " + vvs2.get(t));
                t++;
            }
            if (Collections.frequency(vvs2, vvs2.get(t)) == 3) {
                results2.add("Three of a kind " + vvs2.get(t));
                handResults2.add("Three of a kind " + vvs2.get(t));
                t++;
                t++;
            }
            if (Collections.frequency(vvs2, vvs2.get(t)) == 4) {
                results2.add("Four of a kind " + vvs2.get(t));
                handResults2.add("Four of a kind " + vvs2.get(t));
                t++;
                t++;
                t++;
            }

        }

    }

    /**
     * Straight checking algorithm puts the rank values of usable cards into an
     * ArrayList of Integers. Then, it sorts the list and checks for a streak of
     * 5 consecutive values.
     */
    private static void p1StraightCheck(Player p, Player b) {

        ArrayList<Integer> vvs2 = new ArrayList<>();
        ArrayList<String> results2 = new ArrayList<>();
        ArrayList<Cards> straightUse1 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            straightUse1.add(b.getCards().get(i));
        }
        for (int i = 0; i < 2; i++) {
            straightUse1.add(p.getCards().get(i));
        }

        for (int i = 0; i < straightUse1.size(); i++) {
            vvs2.add(straightUse1.get(i).getRank().getRankValue());

        }

        Collections.sort(vvs2);
        for (int t = 0; t < vvs2.size(); t++) {

            if (Collections.frequency(vvs2, vvs2.get(t) + 1) > 0) {
                if (Collections.frequency(vvs2, vvs2.get(t) + 2) > 0) {
                    if (Collections.frequency(vvs2, vvs2.get(t) + 3) > 0) {
                        if (Collections.frequency(vvs2, vvs2.get(t) + 4) > 0) {

                            if (Collections.frequency(vvs2, vvs2.get(t) + 5) > 0) {

                                if (Collections.frequency(vvs2, vvs2.get(t) + 6) > 0) {

                                    results2.add("Straight " + (vvs2.get(t) + 6) + " High");
                                    handResults1.add("Straight " + (vvs2.get(t) + 6) + " High");
                                    t++;
                                    t++;
                                    t++;
                                    t++;
                                    t++;
                                } else {
                                    results2.add("Straight " + (vvs2.get(t) + 5) + " High");
                                    handResults1.add("Straight " + (vvs2.get(t) + 5) + " High");
                                }
                                t++;
                                t++;
                                t++;
                                t++;
                                t++;
                            } else {
                                results2.add("Straight " + (vvs2.get(t) + 4) + " High");
                                handResults1.add("Straight " + (vvs2.get(t) + 4) + " High");
                            }
                            t++;
                            t++;
                            t++;
                            t++;
                        }
                    }
                }

            }
        }

    }

    /**
     * Straight checking algorithm puts the rank values of usable cards into an
     * ArrayList of Integers. Then, it sorts the list and checks for a streak of
     * 5 consecutive values.
     */
    private static void p2StraightCheck(Player p, Player b) {

        ArrayList<Cards> p2Usable = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            p2Usable.add(b.getCards().get(i));
        }
        for (int i = 0; i < 2; i++) {
            p2Usable.add(p.getCards().get(i));
        }
        Collections.sort(p2Usable);

        ArrayList<Integer> vvs2 = new ArrayList<>();
        ArrayList<String> results2 = new ArrayList<>();
        ArrayList<Cards> straightUse2 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            straightUse2.add(b.getCards().get(i));
        }
        for (int i = 0; i < 2; i++) {
            straightUse2.add(p.getCards().get(i));
        }

        for (int i = 0; i < straightUse2.size(); i++) {
            vvs2.add(straightUse2.get(i).getRank().getRankValue());
        }

        Collections.sort(vvs2);
        for (int t = 0; t < vvs2.size(); t++) {

            if (Collections.frequency(vvs2, vvs2.get(t) + 1) > 0) {
                if (Collections.frequency(vvs2, vvs2.get(t) + 2) > 0) {
                    if (Collections.frequency(vvs2, vvs2.get(t) + 3) > 0) {

                        if (Collections.frequency(vvs2, vvs2.get(t) + 4) > 0) {

                            if (Collections.frequency(vvs2, vvs2.get(t) + 5) > 0) {

                                if (Collections.frequency(vvs2, vvs2.get(t) + 6) > 0) {

                                    results2.add("Straight " + p2Usable.get(t + 6).getRank() + " High");
                                    handResults2.add("Straight " + p2Usable.get(t + 6).getRank() + " High");
                                    t++;
                                    t++;
                                    t++;
                                    t++;
                                    t++;
                                } else {
                                    results2.add("Straight " + p2Usable.get(t + 5).getRank() + " High");
                                    handResults2.add("Straight " + p2Usable.get(t + 5).getRank() + " High");
                                }
                                t++;
                                t++;
                                t++;
                                t++;
                                t++;
                            } else {
                                results2.add("Straight " + p2Usable.get(t + 4).getRank() + " High");
                                handResults2.add("Straight " + p2Usable.get(t + 4).getRank() + " High");
                            }
                            t++;
                            t++;
                            t++;
                            t++;
                        }
                    }
                }

            }
        }

    }

    /**
     * Flush checking algorithm puts the Suits of usable cards into an ArrayList
     * and checks the frequency that each suit is found. If five of a suit is
     * found, the highest card rank of that suit is found.
     *
     */
    private static void flushCheck(Player p, Player b) {

        ArrayList<Cards> flushUsable2 = new ArrayList<>();
        ArrayList<Suit> vvs2 = new ArrayList<>();
        ArrayList<String> results2 = new ArrayList<>();

        ArrayList<Cards> flushUsable = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            flushUsable.add(b.getCards().get(i));
        }

        for (int i = 0; i < 2; i++) {
            flushUsable.add(p.getCards().get(i));
        }

        for (int i = 0; i < flushUsable.size(); i++) {
            vvs2.add(flushUsable.get(i).getSuit());

        }

        Collections.sort(flushUsable);

        if (Collections.frequency(vvs2, Suit.CLUBS) > 4) {

            if (p.getName().contains("1")) {
                handResults1.add("CLUB FLUSH!!!!!!!!!!!!");
            }
            if (p.getName().contains("2")) {
                handResults2.add("CLUB FLUSH!!!!!!!!!!!!");
            }
            System.out.println("CLUB FLUSH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
        if (Collections.frequency(vvs2, Suit.DIAMONDS) > 4) {
            if (p.getName().contains("1")) {
                handResults1.add("DIAMONDS FLUSH!!!!!!!!!!!!");
            }
            if (p.getName().contains("2")) {
                handResults2.add("DIAMONDS FLUSH!!!!!!!!!!!!");
            }
            System.out.println("DIAMOND FLUSH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
        if (Collections.frequency(vvs2, Suit.HEARTS) > 4) {
            if (p.getName().contains("1")) {
                handResults1.add("HEARTS FLUSH!!!!!!!!!!!!");
            }
            if (p.getName().contains("2")) {
                handResults2.add("HEARTS FLUSH!!!!!!!!!!!!");
            }
            System.out.println("HEART FLUSH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
        if (Collections.frequency(vvs2, Suit.SPADES) > 4) {
            if (p.getName().contains("1")) {
                handResults1.add("SPADE FLUSH!!!!!!!!!!!!");
            }
            if (p.getName().contains("2")) {
                handResults2.add("SPADE FLUSH!!!!!!!!!!!!");
            }
            System.out.println("SPADE FLUSH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }

    }

    public static void main(String[] args) {
        GameMain d = new GameMain();

    }

}
