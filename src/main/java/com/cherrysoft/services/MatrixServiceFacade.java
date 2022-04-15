package com.cherrysoft.services;

public class MatrixServiceFacade {
  private final MatrixCalculatorService matrixCalculatorService;
  private final MatrixPersistenceService matrixPersistenceService;

  public MatrixServiceFacade(MatrixCalculatorService matrixCalculatorService,
                             MatrixPersistenceService matrixPersistenceService) {
    this.matrixCalculatorService = matrixCalculatorService;
    this.matrixPersistenceService = matrixPersistenceService;
  }

}
