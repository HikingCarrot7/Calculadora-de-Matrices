package com.cherrysoft.services;

import com.cherrysoft.core.CalculationResult;
import com.cherrysoft.core.InputMatrix;

public interface MatrixService {

  CalculationResult calculateResult(InputMatrix primaryMatrix);

  CalculationResult calculateResult(InputMatrix primaryMatrix, InputMatrix secondaryMatrix);

}
