package com.tu.integrationmaster.prerequisites.data;

import java.io.File;

import com.tu.integrationmaster.pojo.app.settings.config.IntegrationConfigPOJO;
import com.tu.integrationmaster.prerequisites.log.LogFileManager;
import com.tu.integrationmaster.resources.content.ContentProperties;

/**
 *
 * @author Canavans
 */
public class DataFoldersCheck {
    
    public static final DataFoldersCheck INSTANCE = new DataFoldersCheck();
    
    /**
     * This function checks all the data folders for existence and creates them if they don't exist.
     */
    public void doCheckDataFolders(){
        this.checkInputFolder();
        this.checkOutputFolder();
        this.checkMappingFolder();
        this.checkCertificatesFolder();
        this.checkLogFolder();
        this.checkArchiveFolder();
    }
    
    /**
     * Checks the input folder and creates it if it doesn't exist. 
     *
     * This code snippet checks if the input folder exists, and if it doesn't, it creates it. 
     * It also logs the creation or existence of the folder using a system log manager.
     * If there is a security exception during folder creation, it logs the exception message.
     */
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
    
    /**
     * Checks the output folder and creates it if it doesn't exist. 
     * 
     * This code snippet defines a method checkOutputFolder that checks for the existence of a specified output folder.
     * If the folder does not exist, it creates the folder and logs the action.
     * If the folder already exists, it logs a message indicating its existence.
     * If there is a security exception during the folder creation, it logs the exception message. 
     */
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

    /**
     * Checks the mapping folder and creates it if it doesn't exist. 
     * 
     * This code snippet checks if a mapping folder exists, and if it doesn't,
     * it creates the folder. It also logs the process and any exceptions that occur.
     */
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

    /**
     * Checks the certificates folder and creates it if it does not exist. 
     * 
     * This code snippet checks for the existence of a certificates folder and creates it if it does not exist.
     * It uses a File object to represent the folder path, and if the folder does not exist, it creates it and logs the action.
     * If it encounters a security exception, it logs the exception message.
     */
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

    /**
     * Checks if the log folder exists, and creates it if it doesn't. 
     * 
     * This code checks if the log folder exists, and creates it if it doesn't.
     * It uses the IntegrationConfigPOJO class to get the path of the log folder,
     * and then checks if the folder exists. If it doesn't exist, it creates the folder and logs the action.
     * If there's a security exception during the folder creation, it logs the exception message.
     */
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

    /**
     * Checks the archive folder and creates it if it doesn't exist.
     * 
     * This code snippet defines a method checkArchiveFolder that checks
     * for the existence of an archive folder and creates it if it doesn't exist.
     * It uses the IntegrationConfigPOJO class to get the path of the archive folder,
     * creates the folder if it doesn't exist, and logs the outcome.
     */
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
