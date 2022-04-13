package com.sw.controller;

import com.sw.view.components.SquaredMatrixGridPanel;

import javax.swing.*;

/**
 * @author Nicolás
 */
public class DataManager {
  private final String DOUBLE_REGEX = "^-?[0-9]+(.?[0-9]+)*$";
  private static DataManager instance;

  private DataManager() {

  }

  public boolean matrizValida(SquaredMatrixGridPanel vistaMatriz) {
    int longitud = getLongitudMatrizEntrada(vistaMatriz);
    if (longitud < 3)
      return false;
    JTextField[][] entradas = vistaMatriz.getInputFields();
    for (int i = 0; i < longitud; i++)
      for (int j = 0; j < longitud; j++) {
        String entradaTxt = entradas[i][j].getText();
        if (entradaTxt.isEmpty() || !entradaValida(entradaTxt, DOUBLE_REGEX))
          return false;
      }
    return true;
  }

  public boolean matrizValida(String[][] matriz) {
    int longitud = getLongitudMatrizEntrada(matriz);
    if (longitud < 3)
      return false;
    for (int i = 0; i < longitud; i++)
      for (int j = 0; j < longitud; j++) {
        String entradaTxt = matriz[i][j];
        if (entradaTxt.isEmpty() || !entradaValida(entradaTxt, DOUBLE_REGEX))
          return false;
      }
    return true;
  }

  public int getLongitudMatrizEntrada(SquaredMatrixGridPanel vistaMatriz) {
    return getLongitudMatrizEntrada(getEntradasTxt(vistaMatriz));
  }

  public int getLongitudMatrizEntrada(String[][] matriz) {
    int longitud = 0, j = 0;
    while (j < getOrden(matriz) && !matriz[0][j++].isEmpty())
      ++longitud;
    return longitud;
  }

  public double[][] getEntradas(SquaredMatrixGridPanel vistaMatriz) {
    JTextField[][] entradas = vistaMatriz.getInputFields();
    int longitud = getLongitudMatrizEntrada(vistaMatriz);
    double[][] matriz = new double[longitud][longitud];
    for (int i = 0; i < matriz.length; i++)
      for (int j = 0; j < matriz[i].length; j++)
        matriz[i][j] = Double.parseDouble(entradas[i][j].getText());
    return matriz;
  }

  public String[][] getEntradasTxt(SquaredMatrixGridPanel vistaMatriz) {
    JTextField[][] entradas = vistaMatriz.getInputFields();
    String[][] matriz = new String[entradas.length][entradas.length];
    for (int i = 0; i < matriz.length; i++)
      for (int j = 0; j < matriz[i].length; j++)
        matriz[i][j] = entradas[i][j].getText();
    return matriz;
  }

  public double[][] getEntradas(String[][] matrizTxt) {
    int longitud = getLongitudMatrizEntrada(matrizTxt);
    double[][] matriz = new double[longitud][longitud];
    for (int i = 0; i < matriz.length; i++)
      for (int j = 0; j < matriz[i].length; j++)
        matriz[i][j] = Double.parseDouble(matrizTxt[i][j]);
    return matriz;
  }

  public void setEntradas(SquaredMatrixGridPanel vistaMatriz, double[][] matriz) {
    JTextField[][] entradas = vistaMatriz.getInputFields();
    for (int i = 0; i < matriz.length; i++)
      for (int j = 0; j < matriz[i].length; j++)
        entradas[i][j].setText(String.format("%,.2f", matriz[i][j]));
  }

  public void setEntradas(SquaredMatrixGridPanel vistaMatriz, String[][] matriz) {
    JTextField[][] entradas = vistaMatriz.getInputFields();
    int ordenMatrizMenor = getOrdenMatrizMenor(matriz, getEntradasTxt(vistaMatriz));
    for (int i = 0; i < ordenMatrizMenor; i++)
      for (int j = 0; j < ordenMatrizMenor; j++)
        entradas[i][j].setText(matriz[i][j]);
  }

  public void setEntradas(String text, SquaredMatrixGridPanel... vistaMatrices) {
    for (SquaredMatrixGridPanel vistaMatriz : vistaMatrices)
      setEntradas(text, vistaMatriz.getInputFields());
  }

  public void setEntradas(String text, JTextField[][] entradas) {
    for (JTextField[] row : entradas)
      for (JTextField jTextField : row)
        jTextField.setText(text);
  }

  public int getOrden(SquaredMatrixGridPanel squaredMatrixGridPanel) {
    return squaredMatrixGridPanel.getInputFields().length;
  }

  public int getOrden(String[][] matriz) {
    return matriz.length;
  }

  public int getOrdenMatrizMenor(String[][] matriz1, String[][] matriz2) {
    int ordenMatrizMenor = matriz1.length;
    if (ordenMatrizMenor > matriz2.length)
      ordenMatrizMenor = matriz2.length;
    return ordenMatrizMenor;
  }

  private boolean entradaValida(String text, String regex) {
    return text.matches(regex);
  }

  public synchronized static DataManager getInstance() {
    if (instance == null)
      instance = new DataManager();
    return instance;
  }
}
