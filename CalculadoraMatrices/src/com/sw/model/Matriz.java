package com.sw.model;

/**
 *
 * @author Mohammed
 */
public class Matriz
{

    public void pruebaMatriz()
    {
        double[][] miMatriz = rellenarMatriz(3);

        imprimirMatriz(miMatriz);

        imprimirMatriz(inversa(miMatriz));

    }

    public double[][] inversa(double[][] matriz)
    {
        return multiplicarPorEscalar(1 / determinante(0, matriz), adjunta(matriz));
    }

    public double determinante(int i, double[][] matriz)
    {

        if (matriz.length == 2)
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];

        else
        {

            double deter = 0;

            for (int j = 0; j < matriz.length; j++)
            {
                double[][] subMatriz = subMatriz(i, j, matriz);

                deter += Math.pow(-1, i + j) * matriz[i][j] * determinante(i, subMatriz);

            }

            return deter;

        }

    }

    public double[][] adjunta(double[][] matriz)
    {
        double[][] matrizCofactores = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matrizCofactores[i][j] = Math.pow(-1, i + j) * determinante(0, subMatriz(i, j, matriz));

        return transpuesta(matrizCofactores);

    }

    public double[][] transpuesta(double[][] matriz)
    {
        double[][] matrizTranspuesta = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz.length; j++)
                matrizTranspuesta[i][j] = matriz[j][i];

        return matrizTranspuesta;

    }

    public double[][] multiplicarPorEscalar(double escalar, double[][] matriz)
    {
        for (double[] fila : matriz)
            for (int j = 0; j < matriz.length; j++)
                fila[j] *= escalar;

        return matriz;

    }

    public double[][] subMatriz(int fila, int columna, double[][] matriz)
    {

        double[][] subMatriz = new double[matriz.length - 1][matriz.length - 1];

        for (int i = 0, k = 0; i < matriz.length; i++)
            for (int j = 0, l = 0; i != fila && j < matriz.length; j++)
                if (j != columna)
                    subMatriz[l == subMatriz.length - 1 ? k++ : k][l++] = matriz[i][j];

        return subMatriz;

    }

    public double[][] rellenarMatriz(final int i)
    {
        double[][] matrizRellenada = new double[i][i];

        for (double[] fila : matrizRellenada)
            for (int l = 0; l < matrizRellenada.length; l++)
                fila[l] = (double) ((int) (Math.random() * 100));

        return matrizRellenada;

    }

    public void imprimirMatriz(double[][] matriz)
    {
        for (double[] fila : matriz)
        {
            for (double elemento : fila)
                System.out.printf("%-15s", String.format("[%,.5f] ", elemento));

            System.out.println("");

        }

        System.out.println("");

    }

    public static void main(String[] args)
    {
        new Matriz().pruebaMatriz();
    }

}