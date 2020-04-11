package com.sw.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Nicolás
 */
public final class DibujadorMatrices
{

    private final VistaMatriz[] vistaMatrices;
    private final JPanel[] panelesSoporteMatrices;

    public DibujadorMatrices(final int ordenMatriz, JPanel... panelesSoporteMatrices)
    {
        this.panelesSoporteMatrices = panelesSoporteMatrices;
        this.vistaMatrices = new VistaMatriz[panelesSoporteMatrices.length];
        dibujarMatrices(ordenMatriz);
    }

    public void dibujarMatrices(int ordenMatriz)
    {
        for (int i = 0; i < panelesSoporteMatrices.length; i++)
        {
            vistaMatrices[i] = new VistaMatriz(ordenMatriz);
            dibujarMatriz(panelesSoporteMatrices[i], vistaMatrices[i], ordenMatriz);
        }
    }

    public void dibujarMatriz(JPanel panelSoporte, VistaMatriz matriz, int ordenMatriz)
    {
        panelSoporte.removeAll();
        panelSoporte.add(matriz, BorderLayout.CENTER);
        panelSoporte.revalidate();
        panelSoporte.repaint();
    }

    public VistaMatriz[] getVistaMatrices()
    {
        return vistaMatrices;
    }

}
