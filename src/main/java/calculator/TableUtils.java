/**
 * Utility class for table operations
 *
 * @author Yuko Takegoshi
 */

package calculator;

import javax.swing.*;
import javax.swing.table.TableColumn;

public final class TableUtils {

  /**
   * Set table column widths for a given table using percentages
   *
   * @param table               JTable to set column widths for
   * @param tablePreferredWidth Total width of the table
   * @param percentages         Percentages of individual columns. Must add up to 100.
   */
  public static void setTableColumnsWidth(JTable table, int tablePreferredWidth,
                                          double... percentages) {
    double total = 0;
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      total += percentages[i];
    }

    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
      TableColumn column = table.getColumnModel().getColumn(i);
      column.setPreferredWidth((int)
          (tablePreferredWidth * (percentages[i] / total)));
    }
  }
}
