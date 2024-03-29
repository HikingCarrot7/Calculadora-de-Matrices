package com.cherrysoft.matrixcalculator.views.imp;

import com.cherrysoft.matrixcalculator.core.InputMatrix;
import com.cherrysoft.matrixcalculator.views.MatrixPanelsRenderer;
import com.cherrysoft.matrixcalculator.views.SecondaryMatrixView;
import com.cherrysoft.matrixcalculator.views.imp.components.MatrixGridPanelParent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SecondaryMatrixViewImp extends JDialog implements SecondaryMatrixView {
  private MatrixPanelsRenderer matrixPanelsRenderer;
  private SecondaryMatrixView.Listener listener;

  public SecondaryMatrixViewImp(Window owner) {
    super(owner);
    initComponents();
    hookUpEvents();
  }

  private void hookUpEvents() {
    btnClear.addActionListener(e -> listener.onClearSecondaryMatrix());
    btnDone.addActionListener(e -> listener.onDoneFillingSecondaryMatrix());
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        listener.onClosingSecondaryMatrixView();
      }
    });
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jPanel1 = new javax.swing.JPanel();
    btnDone = new javax.swing.JButton();
    btnClear = new javax.swing.JButton();
    secondaryMatrixPanelParent = new MatrixGridPanelParent();
    jPanel3 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();

    setMinimumSize(new java.awt.Dimension(400, 300));

    btnDone.setText("Listo");
    jPanel1.add(btnDone);

    btnClear.setText("Limpiar");
    jPanel1.add(btnClear);

    getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

    secondaryMatrixPanelParent.setBorder(javax.swing.BorderFactory.createTitledBorder("Segunda Matriz"));
    secondaryMatrixPanelParent.setLayout(new java.awt.BorderLayout());
    getContentPane().add(secondaryMatrixPanelParent, java.awt.BorderLayout.CENTER);

    jPanel3.setLayout(new java.awt.GridBagLayout());

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Si no establece un matriz válida, los cálculos se harán con la de entrada.");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.1;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
    jPanel3.add(jLabel1, gridBagConstraints);

    getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  @Override
  public InputMatrix getSecondaryInputMatrix() {
    return new InputMatrix(secondaryMatrixPanelParent.getChildMatrixAsString());
  }

  @Override
  public void setSecondaryMatrixInitialState(int orderOfPrimaryMatrix, InputMatrix inputMatrix) {
    matrixPanelsRenderer = new MatrixPanelsRenderer(
      orderOfPrimaryMatrix,
      secondaryMatrixPanelParent
    );
    secondaryMatrixPanelParent.fillChildInputFieldsWith(inputMatrix);
  }

  @Override
  public void setListener(Listener listener) {
    this.listener = listener;
  }

  @Override
  public void clear() {
    secondaryMatrixPanelParent.clearChildInputFields();
  }

  @Override
  public void closeWindow() {
    dispose();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnClear;
  private javax.swing.JButton btnDone;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel3;
  private MatrixGridPanelParent secondaryMatrixPanelParent;
  // End of variables declaration//GEN-END:variables
}
