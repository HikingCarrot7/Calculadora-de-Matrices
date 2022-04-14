package com.cherrysoft.controllers;

import com.cherrysoft.core.CalculationRequest;
import com.cherrysoft.core.CalculationResult;
import com.cherrysoft.core.InputMatrix;
import com.cherrysoft.core.exceptions.InvalidMatrixException;
import com.cherrysoft.persistence.DAO;
import com.cherrysoft.services.MatrixService;
import com.cherrysoft.views.HomeView;
import com.cherrysoft.views.SecondaryMatrixView;
import com.cherrysoft.views.imp.HomeViewImp;
import com.cherrysoft.views.imp.components.SquaredMatrixGridPanel;

public class AppController implements HomeView.Listener, SecondaryMatrixView.Listener {
  private final int INDEX_MATRIZ_ENTRADA = 0;

  public static final int ORDEN_INICIAL_MATRIZ;

  static {
    DAO dao = new DAO(DAO.RUTA_MATRIZ_PRIMARIA);
    ORDEN_INICIAL_MATRIZ = dao.getOrdenMatriz();
  }

  private final HomeViewImp homeView;
  private final MatrixService matrixService;
  private SecondaryMatrixView secondaryMatrixView;

  private final DataManager dataManager;

  private final DAO daoMatrizPrimaria;
  private final DAO daoMatrizSecundaria;

  public AppController(HomeViewImp homeView, MatrixService matrixService) {
    this.homeView = homeView;
    this.matrixService = matrixService;

    this.daoMatrizPrimaria = new DAO(DAO.RUTA_MATRIZ_PRIMARIA);
    this.daoMatrizSecundaria = new DAO(DAO.RUTA_MATRIZ_SECUNDARIA);
    InputMatrix inputMatrix = new InputMatrix(daoMatrizPrimaria.getMatriz());

    this.homeView.setListener(this);
    homeView.setOrderOfMatrix(inputMatrix.rawMatrixLength());
    homeView.setInitialInputMatrixState(inputMatrix);
    this.dataManager = DataManager.getInstance();
  }

  @Override
  public void onCalculateResult() {
    try {
      InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
      double scalar = homeView.getScalar();
      CalculationRequest request = new CalculationRequest(primaryInputMatrix, scalar);
      CalculationResult result = matrixService.calculateResult(request);
      homeView.showCalculationResult(result);
    } catch (InvalidMatrixException e) {
      homeView.showError(e.getMessage());
    }
  }

  @Override
  public void onSetOrderOfMatrix() {
    InputMatrix primaryInputMatrix = homeView.getPrimaryInputMatrix();
    daoMatrizPrimaria.guardarMatriz(primaryInputMatrix.getRawMatrix());
    homeView.matrixPanelsRenderer.updateMatrixGridPanels(homeView.getOrderOfPrimaryMatrix());
    homeView.setInputMatrixState(new InputMatrix(daoMatrizPrimaria.getMatriz()));
  }

  @Override
  public void onShowSecondaryMatrix() {
    InputMatrix secondaryInputMatrix = new InputMatrix(daoMatrizSecundaria.getMatriz());
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
    InputMatrix inputMatrix = secondaryMatrixView.getSecondaryInputMatrix();
    daoMatrizSecundaria.guardarMatriz(inputMatrix.getRawMatrix());
  }

  private double[][] getMatrizSecundaria() {
    if (!homeView.useSecondaryMatrix())
      return dataManager.getEntradas(getVistaMatrizEntrada());
    if (dataManager.matrizValida(daoMatrizSecundaria.getMatriz()))
      return dataManager.getEntradas(daoMatrizSecundaria.getMatriz());
    return dataManager.getEntradas(getVistaMatrizEntrada());
  }

  private SquaredMatrixGridPanel getVistaMatrizEntrada() {
    return homeView.matrixPanelsRenderer.getMatrixGridPanels()[INDEX_MATRIZ_ENTRADA];
  }
}
