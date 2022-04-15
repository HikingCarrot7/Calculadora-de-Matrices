package com.cherrysoft.matrixcalculator.core;

public class Matrix2D {

  private Matrix2D() {
  }

  public static double[][] matrixAddition(double[][] matrixA, double[][] matrixB) {
    double[][] resultMatrix = new double[matrixA.length][matrixA.length];
    for (int row = 0; row < resultMatrix.length; row++) {
      for (int col = 0; col < resultMatrix[row].length; col++) {
        resultMatrix[row][col] = matrixA[row][col] + matrixB[row][col];
      }
    }
    return resultMatrix;
  }

  public static double[][] inverse(double[][] matrix) {
    double determinant = calculateDeterminant(0, matrix);
    double[][] adjugate = calculateAdjugate(matrix);
    return multipliedByScalar(1 / determinant, adjugate);
  }

  public static double calculateDeterminant(double[][] matrix) {
    return calculateDeterminant(0, matrix);
  }

  private static double calculateDeterminant(int i, double[][] matrix) {
    if (matrix.length == 2) {
      return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    } else {
      double determinant = 0;
      for (int j = 0; j < matrix.length; j++) {
        double[][] subMatrix = getSubMatrix(i, j, matrix);
        double subMatrixDeterminant = calculateDeterminant(i, subMatrix);
        determinant += Math.pow(-1, i + j) * matrix[i][j] * subMatrixDeterminant;
      }
      return determinant;
    }
  }

  public static double[][] getSubMatrix(int row, int col, double[][] matrix) {
    double[][] subMatrix = new double[matrix.length - 1][matrix.length - 1];
    for (int i = 0, k = 0; i < matrix.length; i++) {
      for (int j = 0, l = 0; i != row && j < matrix.length; j++) {
        if (j != col) {
          subMatrix[l == subMatrix.length - 1 ? k++ : k][l++] = matrix[i][j];
        }
      }
    }
    return subMatrix;
  }

  public static double[][] calculateAdjugate(double[][] matrix) {
    double[][] cofactorMatrix = new double[matrix.length][matrix.length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        double[][] subMatrix = getSubMatrix(row, col, matrix);
        double subMatrixDeterminant = calculateDeterminant(0, subMatrix);
        cofactorMatrix[row][col] = Math.pow(-1, row + col) * subMatrixDeterminant;
      }
    }
    return calculateTranspose(cofactorMatrix);
  }

  public static double[][] calculateTranspose(double[][] matrix) {
    double[][] transposeMatrix = new double[matrix.length][matrix.length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        transposeMatrix[row][col] = matrix[col][row];
      }
    }
    return transposeMatrix;
  }

  public static double[][] multipliedByScalar(double scalar, double[][] matrix) {
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        matrix[row][col] *= scalar;
      }
    }
    return matrix;
  }

  public static double[][] calculateDotProduct(double[][] matrixA, double[][] matrixB) {
    double[][] dotProduct = new double[matrixB.length][matrixB.length];
    for (int i = 0; i < dotProduct.length; i++) {
      for (int j = 0; j < dotProduct.length; j++) {
        double sum = 0;
        for (int k = 0; k < dotProduct.length; k++) {
          sum += matrixA[j][k] * matrixB[k][i];
        }
        dotProduct[i][j] = sum;
      }
    }
    return dotProduct;
  }

}
