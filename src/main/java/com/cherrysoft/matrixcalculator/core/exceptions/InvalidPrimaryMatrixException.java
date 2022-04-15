package com.cherrysoft.matrixcalculator.core.exceptions;

public class InvalidPrimaryMatrixException extends RuntimeException {

  public InvalidPrimaryMatrixException() {
    super("Input matrix is invalid!");
  }
}
