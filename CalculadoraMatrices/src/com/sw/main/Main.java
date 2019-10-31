package com.sw.main;

import com.sw.view.InterfazPrincipal;
import javax.swing.SwingUtilities;

/**
 * @author Guillermo Canto Dzul
 * @author Ricardo Nicolás Canul Ibarra
 * @author Victor Cauich Dávalos
 *
 */
public class Main
{

    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(new InterfazPrincipal()::setUpWindow);

    }

}
