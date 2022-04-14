package com.cherrysoft.core.utils;

public class MatrixUtils {

  public static double[][] castToDouble(String[][] matrix) {
    return null;
  }

  public static String[][] cutTopLeftSquareSubMatrix(String[][] matrix) {
    int squareSideLength = calculateSquareSideLength(matrix);
    String[][] trimmedMatrix = new String[squareSideLength][squareSideLength];
    for (int i = 0; i < squareSideLength; i++) {
      System.arraycopy(matrix[i], 0, trimmedMatrix[i], 0, squareSideLength);
    }
    return trimmedMatrix;
  }

  public static int calculateSquareSideLength(String[][] matrix) {
    int sideLength = 0;
    int col = 0;
    while (col < matrix.length && !matrix[0][col++].isEmpty()) {
      sideLength++;
    }
    return sideLength;
  }
}
