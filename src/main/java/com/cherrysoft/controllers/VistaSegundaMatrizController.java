package com.cherrysoft.controllers;

import com.cherrysoft.persistence.DAO;
import com.cherrysoft.views.MatrixPanelsRenderer;
import com.cherrysoft.views.imp.SecondaryMatrixViewImp;

public class VistaSegundaMatrizController {
  private final int INDEX_MATRIZ_SECUNDARIA = 0;

  private SecondaryMatrixViewImp secondaryMatrixViewImp;
  private DataManager dataManager;
  private DAO daoMatrizSecundaria;

  public VistaSegundaMatrizController(final SecondaryMatrixViewImp secondaryMatrixViewImp, final int ordenMatriz) {
  }

}
