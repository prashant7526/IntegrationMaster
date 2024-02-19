package com.tu.integrationmaster;

import com.tu.integrationmaster.input.read.xls.XLSToH2Database;
import com.tu.integrationmaster.pojo.app.env.config.APPConfigPOJO;
import com.tu.integrationmaster.prerequisites.data.DataFoldersCheck;
import com.tu.integrationmaster.prerequisites.log.LogFileManager;
import com.tu.integrationmaster.prerequisites.systm.SystemFolderCheck;
import com.tu.integrationmaster.resources.content.ContentProperties;

/*
 *
 * @author Canavans
 */
public class IntegrationMaster {
    
    /**
     * The main method of the Java program.
     *
     * This code defines the main method of a Java program. It creates an instance of APPConfigPOJO,
     * performs folder checks, logs welcome and start messages, and checks data folders
     * 
     * @param  args  the command-line arguments
     */
    public static void main(String[] args) {
        APPConfigPOJO appConfigPOJO = new APPConfigPOJO();
        
        SystemFolderCheck.INSTANCE.doCheckIfFolders(appConfigPOJO);
        LogFileManager.INSTANCE.systemLogManager(ContentProperties.WELCOME_MGS);
        LogFileManager.INSTANCE.systemLogManager(ContentProperties.EXECUTION_START_LOG_MSG);
        DataFoldersCheck.INSTANCE.doCheckDataFolders();
        XLSToH2Database.INSTANCE.loadXLSXFile();
    }
}
