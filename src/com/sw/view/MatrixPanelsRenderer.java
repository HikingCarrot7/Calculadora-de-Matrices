package com.sw.view;

import com.sw.view.components.SquaredMatrixGridPanel;
import com.sw.view.components.MatrixGridPanelParent;

public class MatrixPanelsRenderer {
  private final SquaredMatrixGridPanel[] matrixPanels;
  private final MatrixGridPanelParent[] matrixPanelParents;

  public MatrixPanelsRenderer(
      int matrixSideLength,
      MatrixGridPanelParent... matrixPanelParents
  ) {
    this.matrixPanels = new SquaredMatrixGridPanel[matrixPanelParents.length];
    this.matrixPanelParents = matrixPanelParents;
    updateMatrixGridPanels(matrixSideLength);
  }

  private void createMatrixGridPanels(int matrixSideLength) {
    for (int i = 0; i < matrixPanelParents.length; i++) {
      matrixPanels[i] = new SquaredMatrixGridPanel(matrixSideLength);
    }
  }

  public void updateMatrixGridPanels(int matrixSideLength) {
    createMatrixGridPanels(matrixSideLength);
    for (int i = 0; i < matrixPanelParents.length; i++) {
      MatrixGridPanelParent matrixPanelParent = matrixPanelParents[i];
      SquaredMatrixGridPanel matrixPanel = matrixPanels[i];
      matrixPanelParent.renderMatrixGridPanelChild(matrixPanel);
    }
  }

  public SquaredMatrixGridPanel[] getMatrixPanels() {
    return matrixPanels;
  }

}
