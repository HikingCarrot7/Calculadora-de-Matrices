package com.cherrysoft.matrixcalculator.core.exceptions;

public class InvalidPrimaryMatrixException extends RuntimeException {

  public InvalidPrimaryMatrixException() {
    super("Primary input matrix is invalid!");
  }
}
