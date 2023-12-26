package com.om.gc.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    public SQLServerConnection() throws IOException {
    }
    public Connection getConnection() throws SQLException {
        SQLServerConfig sqlServerConfig = SQLServerConfig.getInstance();
        String connectionUrl ="jdbc:sqlserver://"+sqlServerConfig.getHost()+":"+sqlServerConfig.getPort()+";database="
                +sqlServerConfig.getDatabase()+";user="+sqlServerConfig.getUsername()+";password="+sqlServerConfig.getPassword()+";";
        return DriverManager.getConnection(connectionUrl);
    }
}
