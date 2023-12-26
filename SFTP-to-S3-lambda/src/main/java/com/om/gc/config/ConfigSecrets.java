package com.om.gc.config;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.om.gc.service.impl.EmailSend;

import java.util.Base64;

public class ConfigSecrets {

    private int  sftpPort = 22;
    private String  bucketName;
    private String  sftpHost;
    private String  sftpUser;
    private String  sftpPassword;
    private String s3KeyPrefix;
    private String s3GDMArchiveFolderName;

    public int getSftpPort() {
        return sftpPort;
    }

    public void setSftpPort(int sftpPort) {
        this.sftpPort = sftpPort;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getSftpHost() {
        return sftpHost;
    }

    public void setSftpHost(String sftpHost) {
        this.sftpHost = sftpHost;
    }

    public String getSftpUser() {
        return sftpUser;
    }

    public void setSftpUser(String sftpUser) {
        this.sftpUser = sftpUser;
    }

    public String getSftpPassword() {
        return sftpPassword;
    }

    public void setSftpPassword(String sftpPassword) {
        this.sftpPassword = sftpPassword;
    }

    public String getS3KeyPrefix() {
        return s3KeyPrefix;
    }

    public void setS3KeyPrefix(String s3KeyPrefix) {
        this.s3KeyPrefix = s3KeyPrefix;
    }

    public String getS3GDMArchiveFolderName() {
        return s3GDMArchiveFolderName;
    }

    public void setS3GDMArchiveFolderName(String s3GDMArchiveFolderName) {
        this.s3GDMArchiveFolderName = s3GDMArchiveFolderName;
    }

    public static ConfigSecrets getConfigSecrets(GetSecretValueResult getSecretValueResult, LambdaLogger logger)  {
        String decodedBinarySecret= null;
        String secret=null;
        ObjectMapper objectMapper  =  new  ObjectMapper();
        ConfigSecrets secrets = new ConfigSecrets();
        JsonNode secretsJson  =  null;
        try {
            if (getSecretValueResult.getSecretString() != null) {
                secret = getSecretValueResult.getSecretString();
                secretsJson = objectMapper.readTree(secret);
            } else {
                decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
                secretsJson = objectMapper.readTree(decodedBinarySecret);
            }
            secrets.setBucketName(secretsJson.get("bucket_name").textValue());
            secrets.setSftpHost(secretsJson.get("sftp_host").textValue());
            secrets.setSftpPort(secretsJson.get("sftp_port").asInt());
            secrets.setSftpUser(secretsJson.get("sftp_user").textValue());
            secrets.setSftpPassword(secretsJson.get("sftp_password").textValue());
            secrets.setS3KeyPrefix(secretsJson.get("s3_key_prefix").textValue());
            secrets.setS3GDMArchiveFolderName(secretsJson.get("s3_gdm_archive_folder").textValue());

            SQLServerConfig sqlServerConfig = SQLServerConfig.getInstance();
            sqlServerConfig.setHost(secretsJson.get("rds_db_host").textValue());
            sqlServerConfig.setPort(secretsJson.get("rds_db_port").textValue());
            sqlServerConfig.setDatabase(secretsJson.get("rds_db_name").textValue());
            sqlServerConfig.setUsername(secretsJson.get("rds_db_username").textValue());
            sqlServerConfig.setPassword(secretsJson.get("rds_db_password").textValue());
        }
        catch (Exception exception){
            logger.log("Exception generated while getting secrets::"+exception.getMessage());
            EmailSend.emailSendOnLambdafailure("Exception generated while getting secrets::"+exception.getMessage());
        }
        return secrets;
    }
}
