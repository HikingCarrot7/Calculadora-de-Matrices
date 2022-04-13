package com.cherrysoft.controller;

import com.cherrysoft.model.Matrix2D;
import com.cherrysoft.persistence.DAO;
import com.cherrysoft.view.MatrixPanelsRenderer;
import com.cherrysoft.view.components.SquaredMatrixGridPanel;
import com.cherrysoft.view.VistaPrincipal;
import com.cherrysoft.view.VistaSegundaMatriz;
import com.cherrysoft.view.components.MatrixGridPanelParent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Nicolás
 */
public class VistaPrincipalController {
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

  private final VistaPrincipal vistaPrincipal;
  private final MatrixPanelsRenderer matrixPanelsRenderer;
  private final DataManager dataManager;
  private final Matrix2D matrix2D;

  private final DAO daoMatrizPrimaria;
  private final DAO daoMatrizSecundaria;

  public VistaPrincipalController(final VistaPrincipal vistaPrincipal) {
    this.vistaPrincipal = vistaPrincipal;
    this.matrixPanelsRenderer = new MatrixPanelsRenderer(ORDEN_INICIAL_MATRIZ, getPanelesSoporteMatrices());
    this.daoMatrizPrimaria = new DAO(DAO.RUTA_MATRIZ_PRIMARIA);
    this.daoMatrizSecundaria = new DAO(DAO.RUTA_MATRIZ_SECUNDARIA);
    this.dataManager = DataManager.getInstance();
    this.matrix2D = Matrix2D.getInstance();
    initComponents();
  }

