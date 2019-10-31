package com.sw.view;

import com.sw.controller.ActionButtonManager;
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

/**
 *
 *
 */
public final class InterfazSegundaMatriz extends JPanel
{

    private static MatrixDesign matrizGuardada, matriz;
    private static JFrame frame;
    private static boolean valida = false;
    private InterfazPrincipal interfaz;
    private JLabel info;
    private JButton listo, limpiar;

    static
    {

        DataManager dataManager = new DataManager();
        DAO dao = new DAO("res/Matriz2.txt");

        matrizGuardada = new MatrixDesign(9);
        dataManager.copiarMatrices(matrizGuardada, dao.readFile());

        matriz = new MatrixDesign(dao.getLadoMatriz());
        dataManager.copiarMatrices(matriz, matrizGuardada);

    }

    public InterfazSegundaMatriz(int ladoMatriz, InterfazPrincipal interfaz)
    {
        this.interfaz = interfaz;

        setLayout(null);

        initComponents(ladoMatriz);

        addComponents();

        buttonActionPerformed();

    }

    public void initComponents(int ladoMatriz)
    {

        Font fuente = new Font("Consolas", Font.PLAIN, 11);

        matriz = new MatrixDesign(ladoMatriz, "Establece la segunda matriz");
        matriz.setBounds(10, 40, 570, 300);

        listo = new JButton();
        listo.setBounds(170, 345, 100, 25);
        listo.setFont(fuente);
        listo.setText("Listo!");
        listo.setToolTipText("Si la matriz es válida, se tomará para hacer los cálculos.");

        limpiar = new JButton();
        limpiar.setBounds(310, 345, 100, 25);
        limpiar.setFont(fuente);
        limpiar.setText("Limpiar");
        limpiar.setToolTipText("Limpia esta matriz");

        info = new JLabel("Si no establece una segunda matriz o no es válida, los cálculos se harán con la de entrada.");
        info.setBounds(10, 0, 570, 50);
        info.setFont(fuente);

    }

    public void addComponents()
    {
        add(matriz);
        add(info);
        add(listo);
        add(limpiar);

    }

    public void buttonActionPerformed()
    {

        ActionButtonManager actionButtonManager = new ActionButtonManager();

        listo.addActionListener((e) ->
        {
            actionButtonManager.accionBotonListo(interfaz);

            frame.dispose();

        });

        limpiar.addActionListener((e) ->
        {
            actionButtonManager.accionLimpiarUnCampo();
        });

    }

    public static boolean isValida()
    {
        return valida;
    }

    public static void setValida(boolean valida)
    {
        InterfazSegundaMatriz.valida = valida;
    }

    public static MatrixDesign getMatrizGuardada()
    {
        return matrizGuardada;
    }

    public static MatrixDesign getMatriz()
    {
        return matriz;
    }

    public static void setUpWindow(int ladoMatriz, InterfazPrincipal interfaz)
    {

        frame = new JFrame();
        InterfazSegundaMatriz secondMatrix = new InterfazSegundaMatriz(ladoMatriz, interfaz);

        frame.setPreferredSize(new Dimension(600, 400));
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Segunda matriz");
        frame.getContentPane().add(secondMatrix);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                interfaz.getSegundaMatriz().setEnabled(true);

                new DAO("res/Matriz2.txt").writeFile(matrizGuardada);

            }

        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}
