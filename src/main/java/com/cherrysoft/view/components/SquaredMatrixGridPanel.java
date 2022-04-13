package com.cherrysoft.view.components;

import com.cherrysoft.view.utils.DoubleFormatterFactory;

import javax.swing.*;
import java.awt.*;

public class SquaredMatrixGridPanel extends JPanel {
  private final JTextField[][] inputFields;
  private final JFormattedTextField.AbstractFormatterFactory formatterFactory;

  public SquaredMatrixGridPanel(int sideLength) {
    this(sideLength, new DoubleFormatterFactory());
  }

  public SquaredMatrixGridPanel(
      int sideLength,
      JFormattedTextField.AbstractFormatterFactory formatterFactory
  ) {
    this.formatterFactory = formatterFactory;
    this.inputFields = new JTextField[sideLength][sideLength];
    setLayout(new GridLayout(sideLength, sideLength, 5, 5));
    renderEmptyMatrix();
  }

  private void renderEmptyMatrix() {
    createInputFieldsMatrix();
    for (JTextField[] inputFieldsRow : inputFields) {
      for (JTextField inputField : inputFieldsRow) {
        inputField.setHorizontalAlignment(JTextField.CENTER);
        addInputFieldToPanel(inputField);
      }
    }
  }

  private void createInputFieldsMatrix() {
    for (JTextField[] inputFieldsRow : inputFields) {
      for (int col = 0; col < inputFieldsRow.length; col++) {
        inputFieldsRow[col] = createInputField();
      }
    }
  }

  private void addInputFieldToPanel(JTextField inputField) {
    this.add(inputField);
  }

  private JTextField createInputField() {
    return new JFormattedTextField(formatterFactory);
  }

  public JTextField[][] getInputFields() {
    return inputFields;
  }

}
