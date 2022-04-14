package com.cherrysoft.services.imp;

import com.cherrysoft.core.CalculationResult;
import com.cherrysoft.core.InputMatrix;
import com.cherrysoft.core.MatrixValidator;
import com.cherrysoft.core.exceptions.InvalidMatrixException;
import com.cherrysoft.services.MatrixService;

public class MatrixServiceImp implements MatrixService {
  private final MatrixValidator validator;

  public MatrixServiceImp(MatrixValidator validator) {
    this.validator = validator;
  }

  @Override
  public CalculationResult calculateResult(InputMatrix primaryMatrix) {
    if (validator.isValidMatrix(primaryMatrix)) {

    }
    throw new InvalidMatrixException();
  }

  @Override
  public CalculationResult calculateResult(InputMatrix primaryMatrix,
                                           InputMatrix secondaryMatrix
  ) {
    return null;
  }
}
