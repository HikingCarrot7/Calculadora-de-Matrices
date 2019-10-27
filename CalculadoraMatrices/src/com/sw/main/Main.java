package com.sw.main;

import com.sw.view.Interfaz;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mohammed
 */
public class Main
{

    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(() ->
        {
            Interfaz.setUpWindow();

        });

    }

}
