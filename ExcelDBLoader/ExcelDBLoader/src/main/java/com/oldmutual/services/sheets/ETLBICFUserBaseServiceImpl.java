package com.oldmutual.services.sheets;

import com.oldmutual.model.ETLBICFUserBase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ETLBICFUserBaseServiceImpl {

    public List<ETLBICFUserBase> getBICFUserBaseList(String filePath) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();
        InputStream inputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVParser parser = new CSVParser(inputStreamReader, format);
        final List<CSVRecord> records = parser.getRecords();
        List<ETLBICFUserBase> etlbicfUserBaseList = getETLBICFUserBaseRecordList(records);
        return etlbicfUserBaseList;
    }

    private static List<ETLBICFUserBase> getETLBICFUserBaseRecordList(List<CSVRecord> records){
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
