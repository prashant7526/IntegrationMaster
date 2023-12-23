package com.tu.integrationmaster.prerequisites.systm;

import com.tu.integrationmaster.pojo.app.env.config.APPConfigPOJO;
import com.tu.integrationmaster.resources.content.ContentProperties;

import java.io.File;

/**
 * @author Canavans
 */
public class SystemFolderCheck {

    public static final SystemFolderCheck INSTANCE = new SystemFolderCheck();

    public void doCheckIfFolders(APPConfigPOJO appConfigPOJO) {
        this.sysFolderCheck(appConfigPOJO);
        this.customConfFolderCheck(appConfigPOJO);
    }

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
