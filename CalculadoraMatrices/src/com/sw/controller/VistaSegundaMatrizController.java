package com.sw.controller;

import com.sw.persistence.DAO;
import com.sw.view.DibujadorMatrices;
import com.sw.view.VistaMatriz;
import com.sw.view.VistaSegundaMatriz;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Nicolás
 */
public class VistaSegundaMatrizController
{

    private final int INDEX_MATRIZ_SECUNDARIA = 0;

    private final VistaSegundaMatriz vistaSegundaMatriz;
    private final DibujadorMatrices dibujadorMatrices;
    private final DataManager dataManager;
    private final DAO daoMatrizSecundaria;

    public VistaSegundaMatrizController(final VistaSegundaMatriz vistaSegundaMatriz, final int ordenMatriz)
    {
        this.vistaSegundaMatriz = vistaSegundaMatriz;
        this.dibujadorMatrices = new DibujadorMatrices(ordenMatriz, vistaSegundaMatriz.getPanelMatriz());
        this.dataManager = DataManager.getInstance();
        this.daoMatrizSecundaria = new DAO(DAO.RUTA_MATRIZ_SECUNDARIA);
        initComponents();
    }

    private void initComponents()
    {
        vistaSegundaMatriz.getBtnListo().addActionListener(this::accionBtnListo);
        vistaSegundaMatriz.getBtnLimpiar().addActionListener(this::accionBtnLimpiar);
        dataManager.setEntradas(getVistaMatrizSecundaria(), daoMatrizSecundaria.getMatriz());

        vistaSegundaMatriz.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                guardarMatriz();
            }
        });
    }

    private void accionBtnListo(ActionEvent e)
    {
        guardarMatriz();
        cerrarVentana();
    }

    private void accionBtnLimpiar(ActionEvent e)
    {
        dataManager.setEntradas("", getVistaMatrizSecundaria());
    }

    private VistaMatriz getVistaMatrizSecundaria()
    {
        return dibujadorMatrices.getVistaMatrices()[INDEX_MATRIZ_SECUNDARIA];
    }

    private void guardarMatriz()
    {
        daoMatrizSecundaria.guardarMatriz(dataManager.getEntradasTxt(getVistaMatrizSecundaria()));
    }

    private void cerrarVentana()
    {
        vistaSegundaMatriz.dispose();
    }

}
