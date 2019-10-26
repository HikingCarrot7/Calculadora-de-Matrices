package com.sw.controller;

import com.sw.model.Matriz;
import com.sw.view.MatrixLayout;
import com.sw.view.Interfaz;

/**
 *
 * @author Mohammed
 */
public class ButtonActionManager
{

    public void accionBotonDefinir(MatrixLayout distribucion, Interfaz interfaz)
    {

        interfaz.remove(distribucion);
        distribucion = new MatrixLayout(Integer.parseInt(interfaz.getEntradaLadoMatrices().getText()));
        distribucion.setBounds(0, 0, 1160, 930);
        interfaz.buttonsActionPerformed(distribucion);
        interfaz.add(distribucion);
        interfaz.validate();
        interfaz.repaint();
        interfaz.updateUI();

    }

    public void accionBotonCalcular(MatrixLayout distribuciones)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.matrizRellenadaCorrectamente(distribuciones.getMatrices()[0]))
            dataManager.actualizarCampo(distribuciones.getMatrices()[1], new Matriz().inversa(dataManager.getMatrizCampo(distribuciones.getMatrices()[0])));

    }

}
