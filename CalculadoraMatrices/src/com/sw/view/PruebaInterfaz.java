package com.sw.view;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Mohammed
 */
public final class PruebaInterfaz extends JFrame
{

    private Interfaz interfaz;

    public PruebaInterfaz()
    {
        interfaz = new Interfaz();

        setPreferredSize(new Dimension(1160, 930));
        setMinimumSize(new Dimension(1160, 930));
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Calculadora de matrices");
        getContentPane().add(interfaz);
        repaint();
        validate();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args)
    {
        new PruebaInterfaz();

    }

}
