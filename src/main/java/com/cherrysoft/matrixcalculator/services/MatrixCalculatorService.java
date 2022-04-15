package com.cherrysoft.matrixcalculator.services;

import com.cherrysoft.matrixcalculator.core.CalculationRequest;
import com.cherrysoft.matrixcalculator.core.CalculationResult;

public interface MatrixCalculatorService {

  CalculationResult calculateResult(CalculationRequest request);

}
