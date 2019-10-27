package com.sw.controller;

import com.sw.view.Interfaz;
import com.sw.view.MatrixLayout;
import com.sw.view.SecondMatrix;

/**
 *
 * @author Mohammed
 */
public class ButtonActionManager
{

    public void accionBotonDefinir(MatrixLayout distribucion, Interfaz interfaz)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.entradaValida(interfaz.getEntradaLadoMatrices().getText(), "^[3-9]$"))
        {

            dataManager.guardarMatrizEntrada(distribucion);

            interfaz.remove(distribucion);
            distribucion = new MatrixLayout(Integer.parseInt(interfaz.getEntradaLadoMatrices().getText()));
            distribucion.setBounds(0, 0, 1160, 930);
            dataManager.reestablecerCampos(distribucion);
            interfaz.getActionButton().setDistribucion(distribucion);
            interfaz.add(distribucion);
            interfaz.updateUI();

        }

    }

    public void accionBotonCalcular(MatrixLayout distribuciones)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.matrizRellenadaCorrectamente(distribuciones.getMatrices()[0]))
            dataManager.rellenarTodosLosCampos(distribuciones.getMatrices(), SecondMatrix.isValida());

    }

    public void accionBotonSegundaMatriz(ActionButton actionButton, Interfaz interfaz)
    {

        SecondMatrix.setUpWindow(actionButton.getDistribucion().getLadoMatrices(), interfaz);

        new DataManager().copiarMatrices(SecondMatrix.getMatriz(), SecondMatrix.getMatrizGuardada());

    }

    public void accionBotonListo(Interfaz interfaz)
    {

        DataManager dataManager = new DataManager();

        dataManager.copiarMatrices(SecondMatrix.getMatrizGuardada(), SecondMatrix.getMatriz());

        SecondMatrix.setValida(dataManager.matrizRellenadaCorrectamente(SecondMatrix.getMatrizGuardada())
                && dataManager.getLongitudCampo(interfaz.getActionButton().getDistribucion().getMatrices()[0]) == dataManager.getLongitudCampo(SecondMatrix.getMatrizGuardada()));

    }

}
