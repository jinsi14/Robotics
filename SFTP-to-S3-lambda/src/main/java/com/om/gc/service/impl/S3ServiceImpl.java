package com.om.gc.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.om.gc.util.AWSConstants;

import java.util.List;

public class S3ServiceImpl {

    private ETLBICFWizardScenarioServiceImpl etlbicfWizardScenarioService = new ETLBICFWizardScenarioServiceImpl();

    private ETLBICFUserBaseServiceImpl etlbicfUserBaseService = new ETLBICFUserBaseServiceImpl();

    public void ingestS3FileDataToRDS(List<String> fileNameList, BasicSessionCredentials basicSessionCredentials, String s3KeyPrefix,
                                      String bucketName, LambdaLogger logger){

        try {
            AmazonS3 s3Client = getS3Client(basicSessionCredentials);
            for (String fileName : fileNameList) {
                logger.log("Processing file:: "+fileName);
                if(AWSConstants.MATCH_BI_CF_WIZARD.matcher(fileName).matches()){

                    etlbicfWizardScenarioService.save(s3Client,s3KeyPrefix,bucketName,fileName,logger);

                }else if (AWSConstants.MATCH_BI_CF_USER.matcher(fileName).matches()){

                    etlbicfUserBaseService.save(s3Client,s3KeyPrefix,bucketName,fileName,logger);
                }
                else{
                    logger.log("Unmatched file name found:: "+fileName);
                }
            }
        }
        catch(Exception exception){
            logger.log("Exception while processing S3 files:: "+exception.getMessage());
            EmailSend.emailSendOnLambdafailure("Exception while processing S3 files :: "+exception.getMessage());
        }
    }

    public void moveS3FilesToArchiveFolder(List<String> fileNameList, BasicSessionCredentials basicSessionCredentials, String s3KeyPrefix,
                                      String bucketName,String archiveFolder, LambdaLogger logger){

        try {
            AmazonS3 s3Client = getS3Client(basicSessionCredentials);
            for (String fileName : fileNameList) {
                logger.log("Archiving file:: "+fileName);
                String sourceKey = s3KeyPrefix + fileName;
                String destinationKey = archiveFolder + fileName;

                /*Copying the File to Archive folder*/
                s3Client.copyObject(bucketName,sourceKey,bucketName,destinationKey);

                /*Deleting the file from Source folder*/
                s3Client.deleteObject(bucketName,sourceKey);
            }
        }
        catch(Exception exception){
            logger.log("Exception while moving S3 files to archive folder :: "+exception.getMessage());
            EmailSend.emailSendOnLambdafailure("Exception while moving S3 files to archive folder :: "+exception.getMessage());
        }
    }

    private AmazonS3 getS3Client(BasicSessionCredentials basicSessionCredentials) {
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .withRegion(Regions.fromName(System.getenv("AWS_REGION")))
                .build();
        return s3Client;
    }
}
