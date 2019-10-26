package com.sw.view;

import com.sw.controller.ButtonActionManager;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mohammed
 */
public final class Interfaz extends JPanel
{

    private JPanel panel;
    private JButton definir, calcular;
    private JLabel info;
    private JTextField entradaLadoMatrices;
    private DistribucionMatrices distribucion;

    public Interfaz()
    {

        setLayout(null);

        initComponents();

        addComponents();

        buttonsActionPerformed(distribucion);

    }

    public static void setUpWindow()
    {

        JFrame frame = new JFrame();
        Interfaz interfaz = new Interfaz();

        frame.setPreferredSize(new Dimension(1160, 930));
        frame.setMinimumSize(new Dimension(1160, 930));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Calculadora de matrices");
        frame.getContentPane().add(interfaz);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initComponents()
    {

        distribucion = new DistribucionMatrices(3);
        distribucion.setBounds(0, 0, 1160, 930);

        panel = new JPanel();
        panel.setLayout(null);

        panel.setBounds(20, 0, 550, 300);

        entradaLadoMatrices = new JTextField();
        entradaLadoMatrices.setBounds(20, 220, 150, 30);

        definir = new JButton();
        definir.setBounds(190, 220, 90, 30);
        definir.setToolTipText("Define los lados para las matrices.");
        definir.setText("Definir");

        calcular = new JButton();
        calcular.setBounds(20, 260, 500, 30);
        calcular.setText("Calcular");

    }

    private void addComponents()
    {
        panel.add(entradaLadoMatrices);
        panel.add(definir);
        panel.add(calcular);

        add(panel);
        add(distribucion);

    }

    public void buttonsActionPerformed(DistribucionMatrices distribucion)
    {
        definir.addActionListener((e) ->
        {

            new ButtonActionManager().accionBotonDefinir(distribucion, this);

        });

        calcular.addActionListener((e) ->
        {
            new ButtonActionManager().accionBotonCalcular(distribucion);

        });

    }

    public JTextField getEntradaLadoMatrices()
    {
        return entradaLadoMatrices;
    }

}
