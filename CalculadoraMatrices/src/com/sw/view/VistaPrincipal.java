package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author Nicolás
 */
public class VistaPrincipal extends javax.swing.JFrame
{

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal()
    {
        initLookAndFeel();
        initComponents();
    }

    private void initLookAndFeel()
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        }
        //</editor-fold>
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDefinirOrden = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnCalcular = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnSetSegundaMatriz = new javax.swing.JButton();
        ckbHabilitarSegundaMatriz = new javax.swing.JCheckBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        jLabel4 = new javax.swing.JLabel();
        txtDeterminante = new javax.swing.JTextField();
        spOrdenMatriz = new javax.swing.JSpinner();
        spEscalar = new javax.swing.JSpinner();
        panelMatriz = new javax.swing.JPanel();
        panelSumaOtraMatriz = new javax.swing.JPanel();
        panelMultiOtraMatriz = new javax.swing.JPanel();
        panelMultiEscalar = new javax.swing.JPanel();
        panelInversaMatriz = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 710));
        setPreferredSize(new java.awt.Dimension(800, 710));
        getContentPane().setLayout(new java.awt.GridLayout(3, 2));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrada"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calculadora de matrices");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Orden de la matriz:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jLabel2, gridBagConstraints);

        btnDefinirOrden.setText("Definir");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(btnDefinirOrden, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Define un escalar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jLabel3, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnCalcular.setText("Calcular");
        jPanel8.add(btnCalcular);

        jPanel7.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        btnLimpiar.setText("Limpiar todos los campos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 1, 1);
        jPanel9.add(btnLimpiar, gridBagConstraints);

        btnSetSegundaMatriz.setText("Establecer una segunda matriz");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 1);
        jPanel9.add(btnSetSegundaMatriz, gridBagConstraints);

        ckbHabilitarSegundaMatriz.setSelected(true);
        ckbHabilitarSegundaMatriz.setText("Habilitar segunda matriz");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(ckbHabilitarSegundaMatriz, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel9.add(filler1, gridBagConstraints);

        jPanel7.add(jPanel9, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jPanel7, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Determinante:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jLabel4, gridBagConstraints);

        txtDeterminante.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(txtDeterminante, gridBagConstraints);

        spOrdenMatriz.setModel(new javax.swing.SpinnerNumberModel(3, 3, 10, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(spOrdenMatriz, gridBagConstraints);

        spEscalar.setModel(new javax.swing.SpinnerNumberModel(1.0d, null, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(spEscalar, gridBagConstraints);

        getContentPane().add(jPanel1);

        panelMatriz.setBorder(javax.swing.BorderFactory.createTitledBorder("Matriz"));
        panelMatriz.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelMatriz);

        panelSumaOtraMatriz.setBorder(javax.swing.BorderFactory.createTitledBorder("Suma con otra matriz"));
        panelSumaOtraMatriz.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelSumaOtraMatriz);

        panelMultiOtraMatriz.setBorder(javax.swing.BorderFactory.createTitledBorder("Multiplicación con otra matriz"));
        panelMultiOtraMatriz.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelMultiOtraMatriz);

        panelMultiEscalar.setBorder(javax.swing.BorderFactory.createTitledBorder("Multiplicación por escalar"));
        panelMultiEscalar.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelMultiEscalar);

        panelInversaMatriz.setBorder(javax.swing.BorderFactory.createTitledBorder("Inversa de la matriz"));
        panelInversaMatriz.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelInversaMatriz);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnCalcular()
    {
        return btnCalcular;
    }

    public JButton getBtnDefinirOrden()
    {
        return btnDefinirOrden;
    }

    public JButton getBtnLimpiar()
    {
        return btnLimpiar;
    }

    public JButton getBtnSetSegundaMatriz()
    {
        return btnSetSegundaMatriz;
    }

    public JPanel getPanelInversaMatriz()
    {
        return panelInversaMatriz;
    }

    public JPanel getPanelMatriz()
    {
        return panelMatriz;
    }

    public JPanel getPanelMultiEscalar()
    {
        return panelMultiEscalar;
    }

    public JPanel getPanelMultiOtraMatriz()
    {
        return panelMultiOtraMatriz;
    }

    public JPanel getPanelSumaOtraMatriz()
    {
        return panelSumaOtraMatriz;
    }

    public JSpinner getSpEscalar()
    {
        return spEscalar;
    }

    public JSpinner getSpOrdenMatriz()
    {
        return spOrdenMatriz;
    }

    public JTextField getTxtDeterminante()
    {
        return txtDeterminante;
    }

    public JCheckBox getCkbHabilitarSegundaMatriz()
    {
        return ckbHabilitarSegundaMatriz;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnDefinirOrden;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSetSegundaMatriz;
    private javax.swing.JCheckBox ckbHabilitarSegundaMatriz;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel panelInversaMatriz;
    private javax.swing.JPanel panelMatriz;
    private javax.swing.JPanel panelMultiEscalar;
    private javax.swing.JPanel panelMultiOtraMatriz;
    private javax.swing.JPanel panelSumaOtraMatriz;
    private javax.swing.JSpinner spEscalar;
    private javax.swing.JSpinner spOrdenMatriz;
    private javax.swing.JTextField txtDeterminante;
    // End of variables declaration//GEN-END:variables
}
