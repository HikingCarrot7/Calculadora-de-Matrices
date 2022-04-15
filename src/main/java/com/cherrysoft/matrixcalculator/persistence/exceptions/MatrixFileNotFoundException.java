package com.cherrysoft.matrixcalculator.persistence.exceptions;

public class MatrixFileNotFoundException extends RuntimeException {

  public MatrixFileNotFoundException(String matrixPath) {
    super("Could not find file with path: " + matrixPath);
  }

}
