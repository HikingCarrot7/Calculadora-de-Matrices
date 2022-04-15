package com.cherrysoft.matrixcalculator.core;

import com.cherrysoft.matrixcalculator.core.utils.MatrixUtils;

public class MatrixValidator {
  private final String DOUBLE_REGEX = "^-?[0-9]+(.?[0-9]+)*$";
  private final int MIN_MATRIX_ORDER = 3;
  private String[][] rawMatrix;

  public boolean isValidMatrix(InputMatrix inputMatrix) {
    return isValidMatrix(inputMatrix.getRawMatrix());
  }

  public boolean isValidMatrix(String[][] rawMatrix) {
    this.rawMatrix = rawMatrix;
    return isValidMatrix();
  }

  private boolean isValidMatrix() {
    if (MatrixUtils.calculateActualSideLength(rawMatrix) < MIN_MATRIX_ORDER) {
      return false;
    }
    return inputFieldsValid();
  }

  private boolean inputFieldsValid() {
    String[][] trimmedMatrix = MatrixUtils.cutTopLeftSquareSubMatrix(rawMatrix);
    for (String[] row : trimmedMatrix) {
      for (String input : row) {
        boolean isInputEmpty = input.isEmpty();
        boolean isInvalidDouble = !input.matches(DOUBLE_REGEX);
        if (isInputEmpty || isInvalidDouble) {
          return false;
        }
      }
    }
    return true;
  }

}
