package com.oldmutual;

import com.jcraft.jsch.*;
import org.joda.time.DateTime;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class SFTPConnectUtil {
    public static final Pattern MATCH_BI_CF_WIZARD = Pattern.compile("BI_CF_IND_Wizard_Scenarios.*");
    public static final Pattern MATCH_BI_CF_USER = Pattern.compile("BI_CF_User_Base.*");

    public static void main(String[] args) {
        send();
    }

    public static void send () {
        String SFTPHOST = "";
        int SFTPPORT = 22;
        String SFTPUSER = "";
        String SFTPPASS = "";
//        String SFTPWORKINGDIR = "/Home/Users/Customer Engagement/XPort/Xplan/OMXPLAN/Inbound/";
        String SFTPWORKINGDIR = "/Home/Users";
        String SFTPDESTDIR="C:\\Users\\pk\\Desktop\\OM\\Group_Compliance\\";


        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println("preparing the host information for sftp.");

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            java.util.Properties config = new java.util.Properties();
            //TODO: Change here...
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
//            channelSftp.cd("Inbound");
//            Vector lsResult = channelSftp.ls("/Home/Users");
//            File f = new File(fileName);
//            channelSftp.put(new FileInputStream(f), f.getName());

            long since = new DateTime().minusDays(1).getMillis() / 1000;
            //LocalDate date = LocalDate.now().minusDays(2);

            Set<String> files = new HashSet<String>();
            for (Object obj : channelSftp.ls("/")) {
                if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                    if (entry.getAttrs().getMTime() >= since) {
                        if (MATCH_BI_CF_WIZARD.matcher(entry.getFilename()).matches() || MATCH_BI_CF_USER.matcher(entry.getFilename()).matches()) {
//                            channelSftp.get(entry.getFilename(),SFTPDESTDIR);
                            System.out.println(entry.getFilename());
                        }
                    }
                }
            }

            //log.info("File transfered successfully to host.");
            System.out.println("Completed");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception found while tranfer the response.");
        } finally {
            channelSftp.exit();
            System.out.println("sftp Channel exited.");
            channel.disconnect();
            System.out.println("Channel disconnected.");
            session.disconnect();
            System.out.println("Host Session disconnected.");
        }
    }

//    public Set<String> getFilesNewerThan(int days, Pattern pattern) throws SftpException {
//        long since = new DateTime().minusDays(days).getMillis() / 1000;
//
//        Set<String> files = new HashSet<String>();
//        for (Object obj : sftp.ls(downloadFrom)) {
//            if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
//                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
//                if (entry.getAttrs().getMTime() >= since) {
//                    if (pattern.matcher(entry.getFilename()).matches()) {
//                        files.add(entry.getFilename());
//                    }
//                }
//            }
//        }
//        return files;
//    }
}
