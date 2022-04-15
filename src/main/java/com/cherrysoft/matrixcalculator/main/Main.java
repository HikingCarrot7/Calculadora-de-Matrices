package com.cherrysoft.matrixcalculator.main;

import com.cherrysoft.matrixcalculator.controllers.AppController;
import com.cherrysoft.matrixcalculator.core.MatrixValidator;
import com.cherrysoft.matrixcalculator.persistence.MatrixRepository;
import com.cherrysoft.matrixcalculator.persistence.imp.MatrixRepositoryImp;
import com.cherrysoft.matrixcalculator.services.MatrixCalculatorService;
import com.cherrysoft.matrixcalculator.services.MatrixServiceFacade;
import com.cherrysoft.matrixcalculator.services.imp.MatrixCalculatorServiceImp;
import com.cherrysoft.matrixcalculator.views.imp.HomeViewImp;

import java.awt.*;

public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      HomeViewImp view = new HomeViewImp();
      view.setVisible(true);
      view.setLocationRelativeTo(null);
      MatrixCalculatorService calculatorService = new MatrixCalculatorServiceImp(new MatrixValidator());
      MatrixRepository matrixRepository = new MatrixRepositoryImp();
      new AppController(view, MatrixServiceFacade.builder()
        .matrixCalculatorService(calculatorService)
        .matrixRepository(matrixRepository)
        .build()
      );
    });
  }
}
