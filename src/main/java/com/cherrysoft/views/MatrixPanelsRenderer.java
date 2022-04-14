package com.cherrysoft.views;

import com.cherrysoft.views.imp.components.SquaredMatrixGridPanel;
import com.cherrysoft.views.imp.components.MatrixGridPanelParent;

public class MatrixPanelsRenderer {
  private final SquaredMatrixGridPanel[] matrixGridPanels;
  private final MatrixGridPanelParent[] matrixPanelParents;

  public MatrixPanelsRenderer(
      int matrixSideLength,
      MatrixGridPanelParent... matrixPanelParents
  ) {
    this.matrixGridPanels = new SquaredMatrixGridPanel[matrixPanelParents.length];
    this.matrixPanelParents = matrixPanelParents;
    updateMatrixGridPanels(matrixSideLength);
  }

  private void createMatrixGridPanels(int matrixSideLength) {
    for (int i = 0; i < matrixPanelParents.length; i++) {
      matrixGridPanels[i] = new SquaredMatrixGridPanel(matrixSideLength);
    }
  }

  public void updateMatrixGridPanels(int matrixSideLength) {
    createMatrixGridPanels(matrixSideLength);
    for (int i = 0; i < matrixPanelParents.length; i++) {
      MatrixGridPanelParent matrixPanelParent = matrixPanelParents[i];
      SquaredMatrixGridPanel matrixPanel = matrixGridPanels[i];
      matrixPanelParent.renderMatrixGridPanelChild(matrixPanel);
    }
  }

  public SquaredMatrixGridPanel[] getMatrixGridPanels() {
    return matrixGridPanels;
  }

}
