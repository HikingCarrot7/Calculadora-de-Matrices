package com.sw.view;

import com.sw.controller.ActionButton;
import com.sw.controller.ButtonActionManager;
import com.sw.controller.DAO;
import com.sw.controller.DataManager;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    private static ActionButton actionButton;
    private JPanel panel;
    private JButton definir, calcular, segundaMatriz, limpiar;
    private JLabel info, determinante, infoEscalar, titulo;
    private JTextField entradaLadoMatrices, escalar;
    private MatrixLayout distribucion;

    public Interfaz()
    {

        setLayout(null);

        initComponents();

        addComponents();

        buttonsActionPerformed();

    }

    public void setUpWindow()
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
        frame.addWindowListener(new WindowManager());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initComponents()
    {

        Font fuente = new Font("Consolas", Font.PLAIN, 11);

        distribucion = new MatrixLayout(new DAO("res/Matriz1.txt").getLadoMatriz());
        distribucion.setBounds(0, 0, 1160, 930);

        panel = new JPanel();
        panel.setLayout(null);

        panel.setBounds(20, 0, 550, 300);

        entradaLadoMatrices = new JTextField();
        entradaLadoMatrices.setFont(fuente);
        entradaLadoMatrices.setBounds(20, 170, 150, 30);

        escalar = new JTextField();
        escalar.setText("1");
        escalar.setFont(fuente);
        escalar.setBounds(20, 110, 150, 30);

        definir = new JButton();
        definir.setFont(fuente);
        definir.setBounds(190, 170, 90, 30);
        definir.setToolTipText("Define los lados para las matrices.");
        definir.setText("Definir");

        limpiar = new JButton();
        limpiar.setFont(fuente);
        limpiar.setBounds(190, 110, 90, 30);
        limpiar.setToolTipText("Limpia las entradas de la matriz");
        limpiar.setText("Limpiar");

        calcular = new JButton();
        calcular.setFont(fuente);
        calcular.setBounds(20, 260, 500, 30);
        calcular.setToolTipText("Hacer los cálculos");
        calcular.setText("Calcular");

        segundaMatriz = new JButton();
        segundaMatriz.setFont(fuente);
        segundaMatriz.setBounds(20, 220, 500, 30);
        segundaMatriz.setToolTipText("Establecer una segunda matriz para hacer los cálculos");
        segundaMatriz.setText("Establece una segunda matriz para hacer los cálculos");

        info = new JLabel("Lado de la matriz:");
        info.setFont(fuente);
        info.setBounds(20, 140, 150, 30);

        titulo = new JLabel("Calculadora de matrices");
        titulo.setFont(new Font("Consolas", Font.BOLD, 35));
        titulo.setToolTipText("Créditos: Voctor y Memo <3");
        titulo.setBounds(50, 0, 500, 100);

        determinante = new JLabel("Determinante:");
        determinante.setFont(fuente);
        determinante.setBounds(300, 170, 200, 30);

        infoEscalar = new JLabel("Define un escalar:");
        infoEscalar.setFont(fuente);
        infoEscalar.setBounds(20, 80, 150, 30);

    }

    private void addComponents()
    {
        panel.add(entradaLadoMatrices);
        panel.add(escalar);
        panel.add(definir);
        panel.add(calcular);
        panel.add(limpiar);
        panel.add(segundaMatriz);
        panel.add(titulo);
        panel.add(info);
        panel.add(infoEscalar);
        panel.add(determinante);

        add(panel);
        add(distribucion);

    }

    public void buttonsActionPerformed()
    {

        ButtonActionManager buttonActionManager = new ButtonActionManager();

        actionButton = new ActionButton(distribucion, buttonActionManager, this);

        definir.addActionListener(actionButton);

        calcular.addActionListener(actionButton);

        limpiar.addActionListener((e) ->
        {

            buttonActionManager.accionBotonLimpiarTodosCampos(actionButton);

        });

        segundaMatriz.addActionListener((e) ->
        {
            buttonActionManager.accionBotonSegundaMatriz(actionButton, this);

        });

        new DataManager().copiarMatrices(distribucion.getMatrices()[0], new DAO("res/Matriz1.txt").readFile());

    }

    private class WindowManager extends WindowAdapter
    {

        @Override
        public void windowClosing(WindowEvent e)
        {

            new DAO("res/Matriz1.txt").writeFile(actionButton.getDistribucion().getMatrices()[0]);

        }

    }

    public JTextField getEntradaLadoMatrices()
    {
        return entradaLadoMatrices;
    }

    public static ActionButton getActionButton()
    {
        return actionButton;
    }

    public JLabel getDeterminante()
    {
        return determinante;
    }

    public JTextField getEscalar()
    {
        return escalar;
    }

    public JButton getSegundaMatriz()
    {
        return segundaMatriz;
    }

}
