package com.sw.controller;

import com.sw.model.Matriz;
import com.sw.view.MatrixDesign;

/**
 *
 * @author Mohammed
 */
public class DataManager
{

    public void actualizarCampo(MatrixDesign matriz, double[][] matrizRellenar)
    {
        for (int i = 0; i < matrizRellenar.length; i++)
            for (int j = 0; j < matrizRellenar[i].length; j++)
                matriz.getEntradasMatriz()[i][j].setText(String.format("%.2f", matrizRellenar[i][j]));

    }

    public double[][] getMatrizCampo(MatrixDesign matriz)
    {
        double[][] matrizARetornar = new double[getLogitudCampo(matriz)][getLogitudCampo(matriz)];

        for (int i = 0; i < matrizARetornar.length; i++)
            for (int j = 0; j < matrizARetornar.length; j++)
                matrizARetornar[i][j] = Double.parseDouble(matriz.getEntradasMatriz()[i][j].getText());

        return matrizARetornar;

    }

    public boolean matrizRellenadaCorrectamente(MatrixDesign matriz)
    {
        int longitud = getLogitudCampo(matriz);

        if (longitud < 3)
            return false;

        for (int i = 0; i < longitud; i++)
            for (int j = 0; j < longitud; j++)
                if (matriz.getEntradasMatriz()[i][j].getText().equals(""))
                    return false;

        return true;

    }

    public void rellenarTodosLosCampos(MatrixDesign[] matrices)
    {
        Matriz matriz = new Matriz();

    }

    public int getLogitudCampo(MatrixDesign matriz)
    {

        int longitud = 0, j = 0;

        while (j < matriz.getLadoMatriz() && !matriz.getEntradasMatriz()[0][j++].getText().equals(""))
            ++longitud;

        return longitud;

    }

}
