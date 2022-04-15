package com.cherrysoft.matrixcalculator.persistence.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

  public static boolean createFileAndParentsIfFileDoesNotExist(File file) {
    return createFileAndParentsIfFileDoesNotExist(file.getPath());
  }

  public static boolean createFileAndParentsIfFileDoesNotExist(String path) {
    File file = new File(path);
    if (!file.exists()) {
      try {
        createParentDir(file);
        return file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public static boolean createParentDir(File file) {
    File parentFile = file.getParentFile();
    return parentFile.mkdirs();
  }
}