  private void initComponents() {
    vistaPrincipal.getBtnDefinirOrden().addActionListener(this::accionBtnDefinirOrden);
    vistaPrincipal.getBtnCalcular().addActionListener(this::accionBtnCalcular);
    vistaPrincipal.getBtnSetSegundaMatriz().addActionListener(this::accionBtnSegundaMatriz);
    vistaPrincipal.getBtnLimpiar().addActionListener(this::accionBtnLimpiar);
    vistaPrincipal.getSpOrdenMatriz().setValue(ORDEN_INICIAL_MATRIZ);
    vistaPrincipal.getCkbHabilitarSegundaMatriz().addActionListener(e -> {
      vistaPrincipal.getBtnSetSegundaMatriz().setEnabled(isHabilitarSegundaMatriz());
    });
    vistaPrincipal.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        daoMatrizPrimaria.guardarMatriz(dataManager.getEntradasTxt(getVistaMatrizEntrada()));
      }
    });
    rellenarMatrizPrimaria(daoMatrizPrimaria.getMatriz());
  }

  private void accionBtnDefinirOrden(ActionEvent e) {
    guardarMatrizPrimaria();
    matrixPanelsRenderer.updateMatrixGridPanels(getOrdenMatriz());
    rellenarMatrizPrimaria(daoMatrizPrimaria.getMatriz());
  }

  private void accionBtnCalcular(ActionEvent e) {
    if (dataManager.matrizValida(getVistaMatrizEntrada()))
      rellenarVistasMatrices();
  }

  private void accionBtnSegundaMatriz(ActionEvent e) {
    crearVentanaSegundaMatriz();
  }

  private void accionBtnLimpiar(ActionEvent e) {
    limpiarTodosLosCampos();
  }

  private void crearVentanaSegundaMatriz() {
    EventQueue.invokeLater(() -> {
      VistaSegundaMatriz vista = new VistaSegundaMatriz(vistaPrincipal);
      vista.setLocationRelativeTo(vistaPrincipal);
      vista.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
      new VistaSegundaMatrizController(vista, getOrdenActualMatriz());
      vista.setVisible(true);
    });
  }

  private void rellenarVistasMatrices() {
    double[][] matrizPrimaria = dataManager.getEntradas(getVistaMatrizEntrada());
    double[][] matrizSecundaria = getMatrizSecundaria();
    dataManager.setEntradas(getVistaMatrizSumaOtraMatriz(), matrix2D.matrixAddition(matrizPrimaria, matrizSecundaria));
    dataManager.setEntradas(getVistaMatrizMultiOtraMatriz(), matrix2D.calculateDotProduct(matrizPrimaria, matrizSecundaria));
    dataManager.setEntradas(getVistaMatrizMultiEscalar(), matrix2D.multipliedByScalar(getEscalar(), matrizPrimaria));
    dataManager.setEntradas(getVistaMatrizInversa(), matrix2D.inverse(matrizPrimaria));
    vistaPrincipal.getTxtDeterminante().setText(String.format("%,.2f", matrix2D.calculateDeterminant(matrizPrimaria)));
  }

  private void rellenarMatrizPrimaria(String[][] datos) {
    dataManager.setEntradas(getVistaMatrizEntrada(), datos);
  }

  private void guardarMatrizPrimaria() {
    daoMatrizPrimaria.guardarMatriz(dataManager.getEntradasTxt(getVistaMatrizEntrada()));
  }

  private void limpiarTodosLosCampos() {
    dataManager.setEntradas("", getVistaMatrizEntrada());
    dataManager.setEntradas("", getVistaMatrizSumaOtraMatriz());
    dataManager.setEntradas("", getVistaMatrizMultiOtraMatriz());
    dataManager.setEntradas("", getVistaMatrizMultiEscalar());
    dataManager.setEntradas("", getVistaMatrizInversa());
    vistaPrincipal.getTxtDeterminante().setText("");
  }

  private int getOrdenMatriz() {
    return (int) vistaPrincipal.getSpOrdenMatriz().getValue();
  }

  private double getEscalar() {
    return (double) vistaPrincipal.getSpEscalar().getValue();
  }

  private int getOrdenActualMatriz() {
    return dataManager.getOrden(matrixPanelsRenderer.getMatrixPanels()[INDEX_MATRIZ_ENTRADA]);
  }

  private double[][] getMatrizSecundaria() {
    if (!isHabilitarSegundaMatriz())
      return dataManager.getEntradas(getVistaMatrizEntrada());
    if (dataManager.matrizValida(daoMatrizSecundaria.getMatriz()))
      return dataManager.getEntradas(daoMatrizSecundaria.getMatriz());
    return dataManager.getEntradas(getVistaMatrizEntrada());
  }

  private SquaredMatrixGridPanel getVistaMatrizEntrada() {
    return matrixPanelsRenderer.getMatrixPanels()[INDEX_MATRIZ_ENTRADA];
  }

  private SquaredMatrixGridPanel getVistaMatrizSumaOtraMatriz() {
    return matrixPanelsRenderer.getMatrixPanels()[INDEX_SUMA_OTRA_MATRIZ];
  }

  private SquaredMatrixGridPanel getVistaMatrizMultiOtraMatriz() {
    return matrixPanelsRenderer.getMatrixPanels()[INDEX_MULTI_OTRA_MATRIZ];
  }

  private SquaredMatrixGridPanel getVistaMatrizMultiEscalar() {
    return matrixPanelsRenderer.getMatrixPanels()[INDEX_MULTI_ESCALAR];
  }

  private SquaredMatrixGridPanel getVistaMatrizInversa() {
    return matrixPanelsRenderer.getMatrixPanels()[INDEX_INVERSA_MATRIZ];
  }

  private boolean isHabilitarSegundaMatriz() {
    return vistaPrincipal.getCkbHabilitarSegundaMatriz().isSelected();
  }

  private MatrixGridPanelParent[] getPanelesSoporteMatrices() {
    return new MatrixGridPanelParent[]{
        vistaPrincipal.getInputMatrixPanel(),
        vistaPrincipal.getMatrixSumResultPanel(),
        vistaPrincipal.getMatrixMultipliedByMatrixResultPanel(),
        vistaPrincipal.getMatrixMultipliedByScalarResultPanel(),
        vistaPrincipal.getMatrixInverseResultPanel()
    };
  }

}
