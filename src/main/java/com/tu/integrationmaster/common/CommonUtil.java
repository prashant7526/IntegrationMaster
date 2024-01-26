package com.tu.integrationmaster.common;

import java.sql.Connection;
import java.sql.DriverManager;

import com.tu.integrationmaster.prerequisites.log.LogFileManager;
import com.tu.integrationmaster.resources.content.ContentProperties;

public class CommonUtil {
    public static final CommonUtil commonUtil = new CommonUtil();

    public Connection getDBConnection() throws ClassNotFoundException{
        
        Class.forName(ContentProperties.H2_DB_JDBC_CLASS_FORNAME);
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(ContentProperties.H2_DB_JDBC_URL, ContentProperties.H2_DB_JDBC_USER_NAME, ContentProperties.H2_DB_JDBC_PASSWORD);
        }
        catch (Exception e) {
             LogFileManager.INSTANCE.systemLogManager(ContentProperties.H2_DB_CONNECTION_CREATION_EXCEPTION_MSG.concat(e.getMessage()));
        }
        return conn;
    }
}
