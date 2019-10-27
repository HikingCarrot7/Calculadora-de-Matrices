package com.sw.controller;

import com.sw.view.Interfaz;
import com.sw.view.MatrixLayout;
import com.sw.view.SecondMatrix;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohammed
 */
public class ButtonActionManager
{

    public void accionBotonDefinir(MatrixLayout distribucion, Interfaz interfaz)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.entradaValida(interfaz.getEntradaLadoMatrices().getText(), "^[3-9]$"))
        {

            dataManager.guardarMatrizEntrada(distribucion);

            interfaz.remove(distribucion);
            distribucion = new MatrixLayout(Integer.parseInt(interfaz.getEntradaLadoMatrices().getText()));
            distribucion.setBounds(0, 0, 1160, 930);
            dataManager.reestablecerCampos(distribucion);
            Interfaz.getActionButton().setDistribucion(distribucion);
            interfaz.add(distribucion);
            interfaz.updateUI();

        }

    }

    public void accionBotonCalcular(MatrixLayout distribuciones, Interfaz interfaz)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.matrizRellenadaCorrectamente(distribuciones.getMatrices()[0])
                && dataManager.entradaValida(interfaz.getEscalar().getText(), "-?[0-9]+$"))
        {
            dataManager.rellenarTodosLosCampos(distribuciones.getMatrices(), Double.parseDouble(interfaz.getEscalar().getText()), SecondMatrix.isValida());
            interfaz.getDeterminante().setText("Determinante: " + dataManager.getCalculosMatriz().determinante(0, dataManager.getMatrizCampo(distribuciones.getMatrices()[0])));

        } else
            JOptionPane.showMessageDialog(null, "Alguna entrada no es válida, revísalas", "Entrada no válida", JOptionPane.ERROR_MESSAGE);

    }

    public void accionBotonSegundaMatriz(ActionButton actionButton, Interfaz interfaz)
    {

        SecondMatrix.setUpWindow(actionButton.getDistribucion().getLadoMatrices(), interfaz);

        new DataManager().copiarMatrices(SecondMatrix.getMatriz(), SecondMatrix.getMatrizGuardada());

        interfaz.getSegundaMatriz().setEnabled(false);

    }

    public void accionBotonListo(Interfaz interfaz)
    {

        DataManager dataManager = new DataManager();

        dataManager.copiarMatrices(SecondMatrix.getMatrizGuardada(), SecondMatrix.getMatriz());

        SecondMatrix.setValida(dataManager.matrizRellenadaCorrectamente(SecondMatrix.getMatrizGuardada())
                && dataManager.getLongitudCampo(Interfaz.getActionButton().getDistribucion().getMatrices()[0]) == dataManager.getLongitudCampo(SecondMatrix.getMatrizGuardada()));

        interfaz.getSegundaMatriz().setEnabled(true);

        new DAO("res/Matriz2.txt").writeFile(SecondMatrix.getMatrizGuardada());

    }

    public void accionBotonLimpiarTodosCampos(ActionButton actionButton)
    {
        new DataManager().limpiarCampos(actionButton.getDistribucion().getMatrices());
    }

    public void accionLimpiarUnCampo()
    {
        new DataManager().limpiarUnCampo(SecondMatrix.getMatriz());
    }

}
