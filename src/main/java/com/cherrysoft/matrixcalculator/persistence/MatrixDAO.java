package com.cherrysoft.matrixcalculator.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MatrixDAO {
  public static final String RUTA_MATRIZ_PRIMARIA = "res//Matriz1.txt";
  public static final String RUTA_MATRIZ_SECUNDARIA = "res//Matriz2.txt";

  private static final String SALTO_LINEA = System.getProperty("line.separator");
  private static final String ESPACIO = " ";
  private static final String SEPARADOR = ",";

  private File file;

  public MatrixDAO(String ruta) {
    file = new File(ruta);
    if (!file.exists())
      try {
        file.createNewFile();
      } catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
  }

  public void guardarMatriz(String[][] matriz) {
    try (Formatter out = new Formatter(file)) {
      for (String[] row : matriz) {
        for (String text : row)
          out.format("%s,", text.isEmpty() ? ESPACIO : text);
        out.format("%s", SALTO_LINEA);
      }
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public String[][] getMatrix() {
    int ordenMatrizGuardada = getOrdenMatriz();
    String[][] matriz = new String[ordenMatrizGuardada][];
    int index = 0;
    try {
      Scanner in = new Scanner(file);
      while (in.hasNextLine()) {
        String[] row = in.nextLine().split(SEPARADOR);
        for (int i = 0; i < row.length; i++)
          if (row[i].equals(ESPACIO))
            row[i] = "";
        matriz[index++] = row;
      }
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return matriz;
  }

  public int getOrdenMatriz() {
    try {
      return new Scanner(file).nextLine().split(",").length;
    } catch (NoSuchElementException | FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
    return 3;
  }

}
