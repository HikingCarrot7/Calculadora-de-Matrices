package com.sw.controller;

import com.sw.model.Matriz;
import com.sw.view.InterfazPrincipal;
import com.sw.view.InterfazSegundaMatriz;
import com.sw.view.MatrixDesign;
import com.sw.view.MatrixLayout;

/**
 *
 *
 */
public class DataManager
{

    private MatrixDesign matriz;
    private Matriz calculosMatriz;

    public DataManager()
    {
        matriz = new MatrixDesign(9);
        calculosMatriz = new Matriz();

    }

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

    public void rellenarTodosLosCampos(MatrixDesign[] matrices, double escalar, boolean segundaMatrizValida)
    {

        double[][] matrizEntrada = getMatrizCampo(matrices[0]);

        actualizarCampo(matrices[1], calculosMatriz.sumarMatrices(matrizEntrada, segundaMatrizValida ? getMatrizCampo(InterfazSegundaMatriz.getMatriz()) : matrizEntrada));
        actualizarCampo(matrices[2], calculosMatriz.productoMatrices(matrizEntrada, segundaMatrizValida ? getMatrizCampo(InterfazSegundaMatriz.getMatriz()) : matrizEntrada));
        actualizarCampo(matrices[4], calculosMatriz.getInversa(matrizEntrada));
        actualizarCampo(matrices[3], calculosMatriz.multiplicarPorEscalar(escalar, matrizEntrada));

    }

    public boolean matrizRellenadaCorrectamente(MatrixDesign matriz)
    {
        int longitud = getLongitudCampo(matriz);

        if (longitud < 3)
            return false;

        for (int i = 0; i < longitud; i++)
            for (int j = 0; j < longitud; j++)
                if (matriz.getEntradasMatriz()[i][j].getText().equals("")
                        || !entradaValida(matriz.getEntradasMatriz()[i][j].getText(), getEntradaDoubleValido()))
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

    public void limpiarCampos(MatrixDesign[] matrices)
    {

        for (MatrixDesign matrix : matrices)
            for (int i = 0; i < matrices[0].getLadoMatriz(); i++)
                for (int j = 0; j < matrices[0].getLadoMatriz(); j++)
                    matrix.getEntradasMatriz()[i][j].setText("");

    }

    public void limpiarUnCampo(MatrixDesign matriz)
    {

        for (int i = 0; i < matriz.getLadoMatriz(); i++)
            for (int j = 0; j < matriz.getLadoMatriz(); j++)
                matriz.getEntradasMatriz()[i][j].setText("");

    }

    public void recortarMatriz(MatrixDesign matriz, int columna)
    {

        for (int i = 0; i < matriz.getLadoMatriz(); i++)
            for (int j = i >= columna ? 0 : columna; j < matriz.getLadoMatriz(); j++)
                matriz.getEntradasMatriz()[i][j].setText("");

    }

    public boolean segundaMatrizValida()
    {
        return matrizRellenadaCorrectamente(InterfazSegundaMatriz.getMatrizGuardada())
                && getLongitudCampo(InterfazPrincipal.getActionButton().getDistribucion().getMatrices()[0])
                == getLongitudCampo(InterfazSegundaMatriz.getMatrizGuardada());

    }

    public String getEntradaDoubleValido()
    {
        return "^-?[0-9]+(.?[0-9]+)*$";
    }

    public boolean entradaValida(String text, String regex)
    {
        return text.matches(regex);
    }

    public Matriz getCalculosMatriz()
    {
        return calculosMatriz;
    }

}
