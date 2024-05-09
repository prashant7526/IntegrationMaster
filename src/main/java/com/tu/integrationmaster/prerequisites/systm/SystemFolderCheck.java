package com.tu.integrationmaster.prerequisites.systm;

import java.io.File;

import com.tu.integrationmaster.pojo.app.env.config.APPConfigPOJO;
import com.tu.integrationmaster.resources.content.ContentProperties;

/**
 * @author Canavans
 */
public class SystemFolderCheck {

  public static final SystemFolderCheck INSTANCE = new SystemFolderCheck();

  /**
   * This method checks if folders exist.
   *
   * This Java code defines a method doCheckIfFolders that takes an APPConfigPOJO
   * object as input.
   * It calls two other methods sysFolderCheck and customConfFolderCheck to check
   * if certain folders
   * 
   * @param appConfigPOJO the app configuration POJO
   * @return void
   */
  public void doCheckIfFolders(APPConfigPOJO appConfigPOJO) {
    this.sysFolderCheck(appConfigPOJO);
    this.customConfFolderCheck(appConfigPOJO);
  }

  /**
   * Check system folders and create them if necessary.
   * 
   * This code snippet defines a method sysFolderCheck that takes an APPConfigPOJO
   * object as a parameter.
   * It checks for the existence of system folders specified in the appConfigPOJO
   * and creates them if they don't exist.
   * If the folder creation fails, it prints an error message. If any exception
   * occurs during the process, it prints the stack trace.
   *
   * @param appConfigPOJO the application configuration POJO
   * @return void
   */
  private void sysFolderCheck(APPConfigPOJO appConfigPOJO) {
    try {

      String tuFolderPath = appConfigPOJO.getDEFAULT_APP_DIRECTORY();
      String syslogFolderPath = appConfigPOJO.getDEFAULT_APP_LOG_DIRECTORY();
      File syslogFolder = new File(syslogFolderPath);

      if (syslogFolder.exists()) {
        System.out.println(ContentProperties.SYS_FOLDER_EXISTS_MSG);
      } else {
        File tuFolder = new File(tuFolderPath);
        if (!tuFolder.exists()) {
          if (tuFolder.mkdir()) {
            System.out.println(ContentProperties.APP_FOLDER_CREATED_MSG);
          } else {
            System.out.println(ContentProperties.APP_FOLDER_CREATION_FAILED_MSG);
            return;
          }
        }
        if (syslogFolder.mkdir()) {
          System.out.println(ContentProperties.APP_LOG_FOLDER_CREATED_MSG);
        } else {
          System.out.println(ContentProperties.APP_FOLDER_CREATION_FAILED_MSG);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Checks for the existence of the custom configuration folder and creates it if
   * it does not exist.
   *
   * This Java code snippet checks for the existence of a custom configuration
   * folder and creates it if it does not exist.
   * It uses the application configuration POJO to get the folder paths, then
   * checks if the folder exists and is a directory.
   * If the folder does not exist, it creates it and prints relevant messages.
   * If any exceptions occur during this process, they are caught and printed to
   * the console.
   * 
   * @param appConfigPOJO the application configuration POJO containing folder
   *                      paths
   * @return none
   */
  private void customConfFolderCheck(APPConfigPOJO appConfigPOJO) {
    try {
      String tuFolderPath = appConfigPOJO.getDEFAULT_APP_DIRECTORY();
      String confFolderPath = appConfigPOJO.getDEFAULT_APP_CONFIG_DIRECTORY();

      File tuFolder = new File(tuFolderPath);
      File confFolder = new File(confFolderPath);

      if (confFolder.exists() && confFolder.isDirectory()) {
        System.out.println(ContentProperties.CUSTOM_CONF_FOLDER_EXISTS_MSG);
      } else {
        if (tuFolder.exists() && tuFolder.isDirectory()) {
          // .tu folder exists, create .conf folder inside it
          if (confFolder.mkdir()) {
            System.out.println(ContentProperties.CUSTOM_CONF_FOLDER_CREATION_MSG);
          } else {
            System.out.println(ContentProperties.CUSTOM_CONF_FOLDER_CREATION_FAILED_MSG);
          }
        } else {
          System.out.println(ContentProperties.DEFAULT_APP_DIRECTORY_NOT_EXISTS_MSG);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
