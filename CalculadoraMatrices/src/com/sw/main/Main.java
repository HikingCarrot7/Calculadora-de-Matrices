package com.sw.main;

/**
 *
 * @author Mohammed
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hola mundo!");

        double[][] matrizEjemplo = {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0},
            {7.0, 8.0, 9.0},};

        imprimirMatriz(matrizEjemplo);
        imprimirMatriz(getMatrizTranspuesta(matrizEjemplo));

    }

    /**
     *
     * @param matrizA matrix A as a 3x3 array of doubles
     * @param matrizB matrix B as a 3x3 array of doubles
     * @return matrix AB as a 3x3 array of doubles
     */
    public double[][] getProductoMatrices(double matrizA[][], double matrizB[][]) {
        double matrizProducto[][] = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double sum = 0.0;
                for (int k = 0; k < 10; k++) {

                    sum += matrizA[i][k] * matrizB[k][j];
                }
                matrizProducto[i][j] = sum;
            }
        }

        return matrizProducto;

    }

    public static double[][] getMatrizTranspuesta(double[][] matriz) {
        double[][] matrizTranspuesta = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                matrizTranspuesta[i][j] = matriz[j][i];
            }

        }

        return matrizTranspuesta;

    }

    /**
     * Imprime una matriz de 3x3 con forma: <br>
     *
     * x x x   <br>
     * x x x   <br>
     * x x x   <br>
     *
     * @param matriz array 2d de doubles
     */
    public static void imprimirMatriz(double[][] matriz) {

        for (double[] fila : matriz) {
            for (double elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println("");
        }

    }
}
