/**
 * History panel to display past games
 *
 * @author elich
 * @author Yuko Takegoshi
 */

package calculator;

import javax.swing.*;

public class PokerHistory extends JPanel {

  public PokerHistory() {
    JPanel historyPanel = new JPanel();

    String[] columnNames = {"Name", "Date", "Hand", "Flop", "Turn", "River", "Result"};
    String[][] data = Game.aggregateData();

    JTable historyTable = new JTable(data, columnNames);

    JScrollPane scroll = new JScrollPane(historyTable);

    historyPanel.add(scroll);
//    String hand = "", flop = "", turn = "", river = "", result = "";
//    String[][] dataRow = {{hand, flop, turn, river, result}, {"", "", "", "", ""}};
//    String[] columnNames = {"Hand", "Flop", "Turn", "River", "Result"};
//    JPanel calcPanel = new JPanel();
//    JTable calcTable = new JTable(dataRow, columnNames);
//
//    JScrollPane sp = new JScrollPane(calcTable);
//    sp.setBounds(0, 0, 400, 100);
//    calcPanel.add(sp);
//
//    JButton clearButton = new JButton("Clear History");
//    JButton deleteHistoryButton = new JButton("Delete Entry");
//    //JButton pokerCalculator = new JButton("Poker Calculator");
//
//    calcPanel.add(sp);
//    calcPanel.add(clearButton);
//    calcPanel.add(deleteHistoryButton);
//    //calcPanel.add(pokerCalculator);
//
//    add(calcPanel);
    add(historyPanel);
  }

}
