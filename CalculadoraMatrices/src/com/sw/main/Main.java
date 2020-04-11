package com.sw.main;

import com.sw.controller.VistaPrincipalController;
import com.sw.view.VistaPrincipal;
import java.awt.EventQueue;

/**
 *
 * @author Nicolás
 */
public class Main
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            VistaPrincipal vista = new VistaPrincipal();
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
            new VistaPrincipalController(vista);
        });
    }

}
