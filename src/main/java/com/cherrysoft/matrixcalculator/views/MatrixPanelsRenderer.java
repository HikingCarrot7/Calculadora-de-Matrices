package com.cherrysoft.matrixcalculator.views;

import com.cherrysoft.matrixcalculator.views.imp.components.MatrixGridPanelParent;

public class MatrixPanelsRenderer {
  private final MatrixGridPanelParent[] matrixPanelParents;

  public MatrixPanelsRenderer(
      int matrixSideLength,
      MatrixGridPanelParent... matrixPanelParents
  ) {
    this.matrixPanelParents = matrixPanelParents;
    updateMatrixGridPanels(matrixSideLength);
  }

  public void updateMatrixGridPanels(int matrixSideLength) {
    for (MatrixGridPanelParent matrixPanelParent : matrixPanelParents) {
      matrixPanelParent.resetMatrixGridPanel(matrixSideLength);
      matrixPanelParent.renderMatrixGridPanelChild();
    }
  }

}
