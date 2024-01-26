package com.tu.integrationmaster;

import com.tu.integrationmaster.pojo.app.env.config.APPConfigPOJO;
import com.tu.integrationmaster.prerequisites.data.DataFoldersCheck;
import com.tu.integrationmaster.prerequisites.log.LogFileManager;
import com.tu.integrationmaster.prerequisites.systm.SystemFolderCheck;
import com.tu.integrationmaster.resources.content.ContentProperties;

/**
 *
 * @author Canavans
 */
public class IntegrationMaster {
    
    public static void main(String[] args) {
        APPConfigPOJO appConfigPOJO = new APPConfigPOJO();
        
        SystemFolderCheck.INSTANCE.doCheckIfFolders(appConfigPOJO);
        LogFileManager.INSTANCE.systemLogManager(ContentProperties.WELCOME_MGS);
        LogFileManager.INSTANCE.systemLogManager(ContentProperties.EXECUTION_START_LOG_MSG);
        DataFoldersCheck.INSTANCE.doCheckDataFolders();
        
    }
}
