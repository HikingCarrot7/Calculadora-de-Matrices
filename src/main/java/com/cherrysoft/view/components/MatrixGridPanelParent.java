package com.cherrysoft.view.components;

import javax.swing.*;
import java.awt.*;

public class MatrixGridPanelParent extends JPanel {

  public void renderMatrixGridPanelChild(SquaredMatrixGridPanel squaredMatrixGridPanel) {
    removeAll();
    add(squaredMatrixGridPanel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }

}
