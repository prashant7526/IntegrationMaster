package com.tu.integrationmaster.input.read.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.tu.integrationmaster.common.CommonUtil;
import com.tu.integrationmaster.pojo.app.settings.config.IntegrationConfigPOJO;
import com.tu.integrationmaster.prerequisites.log.LogFileManager;

public class CSVToH2Database {

    public static final CSVToH2Database INSTANCE = new CSVToH2Database();

    /**
     * Loads a CSV file into a database table.
     *
     * @param None
     * @return None
     */
    public void loadCSVFile() {

        String csvFilePath = IntegrationConfigPOJO.INSTANCE.getINPUT_FOLDER().concat("\\")
                .concat(IntegrationConfigPOJO.INSTANCE.getCSV_FILE_NAME());
        String tableName = IntegrationConfigPOJO.INSTANCE.getCSV_FILE_NAME().replace(".csv", "");

        try {
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
        } catch (Exception e) {
            LogFileManager.INSTANCE.systemLogManager(e.getMessage());
        }
    }

    /**
     * A method to create a table in the database using the provided connection,
     * table name, and headers.
     *
     * @param connection the connection to the database
     * @param tableName  the name of the table to be created
     * @param headers    an array of column headers for the table
     * @throws SQLException if a database access error occurs
     */
    private void createTable(Connection connection, String tableName, String[] headers) throws SQLException {
        try {
            String createTableQuery = "CREATE TABLE " + tableName + "(" +
                    Arrays.stream(headers)
                            .map(header -> header.replace(" ", "").trim() + " VARCHAR(255)")
                            .collect(Collectors.joining(","))
                    + ")";
            try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
                statement.executeUpdate();
            }
        } catch (Exception e) {
            LogFileManager.INSTANCE.systemLogManager(e.getMessage());
        }
    }

    /**
     * Insert a new row into the specified table with the given headers and values.
     *
     * @param connection the connection to the database
     * @param tableName  the name of the table to insert into
     * @param headers    the headers of the table
     * @param values     the values to insert into the table
     * @return nothing
     */
    private void insertRow(Connection connection, String tableName, String[] headers, String[] values)
            throws SQLException {
        try {
            StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(" (");
            String headerList = String.join(",", headers)
                    .replaceAll(" ", "")
                    .trim();
            insertQuery.append(headerList).append(") VALUES ('")
                    .append(String.join("','", values))
                    .append("')");
            try (PreparedStatement statement = connection.prepareStatement(insertQuery.toString())) {
                statement.executeUpdate();
            }
        } catch (Exception e) {
            LogFileManager.INSTANCE.systemLogManager(e.getMessage());
        }
    }
}
