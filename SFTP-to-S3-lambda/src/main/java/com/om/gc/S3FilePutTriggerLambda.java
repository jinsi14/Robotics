package com.om.gc;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.secretsmanager.model.*;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.om.gc.config.ConfigSecrets;
import com.om.gc.service.impl.EmailSend;
import com.om.gc.service.impl.S3ServiceImpl;
import com.om.gc.service.impl.SFTPServiceImpl;
import com.om.gc.service.impl.STSServiceImpl;

import java.util.List;

public class S3FilePutTriggerLambda implements RequestHandler<S3Event, String> {

    private STSServiceImpl stsService = new STSServiceImpl();

    private SFTPServiceImpl sftpService = new SFTPServiceImpl();

    private S3ServiceImpl s3Service = new S3ServiceImpl();

    @Override
    public String handleRequest(S3Event event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Received event: " + event);
        try {

            /*Getting the STS Token for having the config secrets*/
            AWSSecurityTokenService awsSecurityTokenService = AWSSecurityTokenServiceClientBuilder.standard().withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(
//                                    AWSConstants.AWS_STS_SERVICE_END_POINT,
                                    System.getenv("AWS_STS_SERVICE_END_POINT"),
                                    System.getenv("AWS_REGION")))
                    .build();
            BasicSessionCredentials basicSessionCredentials = stsService.getBasicSessionCredentials(awsSecurityTokenService);

            GetSecretValueResult secretValueResult = stsService.getSecretManagerValueResult(basicSessionCredentials,logger);

            ConfigSecrets configSecrets = ConfigSecrets.getConfigSecrets(secretValueResult,logger);

            /*Get the files from SFTP and upload it to S3*/
            List<String> fileNameList = sftpService.getGDMFilesAndUploadToS3(configSecrets,basicSessionCredentials,logger);

            /*Ingest Data to RDS*/
            s3Service.ingestS3FileDataToRDS(fileNameList,basicSessionCredentials,configSecrets.getS3KeyPrefix(),configSecrets.getBucketName(),logger);

            /*Move the processed files to Archive Folder.*/
            s3Service.moveS3FilesToArchiveFolder(fileNameList,basicSessionCredentials,configSecrets.getS3KeyPrefix(),configSecrets.getBucketName(),
                    configSecrets.getS3GDMArchiveFolderName(),logger);
            logger.log(event.toJson());
            EmailSend.emailSendOnLambdaSuccess("");

            return new String("200 OK");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log("Exception while processing GDM files."+e.getMessage());
            throw e;
        }
    }
}
