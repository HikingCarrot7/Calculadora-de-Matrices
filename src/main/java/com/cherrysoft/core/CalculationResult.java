package com.cherrysoft.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationResult {
  private double[][] matrixSumResult;
  private double[][] matrixDotProductResult;
  private double[][] matrixMultipliedByScalarResult;
  private double[][] matrixInverseResult;
  private double determinant;
}
