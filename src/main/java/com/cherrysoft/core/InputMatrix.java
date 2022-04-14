package com.cherrysoft.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputMatrix {
  private String[][] rawMatrix;

  public int rawMatrixLength() {
    return rawMatrix.length;
  }
}
