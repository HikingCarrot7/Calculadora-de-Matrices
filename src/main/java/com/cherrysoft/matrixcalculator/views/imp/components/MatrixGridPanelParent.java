package com.cherrysoft.matrixcalculator.views.imp.components;

import com.cherrysoft.matrixcalculator.core.InputMatrix;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class MatrixGridPanelParent extends JPanel {
  private SquaredMatrixGridPanel squaredMatrixGridPanel;
  private final DecimalFormat decimalFormat;

  public MatrixGridPanelParent() {
    this(SquaredMatrixGridPanel.DOUBLE_2_DECIMAL_PLACES_FORMAT);
  }

  public MatrixGridPanelParent(DecimalFormat decimalFormat) {
    this.decimalFormat = decimalFormat;
  }

  public void renderMatrixGridPanelChild() {
    removeAll();
    add(squaredMatrixGridPanel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }

  public void resetMatrixGridPanel(int matrixSideLength) {
    squaredMatrixGridPanel = new SquaredMatrixGridPanel(matrixSideLength, decimalFormat);
  }

  public SquaredMatrixGridPanel getSquaredMatrixGridPanelChild() {
    return (SquaredMatrixGridPanel) getComponent(0);
  }

  public void clearChildInputFields() {
    SquaredMatrixGridPanel child = getSquaredMatrixGridPanelChild();
    child.clearInputFields();
  }

  public void fillChildInputFieldsWith(InputMatrix inputMatrix) {
    fillChildInputFieldsWith(inputMatrix.getRawMatrix());
  }

  public void fillChildInputFieldsWith(double[][] items) {
    String[][] stringItems = new String[items.length][items[0].length];
    for (int i = 0; i < items.length; i++) {
      for (int j = 0; j < items[0].length; j++) {
        stringItems[i][j] = String.valueOf(items[i][j]);
      }
    }
    fillChildInputFieldsWith(stringItems);
  }

  public void fillChildInputFieldsWith(String[][] items) {
    SquaredMatrixGridPanel child = getSquaredMatrixGridPanelChild();
    child.fillInputFieldsWith(items);
  }

  public String[][] getChildMatrixAsString() {
    SquaredMatrixGridPanel child = getSquaredMatrixGridPanelChild();
    return child.getMatrixAsString();
  }

}
