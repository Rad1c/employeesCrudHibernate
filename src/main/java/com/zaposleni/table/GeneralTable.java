package com.zaposleni.table;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class GeneralTable {

  private final JTable table;

  public GeneralTable(Panel pnlTable, DefaultTableModel model, Dimension frameDim) {
    model.addColumn("ID");
    model.addColumn("Ime");
    model.addColumn("Broj godina");
    model.addColumn("Adresa");
    model.addColumn("Visina dohotka");
      
    table = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      };

      @Override
      public boolean getScrollableTracksViewportWidth() {
        return getPreferredSize().width < getParent().getWidth();
      }
    };
    
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setPreferredScrollableViewportSize(new Dimension((int)frameDim.width, frameDim.height));
    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension((int)(frameDim.width*0.95), frameDim.height));
    pnlTable.add(scrollPane, BorderLayout.LINE_START);
  }

}