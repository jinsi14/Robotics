package com.om.gc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

public class EmailSend {
    public static int etlbicfindWizardScenarioListCount = 0;
    public static int etlbicfUserBaseListCount = 0;

    private static Logger logger = LoggerFactory.getLogger(EmailSend.class);

    public static void emailSendOnLambdafailure(String message) {
        SnsClient snsClient = SnsClient.builder()
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();
        String topicArn=(System.getenv("EMAIL_SEND_TOPIC_ARN"));

        try {
            message=message+"\n Stage : "+(System.getenv("STAGE"));
            PublishRequest request = PublishRequest.builder()
                    .message(message).subject("Group compliance datastore data load : failed")
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
            logger.info(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            logger.error("Error in Email send"+e.getMessage());
            System.exit(1);
        }
    }

    public static void emailSendOnLambdaSuccess(String message) {
        SnsClient snsClient = SnsClient.builder()
                .region(Region.of(System.getenv("AWS_REGION")))
                .build();
        String topicArn=(System.getenv("EMAIL_SEND_TOPIC_ARN"));

        try {
            message=message+"\n Stage : "+(System.getenv("STAGE")+
                    "\n Total processed records count WizardScenario : "+ etlbicfindWizardScenarioListCount+
                    "\n Total processed records count UserBase : "+ etlbicfUserBaseListCount);
            PublishRequest request = PublishRequest.builder()
                    .message(message).subject("Group compliance datastore data load : Successful")
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
            logger.info(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            logger.error("Error in Email send"+e.getMessage());
            System.exit(1);
        }
    }
}
