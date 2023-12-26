package com.om.gc.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

public class STSServiceImpl {

    public GetSecretValueResult getSecretManagerValueResult(BasicSessionCredentials basicSessionCredentials, LambdaLogger logger){

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(Regions.fromName(System.getenv("AWS_REGION")))
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(System.getenv("AWS_SM_SECRET_NAME"))
//                .withSecretId(AWSConstants.AWS_SM_SECRET_NAME)
                .withVersionStage("AWSCURRENT");

        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (DecryptionFailureException e) {
            // Deal with the exception here, and/or rethrow at your discretion.
            logger.log("Secrets Manager can't decrypt the protected secret text using the provided KMS key."+e.getMessage());
            EmailSend.emailSendOnLambdafailure("Secrets Manager can't decrypt the protected secret text using the provided KMS key."+e.getMessage());
            throw e;
        } catch (InternalServiceErrorException e) {
            // Deal with the exception here, and/or rethrow at your discretion.
            logger.log("An error occurred on the server side."+e.getMessage());
            EmailSend.emailSendOnLambdafailure("An error occurred on the server side."+e.getMessage());
            throw e;
        } catch (InvalidParameterException e) {
            logger.log("You provided an invalid value for a parameter."+e.getMessage());
            EmailSend.emailSendOnLambdafailure("You provided an invalid value for a parameter."+e.getMessage());
            throw e;
        } catch (InvalidRequestException e) {
            logger.log("You provided a parameter value that is not valid for the current state of the resource."+e.getMessage());
            EmailSend.emailSendOnLambdafailure("You provided a parameter value that is not valid for the current state of the resource."+e.getMessage());
            throw e;
        } catch (ResourceNotFoundException e) {
            logger.log("We can't find the resource that you asked for."+e.getMessage());
            EmailSend.emailSendOnLambdafailure("We can't find the resource that you asked for."+e.getMessage());
            throw e;
        }
        return getSecretValueResult;
    }

    public BasicSessionCredentials getBasicSessionCredentials(AWSSecurityTokenService awsSecurityTokenService) {

        AssumeRoleRequest roleRequest = new AssumeRoleRequest()
                .withRoleArn(System.getenv("ROLE_ARN"))
                .withRoleSessionName(System.getenv("ROLE_SESSION_NAME"));

        AssumeRoleResult roleResponse = awsSecurityTokenService.assumeRole(roleRequest);
        Credentials sessionCreds = roleResponse.getCredentials();
        BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
                sessionCreds.getAccessKeyId(),
                sessionCreds.getSecretAccessKey(),
                sessionCreds.getSessionToken());
        return basicSessionCredentials;
    }

    public AWSSecurityTokenService getAwsSecurityTokenService() {
        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
//                                AWSConstants.AWS_STS_SERVICE_END_POINT,
                                System.getenv("AWS_STS_SERVICE_END_POINT"),
                                System.getenv("AWS_REGION")))
                .build();
        return stsClient;
    }
}
