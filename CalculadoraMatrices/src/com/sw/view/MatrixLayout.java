package com.sw.view;

import com.sw.controller.LayoutManagerAdapter;
import java.awt.Container;
import javax.swing.JPanel;

/**
 *
 *
 */
public final class MatrixLayout extends JPanel
{

    private MatrixDesign[] matrices;

    private String[] tituloMatrices
            =
            {
                String.format("20s", "Hola"),
                "Suma con otra matriz",
                "Multiplicación con otra matriz",
                "Multiplicación por escalar",
                "Inversa de la matriz"

            };

    private int ladoMatrices;

    public MatrixLayout(int ladoMatrices)
    {

        this.ladoMatrices = ladoMatrices;

        initComponents();

        addComponents();

    }

    private void initComponents()
    {
        matrices = new MatrixDesign[5];
    }

    private void addComponents()
    {

        setLayout(new Diseno());

        for (int i = 0; i < matrices.length; i++)
        {

            matrices[i] = new MatrixDesign(ladoMatrices, tituloMatrices[i]);
            add(matrices[i]);

        }

    }

    public MatrixDesign[] getMatrices()
    {
        return matrices;
    }

    public int getLadoMatrices()
    {
        return ladoMatrices;
    }

    private class Diseno extends LayoutManagerAdapter
    {

        @Override
        public void layoutContainer(Container parent)
        {
            int x = 600, y = 0;

            for (int i = 0; i < 5; i++)
            {
                if (i == 0)
                {
                    parent.getComponent(i).setBounds(x, y, 550, 300);
                    continue;

                }

                x += 580;

                if ((i + 1) % 2 == 0)
                {
                    y += 300;
                    x = 20;
                }

                parent.getComponent(i).setBounds(x, y, 550, 300);

            }

        }

    }

}
