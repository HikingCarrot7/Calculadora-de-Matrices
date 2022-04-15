package com.cherrysoft.matrixcalculator.persistence.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.cherrysoft.matrixcalculator.persistence.imp.MatrixRepositoryImp.SEPARATOR;

class MatrixReader {
  private File file;

  public MatrixReader(String matrixPath) {
    this.file = new File(matrixPath);
  }

  public String[] readMatrix() {
    // TODO(Create and set default matrix if file does not exist)
    try {
      return tryToReadMatrix();
    } catch (FileNotFoundException e) {
      throw new RuntimeException("");
    }
  }

  private String[] tryToReadMatrix() throws FileNotFoundException {
    try (Scanner scanner = new Scanner(file)) {
      String firstRow = scanner.nextLine();
      String[] cols = firstRow.split(SEPARATOR);
      int matrixLength = cols.length;
      String[] matrix = new String[matrixLength];
      matrix[0] = firstRow;
      int index = 1;
      while (scanner.hasNextLine()) {
        matrix[index] = scanner.nextLine();
        index++;
      }
      return matrix;
    }
  }
}
