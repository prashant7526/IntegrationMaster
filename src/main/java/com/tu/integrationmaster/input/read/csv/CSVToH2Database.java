package com.tu.integrationmaster.input.read.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tu.integrationmaster.common.CommonUtil;
import com.tu.integrationmaster.pojo.app.settings.config.IntegrationConfigPOJO;
import com.tu.integrationmaster.prerequisites.log.LogFileManager;

public class CSVToH2Database{

    public static final CSVToH2Database INSTANCE = new CSVToH2Database();

    public void loadCSVFile(){
      
        String csvFilePath = IntegrationConfigPOJO.INSTANCE.getINPUT_FOLDER().concat("\\").concat(IntegrationConfigPOJO.INSTANCE.getCSV_FILE_NAME());
        String tableName = IntegrationConfigPOJO.INSTANCE.getCSV_FILE_NAME().replace(".csv", "");

        try
        {
            Connection connection = CommonUtil.commonUtil.getDBConnection();
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));

            String line;
            String[] headers = null;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (isFirstLine) {
                    headers = values;
                    createTable(connection, tableName, headers);
                    isFirstLine = false;
                } else {
                    insertRow(connection, tableName, headers, values);
                }
            }
            System.out.println("CSV data imported into H2 database table: " + tableName);
        }
        catch (Exception e) {
            LogFileManager.INSTANCE.systemLogManager(e.getMessage());
        }
    }
      
    private void createTable(Connection connection, String tableName, String[] headers) throws SQLException {
        try{
            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE ")
                    .append(tableName)
                    .append("(");

            for (String header : headers) {
                header = header.replace(" ", "").trim();
                createTableQuery.append(header).append(" VARCHAR(255),");
            }

            // Remove the last comma
            createTableQuery.setLength(createTableQuery.length() - 1);
            createTableQuery.append(")");

            try (PreparedStatement statement = connection.prepareStatement(createTableQuery.toString())) {
                statement.executeUpdate();
            }
        }
        catch (Exception e) {
            LogFileManager.INSTANCE.systemLogManager(e.getMessage());
        }
    }
    
    private void insertRow(Connection connection, String tableName, String[] headers, String[] values) throws SQLException {
        
        try{
            StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(" (");

            for (String header : headers) {
                header = header.replace(" ", "").trim();
                insertQuery.append(header).append(",");
            }

            // Remove the last comma
            insertQuery.setLength(insertQuery.length() - 1);
            insertQuery.append(") VALUES (");

            for (String value : values) {
                insertQuery.append("'").append(value).append("',");
            }

            // Remove the last comma
            insertQuery.setLength(insertQuery.length() - 1);
            insertQuery.append(")");

            try (PreparedStatement statement = connection.prepareStatement(insertQuery.toString())) {
                statement.executeUpdate();
            }
        }
        catch (Exception e) {
            LogFileManager.INSTANCE.systemLogManager(e.getMessage());
        }
    }
}
