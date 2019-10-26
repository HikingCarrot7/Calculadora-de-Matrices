package com.sw.view;

import com.sw.controller.LayoutManagerAdapter;
import java.awt.Container;
import javax.swing.JPanel;

/**
 *
 * @author Mohammed
 */
public final class DistribucionMatrices extends JPanel
{

    private Matriz[] matrices;
    private int ladoMatrices;

    public DistribucionMatrices(int ladoMatrices)
    {

        this.ladoMatrices = ladoMatrices;

        initComponents();

        addComponents();

    }

    private void initComponents()
    {
        matrices = new Matriz[5];
    }

    private void addComponents()
    {

        setLayout(new Diseno());

        for (int i = 0; i < matrices.length; i++)
        {
            matrices[i] = new Matriz(ladoMatrices);
            add(matrices[i]);

        }

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
