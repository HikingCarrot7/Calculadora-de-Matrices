package com.cherrysoft.services;

import com.cherrysoft.core.CalculationRequest;
import com.cherrysoft.core.CalculationResult;

public interface MatrixService {

  CalculationResult calculateResult(CalculationRequest request);

}
