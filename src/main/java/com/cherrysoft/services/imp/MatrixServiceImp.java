package com.cherrysoft.services.imp;

import com.cherrysoft.core.*;
import com.cherrysoft.core.exceptions.InvalidMatrixException;
import com.cherrysoft.services.MatrixService;

import static com.cherrysoft.core.Matrix2D.*;
import static com.cherrysoft.core.utils.MatrixUtils.tryToCastToDouble;

public class MatrixServiceImp implements MatrixService {
  private final MatrixValidator validator;

  public MatrixServiceImp(MatrixValidator validator) {
    this.validator = validator;
  }

  @Override
  public CalculationResult calculateResult(CalculationRequest request) {
    InputMatrix primaryMatrix = request.getPrimaryMatrix();
    if (validator.isValidMatrix(primaryMatrix)) {
      return doMatrixOperations(request);
    }
    throw new InvalidMatrixException();
  }

  private CalculationResult doMatrixOperations(CalculationRequest request) {
    double[][] primaryMatrix = tryToCastToDouble(request.getPrimaryMatrix());
    double[][] secondaryMatrix = tryToCastToDouble(request.getSecondaryMatrix());
    double scalar = request.getScalar();

    return CalculationResult.builder()
      .matrixSum(matrixAddition(primaryMatrix, primaryMatrix))
      .matrixDotProduct(calculateDotProduct(primaryMatrix, secondaryMatrix))
      .matrixMultipliedByScalar(multipliedByScalar(scalar, primaryMatrix))
      .matrixInverse(inverse(primaryMatrix))
      .determinant(calculateDeterminant(primaryMatrix))
      .build();
  }
}
