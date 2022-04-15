package com.cherrysoft.matrixcalculator.controllers;

import com.cherrysoft.matrixcalculator.core.CalculationRequest;
import com.cherrysoft.matrixcalculator.core.CalculationResult;
import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.core.exceptions.InvalidPrimaryMatrixException;
import com.cherrysoft.matrixcalculator.services.MatrixServiceFacade;
import com.cherrysoft.matrixcalculator.views.HomeView;
import com.cherrysoft.matrixcalculator.views.SecondaryMatrixView;

public class AppController implements HomeView.Listener, SecondaryMatrixView.Listener {
  private final HomeView homeView;
  private SecondaryMatrixView secondaryMatrixView;
  private final MatrixServiceFacade facade;

  public AppController(HomeView homeView, MatrixServiceFacade facade) {
    this.homeView = homeView;
    this.facade = facade;
    initHomeView();
  }

  private void initHomeView() {
    InputMatrix primaryInputMatrix = facade.getPrimaryMatrix();
    homeView.setListener(this);
    homeView.setOrderOfMatrix(primaryInputMatrix.rawMatrixLength());
    homeView.setInitialInputMatrixState(primaryInputMatrix);
  }

  @Override
  public void onCalculateResult() {
    try {
      CalculationRequest request = createCalculationRequest();
      CalculationResult result = facade.calculateResult(request);
      homeView.showCalculationResult(result);
    } catch (InvalidPrimaryMatrixException e) {
      homeView.showError(e.getMessage());
    }
  }

  private CalculationRequest createCalculationRequest() {
    InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
    InputMatrix secondaryInputMatrix = facade.getSecondaryMatrix();
    return CalculationRequest.builder()
      .primaryMatrix(primaryInputMatrix)
      .secondaryMatrix(homeView.useSecondaryMatrix() ? secondaryInputMatrix : null)
      .scalar(homeView.getScalar())
      .build();
  }

  @Override
  public void onSetOrderOfMatrix() {
    InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
    facade.saveAsPrimaryMatrix(primaryInputMatrix);
    homeView.updatePanelsToMatchOrderOfMatrix();
    homeView.setPrimaryInputMatrixState(primaryInputMatrix);
  }

  @Override
  public void onShowSecondaryMatrix() {
    InputMatrix secondaryInputMatrix = facade.getSecondaryMatrix();
    int orderOfPrimaryMatrix = homeView.getOrderOfPrimaryMatrix();
    secondaryMatrixView = homeView.createSecondaryMatrixView();
    secondaryMatrixView.setListener(this);
    secondaryMatrixView.setSecondaryMatrixInitialState(orderOfPrimaryMatrix, secondaryInputMatrix);
    secondaryMatrixView.setVisible(true);
  }

  @Override
  public void onClearAll() {
    homeView.clearAll();
  }

  @Override
  public void onSystemClosing() {
    InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
    facade.saveAsPrimaryMatrix(primaryInputMatrix);
  }

  @Override
  public void onClearSecondaryMatrix() {
    secondaryMatrixView.clear();
  }

  @Override
  public void onDoneFillingSecondaryMatrix() {
    InputMatrix secondaryInputMatrix = secondaryMatrixView.getSecondaryInputMatrix();
    facade.saveAsSecondaryMatrix(secondaryInputMatrix);
    secondaryMatrixView.closeWindow();
  }

  @Override
  public void onClosingSecondaryMatrixView() {
    InputMatrix secondaryInputMatrix = secondaryMatrixView.getSecondaryInputMatrix();
    facade.saveAsSecondaryMatrix(secondaryInputMatrix);
  }
}
