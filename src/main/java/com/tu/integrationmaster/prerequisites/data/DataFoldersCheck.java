package com.tu.integrationmaster.prerequisites.data;

import com.tu.integrationmaster.pojo.app.settings.config.IntegrationConfigPOJO;
import com.tu.integrationmaster.prerequisites.log.LogFileManager;
import com.tu.integrationmaster.resources.content.ContentProperties;
import java.io.File;

/**
 *
 * @author Canavans
 */
public class DataFoldersCheck {
    
    public static final DataFoldersCheck INSTANCE = new DataFoldersCheck();
    
    public void doCheckDataFolders(){
        this.checkInputFolder();
        this.checkOutputFolder();
        this.checkMappingFolder();
        this.checkCertificatesFolder();
        this.checkLogFolder();
        this.checkArchiveFolder();
    }
    
    private void checkInputFolder(){
        File folder = new File(IntegrationConfigPOJO.INSTANCE.getINPUT_FOLDER());

        try {
            if (!folder.exists()) {
                folder.mkdir();
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.INPUT_FOLDER_CREATED_MSG);
            } else {
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.INPUT_FOLDER_EXISTS_MSG);
            }
        } catch (SecurityException e) {
            LogFileManager.INSTANCE.systemLogManager(ContentProperties.INPUT_FOLDER_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
    }
    
    private void checkOutputFolder(){
        File folder = new File(IntegrationConfigPOJO.INSTANCE.getOUTPUT_FOLDER());

        try {
            if (!folder.exists()) {
                folder.mkdir();
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.OUTPUT_FOLDER_CREATED_MSG);
            } else {
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.OUTPUT_FOLDER_EXISTS_MSG);
            }
        } catch (SecurityException e) {
            LogFileManager.INSTANCE.systemLogManager(ContentProperties.OUTPUT_FOLDER_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
    }
    private void checkMappingFolder(){
        File folder = new File(IntegrationConfigPOJO.INSTANCE.getMAPPING_FOLDER());

        try {
            if (!folder.exists()) {
                folder.mkdir();
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.MAPPING_FOLDER_CREATED_MSG);
            } else {
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.MAPPING_FOLDER_EXISTS_MSG);
            }
        } catch (SecurityException e) {
            LogFileManager.INSTANCE.systemLogManager(ContentProperties.MAPPING_FOLDER_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
    }
    private void checkCertificatesFolder(){
        File folder = new File(IntegrationConfigPOJO.INSTANCE.getCERTIFICATES_FOLDER());

        try {
            if (!folder.exists()) {
                folder.mkdir();
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.CERTIFICATES_FOLDER_CREATED_MSG);
            } else {
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.CERTIFICATES_FOLDER_EXISTS_MSG);
            }
        } catch (SecurityException e) {
            LogFileManager.INSTANCE.systemLogManager(ContentProperties.CERTIFICATES_FOLDER_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
    }
    private void checkLogFolder(){
        File folder = new File(IntegrationConfigPOJO.INSTANCE.getBUSINESS_LOGS_FOLDER());

        try {
            if (!folder.exists()) {
                folder.mkdir();
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.LOGS_FOLDER_CREATED_MSG);
            } else {
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.LOGS_FOLDER_EXISTS_MSG);
            }
        } catch (SecurityException e) {
            LogFileManager.INSTANCE.systemLogManager(ContentProperties.LOGS_FOLDER_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
    }
    private void checkArchiveFolder(){
        File folder = new File(IntegrationConfigPOJO.INSTANCE.getARCHIVES_FOLDER());

        try {
            if (!folder.exists()) {
                folder.mkdir();
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.ARCHIVES_FOLDER_CREATED_MSG);
            } else {
                LogFileManager.INSTANCE.systemLogManager(ContentProperties.ARCHIVES_FOLDER_EXISTS_MSG);
            }
        } catch (SecurityException e) {
            LogFileManager.INSTANCE.systemLogManager(ContentProperties.ARCHIVES_FOLDER_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
    }
}
