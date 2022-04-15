package com.cherrysoft.controllers;

import com.cherrysoft.core.CalculationRequest;
import com.cherrysoft.core.CalculationResult;
import com.cherrysoft.core.InputMatrix;
import com.cherrysoft.core.exceptions.InvalidPrimaryMatrixException;
import com.cherrysoft.persistence.MatrixDAO;
import com.cherrysoft.services.MatrixCalculatorService;
import com.cherrysoft.views.HomeView;
import com.cherrysoft.views.SecondaryMatrixView;
import com.cherrysoft.views.imp.HomeViewImp;

public class AppController implements HomeView.Listener, SecondaryMatrixView.Listener {
  public static final int ORDEN_INICIAL_MATRIZ;

  static {
    MatrixDAO matrixDao = new MatrixDAO(MatrixDAO.RUTA_MATRIZ_PRIMARIA);
    ORDEN_INICIAL_MATRIZ = matrixDao.getOrdenMatriz();
  }

  private final HomeViewImp homeView;
  private final MatrixCalculatorService calculatorService;
  private SecondaryMatrixView secondaryMatrixView;

  private final MatrixDAO daoMatrizPrimaria;
  private final MatrixDAO daoMatrizSecundaria;

  public AppController(HomeViewImp homeView, MatrixCalculatorService calculatorService) {
    this.homeView = homeView;
    this.calculatorService = calculatorService;

    this.daoMatrizPrimaria = new MatrixDAO(MatrixDAO.RUTA_MATRIZ_PRIMARIA);
    this.daoMatrizSecundaria = new MatrixDAO(MatrixDAO.RUTA_MATRIZ_SECUNDARIA);
    InputMatrix inputMatrix = new InputMatrix(daoMatrizPrimaria.getMatrix());

    this.homeView.setListener(this);
    homeView.setOrderOfMatrix(inputMatrix.rawMatrixLength());
    homeView.setInitialInputMatrixState(inputMatrix);
  }

  @Override
  public void onCalculateResult() {
    try {
      CalculationRequest request = createCalculationRequest();
      CalculationResult result = calculatorService.calculateResult(request);
      homeView.showCalculationResult(result);
    } catch (InvalidPrimaryMatrixException e) {
      homeView.showError(e.getMessage());
    }
  }

  private CalculationRequest createCalculationRequest() {
    InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
    InputMatrix secondaryInputMatrix = new InputMatrix(daoMatrizSecundaria.getMatrix());
    return CalculationRequest.builder()
      .primaryMatrix(primaryInputMatrix)
      .secondaryMatrix(homeView.useSecondaryMatrix() ? secondaryInputMatrix : null)
      .scalar(homeView.getScalar())
      .build();
  }

  @Override
  public void onSetOrderOfMatrix() {
    InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
    daoMatrizPrimaria.guardarMatriz(primaryInputMatrix.getRawMatrix());
    homeView.matrixPanelsRenderer.updateMatrixGridPanels(homeView.getOrderOfPrimaryMatrix());
    homeView.setInputMatrixState(new InputMatrix(daoMatrizPrimaria.getMatrix()));
  }

  @Override
  public void onShowSecondaryMatrix() {
    InputMatrix secondaryInputMatrix = new InputMatrix(daoMatrizSecundaria.getMatrix());
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
    daoMatrizPrimaria.guardarMatriz(primaryInputMatrix.getRawMatrix());
  }

  @Override
  public void onClearSecondaryMatrix() {
    secondaryMatrixView.clear();
  }

  @Override
  public void onDoneFillingSecondaryMatrix() {
    InputMatrix inputMatrix = secondaryMatrixView.getSecondaryInputMatrix();
    daoMatrizSecundaria.guardarMatriz(inputMatrix.getRawMatrix());
    secondaryMatrixView.closeWindow();
  }

  @Override
  public void onClosingSecondaryMatrixView() {
    InputMatrix secondaryInputMatrix = secondaryMatrixView.getSecondaryInputMatrix();
    daoMatrizSecundaria.guardarMatriz(secondaryInputMatrix.getRawMatrix());
  }
}
