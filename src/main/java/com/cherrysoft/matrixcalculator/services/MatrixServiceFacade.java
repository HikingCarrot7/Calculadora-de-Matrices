package com.cherrysoft.matrixcalculator.services;

import com.cherrysoft.matrixcalculator.core.CalculationRequest;
import com.cherrysoft.matrixcalculator.core.CalculationResult;
import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.persistence.MatrixRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class MatrixServiceFacade implements MatrixCalculatorService, MatrixRepository {
  private final MatrixCalculatorService matrixCalculatorService;
  private final MatrixRepository matrixRepository;

  @Override
  public CalculationResult calculateResult(CalculationRequest request) {
    return matrixCalculatorService.calculateResult(request);
  }

  @Override
  public InputMatrix getPrimaryMatrix() {
    return matrixRepository.getPrimaryMatrix();
  }

  @Override
  public InputMatrix getSecondaryMatrix() {
    return matrixRepository.getSecondaryMatrix();
  }

  @Override
  public void saveAsPrimaryMatrix(InputMatrix matrix) {
    matrixRepository.saveAsPrimaryMatrix(matrix);
  }

  @Override
  public void saveAsSecondaryMatrix(InputMatrix matrix) {
    matrixRepository.saveAsSecondaryMatrix(matrix);
  }

}
