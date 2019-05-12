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
  public GameDetailTable(Game game) {
    JPanel detailsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints detailsConstraints = new GridBagConstraints();
    JPanel header = new JPanel(new GridBagLayout());
    GridBagConstraints headerConstraints = new GridBagConstraints();
    headerConstraints.gridx = 0;
    headerConstraints.gridy = 0;
    int gridX = 0;
    String[] labels = {"Flop", "Turn", "River"};
    for (int i = 0; i < labels.length; i++) {
      headerConstraints.gridx = gridX;
      JLabel label = new JLabel(labels[i]);
      header.add(label, headerConstraints);
      gridX++;
    }

    String flop = Cards.arrayToString(game.getFlop());
    String turn = game.getTurn().toString();
    String river = game.getRiver().toString();

    String[] community = {flop, turn, river};
    headerConstraints.gridy = 1;
    headerConstraints.gridx = 0;
    gridX = 0;

    for (int i = 0; i < community.length; i++) {
      headerConstraints.gridx = gridX;
      JLabel label = new JLabel(community[i]);
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


    JScrollPane scrollPane = new JScrollPane(table);

    detailsConstraints.gridx = 0;
    detailsConstraints.gridy = 0;
    detailsPanel.add(header, detailsConstraints);
    detailsConstraints.gridy = 1;
    detailsPanel.add(scrollPane, detailsConstraints);


    add(detailsPanel);
  }
}
