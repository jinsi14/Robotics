package com.oldmutual.dao;

import com.oldmutual.common.CommonUtils;
import com.oldmutual.config.SQLServerConnection;
import com.oldmutual.model.PFRBABluePrism;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class PFRBABluePrismSheetDAO {
    private static final Logger log = (Logger) LogManager.getLogger(PFRBABluePrismSheetDAO.class);

    private static final  String DATE_FORMAT = "dd/MM/yyyy"; //TODO:Test for PROD
    private static final  String SAMPLE_DATE_FORMAT = "yyyy-MM-dd"; //TODO:Dataloading Sample from Andrew - 2/1/2022

    public void save(List<PFRBABluePrism> pfrbaBluePrismList) throws Exception {
        Connection conn = null;
        try {
            SQLServerConnection sqlConn = new SQLServerConnection();
            conn = sqlConn.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO zzzz_OMCD_PF_RBA_blue_prism_output (transaction_date, intermediary_number, client_surname, client_name, client_id, client_initials, product_provider, product_code, contract_number,intermediary_channel , business_exception) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    log.info("File uploading process start!");
                    for (PFRBABluePrism pfrbaBluePrism : pfrbaBluePrismList) {
                        preparedStatement.setTimestamp(1, CommonUtils.getSQLDateTime(pfrbaBluePrism.getTransactionDate(), DATE_FORMAT));
                        preparedStatement.setString(2, pfrbaBluePrism.getIntermediaryNumber());
                        preparedStatement.setString(3, pfrbaBluePrism.getClientSurname());
                        preparedStatement.setString(4, pfrbaBluePrism.getClientName());
                        preparedStatement.setString(5, pfrbaBluePrism.getClientID());
                        preparedStatement.setString(6, pfrbaBluePrism.getClientInitials());
                        preparedStatement.setString(7, pfrbaBluePrism.getProductProvider());
                        preparedStatement.setString(8, pfrbaBluePrism.getProductCode());
                        preparedStatement.setString(9, pfrbaBluePrism.getContractNumber());
                        preparedStatement.setString(10, pfrbaBluePrism.getIntermediaryChannel());
                        preparedStatement.setString(11, pfrbaBluePrism.getBusinessException());
                        preparedStatement.addBatch();
                    }
                    int[] countOfInsertedRows = preparedStatement.executeBatch();
                    log.info("File uploaded successfully!!!");
                    log.info("Total "+countOfInsertedRows.length+" rows inserted.");
                }
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
