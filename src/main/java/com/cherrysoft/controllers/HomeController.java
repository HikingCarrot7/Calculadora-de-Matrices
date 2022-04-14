package com.cherrysoft.controllers;

import com.cherrysoft.core.CalculationResult;
import com.cherrysoft.core.InputMatrix;
import com.cherrysoft.core.exceptions.InvalidMatrixException;
import com.cherrysoft.persistence.DAO;
import com.cherrysoft.services.MatrixService;
import com.cherrysoft.views.HomeView;
import com.cherrysoft.views.VistaSegundaMatriz;
import com.cherrysoft.views.imp.HomeViewImp;
import com.cherrysoft.views.imp.components.SquaredMatrixGridPanel;

import java.awt.*;

import static com.cherrysoft.core.Matrix2D.*;

public class HomeController implements HomeView.Listener {
  private final int INDEX_MATRIZ_ENTRADA = 0;
  private final int INDEX_SUMA_OTRA_MATRIZ = 1;
  private final int INDEX_MULTI_OTRA_MATRIZ = 2;
  private final int INDEX_MULTI_ESCALAR = 3;
  private final int INDEX_INVERSA_MATRIZ = 4;

  public static final int ORDEN_INICIAL_MATRIZ;

  static {
    DAO dao = new DAO(DAO.RUTA_MATRIZ_PRIMARIA);
    ORDEN_INICIAL_MATRIZ = dao.getOrdenMatriz();
  }

  private final HomeViewImp homeView;
  private final MatrixService matrixService;

  private final DataManager dataManager;

  private final DAO daoMatrizPrimaria;
  private final DAO daoMatrizSecundaria;

  public HomeController(HomeViewImp homeView, MatrixService matrixService) {
    this.homeView = homeView;
    this.matrixService = matrixService;

    this.daoMatrizPrimaria = new DAO(DAO.RUTA_MATRIZ_PRIMARIA);
    this.daoMatrizSecundaria = new DAO(DAO.RUTA_MATRIZ_SECUNDARIA);
    InputMatrix inputMatrix = new InputMatrix(daoMatrizPrimaria.getMatriz());

    this.homeView.setListener(this);
    homeView.setOrderOfMatrix(inputMatrix.orderOfMatrix());
    homeView.setInitialInputMatrixState(inputMatrix);
    rellenarMatrizPrimaria(daoMatrizPrimaria.getMatriz());
    this.dataManager = DataManager.getInstance();
  }

  @Override
  public void onCalculateResult() {
    try {
      InputMatrix inputMatrix = homeView.getInputMatrix();
      CalculationResult result = matrixService.calculateResult(inputMatrix);
      homeView.showCalculationResult(result);
    } catch (InvalidMatrixException e) {
      homeView.showError(e.getMessage());
    }
  }

  @Override
  public void onSetOrderOfMatrix() {
    guardarMatrizPrimaria();
    homeView.matrixPanelsRenderer.updateMatrixGridPanels(homeView.getOrderOfMatrix());
    rellenarMatrizPrimaria(daoMatrizPrimaria.getMatriz());
  }

  @Override
  public void onShowSecondaryMatrix() {
    crearVentanaSegundaMatriz();
  }

  @Override
  public void onClearAll() {
    homeView.clearAll();
  }

  @Override
  public void onSystemClosing() {
    daoMatrizPrimaria.guardarMatriz(dataManager.getEntradasTxt(getVistaMatrizEntrada()));
  }

  private void crearVentanaSegundaMatriz() {
    EventQueue.invokeLater(() -> {
      VistaSegundaMatriz vista = new VistaSegundaMatriz(homeView);
      vista.setLocationRelativeTo(homeView);
      vista.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
      new VistaSegundaMatrizController(vista, getOrdenActualMatriz());
      vista.setVisible(true);
    });
  }

  private void rellenarVistasMatrices() {
    double[][] matrizPrimaria = dataManager.getEntradas(getVistaMatrizEntrada());
    double[][] matrizSecundaria = getMatrizSecundaria();
    dataManager.setEntradas(getVistaMatrizSumaOtraMatriz(), matrixAddition(matrizPrimaria, matrizSecundaria));
    dataManager.setEntradas(getVistaMatrizMultiOtraMatriz(), calculateDotProduct(matrizPrimaria, matrizSecundaria));
    dataManager.setEntradas(getVistaMatrizMultiEscalar(), multipliedByScalar(homeView.getScalar(), matrizPrimaria));
    dataManager.setEntradas(getVistaMatrizInversa(), inverse(matrizPrimaria));
    homeView.getTxtDeterminant().setText(String.format("%,.2f", calculateDeterminant(matrizPrimaria)));
  }

  private void rellenarMatrizPrimaria(String[][] datos) {
    dataManager.setEntradas(getVistaMatrizEntrada(), datos);
  }

  private void guardarMatrizPrimaria() {
    daoMatrizPrimaria.guardarMatriz(dataManager.getEntradasTxt(getVistaMatrizEntrada()));
  }

  private int getOrdenActualMatriz() {
    return dataManager.getOrden(homeView.matrixPanelsRenderer.getMatrixGridPanels()[INDEX_MATRIZ_ENTRADA]);
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

  private SquaredMatrixGridPanel getVistaMatrizSumaOtraMatriz() {
    return homeView.matrixPanelsRenderer.getMatrixGridPanels()[INDEX_SUMA_OTRA_MATRIZ];
  }

  private SquaredMatrixGridPanel getVistaMatrizMultiOtraMatriz() {
    return homeView.matrixPanelsRenderer.getMatrixGridPanels()[INDEX_MULTI_OTRA_MATRIZ];
  }

  private SquaredMatrixGridPanel getVistaMatrizMultiEscalar() {
    return homeView.matrixPanelsRenderer.getMatrixGridPanels()[INDEX_MULTI_ESCALAR];
  }

  private SquaredMatrixGridPanel getVistaMatrizInversa() {
    return homeView.matrixPanelsRenderer.getMatrixGridPanels()[INDEX_INVERSA_MATRIZ];
  }

}
