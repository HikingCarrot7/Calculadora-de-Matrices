package com.cherrysoft.matrixcalculator.core.utils;

import com.cherrysoft.matrixcalculator.core.InputMatrix;

public class MatrixUtils {

  public static double[][] tryToCastToDouble(InputMatrix inputMatrix) {
    return tryToCastToDouble(inputMatrix.getRawMatrix());
  }

  public static double[][] tryToCastToDouble(String[][] rawMatrix) {
    int actualSideLength = calculateActualSideLength(rawMatrix);
    double[][] result = new double[actualSideLength][actualSideLength];
    for (int i = 0; i < actualSideLength; i++) {
      for (int j = 0; j < actualSideLength; j++) {
        result[i][j] = Double.parseDouble(rawMatrix[i][j]);
      }
    }
    return result;
  }

  public static String[][] cutTopLeftSquareSubMatrix(String[][] rawMatrix) {
    int actualSideLength = calculateActualSideLength(rawMatrix);
    String[][] trimmedMatrix = new String[actualSideLength][actualSideLength];
    for (int i = 0; i < actualSideLength; i++) {
      System.arraycopy(rawMatrix[i], 0, trimmedMatrix[i], 0, actualSideLength);
    }
    return trimmedMatrix;
  }

  public static int calculateActualSideLength(String[][] rawMatrix) {
    String[] firstRow = rawMatrix[0];
    for (int i = 0; i < firstRow.length; i++) {
      String input = firstRow[i].trim();
      if (input.isEmpty()) {
        return i;
      }
    }
    return 0;
  }
}
