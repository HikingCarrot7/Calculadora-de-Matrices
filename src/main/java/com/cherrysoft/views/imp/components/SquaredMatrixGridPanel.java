package com.cherrysoft.views.imp.components;

import com.cherrysoft.views.utils.DoubleFormatterFactory;

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

  private JTextField createInputField() {
    return new JFormattedTextField(formatterFactory);
  }

  private void addInputFieldToPanel(JTextField inputField) {
    this.add(inputField);
  }

  public void clearInputFields() {
    for (JTextField[] inputFieldsRow : inputFields) {
      for (JTextField inputField : inputFieldsRow) {
        inputField.setText("");
      }
    }
  }

  public void fillInputFieldsWith(String[][] items) {
    int minLength = Math.min(items.length, inputFields.length);
    for (int i = 0; i < minLength; i++) {
      for (int j = 0; j < minLength; j++) {
        inputFields[i][j].setText(items[i][j]);
      }
    }
  }

  public JTextField[][] getInputFields() {
    return inputFields;
  }

  public String[][] getAsRawMatrix() {
    String[][] items = new String[inputFields.length][inputFields[0].length];
    for (int i = 0; i < items.length; i++) {
      for (int j = 0; j < items[i].length; j++) {
        items[i][j] = inputFields[i][j].getText();
      }
    }
    return items;
  }
}
