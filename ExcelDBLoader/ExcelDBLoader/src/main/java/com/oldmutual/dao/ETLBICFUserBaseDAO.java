package com.oldmutual.dao;

import com.oldmutual.common.CommonUtils;
import com.oldmutual.config.SQLServerConnection;
import com.oldmutual.model.ETLBICFINDWizardScenario;
import com.oldmutual.model.ETLBICFUserBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ETLBICFUserBaseDAO {

    public static final String DATE_FORMAT = "dd MMM yyyy";
    //    public static final String DATE_FORMAT = "dd-MM-yy";
    public static final String TIMESTAMP_FORMAT = "dd MMM yyyy, hh:mm:ss a";

    public void save(List<ETLBICFUserBase> etlbicfUserBaseList) throws SQLException {
        Connection connection = null;
        final int BATCH_SIZE = 1000;
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
//                    log.info("File uploading start!");
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

                        if (index % BATCH_SIZE == BATCH_SIZE - 1)
                            preparedStatement.executeBatch();
                    }
                    if (index > 0 && DATA_SIZE % BATCH_SIZE != 0){
                        preparedStatement.executeBatch();
                    }
                    connection.commit();
//                    log.info("File uploaded successfully!!!");
//                    log.info("Total "+countOfInsertedRows.length+" rows inserted.");
                }
            }
        }
        catch (Exception e){
//            log.error(e.getMessage());
            connection.rollback();
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }
    }
}
