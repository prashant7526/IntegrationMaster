package com.tu.integrationmaster.input.read.xls;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tu.integrationmaster.common.CommonUtil;
import com.tu.integrationmaster.pojo.app.settings.config.IntegrationConfigPOJO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSToH2Database {
    public static XLSToH2Database INSTANCE = new XLSToH2Database();
    
    /**
     * Reads an XLSX file, extracts the data, and loads it into an in-memory H2 database table.
     * The table name is derived from the XLSX file name provided in the IntegrationConfigPOJO class.
     * The first row of the XLSX file is treated as the header row, and its values are used as column names in the database table.
     * Each subsequent row is inserted as a new row in the database table.
     * The XLSX file is assumed to have one sheet, specified by the sheetIndex parameter (0-based).
     * The XLSX file path and other configuration details are fetched from the IntegrationConfigPOJO class.
     *
     */
    public void loadXLSXFile() {
        String excelFilePath = IntegrationConfigPOJO.INSTANCE.getINPUT_FOLDER().concat("\\")
                .concat(IntegrationConfigPOJO.INSTANCE.getXLSX_FILE_NAME());
        String tableName = IntegrationConfigPOJO.INSTANCE.getXLSX_FILE_NAME().replace(".xlsx", "");
        
        int sheetIndex = 0; // Specify the sheet index here (0-based)
        
        try (Connection connection = CommonUtil.commonUtil.getDBConnection();
         FileInputStream fileInputStream = new FileInputStream(excelFilePath);
         Workbook workbook = new XSSFWorkbook(fileInputStream);) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();
            
            String[] headers = new String[columnCount];
            Iterator<Cell> cellIterator = headerRow.cellIterator();
            int columnIndex = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                    headers[columnIndex++] = "`"+cell.getStringCellValue()+"`";
                
            }
            
            createTable(connection, tableName, headers);
            
            int totalRows = sheet.getLastRowNum();
            
            for (int i = 1; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                String[] values = new String[columnCount];
                if (row != null) {
                    for (int j = 0; j < columnCount; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    values[j] = cell.getStringCellValue();
                                    break;
                                case NUMERIC:
                                    values[j] = String.valueOf(cell.getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    values[j] = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                default:
                                    values[j] = "";
                            }
                        } else {
                            values[j] = "";
                        }
                    }
                }
                insertRow(connection, tableName, headers, values);
                
                // Update the progress indicator
                int progress = (int) ((double) i / totalRows * 100);
                System.out.print("\rProgress: " + progress + "%");
            }
            System.out.println();
            System.out.println("Excel data imported into H2 database table: " + tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a table in the database with the specified table name and column names derived from the provided headers array.
     * Each header is treated as a column name, and all columns are defined as VARCHAR(255) data type.
     *
     * @param connection  The database connection.
     * @param tableName   The name of the table to be created.
     * @param headers     An array of column names derived from the XLSX file header row.
     * @throws SQLException  If a database access error occurs.
     */
    private void createTable(Connection connection, String tableName, String[] headers) throws SQLException {
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE ")
                .append(tableName)
                .append("(");
        
        for (String header : headers) {
            createTableQuery.append(header).append(" VARCHAR(255),");
        }
        
        // Remove the last comma
        createTableQuery.setLength(createTableQuery.length() - 1);
        createTableQuery.append(")");
        
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery.toString())) {
            statement.executeUpdate();
        }
    }
    
    /**
     * Inserts a row of data into the specified table in the database.
     * The column names are derived from the provided headers array,
     * and the corresponding values are derived from the provided values array.
     * The values are inserted into the respective columns in the database table.
     *
     * @param connection  The database connection.
     * @param tableName   The name of the table to insert the row into.
     * @param headers     An array of column names derived from the XLSX file header row.
     * @param values      An array of values corresponding to each column in the row.
     * @throws SQLException  If a database access error occurs.
     */
    private void insertRow(Connection connection, String tableName, String[] headers, String[] values)
            throws SQLException {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" (");
        
        for (String header : headers) {
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
}
