/**
 * Custom table model for the general game history table
 *
 * @author Yuko Takegoshi
 */

package calculator;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameTableModel extends AbstractTableModel {
  private static final String[] COLUMN_NAMES = new String[] {"Date", "Flop", "Turn", "River", "View details", "Delete"};
  private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Date.class, String.class, String.class, String.class, JButton.class, JButton.class};
  private ArrayList<Game> games = Game.findAll();

  @Override
  public int getColumnCount() {
    return COLUMN_NAMES.length;
  }

  @Override
  public int getRowCount() {
    return games.size();
  }

  @Override
  public String getColumnName(int columnIndex) {
    return COLUMN_NAMES[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return COLUMN_TYPES[columnIndex];
  }

  @Override
  public Object getValueAt(final int rowIndex, final int columnIndex) {
    Object[][] data = Game.aggregateData();
    int dataColumnCount = data[rowIndex].length;
    if (columnIndex >= 0 && columnIndex < dataColumnCount) {
      return data[rowIndex][columnIndex];
    } else if (columnIndex == dataColumnCount) {
      final JButton viewButton = new JButton(COLUMN_NAMES[columnIndex]);
      viewButton.addActionListener((ActionEvent e) -> {
        viewButtonHandler(e, rowIndex);
      });
      return viewButton;
    } else if (columnIndex == dataColumnCount + 1) {
      final JButton deleteButton = new JButton(COLUMN_NAMES[columnIndex]);
      deleteButton.addActionListener((ActionEvent e) -> {
        deleteButtonHandler(e, rowIndex);
      });
      return deleteButton;
    } else {
      return "Error";
    }
  }

  private void viewButtonHandler(ActionEvent e, final int rowIndex) {
    JButton source = (JButton) e.getSource();
    Game game = games.get(rowIndex);

    JPanel gameInfo = new GameDetailTable(game);

    JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(source), "Game details", true);
    dialog.setSize(1000, 300);
    dialog.add(gameInfo);
    dialog.setVisible(true);
  }

  private void deleteButtonHandler(ActionEvent e, final int rowIndex) {
    Game game = games.get(rowIndex);
    JButton source = (JButton) e.getSource();

    int n = JOptionPane.showConfirmDialog(
        JOptionPane.getFrameForComponent(source),
        "Are you sure you want to delete this game?",
        "Are you sure?",
        JOptionPane.YES_NO_OPTION);

    if (n == JOptionPane.YES_OPTION) {
      try {
        game.delete();
        games = Game.findAll();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }
}