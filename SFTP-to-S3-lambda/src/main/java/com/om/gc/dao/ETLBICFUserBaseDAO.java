package com.om.gc.dao;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.om.gc.common.CommonUtils;
import com.om.gc.config.SQLServerConnection;
import com.om.gc.model.ETLBICFUserBase;
import com.om.gc.service.impl.EmailSend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ETLBICFUserBaseDAO {

    private static final String DATE_FORMAT = "dd MMM yyyy";
    private static final String TIMESTAMP_FORMAT = "dd MMM yyyy, hh:mm:ss a";
    private static final int BATCH_SIZE = 1000;

    public void save(List<ETLBICFUserBase> etlbicfUserBaseList, LambdaLogger logger) {
        Connection connection = null;

        final int DATA_SIZE = etlbicfUserBaseList.size();
        try{
            SQLServerConnection sqlServerConnection = new SQLServerConnection();
            connection = sqlServerConnection.getConnection();
            if (connection != null){
                String sql = "INSERT INTO ETL_OMCD_BI_CF_user_base (key_details_entity_id, key_details_entity_name , key_details_created_date , key_details_last_login_time , user_detail_access_level , " +
                        "user_detail_adviser_code , user_access_user_id ,group_detail_primary_user_group , key_details_id , user_detail_distribution_channel , user_detail_user_group_channel , " +
                        "contact_preferred_email , groups_parent_user_group ) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                connection.setAutoCommit(false);
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    logger.log("DB insertion started");
                    int index = 0;
                    for (ETLBICFUserBase  etlbicfUserBase : etlbicfUserBaseList) {
                        index++;
                        preparedStatement.setString(1, etlbicfUserBase.getKeyDetailsEntityId());
                        preparedStatement.setString(2, etlbicfUserBase.getKeyDetailsEntityName());
                        preparedStatement.setDate(3, CommonUtils.getSQLDate(etlbicfUserBase.getKeyDetailsCreatedDate(), DATE_FORMAT));
                        preparedStatement.setTimestamp(4,
                                (etlbicfUserBase.getKeyDetailsLastLoginTime() != null &&
                                        !etlbicfUserBase.getKeyDetailsLastLoginTime().equalsIgnoreCase("") ?
                                CommonUtils.getSQLDateTime(etlbicfUserBase.getKeyDetailsLastLoginTime(), TIMESTAMP_FORMAT) : null));
                        preparedStatement.setString(5, etlbicfUserBase.getUserDetailAccessLevel());
                        preparedStatement.setString(6, etlbicfUserBase.getUserDetailAdviserCode());
                        preparedStatement.setString(7, etlbicfUserBase.getUserAccessUserId());
                        preparedStatement.setString(8, etlbicfUserBase.getGroupDetailPrimaryUserGroup());
                        preparedStatement.setString(9, etlbicfUserBase.getKeyDetailsId());
                        preparedStatement.setString(10, etlbicfUserBase.getUserDetailDistributionChannel());
                        preparedStatement.setString(11, etlbicfUserBase.getUserDetailUserGroupChannel());
                        preparedStatement.setString(12, etlbicfUserBase.getContactPreferredEmail());
                        preparedStatement.setString(13, etlbicfUserBase.getGroupsParentUserGroup());
                        preparedStatement.addBatch();

                        EmailSend.etlbicfUserBaseListCount++;
                        if (index % BATCH_SIZE == BATCH_SIZE - 1){
                            int[] rowsInserted = preparedStatement.executeBatch();
                            logger.log("NO of rows inserted::"+rowsInserted.length);
                        }

                    }
                    if (index > 0 && DATA_SIZE % BATCH_SIZE != 0){
                        int[] rowsInserted = preparedStatement.executeBatch();
                        logger.log("NO of rows inserted::"+rowsInserted.length);
                    }
                    connection.commit();
                    logger.log("Transaction Committed !!!");
//                    EmailSend.emailSendOnLambdaSuccess("Record inserted successfully");
                }
            }
        }
        catch (Exception e){
            logger.log("Exception generated while DB records insertion..rolling back transaction");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.log("Exception generated::"+ex.getMessage());
                EmailSend.emailSendOnLambdafailure("Exception generated ::"+e.getMessage());
            }
            logger.log("Exception generated ::"+e.getMessage());
            EmailSend.emailSendOnLambdafailure("Exception generated ::"+e.getMessage());
        }
        finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.log("Exception generated ::"+e.getMessage());
                    EmailSend.emailSendOnLambdafailure("Exception generated ::"+e.getMessage());
                }
            }
        }
    }
}
