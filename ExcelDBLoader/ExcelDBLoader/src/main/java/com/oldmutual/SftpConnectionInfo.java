package com.oldmutual;

import java.util.Properties;

/**
 * Hold all the necessary information for the SFTP connection
 */
public class SftpConnectionInfo {
    public static final int DEFAULT_PORT = 22;
    public static final int DEFAULT_TIMEOUT = 5000;
    private String username;
    private String password;
    private String hostname;
    private int port;
    private int timeout;
    private String downloadFrom;

    /**
     * Initialize from the given Properties, containing the following keys
     *
     * <ul>
     *   <li>sftp.username</li>
     *   <li>sftp.password</li>
     *   <li>sftp.folder</li>
     *   <li>sftp.hostname</li>
     *   <li>sftp.port</li>
     *   <li>sftp.timeout</li>
     * </ul>
     *
     * @param properties the given properties
     */
    public SftpConnectionInfo(Properties properties) {
        this(properties.getProperty("sftp.username"),
                properties.getProperty("sftp.password"),
                properties.getProperty("sftp.folder", ""), properties.getProperty("sftp.hostname"),
                Integer.valueOf(properties.getProperty("sftp.port", String.valueOf(DEFAULT_PORT))),
                Integer.valueOf(properties.getProperty("sftp.timeout", String.valueOf(DEFAULT_TIMEOUT))));
    }

    public SftpConnectionInfo(String username, String password, String downloadFrom, String hostname, int port, int timeout) {
        this.username = username;
        this.password = password;
        this.hostname = hostname;
        this.downloadFrom = downloadFrom;
        this.port = port;
        this.timeout = timeout;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getDownloadFrom() {
        return downloadFrom;
    }
}
