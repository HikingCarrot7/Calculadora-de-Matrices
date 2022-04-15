package com.cherrysoft.matrixcalculator.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculationResult {
  private double[][] matrixSum;
  private double[][] matrixDotProduct;
  private double[][] matrixMultipliedByScalar;
  private double[][] matrixInverse;
  private double determinant;
}
