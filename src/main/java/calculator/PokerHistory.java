/**
 * History panel to display past games
 *
 * @author elich
 * @author Yuko Takegoshi
 */

package calculator;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PokerHistory extends JPanel {

  public PokerHistory(JPanel parent) {
    JPanel historyPanel = new JPanel();
    Dimension parentSize = new Dimension((int) parent.getPreferredSize().getWidth(), (int) parent.getPreferredSize().getHeight());
    historyPanel.setPreferredSize(parentSize);

    JTable gameTable = new JTable(new GameTableModel());
    JScrollPane scrollPane = new JScrollPane(gameTable);
    scrollPane.setPreferredSize(parentSize);
    TableUtils.setTableColumnsWidth(gameTable, (int) parentSize.getWidth(), 7, 28, 15, 15, 15, 15);
    gameTable.setFillsViewportHeight(true);

    TableCellRenderer buttonRenderer = new JTableButtonRenderer();
    gameTable.getColumn("View details").setCellRenderer(buttonRenderer);
    gameTable.getColumn("Delete").setCellRenderer(buttonRenderer);
    gameTable.addMouseListener(new JTableButtonMouseListener(gameTable));

    historyPanel.add(scrollPane);

    add(historyPanel);
  }
}
