package com.cherrysoft.matrixcalculator.persistence.imp;

import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.persistence.MatrixRepository;
import com.cherrysoft.matrixcalculator.persistence.exceptions.MatrixFileNotFoundException;

public class MatrixRepositoryImp implements MatrixRepository {
  public static final String PRIMARY_MATRIX_PATH = "res/primary_matrix.txt";
  public static final String SECONDARY_MATRIX_PATH = "res/secondary_matrix.txt";
  public static final String SEPARATOR = ",";
  public static final String[][] EMPTY_3X3_MATRIX = new String[][]{
    new String[]{"", "", ""},
    new String[]{"", "", ""},
    new String[]{"", "", ""},
  };

  private MatrixReader reader;
  private MatrixWriter writer;
  private MatrixParser parser;

  public MatrixRepositoryImp() {
    reader = new MatrixReader();
    writer = new MatrixWriter();
    parser = new MatrixParser();
  }

  @Override
  public InputMatrix getPrimaryMatrix() {
    return readAndParseMatrix(PRIMARY_MATRIX_PATH);
  }

  @Override
  public InputMatrix getSecondaryMatrix() {
    return readAndParseMatrix(SECONDARY_MATRIX_PATH);
  }

  private InputMatrix readAndParseMatrix(String matrixPath) {
    try {
      return tryReadAndParseMatrix(matrixPath);
    } catch (MatrixFileNotFoundException e) {
      return saveAndReadEmptyMatrix(matrixPath);
    }
  }

  private InputMatrix tryReadAndParseMatrix(String matrixPath) {
    reader.setMatrixPath(matrixPath);
    String[] matrix = reader.readMatrix();
    parser.setMatrix(matrix);
    return parser.parseMatrix();
  }

  private InputMatrix saveAndReadEmptyMatrix(String matrixPath) {
    writer.setMatrixPath(matrixPath);
    InputMatrix inputMatrix = new InputMatrix(EMPTY_3X3_MATRIX);
    writer.saveInputMatrix(inputMatrix);
    return inputMatrix;
  }

  @Override
  public void saveAsPrimaryMatrix(InputMatrix inputMatrix) {
    writer.setMatrixPath(PRIMARY_MATRIX_PATH);
    writer.saveInputMatrix(inputMatrix);
  }

  @Override
  public void saveAsSecondaryMatrix(InputMatrix inputMatrix) {
    writer.setMatrixPath(SECONDARY_MATRIX_PATH);
    writer.saveInputMatrix(inputMatrix);
  }
}
