package com.cherrysoft.matrixcalculator.persistence.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

  public static boolean createFileIfDoesNotExist(File file) {
    return createFileIfDoesNotExist(file.getPath());
  }

  public static boolean createFileIfDoesNotExist(String path) {
    File file = new File(path);
    if (!file.exists()) {
      try {
        return file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

}
