package com.sw.controller;

import com.sw.view.InterfazPrincipal;
import com.sw.view.MatrixLayout;
import com.sw.view.InterfazSegundaMatriz;
import javax.swing.JOptionPane;

/**
 *
 *  
 */
public class ActionButtonManager
{

    public void accionBotonDefinir(MatrixLayout distribucion, InterfazPrincipal interfaz)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.entradaValida(interfaz.getEntradaLadoMatrices().getText(), "^[3-9]$"))
        {

            dataManager.guardarMatrizEntrada(distribucion);

            interfaz.remove(distribucion);
            distribucion = new MatrixLayout(Integer.parseInt(interfaz.getEntradaLadoMatrices().getText()));
            distribucion.setBounds(0, 0, 1160, 930);
            dataManager.reestablecerCampos(distribucion);
            InterfazPrincipal.getActionButton().setDistribucion(distribucion);
            interfaz.add(distribucion);
            interfaz.updateUI();

        }

    }

    public void accionBotonCalcular(MatrixLayout distribuciones, InterfazPrincipal interfaz)
    {
        DataManager dataManager = new DataManager();

        if (dataManager.matrizRellenadaCorrectamente(distribuciones.getMatrices()[0])
                && dataManager.entradaValida(interfaz.getEscalar().getText(), dataManager.getEntradaDoubleValido()))
        {

            if (InterfazSegundaMatriz.isValida())
            {

                dataManager.recortarMatriz(InterfazSegundaMatriz.getMatriz(), InterfazPrincipal.getActionButton().getDistribucion().getMatrices()[0].getLadoMatriz());
                dataManager.recortarMatriz(InterfazSegundaMatriz.getMatrizGuardada(), InterfazPrincipal.getActionButton().getDistribucion().getMatrices()[0].getLadoMatriz());

            }

            dataManager.rellenarTodosLosCampos(distribuciones.getMatrices(), Double.parseDouble(interfaz.getEscalar().getText()), dataManager.segundaMatrizValida());
            interfaz.getDeterminante().setText("Determinante: " + dataManager.getCalculosMatriz().determinante(0, dataManager.getMatrizCampo(distribuciones.getMatrices()[0])));

        } else
            JOptionPane.showMessageDialog(null, "Alguna entrada no es válida, revísalas", "Entrada no válida", JOptionPane.ERROR_MESSAGE);

    }

    public void accionBotonSegundaMatriz(ActionButton actionButton, InterfazPrincipal interfaz)
    {

        InterfazSegundaMatriz.setUpWindow(actionButton.getDistribucion().getLadoMatrices(), interfaz);

        new DataManager().copiarMatrices(InterfazSegundaMatriz.getMatriz(), InterfazSegundaMatriz.getMatrizGuardada());

        interfaz.getSegundaMatriz().setEnabled(false);

    }

    public void accionBotonListo(InterfazPrincipal interfaz)
    {

        DataManager dataManager = new DataManager();

        dataManager.recortarMatriz(InterfazSegundaMatriz.getMatriz(), InterfazPrincipal.getActionButton().getDistribucion().getMatrices()[0].getLadoMatriz());
        dataManager.recortarMatriz(InterfazSegundaMatriz.getMatrizGuardada(), InterfazPrincipal.getActionButton().getDistribucion().getMatrices()[0].getLadoMatriz());

        dataManager.copiarMatrices(InterfazSegundaMatriz.getMatrizGuardada(), InterfazSegundaMatriz.getMatriz());

        InterfazSegundaMatriz.setValida(dataManager.segundaMatrizValida());

        interfaz.getSegundaMatriz().setEnabled(true);

        new DAO("res/Matriz2.txt").writeFile(InterfazSegundaMatriz.getMatrizGuardada());

    }

    public void accionBotonLimpiarTodosCampos(ActionButton actionButton)
    {
        new DataManager().limpiarCampos(actionButton.getDistribucion().getMatrices());
    }

    public void accionLimpiarUnCampo()
    {
        new DataManager().limpiarUnCampo(InterfazSegundaMatriz.getMatriz());
    }

}
