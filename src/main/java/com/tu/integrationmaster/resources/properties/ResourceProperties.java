package com.tu.integrationmaster.resources.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.tu.integrationmaster.resources.content.ContentProperties;

/**
 * @author Canavans
 */
public class ResourceProperties {

  /**
   * A method to retrieve the default application configuration bundle.
   *
   * This code defines a method defaultAppConfigBundle that retrieves the default
   * application configuration bundle as a
   * Properties object and loads it from a specified resource file. If there is an
   * error loading the file, it will print the stack trace.
   * 
   * @return the default application configuration bundle
   */
  public Properties defaultAppConfigBundle() {
    Properties properties = new Properties();
    InputStream input = null;
    try {
      input = ResourceProperties.class.getClassLoader()
          .getResourceAsStream(ContentProperties.APP_DEFAULT_PROPRTIES_PATH);
      properties.load(input);

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return properties;
  }

  /**
   * This function creates a properties object and loads data from a file.
   *
   * This code defines a function that creates a properties object and loads data
   * from a file called "IntegrationConf.properties" located in a specific
   * directory.
   * The properties object contains integration configuration data.
   * If the file is not found or an error occurs during reading, the function
   * prints the stack trace. Finally, it returns the properties object.
   * 
   * @return properties object containing integration configuration data
   */
  public Properties integrationConfigBundle() {
    Properties properties = new Properties();
    FileInputStream input = null;
    String LOGFILEPATH = System.getProperty("user.home") + File.separator + ".tu" + File.separator + ".conf"
        + File.separator + "/IntegrationConf.properties";
    try {
      input = new FileInputStream(LOGFILEPATH);
      properties.load(input);

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return properties;
  }
}
