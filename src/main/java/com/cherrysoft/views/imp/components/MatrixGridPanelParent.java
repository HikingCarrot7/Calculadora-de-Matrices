package com.cherrysoft.views.imp.components;

import com.cherrysoft.core.InputMatrix;

import javax.swing.*;
import java.awt.*;

public class MatrixGridPanelParent extends JPanel {

  public void renderMatrixGridPanelChild(SquaredMatrixGridPanel squaredMatrixGridPanel) {
    removeAll();
    add(squaredMatrixGridPanel, BorderLayout.CENTER);
    revalidate();
    repaint();
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

  public String[][] getChildMatrixAsRawMatrix() {
    SquaredMatrixGridPanel child = getSquaredMatrixGridPanelChild();
    return child.getAsRawMatrix();
  }

}
