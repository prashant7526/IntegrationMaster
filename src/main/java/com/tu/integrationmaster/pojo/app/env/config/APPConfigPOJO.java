package com.tu.integrationmaster.pojo.app.env.config;

import java.io.File;

/**
 * @author Canavans
 */

public class APPConfigPOJO {

  private final String USER_DIRECTORY = System.getProperty("user.home");
  private final String DEFAULT_APP_DIRECTORY = USER_DIRECTORY + File.separator + ".tu";
  private final String DEFAULT_APP_LOG_DIRECTORY = DEFAULT_APP_DIRECTORY + File.separator + "syslog";
  private final String DEFAULT_APP_CONFIG_DIRECTORY = DEFAULT_APP_DIRECTORY + File.separator + ".conf";
  private final String DEFAULT_LOG_FILE_PATH = DEFAULT_APP_CONFIG_DIRECTORY + "/IntegrationConf.properties";

  public String getUSER_DIRECTORY() {
    return USER_DIRECTORY;
  }

  public String getDEFAULT_APP_DIRECTORY() {
    return DEFAULT_APP_DIRECTORY;
  }

  public String getDEFAULT_APP_LOG_DIRECTORY() {
    return DEFAULT_APP_LOG_DIRECTORY;
  }

  public String getDEFAULT_APP_CONFIG_DIRECTORY() {
    return DEFAULT_APP_CONFIG_DIRECTORY;
  }

  public String getDEFAULT_LOG_FILE_PATH() {
    return DEFAULT_LOG_FILE_PATH;
  }
}
