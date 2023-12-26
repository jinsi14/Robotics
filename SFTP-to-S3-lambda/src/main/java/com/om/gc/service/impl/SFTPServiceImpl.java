package com.om.gc.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.jcraft.jsch.*;
import com.om.gc.config.ConfigSecrets;
import com.om.gc.util.AWSConstants;
import org.joda.time.DateTime;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SFTPServiceImpl {

    public List<String> getGDMFilesAndUploadToS3(ConfigSecrets configSecrets, BasicSessionCredentials basicSessionCredentials
            , LambdaLogger logger){

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        List<String> fileNameList = new ArrayList<>();
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(configSecrets.getSftpUser(), configSecrets.getSftpHost(), configSecrets.getSftpPort());
            session.setPassword(configSecrets.getSftpPassword());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no"); //TODO: Change here...
            session.setConfig(config);
            session.connect();
            logger.log("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            logger.log("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
            long since = new DateTime().minusDays(1).getMillis() / 1000;
            for (Object obj : channelSftp.ls("/")) {
                if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                    if (entry.getAttrs().getMTime() >= since) {
                        if (AWSConstants.MATCH_BI_CF_WIZARD.matcher(entry.getFilename()).matches() ||
                                AWSConstants.MATCH_BI_CF_USER.matcher(entry.getFilename()).matches()) {
                            fileNameList.add(entry.getFilename());
                        }
                    }
                }
            }
            logger.log("SFTP File reading completed.");

            /*Uploading the SFTP Files to S3 Bucket*/
            try {
                AmazonS3 s3client = AmazonS3ClientBuilder
                        .standard()
                        .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                        .withRegion(Regions.fromName(System.getenv("AWS_REGION")))
                        .build();

                for (String fileName : fileNameList) {
                    InputStream is = channelSftp.get(fileName);
                    ObjectMetadata meta = new ObjectMetadata();
                    String key = configSecrets.getS3KeyPrefix() + fileName;
                    byte[] bytes = IOUtils.toByteArray(is);
                    meta.setContentLength(bytes.length);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    PutObjectRequest putObjectRequest = new PutObjectRequest(configSecrets.getBucketName(), key, byteArrayInputStream, meta);
                    s3client.putObject(putObjectRequest);
                    is.close();
                    logger.log("File "+ fileName + " is uploaded.");
                }
            }
            catch(SftpException sftpException)
            {
                logger.log("Exception while uploading the file to S3 "+sftpException.getMessage());
                EmailSend.emailSendOnLambdafailure("Exception while uploading the file to S3 "+sftpException.getMessage());
                sftpException.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log("Exception Generated");
            EmailSend.emailSendOnLambdafailure("Exception Generated");
        } finally {
            channelSftp.exit();
            logger.log("sftp Channel exited.");
            channel.disconnect();
            logger.log("Channel disconnected.");
            session.disconnect();
            logger.log("Host Session disconnected.");
        }
        return fileNameList;
    }
}
