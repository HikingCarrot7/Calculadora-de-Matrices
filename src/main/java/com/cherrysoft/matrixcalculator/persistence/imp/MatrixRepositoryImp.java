package com.cherrysoft.matrixcalculator.persistence.imp;

import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.persistence.MatrixRepository;

public class MatrixRepositoryImp implements MatrixRepository {
  public static final String PRIMARY_MATRIX_PATH = "res//Matriz1.txt";
  public static final String SECONDARY_MATRIX_PATH = "res//Matriz2.txt";
  public static final String SEPARATOR = ",";

  @Override
  public InputMatrix getPrimaryMatrix() {
    return readAndParseMatrix(PRIMARY_MATRIX_PATH);
  }

  @Override
  public InputMatrix getSecondaryMatrix() {
    return readAndParseMatrix(SECONDARY_MATRIX_PATH);
  }

  private InputMatrix readAndParseMatrix(String matrixPath) {
    MatrixReader reader = new MatrixReader(matrixPath);
    String[] matrix = reader.readMatrix();
    MatrixParser parser = new MatrixParser(matrix);
    return parser.parseMatrix();
  }

  @Override
  public void saveAsPrimaryMatrix(InputMatrix inputMatrix) {
    MatrixWriter writer = new MatrixWriter(PRIMARY_MATRIX_PATH);
    writer.saveInputMatrix(inputMatrix);
  }

  @Override
  public void saveAsSecondaryMatrix(InputMatrix inputMatrix) {
    MatrixWriter writer = new MatrixWriter(SECONDARY_MATRIX_PATH);
    writer.saveInputMatrix(inputMatrix);
  }
}
