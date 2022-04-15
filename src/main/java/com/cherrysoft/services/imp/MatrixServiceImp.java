package com.cherrysoft.services.imp;

import com.cherrysoft.core.*;
import com.cherrysoft.core.exceptions.InvalidPrimaryMatrixException;
import com.cherrysoft.services.MatrixCalculatorService;

import static com.cherrysoft.core.Matrix2D.*;
import static com.cherrysoft.core.utils.MatrixUtils.tryToCastToDouble;

public class MatrixServiceImp implements MatrixCalculatorService {
  private final MatrixValidator validator;
  private CalculationRequest request;

  public MatrixServiceImp(MatrixValidator validator) {
    this.validator = validator;
  }

  @Override
  public CalculationResult calculateResult(CalculationRequest request) {
    this.request = request;
    if (isPrimaryMatrixValid()) {
      double[][] primaryMatrix = tryToCastToDouble(request.getPrimaryMatrix());
      if (!isSecondaryMatrixValid()) {
        return doMatrixOperations(primaryMatrix, primaryMatrix);
      }
      double[][] secondaryMatrix = tryToCastToDouble(request.getSecondaryMatrix());
      return doMatrixOperations(primaryMatrix, secondaryMatrix);
    }
    throw new InvalidPrimaryMatrixException();
  }

  private boolean isPrimaryMatrixValid() {
    InputMatrix primaryInputMatrix = request.getPrimaryMatrix();
    return validator.isValidMatrix(primaryInputMatrix);
  }

  private boolean isSecondaryMatrixValid() {
    InputMatrix secondaryInputMatrix = request.getSecondaryMatrix();
    return validator.isValidMatrix(secondaryInputMatrix);
  }

  private CalculationResult doMatrixOperations(
    double[][] primaryMatrix, double[][] secondaryMatrix
  ) {
    double scalar = request.getScalar();

    return CalculationResult.builder()
      .matrixSum(matrixAddition(primaryMatrix, secondaryMatrix))
      .matrixDotProduct(calculateDotProduct(primaryMatrix, secondaryMatrix))
      .matrixMultipliedByScalar(multipliedByScalar(scalar, primaryMatrix))
      .matrixInverse(inverse(primaryMatrix))
      .determinant(calculateDeterminant(primaryMatrix))
      .build();
  }
}
