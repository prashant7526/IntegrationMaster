package com.tu.integrationmaster.prerequisites.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.tu.integrationmaster.pojo.app.env.config.APPConfigPOJO;
import com.tu.integrationmaster.resources.content.ContentProperties;

public class LogFileManager {

  public final static LogFileManager INSTANCE = new LogFileManager();
  private final APPConfigPOJO appConfigPOJO = new APPConfigPOJO();

  private final String LOG_FOLDER = appConfigPOJO.getDEFAULT_APP_LOG_DIRECTORY();
  private final String LOG_FILE_NAME = ContentProperties.LOG_FILE_NAME;

  /**
   * Manages system log by checking if log file exists and appending the log
   * message.
   *
   * This Java code defines a method systemLogManager that manages system logs by
   * first checking if the log file exists and then appending the log message to
   * the file
   * 
   * @param logMessage the message to be appended to the log file
   */
  public void systemLogManager(String logMessage) {
    this.checkLogFileExists();
    this.appendToLogFile(logMessage);
  }

  /**
   * Checks if the log file exists, creates the log folder if it doesn't exist,
   * and creates the log file if it doesn't exist. Prints messages to the
   * system output based on the result.
   */
  private void checkLogFileExists() {
    File logFolder = new File(LOG_FOLDER);
    if (!logFolder.exists()) {
      logFolder.mkdirs();
    }

    File logFile = new File(LOG_FOLDER, LOG_FILE_NAME);
    try {
      if (logFile.createNewFile()) {
        System.out.println(ContentProperties.SYSTEM_LOG_FILE_CREATED_MSG + logFile.getAbsolutePath());
      } else {
        System.out.println(ContentProperties.SYSTEM_LOG_FILE_EXISTS_MSG + logFile.getAbsolutePath());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Appends the given log message to the log file.
   *
   * This code snippet defines a method appendToLogFile that appends a given log
   * message to a log file.
   * It first creates a File object representing the log file, then uses a
   * FileWriter to append the log message to the file.
   * If an IOException occurs, it prints the stack trace.
   * 
   * @param logMessage the message to append to the log file
   */
  public void appendToLogFile(String logMessage) {
    File logFile = new File(LOG_FOLDER, LOG_FILE_NAME);
    try (FileWriter writer = new FileWriter(logFile, true)) {
      writer.write(logMessage + "\n");
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
