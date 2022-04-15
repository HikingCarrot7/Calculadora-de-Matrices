package com.cherrysoft.matrixcalculator.persistence;

import com.cherrysoft.matrixcalculator.core.InputMatrix;

public interface MatrixRepository {

  InputMatrix getPrimaryMatrix();

  InputMatrix getSecondaryMatrix();

  void saveAsPrimaryMatrix(InputMatrix matrix);

  void saveAsSecondaryMatrix(InputMatrix matrix);

}
