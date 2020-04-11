package com.sw.view;

import com.sw.controller.MyFormatterFactory;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nicolás
 */
public final class VistaMatriz extends JPanel
{

    private final int H_GAP = 5;
    private final int V_GAP = 5;
    private final JTextField[][] entradas;

    public VistaMatriz(int ordenMatriz)
    {
        entradas = new JTextField[ordenMatriz][ordenMatriz];
        setLayout(new GridLayout(ordenMatriz, ordenMatriz, H_GAP, V_GAP));
        dibujarMatriz();
    }

    private void dibujarMatriz()
    {
        MyFormatterFactory myFormatterFactory = new MyFormatterFactory();

        for (JTextField[] row : entradas)
            for (int j = 0; j < row.length; j++)
            {
                row[j] = new JFormattedTextField(myFormatterFactory);
                row[j].setHorizontalAlignment(JTextField.CENTER);
                add(row[j]);
            }
    }

    public JTextField[][] getEntradas()
    {
        return entradas;
    }

}
