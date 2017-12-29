package com.ettp.ejb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.text.DateFormat;

import java.util.Date;


public class EJBLog {
  public final int SEVERE = 10;
  public final int WARNING = 7;
  public final int INFO = 5;
  public final int FINE = 3;
  public final int FINEST = 0;
  private int CURENT_LEVEL = FINEST;
  private FileOutputStream fos;
  private PrintStream sessionPrintStream = null;

  public EJBLog(String sessionId) {
    try {
      //fos = new FileOutputStream("logs" + File.separator + "robot" + File.separator + sessionId, true);
      fos = new FileOutputStream(sessionId, true);
      sessionPrintStream = new PrintStream(fos, true);
    }
    catch (FileNotFoundException e) {
    }
  }

  public void close() {
    sessionPrintStream.close();

    try {
      fos.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getLevelName(int level) {
    if (level >= CURENT_LEVEL) {
      switch (level) {
      case SEVERE:
        return "SEVERE";

      case INFO:
        return "INFO";

      case FINE:
        return "FINE";

      case FINEST:
        return "FINEST";
      }
    }

    return "INFO";
  }

  private void log(int level, String message) {
    String levelName = getLevelName(level);
    log(levelName + " : EJB : " + message);
  }

  private void log(int level, Exception ex) {
    String levelName = getLevelName(level);
    log(levelName + " : " + ex.getMessage());

    for (int i = 0; i < ex.getStackTrace().length; i++) {
      log("\t" + ex.getStackTrace()[i]);
    }
  }

  private synchronized void log(String string) {
    try {
      Date now = new Date();
      System.out.println(string);

      if (sessionPrintStream != null) {
        sessionPrintStream.println(DateFormat.getDateInstance(DateFormat.SHORT).format(now) + " " +
          DateFormat.getTimeInstance(DateFormat.MEDIUM).format(now) + " " + string);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void info(String message) {
    log(INFO, message);
  }

  public void fine(String message) {
    log(FINE, message);
  }

  public void finest(String message) {
    log(FINEST, message);
  }

  public void severe(String message) {
    log(SEVERE, message);
  }

  public void warning(String message) {
    log(WARNING, message);
  }

  public void info(Exception ex) {
    log(INFO, ex);
  }

  public void fine(Exception ex) {
    log(FINE, ex);
  }

  public void finest(Exception ex) {
    log(FINEST, ex);
  }

  public void severe(Exception ex) {
    log(SEVERE, ex);
  }

  public void warning(Exception ex) {
    log(WARNING, ex);
  }

  public boolean isDebugMode() {
    return (CURENT_LEVEL < INFO);
  }
}
