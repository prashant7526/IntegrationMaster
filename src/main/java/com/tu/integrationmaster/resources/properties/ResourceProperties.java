package com.tu.integrationmaster.resources.properties;

import com.tu.integrationmaster.resources.content.ContentProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Canavans
 */
public class ResourceProperties {

    public Properties defaultAppConfigBundle() {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = ResourceProperties.class.getClassLoader().getResourceAsStream(ContentProperties.APP_DEFAULT_PROPRTIES_PATH);
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

    public Properties integrationConfigBundle() {
        Properties properties = new Properties();
        FileInputStream input = null;
        String LOGFILEPATH = System.getProperty("user.home")+ File.separator + ".tu" + File.separator + ".conf" + File.separator + "/IntegrationConf.properties";
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
