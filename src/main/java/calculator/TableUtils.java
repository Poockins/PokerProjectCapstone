package calculator;

import javax.swing.*;
import javax.swing.table.TableColumn;

public final class TableUtils {

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
