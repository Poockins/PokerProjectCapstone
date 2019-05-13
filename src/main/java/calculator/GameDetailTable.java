/**
 * Creates a JPanel of the Game details data.
 *
 * @author Yuko Takegoshi
 */

package calculator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GameDetailTable extends JPanel {
  public GameDetailTable(Game game, Dimension size) {
    JPanel detailsPanel = new JPanel(new BorderLayout());
    detailsPanel.setPreferredSize(size);
    JPanel header = new JPanel(new GridBagLayout());
    header.setPreferredSize(new Dimension(size.width, 40));
    GridBagConstraints headerConstraints = new GridBagConstraints();
    headerConstraints.gridx = 0;
    headerConstraints.gridy = 0;
    headerConstraints.anchor = GridBagConstraints.CENTER;
    int gridX = 0;
    String[] labels = {"Flop", "Turn", "River"};
    for (int i = 0; i < labels.length; i++) {
      headerConstraints.gridx = gridX;
      JLabel label = new JLabel(labels[i]);
      if (i == 0) {
        headerConstraints.gridwidth = 2;
        headerConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridX++;
      } else {
        headerConstraints.gridwidth = 1;
        headerConstraints.fill = GridBagConstraints.NONE;
      }
      header.add(label, headerConstraints);
      gridX++;
    }

    String flop = Cards.arrayToHumanString(game.getFlop());
    String turn = game.getTurn().toString();
    String river = game.getRiver().toString();

    String[] community = {flop, turn, river};
    headerConstraints.gridy = 1;
    headerConstraints.gridx = 0;
    gridX = 0;

    for (int i = 0; i < community.length; i++) {
      headerConstraints.gridx = gridX;
      JLabel label = new JLabel(community[i]);
      if (i == 0) {
        headerConstraints.gridwidth = 2;
        headerConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridX++;
      } else {
        headerConstraints.gridwidth = 1;
        headerConstraints.fill = GridBagConstraints.NONE;
      }
      header.add(label, headerConstraints);
      gridX++;
    }

    String[] columnNames = {"Player", "Hand", "Pre-flop", "Flop", "Turn", "River"};
    String[][] data = game.aggregateDetails();

    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    JTable table = new JTable(tableModel);
    int tableWidth = size.width;
    int tableHeight = size.height;
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setSize(tableWidth, tableHeight);
    TableUtils.setTableColumnsWidth(table, tableWidth, 10, 50, 10, 10, 10, 10);


    JScrollPane scrollPane = new JScrollPane(table);

    detailsPanel.add(header, BorderLayout.PAGE_START);
    detailsPanel.add(scrollPane, BorderLayout.CENTER);


    add(detailsPanel);
  }
}
