package com.cherrysoft.matrixcalculator.main;

import com.cherrysoft.matrixcalculator.controllers.AppController;
import com.cherrysoft.matrixcalculator.core.MatrixValidator;
import com.cherrysoft.matrixcalculator.services.imp.MatrixServiceImp;
import com.cherrysoft.matrixcalculator.views.imp.HomeViewImp;

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
