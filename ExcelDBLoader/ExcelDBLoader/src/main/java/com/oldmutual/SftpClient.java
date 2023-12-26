package com.oldmutual;

import com.jcraft.jsch.*;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * A wrapper for fetching files from SFTP
 */
public class SftpClient {
    private ChannelSftp sftp;
    private Session session;
    private String downloadFrom;

    /**
     * Initialize using the supplied {@link JSch} client SftpConnectionInfo
     *
     * @param jsch the JSch client
     * @param connectionInfo the connection info bean
     *
     * @throws JSchException
     */
    public SftpClient(JSch jsch, SftpConnectionInfo connectionInfo) throws JSchException {
        this.session = jsch.getSession(connectionInfo.getUsername(), connectionInfo.getHostname(), connectionInfo.getPort());
        jsch.setKnownHosts(new File(System.getProperty("user.home"), ".ssh/known_hosts").getAbsolutePath());
        this.downloadFrom = connectionInfo.getDownloadFrom();
        this.session.setUserInfo(new PasswordBasedAuthentication(connectionInfo.getPassword()));
        this.session.connect(connectionInfo.getTimeout());
        this.sftp = (ChannelSftp) session.openChannel("sftp");
        this.sftp.connect(connectionInfo.getTimeout());
    }

    /**
     * Disconnect the sftp connection and session
     */
    public void close() {
        this.sftp.disconnect();
        this.session.disconnect();
    }

    /**
     * Get all the file names newer than days ago
     *
     * @param days the number of days
     *
     * @return a set of file names
     * @throws SftpException
     */
    public Set<String> getFilesNewerThan(int days) throws SftpException {
        return getFilesNewerThan(days, Pattern.compile(".*"));
    }

    /**
     * Get all the file names newer than days ago that match the given pattern
     *
     * @param days the number of days
     * @param pattern the pattern to match
     *
     * @return a set of file names
     * @throws SftpException
     */
    public Set<String> getFilesNewerThan(int days, Pattern pattern) throws SftpException {
        long since = new DateTime().minusDays(days).getMillis() / 1000;

        Set<String> files = new HashSet<String>();
        for (Object obj : sftp.ls(downloadFrom)) {
            if (obj instanceof ChannelSftp.LsEntry) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                if (entry.getAttrs().getMTime() >= since) {
                    if (pattern.matcher(entry.getFilename()).matches()) {
                        files.add(entry.getFilename());
                    }
                }
            }
        }
        return files;
    }

    /**
     * Create a path from the given filename and the folder we are downloading from
     *
     * @param filename the filename
     * @return the full path on the remote server
     */
    String pathForFilename(String filename) {
        if (downloadFrom == null || downloadFrom.length() == 0) {
            return filename;
        } else {
            if (downloadFrom.endsWith("/")) {
                return downloadFrom + filename;
            } else {
                return downloadFrom + "/" + filename;
            }
        }
    }

    /**
     * Download the given file
     *
     * @param filename a filename (relative to the downloadFrom folder)
     * @return a temporary file storing the downloaded contents
     *
     * @throws SftpException
     * @throws IOException
     */
    public File downloadFile(String filename) throws SftpException, IOException {
        File tempFile = File.createTempFile("sftp", ".download");
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        this.sftp.get(pathForFilename(filename), fileOutputStream);
        fileOutputStream.flush();
        return tempFile;
    }
}
