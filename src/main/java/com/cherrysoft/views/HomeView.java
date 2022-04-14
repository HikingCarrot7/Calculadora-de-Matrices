package com.cherrysoft.views;

import com.cherrysoft.core.CalculationResult;
import com.cherrysoft.core.InputMatrix;

public interface HomeView {

  InputMatrix getInputMatrix();

  double getScalar();

  int getOrderOfMatrix();

  void setOrderOfMatrix(int orderOfMatrix);

  boolean useSecondaryMatrix();

  void setInitialInputMatrixState(InputMatrix inputMatrix);

  void showCalculatedDeterminant(double determinant);

  void showCalculationResult(CalculationResult calculationResult);

  void clearAll();

  void showError(String message);

  void setListener(HomeView.Listener listener);

  interface Listener {

    void onCalculateResult();

    void onSetOrderOfMatrix();

    void onShowSecondaryMatrix();

    void onClearAll();

    void onSystemClosing();
  }

}
