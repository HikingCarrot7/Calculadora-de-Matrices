package com.cherrysoft.matrixcalculator.core.utils;

import com.cherrysoft.matrixcalculator.core.InputMatrix;

public class MatrixUtils {

  public static double[][] tryToTrimAndCastToDouble(InputMatrix inputMatrix) {
    return tryToTrimAndCastToDouble(inputMatrix.getRawMatrix());
  }

  public static double[][] tryToTrimAndCastToDouble(String[][] rawMatrix) {
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
    int sideLength = 0;
    for (int i = 0; i < firstRow.length; i++) {
      String input = firstRow[i].trim();
      if (input.isEmpty()) {
        return sideLength;
      }
      sideLength++;
    }
    return sideLength;
  }

  public static double[][] subSquaredMatrixFromTopLeft(double[][] source, int length) {
    double[][] subMatrix = new double[length][length];
    for (int i = 0; i < length; i++) {
      System.arraycopy(source[i], 0, subMatrix[i], 0, length);
    }
    return subMatrix;
  }
}
