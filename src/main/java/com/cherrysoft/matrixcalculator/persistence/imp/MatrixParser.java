package com.cherrysoft.matrixcalculator.persistence.imp;

import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.sun.security.jgss.GSSUtil;

import java.util.Arrays;

import static com.cherrysoft.matrixcalculator.persistence.imp.MatrixRepositoryImp.SEPARATOR;

class MatrixParser {
  private final String[] matrix;

  public MatrixParser(String[] matrix) {
    this.matrix = matrix;
  }

  public InputMatrix parseMatrix() {
    String[][] parsedMatrix = new String[matrix.length][];
    for (int i = 0; i < matrix.length; i++) {
      String[] cols = matrix[i].split(SEPARATOR);
      parsedMatrix[i] = cols;
    }
    return new InputMatrix(parsedMatrix);
  }
}
