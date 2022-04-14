package com.cherrysoft.views;

import com.cherrysoft.core.InputMatrix;

public interface SecondaryMatrixView {

  InputMatrix getSecondaryInputMatrix();

  void setSecondaryMatrixInitialState(int orderOfPrimaryMatrix, InputMatrix matrix);

  void setVisible(boolean visible);

  void setListener(SecondaryMatrixView.Listener listener);

  void clear();

  void closeWindow();

  interface Listener {

    void onClearSecondaryMatrix();

    void onDoneFillingSecondaryMatrix();

    void onClosingSecondaryMatrixView();

  }

}
