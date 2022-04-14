package com.cherrysoft.views.imp.components;

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

  public void fillChildInputFieldsWith(String[][] items) {
    SquaredMatrixGridPanel child = getSquaredMatrixGridPanelChild();
    child.fillInputFieldsWith(items);
  }

  public String[][] getChildMatrixAsRawMatrix() {
    SquaredMatrixGridPanel child = getSquaredMatrixGridPanelChild();
    return child.getAsRawMatrix();
  }

}
