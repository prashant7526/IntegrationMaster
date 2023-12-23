package com.tu.integrationmaster.prerequisites.log;

import com.tu.integrationmaster.pojo.app.env.config.APPConfigPOJO;
import com.tu.integrationmaster.resources.content.ContentProperties;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFileManager {

    public final static LogFileManager INSTANCE = new LogFileManager();
    private final APPConfigPOJO appConfigPOJO = new APPConfigPOJO();

    private final String LOG_FOLDER = appConfigPOJO.getDEFAULT_APP_LOG_DIRECTORY();
    private final String LOG_FILE_NAME = ContentProperties.LOG_FILE_NAME;

    public void systemLogManager(String logMessage) {
        this.checkLogFileExists();
        this.appendToLogFile(logMessage);
    }

    private void checkLogFileExists() {
        File logFolder = new File(LOG_FOLDER);
        if (!logFolder.exists()) {
            logFolder.mkdirs();
        }

        File logFile = new File(LOG_FOLDER, LOG_FILE_NAME);
        try {
            if (logFile.createNewFile()) {
                System.out.println(ContentProperties.SYSTEM_LOG_FILE_CREATED_MSG + logFile.getAbsolutePath());
            } else {
                System.out.println(ContentProperties.SYSTEM_LOG_FILE_EXISTS_MSG + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToLogFile(String logMessage) {
        File logFile = new File(LOG_FOLDER, LOG_FILE_NAME);
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(logMessage + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
