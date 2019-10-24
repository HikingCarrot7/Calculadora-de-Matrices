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

        System.out.println(determinante(0, miMatriz));

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

    private double[][] subMatriz(int fila, int columna, double[][] matriz)
    {

        double[][] subMatriz = new double[matriz.length - 1][matriz.length - 1];

        for (int i = 0, k = 0; i < matriz.length; i++)
            for (int j = 0, l = 0; i != fila && j < matriz.length; j++)
                if (j != columna)
                    subMatriz[l == subMatriz.length - 1 ? k++ : k][l++] = matriz[i][j];

        return subMatriz;

    }

    private void imprimirMatriz(double[][] matriz)
    {
        for (double[] fila : matriz)
        {
            for (double elemento : fila)
                System.out.printf(elemento < 10 ? "[0%,.2f] " : "[%,.2f] ", elemento);

            System.out.println("");

        }

        System.out.println("");

    }

    private double[][] rellenarMatriz(final int i)
    {
        double[][] matrizRellenada = new double[i][i];

        for (double[] fila : matrizRellenada)
            for (int l = 0; l < matrizRellenada.length; l++)
                fila[l] = (double) ((int) (Math.random() * 100));

        return matrizRellenada;

    }

    public static void main(String[] args)
    {
        new Matriz().pruebaMatriz();
    }

}
