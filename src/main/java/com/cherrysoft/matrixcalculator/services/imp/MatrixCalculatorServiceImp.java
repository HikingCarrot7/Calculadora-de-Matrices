package com.cherrysoft.matrixcalculator.services.imp;

import com.cherrysoft.matrixcalculator.core.CalculationRequest;
import com.cherrysoft.matrixcalculator.core.CalculationResult;
import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.core.MatrixValidator;
import com.cherrysoft.matrixcalculator.core.exceptions.InvalidPrimaryMatrixException;
import com.cherrysoft.matrixcalculator.services.MatrixCalculatorService;

import static com.cherrysoft.matrixcalculator.core.Matrix2D.*;
import static com.cherrysoft.matrixcalculator.core.utils.MatrixUtils.subSquaredMatrixFromTopLeft;
import static com.cherrysoft.matrixcalculator.core.utils.MatrixUtils.tryToTrimAndCastToDouble;

public class MatrixCalculatorServiceImp implements MatrixCalculatorService {
  private final MatrixValidator validator;
  private CalculationRequest request;

  public MatrixCalculatorServiceImp(MatrixValidator validator) {
    this.validator = validator;
  }

  @Override
  public CalculationResult calculateResult(CalculationRequest request) {
    this.request = request;
    if (isPrimaryMatrixValid()) {
      InputMatrix primaryInputMatrix = request.getPrimaryMatrix();
      double[][] primaryMatrix = tryToTrimAndCastToDouble(primaryInputMatrix);
      if (!isSecondaryMatrixValid()) {
        return doMatrixOperations(primaryMatrix, primaryMatrix);
      }
      double[][] secondaryMatrix = prepareSecondaryMatrix(primaryMatrix.length);
      return doMatrixOperations(primaryMatrix, secondaryMatrix);
    }
    throw new InvalidPrimaryMatrixException();
  }

  private double[][] prepareSecondaryMatrix(int primaryMatrixLength) {
    InputMatrix primaryInputMatrix = request.getPrimaryMatrix();
    InputMatrix secondaryInputMatrix = request.getSecondaryMatrix();
    double[][] secondaryMatrix = tryToTrimAndCastToDouble(secondaryInputMatrix);
    if (secondaryInputMatrix.sideLengthGreaterThan(primaryInputMatrix)) {
      secondaryMatrix = subSquaredMatrixFromTopLeft(secondaryMatrix, primaryMatrixLength);
    }
    return secondaryMatrix;
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
