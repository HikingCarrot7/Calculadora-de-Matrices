package com.sw.controller;

import com.sw.model.Matriz;
import com.sw.view.MatrixDesign;
import com.sw.view.MatrixLayout;

/**
 *
 * @author Mohammed
 */
public class DataManager
{

    private MatrixDesign matriz;

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

    public void rellenarTodosLosCampos(MatrixDesign[] matrices)
    {
        Matriz calculosMatriz = new Matriz();
        double[][] matrizEntrada = getMatrizCampo(matrices[0]);

        actualizarCampo(matrices[1], calculosMatriz.sumaOtraMatriz(matrizEntrada, matrizEntrada));
        actualizarCampo(matrices[2], calculosMatriz.productoMatrices(matrizEntrada, matrizEntrada));
        actualizarCampo(matrices[3], calculosMatriz.multiplicarPorEscalar(2, matrizEntrada));
        actualizarCampo(matrices[4], calculosMatriz.inversa(matrizEntrada));

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

    public int getLogitudCampo(MatrixDesign matriz)
    {

        int longitud = 0, j = 0;

        while (j < matriz.getLadoMatriz() && !matriz.getEntradasMatriz()[0][j++].getText().equals(""))
            ++longitud;

        return longitud;

    }

    public void guardarMatrizEntrada(MatrixLayout distribucion)
    {
        matriz = new MatrixDesign(9);

        for (int i = 0; i < distribucion.getMatrices()[0].getLadoMatriz(); i++)
            for (int j = 0; j < distribucion.getMatrices()[0].getLadoMatriz(); j++)
                matriz.getEntradasMatriz()[i][j].setText(distribucion.getMatrices()[0].getEntradasMatriz()[i][j].getText());

    }

    public void reestablecerCampos(MatrixLayout distribucion)
    {
        for (int i = 0; i < distribucion.getMatrices()[0].getLadoMatriz(); i++)
            for (int j = 0; j < distribucion.getMatrices()[0].getLadoMatriz(); j++)
                distribucion.getMatrices()[0].getEntradasMatriz()[i][j].setText(matriz.getEntradasMatriz()[i][j].getText());

    }

    public boolean entradaValida(String text, String regex)
    {
        return text.matches(regex);
    }

}
