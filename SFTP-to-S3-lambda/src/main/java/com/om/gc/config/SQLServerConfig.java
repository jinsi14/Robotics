package com.om.gc.config;

public class SQLServerConfig {

    private String host;
    private String port;
    private String username;
    private String password;
    private String database;

    private static SQLServerConfig instance=null;

    private SQLServerConfig(){
    }

    public static SQLServerConfig getInstance(){
        if(instance == null){
            instance = new SQLServerConfig();
        }
        return instance;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
