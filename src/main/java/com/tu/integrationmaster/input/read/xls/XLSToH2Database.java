package com.tu.integrationmaster.input.read.xls;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tu.integrationmaster.pojo.app.settings.config.IntegrationConfigPOJO;

public class XLSToH2Database {
    public static XLSToH2Database INSTANCE = new XLSToH2Database();

    public void loadXLSXFile() {
        String excelFilePath = IntegrationConfigPOJO.INSTANCE.getINPUT_FOLDER().concat("\\")
                .concat(IntegrationConfigPOJO.INSTANCE.getCSV_FILE_NAME());
        String tableName = IntegrationConfigPOJO.INSTANCE.getCSV_FILE_NAME().replace(".csv", "");

        int sheetIndex = 0; // Specify the sheet index here (0-based)

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
                FileInputStream fis = new FileInputStream(excelFilePath);
                Workbook workbook = new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();

            String[] headers = new String[columnCount];
            Iterator<Cell> cellIterator = headerRow.cellIterator();
            int columnIndex = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                headers[columnIndex++] = cell.getStringCellValue();
            }

            createTable(connection, tableName, headers);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
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
            }

            System.out.println("Excel data imported into H2 database table: " + tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
