package com.cherrysoft.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class CalculationRequest {
  private InputMatrix primaryMatrix;
  private InputMatrix secondaryMatrix;
  private double scalar;

  public CalculationRequest(InputMatrix primaryMatrix, double scalar) {
    this(primaryMatrix, null, scalar);
  }

  public InputMatrix getSecondaryMatrix() {
    if (Objects.isNull(secondaryMatrix)) {
      return primaryMatrix;
    }
    return secondaryMatrix;
  }

}
