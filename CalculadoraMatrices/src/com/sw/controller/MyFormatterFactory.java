package com.sw.controller;

import java.text.ParseException;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Nicolás
 */
public class MyFormatterFactory extends JFormattedTextField.AbstractFormatterFactory
{

    @Override
    public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf)
    {
        return new JFormattedTextField.AbstractFormatter()
        {
            @Override
            public Object stringToValue(String text) throws ParseException
            {
                try
                {
                    return Double.parseDouble(text);

                } catch (NumberFormatException e)
                {
                    throw new ParseException(text, 0);
                }
            }

            @Override
            public String valueToString(Object value) throws ParseException
            {
                return String.valueOf(value == null ? "" : value);
            }
        };
    }

}
