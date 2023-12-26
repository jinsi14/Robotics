package com.oldmutual.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLServerConnection {

    private final String host;
    private final String port;
    private final String username;
    private final String password;
    private final String database;

    public SQLServerConnection() throws IOException {
        Properties p = new Properties();
        try (InputStream is = new FileInputStream("dataConfig.properties")) {
            p.load(is);
        }
        this.host = p.getProperty("host");
        this.port = p.getProperty("port");
        this.username = p.getProperty("username");
        this.password = p.getProperty("password");
        this.database = p.getProperty("database");
    }

    public Connection getConnection() throws SQLException {
        String connectionUrl ="jdbc:sqlserver://"+host+":"+port+";database="+database+";user="+username+";password="+password+";";
        return DriverManager.getConnection(connectionUrl);
    }
}
