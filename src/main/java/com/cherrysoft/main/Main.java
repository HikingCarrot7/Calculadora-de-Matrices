package com.cherrysoft.main;

import com.cherrysoft.controller.VistaPrincipalController;
import com.cherrysoft.view.VistaPrincipal;

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
