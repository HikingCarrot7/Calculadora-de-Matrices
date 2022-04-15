package com.cherrysoft.matrixcalculator.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.cherrysoft.matrixcalculator.core.utils.MatrixUtils.calculateActualSideLength;

@Data
@AllArgsConstructor
public class InputMatrix {
  private String[][] rawMatrix;

  public int rawMatrixLength() {
    return rawMatrix.length;
  }

  public boolean sideLengthGreaterThan(InputMatrix other) {
    int thisSideLength = calculateActualSideLength(rawMatrix);
    int otherSideLength = calculateActualSideLength(other.rawMatrix);
    return thisSideLength > otherSideLength;
  }

}
