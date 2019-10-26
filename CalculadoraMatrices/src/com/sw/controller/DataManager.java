package com.sw.controller;

import com.sw.view.Matriz;

/**
 *
 * @author Mohammed
 */
public class DataManager
{

    public void actualizarCampo(Matriz matriz, double[][] matrizRellenar)
    {
        for (int i = 0; i < matrizRellenar.length; i++)
            for (int j = 0; j < matrizRellenar[i].length; j++)
                matriz.getEntradasMatriz()[i][j].setText(String.format("%.2f", matrizRellenar[i][j]));

    }

    public double[][] getMatrizCampo(Matriz matriz)
    {
        double[][] matrizARetornar = new double[getLogitudCampo(matriz)][getLogitudCampo(matriz)];

        System.out.println(this.getLogitudCampo(matriz));

        for (int i = 0; i < matrizARetornar.length; i++)
            for (int j = 0; j < matrizARetornar.length; j++)
                matrizARetornar[i][j] = Double.parseDouble(matriz.getEntradasMatriz()[i][j].getText());

        return matrizARetornar;

    }

    public int getLogitudCampo(Matriz matriz)
    {

        int longitud = 0, j = 0;

        while (j < matriz.getLadoMatriz() && !matriz.getEntradasMatriz()[0][j++].getText().equals(""))
            ++longitud;

        return longitud;

    }

}
