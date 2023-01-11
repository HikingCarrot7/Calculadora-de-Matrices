package com.cherrysoft.matrixcalculator.views.utils;

import javax.swing.*;
import java.text.ParseException;

public class DoubleFormatterFactory extends JFormattedTextField.AbstractFormatterFactory {

  @Override
  public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
    return new JFormattedTextField.AbstractFormatter() {

      @Override
      public Object stringToValue(String text) throws ParseException {
        try {
          return Double.parseDouble(text);
        } catch (NumberFormatException e) {
          throw new ParseException(text, 0);
        }
      }

      @Override
      public String valueToString(Object value) {
        return String.valueOf(value == null ? "" : value);
      }

    };

  }

}
