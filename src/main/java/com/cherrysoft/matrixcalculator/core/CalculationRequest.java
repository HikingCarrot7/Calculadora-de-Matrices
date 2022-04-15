package com.cherrysoft.matrixcalculator.core;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class CalculationRequest {
  private InputMatrix primaryMatrix;
  private InputMatrix secondaryMatrix;
  private double scalar;

  public boolean providedSecondaryMatrix() {
    return Objects.nonNull(secondaryMatrix);
  }

  public InputMatrix getSecondaryMatrix() {
    if (Objects.isNull(secondaryMatrix)) {
      return primaryMatrix;
    }
    return secondaryMatrix;
  }
}
