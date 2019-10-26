package com.sw.controller;

import com.sw.view.DistribucionMatrices;
import com.sw.view.Interfaz;

/**
 *
 * @author Mohammed
 */
public class ButtonActionManager
{

    public void accionBotonDefinir(DistribucionMatrices distribucion, Interfaz interfaz)
    {

        interfaz.remove(distribucion);
        distribucion = new DistribucionMatrices(Integer.parseInt(interfaz.getEntradaLadoMatrices().getText()));
        distribucion.setBounds(0, 0, 1160, 930);
        interfaz.buttonsActionPerformed(distribucion);
        interfaz.add(distribucion);
        interfaz.validate();
        interfaz.repaint();
        interfaz.updateUI();

    }

    public void accionBotonCalcular(DistribucionMatrices distribuciones)
    {
        DataManager dataManager = new DataManager();

        int longitudMatrices = dataManager.getLogitudCampo(distribuciones.getMatrices()[0]);

        double[][] matrizEntrada = new double[longitudMatrices][longitudMatrices];

        dataManager.actualizarCampo(distribuciones.getMatrices()[1], matrizEntrada);

    }

}
