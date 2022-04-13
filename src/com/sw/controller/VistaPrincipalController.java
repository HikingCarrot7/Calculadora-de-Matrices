package com.sw.controller;

import com.sw.model.MatrixLogic;
import com.sw.persistence.DAO;
import com.sw.view.DibujadorMatrices;
import com.sw.view.VistaMatriz;
import com.sw.view.VistaPrincipal;
import com.sw.view.VistaSegundaMatriz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

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
  private final DibujadorMatrices dibujadorMatrices;
  private final DataManager dataManager;
  private final MatrixLogic matrixLogic;

  private final DAO daoMatrizPrimaria;
  private final DAO daoMatrizSecundaria;

  public VistaPrincipalController(final VistaPrincipal vistaPrincipal) {
    this.vistaPrincipal = vistaPrincipal;
    this.dibujadorMatrices = new DibujadorMatrices(ORDEN_INICIAL_MATRIZ, getPanelesSoporteMatrices());
    this.daoMatrizPrimaria = new DAO(DAO.RUTA_MATRIZ_PRIMARIA);
    this.daoMatrizSecundaria = new DAO(DAO.RUTA_MATRIZ_SECUNDARIA);
    this.dataManager = DataManager.getInstance();
    this.matrixLogic = MatrixLogic.getInstance();
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
    dibujadorMatrices.dibujarMatrices(getOrdenMatriz());
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
    dataManager.setEntradas(getVistaMatrizSumaOtraMatriz(), matrixLogic.sumarMatrices(matrizPrimaria, matrizSecundaria));
    dataManager.setEntradas(getVistaMatrizMultiOtraMatriz(), matrixLogic.productoMatrices(matrizPrimaria, matrizSecundaria));
    dataManager.setEntradas(getVistaMatrizMultiEscalar(), matrixLogic.multiplicarPorEscalar(getEscalar(), matrizPrimaria));
    dataManager.setEntradas(getVistaMatrizInversa(), matrixLogic.getInversa(matrizPrimaria));
    vistaPrincipal.getTxtDeterminante().setText(String.format("%,.2f", matrixLogic.getDeterminante(matrizPrimaria)));
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
    return dataManager.getOrden(dibujadorMatrices.getVistaMatrices()[INDEX_MATRIZ_ENTRADA]);
  }

  private double[][] getMatrizSecundaria() {
    if (!isHabilitarSegundaMatriz())
      return dataManager.getEntradas(getVistaMatrizEntrada());
    if (dataManager.matrizValida(daoMatrizSecundaria.getMatriz()))
      return dataManager.getEntradas(daoMatrizSecundaria.getMatriz());
    return dataManager.getEntradas(getVistaMatrizEntrada());
  }

  private VistaMatriz getVistaMatrizEntrada() {
    return dibujadorMatrices.getVistaMatrices()[INDEX_MATRIZ_ENTRADA];
  }

  private VistaMatriz getVistaMatrizSumaOtraMatriz() {
    return dibujadorMatrices.getVistaMatrices()[INDEX_SUMA_OTRA_MATRIZ];
  }

  private VistaMatriz getVistaMatrizMultiOtraMatriz() {
    return dibujadorMatrices.getVistaMatrices()[INDEX_MULTI_OTRA_MATRIZ];
  }

  private VistaMatriz getVistaMatrizMultiEscalar() {
    return dibujadorMatrices.getVistaMatrices()[INDEX_MULTI_ESCALAR];
  }

  private VistaMatriz getVistaMatrizInversa() {
    return dibujadorMatrices.getVistaMatrices()[INDEX_INVERSA_MATRIZ];
  }

  private boolean isHabilitarSegundaMatriz() {
    return vistaPrincipal.getCkbHabilitarSegundaMatriz().isSelected();
  }

  private JPanel[] getPanelesSoporteMatrices() {
    return new JPanel[]{
        vistaPrincipal.getPanelMatriz(),
        vistaPrincipal.getPanelSumaOtraMatriz(),
        vistaPrincipal.getPanelMultiOtraMatriz(),
        vistaPrincipal.getPanelMultiEscalar(),
        vistaPrincipal.getPanelInversaMatriz()
    };
  }

}
