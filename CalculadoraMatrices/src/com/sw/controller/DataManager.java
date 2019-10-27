package com.sw.controller;

import com.sw.model.Matriz;
import com.sw.view.MatrixDesign;
import com.sw.view.MatrixLayout;
import com.sw.view.SecondMatrix;

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
        double[][] matrizARetornar = new double[getLongitudCampo(matriz)][getLongitudCampo(matriz)];

        for (int i = 0; i < matrizARetornar.length; i++)
            for (int j = 0; j < matrizARetornar.length; j++)
                matrizARetornar[i][j] = Double.parseDouble(matriz.getEntradasMatriz()[i][j].getText());

        return matrizARetornar;

    }

    public void copiarMatrices(MatrixDesign matriz1, MatrixDesign matriz2)
    {
        int longitudMenor = matriz1.getLadoMatriz();

        if (longitudMenor > matriz2.getLadoMatriz())
            longitudMenor = matriz2.getLadoMatriz();

        for (int i = 0; i < longitudMenor; i++)
            for (int j = 0; j < longitudMenor; j++)
                matriz1.getEntradasMatriz()[i][j].setText(matriz2.getEntradasMatriz()[i][j].getText());

    }

    public void rellenarTodosLosCampos(MatrixDesign[] matrices, boolean segundaMatriz)
    {
        Matriz calculosMatriz = new Matriz();
        double[][] matrizEntrada = getMatrizCampo(matrices[0]);

        actualizarCampo(matrices[1], calculosMatriz.sumaOtraMatriz(matrizEntrada, segundaMatriz ? getMatrizCampo(SecondMatrix.getMatrizGuardada()) : matrizEntrada));
        actualizarCampo(matrices[2], calculosMatriz.productoMatrices(matrizEntrada, segundaMatriz ? getMatrizCampo(SecondMatrix.getMatrizGuardada()) : matrizEntrada));
        actualizarCampo(matrices[3], calculosMatriz.multiplicarPorEscalar(2, matrizEntrada));
        actualizarCampo(matrices[4], calculosMatriz.inversa(matrizEntrada));

    }

    public boolean matrizRellenadaCorrectamente(MatrixDesign matriz)
    {
        int longitud = getLongitudCampo(matriz);

        if (longitud < 3)
            return false;

        for (int i = 0; i < longitud; i++)
            for (int j = 0; j < longitud; j++)
                if (matriz.getEntradasMatriz()[i][j].getText().equals(""))
                    return false;

        return true;

    }

    public int getLongitudCampo(MatrixDesign matriz)
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
