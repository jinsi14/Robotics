package com.om.gc.service.impl;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.om.gc.dao.ETLBICFUserBaseDAO;
import com.om.gc.model.ETLBICFUserBase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ETLBICFUserBaseServiceImpl {

    private ETLBICFUserBaseDAO etlbicfUserBaseDAO = new ETLBICFUserBaseDAO();

    public void save(AmazonS3 s3Client, String s3KeyPrefix, String bucketName, String fileName, LambdaLogger logger){
        List<ETLBICFUserBase> etlbicfUserBaseList = this.getBICFUserBaseListS3(s3Client,s3KeyPrefix,bucketName,fileName,logger);
        etlbicfUserBaseDAO.save(etlbicfUserBaseList, logger);
    }

    private List<ETLBICFUserBase> getBICFUserBaseListS3(AmazonS3 s3Client, String s3KeyPrefix, String bucketName, String fileName, LambdaLogger logger){
        List<ETLBICFUserBase> etlbicfUserBaseList = new ArrayList<>();
        try {
            CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();
            String key = s3KeyPrefix + fileName;
            S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
            InputStream inputStream = object.getObjectContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVParser parser = new CSVParser(inputStreamReader, format);
            etlbicfUserBaseList = getETLBICFUserBaseRecordList(parser.getRecords());
            inputStreamReader.close();
            inputStream.close();
        }
        catch (IOException ioException){
            logger.log("Exception generated while processing BI_CF_User_Base file::"+fileName+" Exception::"+ioException.getMessage());
            EmailSend.emailSendOnLambdafailure("Exception generated while processing BI_CF_User_Base file::"+fileName+" Exception::"+ioException.getMessage());
        }
        return etlbicfUserBaseList;
    }

    private List<ETLBICFUserBase> getBICFUserBaseListLocal(String filePath) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();
        InputStream inputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParser(inputStreamReader, format);
        return getETLBICFUserBaseRecordList(parser.getRecords());
    }

    private List<ETLBICFUserBase> getETLBICFUserBaseRecordList(List<CSVRecord> records){
        List<ETLBICFUserBase> etlbicfUserBaseList = new ArrayList<>(records.size());
        for(CSVRecord record : records){
            ETLBICFUserBase etlbicfUserBase = new ETLBICFUserBase();
            etlbicfUserBase.setKeyDetailsEntityId(record.get(0));
            etlbicfUserBase.setKeyDetailsEntityName(record.get(1));
            etlbicfUserBase.setKeyDetailsCreatedDate(record.get(2));
            etlbicfUserBase.setKeyDetailsLastLoginTime(record.get(3));
            etlbicfUserBase.setUserDetailAccessLevel(record.get(4));
            etlbicfUserBase.setUserDetailAdviserCode(record.get(5));
            etlbicfUserBase.setUserAccessUserId(record.get(6));
            etlbicfUserBase.setGroupDetailPrimaryUserGroup(record.get(7));
            etlbicfUserBase.setKeyDetailsId(record.get(8));
            etlbicfUserBase.setUserDetailDistributionChannel(record.get(9));
            etlbicfUserBase.setUserDetailUserGroupChannel(record.get(10));
            etlbicfUserBase.setContactPreferredEmail(record.get(11));
            etlbicfUserBase.setGroupsParentUserGroup(record.get(12));
            etlbicfUserBaseList.add(etlbicfUserBase);
        }
        return etlbicfUserBaseList;
    }
}
