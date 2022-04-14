package com.cherrysoft.main;

import com.cherrysoft.controllers.HomeController;
import com.cherrysoft.views.imp.HomeViewImp;

import java.awt.*;

/**
 * @author Nicolás
 */
public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HomeViewImp view = new HomeViewImp();
      view.setVisible(true);
      view.setLocationRelativeTo(null);
      new HomeController(view);
    });
  }
}
