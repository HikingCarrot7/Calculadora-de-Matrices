package com.sw.controller;

import com.sw.view.Interfaz;
import com.sw.view.MatrixLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mohammed
 */
public class ActionButton implements ActionListener
{

    private MatrixLayout distribucion;
    private ButtonActionManager buttonActionManager;
    private Interfaz interfaz;

    public ActionButton(MatrixLayout distribucion, ButtonActionManager buttonActionManager, Interfaz interfaz)
    {
        this.distribucion = distribucion;
        this.buttonActionManager = buttonActionManager;
        this.interfaz = interfaz;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Definir"))
            buttonActionManager.accionBotonDefinir(distribucion, interfaz);

        else
            buttonActionManager.accionBotonCalcular(distribucion, interfaz);

    }

    public void setDistribucion(MatrixLayout distribucion)
    {
        this.distribucion = distribucion;
    }

    public MatrixLayout getDistribucion()
    {
        return distribucion;
    }

}
