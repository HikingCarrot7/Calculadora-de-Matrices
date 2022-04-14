package com.cherrysoft.main;

import com.cherrysoft.controllers.AppController;
import com.cherrysoft.core.MatrixValidator;
import com.cherrysoft.services.imp.MatrixServiceImp;
import com.cherrysoft.views.imp.HomeViewImp;

import java.awt.*;

public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HomeViewImp view = new HomeViewImp();
      view.setVisible(true);
      view.setLocationRelativeTo(null);
      new AppController(view, new MatrixServiceImp(new MatrixValidator()));
    });
  }
}
