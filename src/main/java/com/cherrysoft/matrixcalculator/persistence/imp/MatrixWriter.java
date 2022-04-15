package com.cherrysoft.matrixcalculator.persistence.imp;

import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.persistence.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;

class MatrixWriter {
  public static final String LINE_BREAK = System.getProperty("line.separator");
  public static final String SPACE = " ";
  private final File file;

  public MatrixWriter(String matrixPath) {
    this.file = new File(matrixPath);
  }

  public void saveInputMatrix(InputMatrix inputMatrix) {
    FileUtils.createFileIfDoesNotExist(file);
    try {
      String[][] rawMatrix = inputMatrix.getRawMatrix();
      tryToSaveMatrix(rawMatrix);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void tryToSaveMatrix(String[][] matrix) throws IOException {
    try (Formatter formatter = new Formatter(file)) {
      for (String[] row : matrix) {
        for (String input : row) {
          formatter.format("%s,", input.isEmpty() ? SPACE : input);
        }
        formatter.format("%s", LINE_BREAK);
      }
    }
  }

}
