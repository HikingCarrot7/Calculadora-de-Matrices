package com.sw.controller;

import com.sw.view.MatrixDesign;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 *  
 */
public class DAO
{

    private File file;

    public DAO(String ruta)
    {

        file = new File(ruta);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public void writeFile(MatrixDesign matriz)
    {

        try (Formatter out = new Formatter(new FileWriter(file, false)))
        {

            for (int i = 0; i < matriz.getLadoMatriz(); i++)
            {
                for (int j = 0; j < matriz.getLadoMatriz(); j++)
                    out.format("%s,", matriz.getEntradasMatriz()[i][j].getText().equals("") ? " " : matriz.getEntradasMatriz()[i][j].getText());

                out.format("%s", System.getProperty("line.separator"));

            }

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public MatrixDesign readFile()
    {

        MatrixDesign matriz = null;
        int i = 0, j = 0;

        try
        {

            Scanner in = new Scanner(file);
            String fila = in.nextLine();
            StringTokenizer tokens = new StringTokenizer(fila, ",");
            matriz = new MatrixDesign(tokens.countTokens());

            while (tokens.hasMoreTokens())
            {
                String temp = tokens.nextToken();
                matriz.getEntradasMatriz()[0][j++].setText(temp.equals(" ") ? "" : temp);

            }

            while (in.hasNext())
            {

                i++;
                j = 0;

                fila = in.nextLine();
                tokens = new StringTokenizer(fila, ",");

                while (tokens.hasMoreTokens())
                {
                    String temp = tokens.nextToken();
                    matriz.getEntradasMatriz()[i][j++].setText(temp.equals(" ") ? "" : temp);
                }

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return matriz;

    }

    public int getLadoMatriz()
    {

        try
        {
            return new Scanner(file).nextLine().split(",").length;

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return 3;

    }

}
