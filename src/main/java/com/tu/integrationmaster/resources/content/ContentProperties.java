package com.tu.integrationmaster.resources.content;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Canavans
 */
public class ContentProperties {
    
    public static final String USER_DIRECTORY = "user.home";
    public static final String DEFAULT_APP_DIRECTORY = ".tu";
    public static final String DEFAULT_APP_LOG_DIRECTORY = "syslog";
    public static final String DEFAULT_APP_CONFIG_DIRECTORY = ".conf";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final String LOG_FILE_NAME = "applog_" + DATE_FORMAT.format(new Date()) + ".log";
    
    public static final String INPUTS_FOLDER = "Inputs";
    public static final String OUTPUTS_FOLDER = "Outputs";
    public static final String MAPPINGS_FOLDER = "Mappings";
    public static final String CERTIFICATES_FOLDER = "Certificates";
    public static final String ARCHIVES_FOLDER = "Archives";
    public static final String BUSINESS_LOGS_FOLDER = "Logs";

    public static final String H2_DB_JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String H2_DB_JDBC_USER_NAME = "sa";
    public static final String H2_DB_JDBC_PASSWORD = "sa";
    public static final String H2_DB_JDBC_CLASS_FORNAME = "org.h2.Driver";

    public static final String APP_DEFAULT_PROPRTIES_PATH = "app/env/config/APPConfig.properties";
    
    public static final String WELCOME_MGS = "Universal Integration Master 1.0";
    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    private static String formattedDate = sdf.format(new Date());
    public static final String EXECUTION_START_LOG_MSG = "Execution Started at " + formattedDate;
    public static final String SYS_FOLDER_EXISTS_MSG = "The 'syslog' folder already exists under the '.tu' folder in the user directory.";
    public static final String APP_FOLDER_CREATED_MSG = "The '.tu' folder has been created in the user directory.";
    public static final String APP_FOLDER_CREATION_FAILED_MSG = "Failed to create the '.tu' folder in the user directory.";
    public static final String APP_LOG_FOLDER_CREATED_MSG = "The 'syslog' folder has been created under the '.tu' folder in the user directory.";
    public static final String APP_LOG_FOLDER_CREATION_FAILED_MSG = "Failed to create the 'syslog' folder under the '.tu' folder in the user directory.";
    public static final String CUSTOM_CONF_FOLDER_EXISTS_MSG = ".conf folder already exists inside .tu folder.";
    public static final String CUSTOM_CONF_FOLDER_CREATION_MSG = ".conf folder created inside .tu folder.";
    public static final String CUSTOM_CONF_FOLDER_CREATION_FAILED_MSG = "Failed to create .conf folder inside .tu folder.";
    public static final String DEFAULT_APP_DIRECTORY_NOT_EXISTS_MSG = ".tu folder does not exist. Please create the .tu folder first.";
    public static final String SYSTEM_LOG_FILE_CREATED_MSG = "Log file created: ";
    public static final String SYSTEM_LOG_FILE_EXISTS_MSG = "Log file already exists: ";
    
    public static final String INPUT_FOLDER_CREATED_MSG = "Inputs Folder created successfully.";
    public static final String INPUT_FOLDER_EXISTS_MSG = "Inputs Folder already exists.";
    public static final String INPUT_FOLDER_CREATION_EXCEPTION_MSG = "Error occurred while creating the Inputs folder: ";
    public static final String OUTPUT_FOLDER_CREATED_MSG = "Outputs Folder created successfully.";
    public static final String OUTPUT_FOLDER_EXISTS_MSG = "Outputs Folder already exists.";
    public static final String OUTPUT_FOLDER_CREATION_EXCEPTION_MSG = "Error occurred while creating the Outputs folder: ";
    public static final String MAPPING_FOLDER_CREATED_MSG = "Mappings Folder created successfully.";
    public static final String MAPPING_FOLDER_EXISTS_MSG = "Mappings Folder already exists.";
    public static final String MAPPING_FOLDER_CREATION_EXCEPTION_MSG = "Error occurred while creating the Mappings folder: ";
    public static final String CERTIFICATES_FOLDER_CREATED_MSG = "Certificates Folder created successfully.";
    public static final String CERTIFICATES_FOLDER_EXISTS_MSG = "Certificates Folder already exists.";
    public static final String CERTIFICATES_FOLDER_CREATION_EXCEPTION_MSG = "Error occurred while creating the Certificates folder: ";
    public static final String LOGS_FOLDER_CREATED_MSG = "Logs Folder created successfully.";
    public static final String LOGS_FOLDER_EXISTS_MSG = "Logs Folder already exists.";
    public static final String LOGS_FOLDER_CREATION_EXCEPTION_MSG = "Error occurred while creating the Logs folder: ";
    public static final String ARCHIVES_FOLDER_CREATED_MSG = "Archives Folder created successfully.";
    public static final String ARCHIVES_FOLDER_EXISTS_MSG = "Archives Folder already exists.";
    public static final String ARCHIVES_FOLDER_CREATION_EXCEPTION_MSG = "Error occurred while creating the Archives folder: ";

    public static final String H2_DB_CONNECTION_CREATION_EXCEPTION_MSG = "Error occurred while creating H2 Database Connection: ";
}
