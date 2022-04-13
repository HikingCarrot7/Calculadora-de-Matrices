package com.sw.main;

import com.sw.controller.VistaPrincipalController;
import com.sw.view.VistaPrincipal;

import java.awt.*;

/**
 * @author Nicolás
 */
public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      VistaPrincipal view = new VistaPrincipal();
      view.setVisible(true);
      view.setLocationRelativeTo(null);
      new VistaPrincipalController(view);
    });
  }
}
