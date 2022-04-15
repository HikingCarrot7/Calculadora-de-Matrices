package com.cherrysoft.matrixcalculator.persistence.imp;

import com.cherrysoft.matrixcalculator.persistence.exceptions.MatrixFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.cherrysoft.matrixcalculator.persistence.imp.MatrixRepositoryImp.SEPARATOR;

class MatrixReader {
  private File file;

  public String[] readMatrix() {
    try {
      return tryToReadMatrix();
    } catch (FileNotFoundException e) {
      throw new MatrixFileNotFoundException(file.getPath());
    }
  }

  private String[] tryToReadMatrix() throws FileNotFoundException {
    try (Scanner scanner = new Scanner(file)) {
      String firstRow = scanner.nextLine();
      String[] cols = firstRow.split(SEPARATOR);
      String[] matrix = new String[cols.length];
      matrix[0] = firstRow;
      int index = 1;
      while (scanner.hasNextLine()) {
        matrix[index] = scanner.nextLine();
        index++;
      }
      return matrix;
    }
  }

  public void setMatrixPath(String matrixPath) {
    this.file = new File(matrixPath);
  }

}
