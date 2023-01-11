package com.cherrysoft.matrixcalculator.views.imp.components;

import com.cherrysoft.matrixcalculator.views.utils.DoubleFormatterFactory;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SquaredMatrixGridPanel extends JPanel {
  public static final DecimalFormat DOUBLE_2_DECIMAL_PLACES_FORMAT = new DecimalFormat("0.00");
  public static final DecimalFormat DOUBLE_6_DECIMAL_PLACES_FORMAT = new DecimalFormat("0.000000");
  private final JTextField[][] inputFields;
  private final DecimalFormat df;

  public SquaredMatrixGridPanel(int sideLength, DecimalFormat decimalFormat) {
    this.df = decimalFormat;
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
    return new JFormattedTextField(new DoubleFormatterFactory());
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
        String item = items[i][j];
        JTextField inputField = inputFields[i][j];
        if (item.trim().isEmpty()) {
          inputField.setText(item);
        } else {
          inputField.setText(df.format(Double.parseDouble(item)));
        }
      }
    }
  }

  public String[][] getMatrixAsString() {
    String[][] items = new String[inputFields.length][inputFields[0].length];
    for (int i = 0; i < items.length; i++) {
      for (int j = 0; j < items[i].length; j++) {
        items[i][j] = inputFields[i][j].getText();
      }
    }
    return items;
  }

}
