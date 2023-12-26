package com.oldmutual.dao;

import com.oldmutual.config.SQLServerConnection;
import com.oldmutual.model.AdvisorRecon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class AdvisorReconSheetDAO {
    private static final Logger log = (Logger) LogManager.getLogger(AdvisorReconSheetDAO.class);

    public void save(List<AdvisorRecon> advisorRecons) throws SQLException {
        Connection conn = null;
        try {
            SQLServerConnection sqlConn = new SQLServerConnection();
            conn = sqlConn.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO OMCD_advisor_recon_report (id_or_passport_number, initials, first_name, surname, sales_code, business_partner_type, channel, employee_number, id_type, advisor_registered_on_FSCA_register, advisor_apears_on_plumbline_extract, CPD_to_fail_due_in_3_months, advisor_on_register_with_no_CPD_indicator, advisor_with_0_CPD_hours_for_this_cycle, advisor_appears_on_workday_rep_register, product_category, product_category_found_on_FSCA_register, product_category_found_on_workday_rep_register, advisor_with_COB_due_and_not_completed, advisor_with_RE_due_and_not_completed, advisor_with_qualifications_due_and_not_completed, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    log.info("File uploading process start.");
                    for (AdvisorRecon advisorRecon : advisorRecons) {
                        preparedStatement.setString(1, advisorRecon.getIdOrPassportNumber());
                        preparedStatement.setString(2, advisorRecon.getInitials());
                        preparedStatement.setString(3, advisorRecon.getFirstName());
                        preparedStatement.setString(4, advisorRecon.getSurname());
                        preparedStatement.setString(5, advisorRecon.getSalesCode());
                        preparedStatement.setString(6, advisorRecon.getBusinessPartnerType());
                        preparedStatement.setString(7, advisorRecon.getChannel());
                        preparedStatement.setString(8, advisorRecon.getEmployeeNumber());
                        preparedStatement.setString(9, advisorRecon.getIdType());
                        preparedStatement.setBoolean(10, advisorRecon.getAdvisorRegisteredOnFSCARegister().equalsIgnoreCase("Yes"));
                        preparedStatement.setBoolean(11, advisorRecon.getAdvisorApearsOnPlumblineExtract().equalsIgnoreCase("Yes"));
                        preparedStatement.setString(12, advisorRecon.getCpdToFail());
                        preparedStatement.setString(13, advisorRecon.getAdvisorOnRegisterWithNoCPD());
                        preparedStatement.setString(14, advisorRecon.getAdvisorWithZeroCPDHours());
                        preparedStatement.setString(15, advisorRecon.getAdvisorAppearsWorkday());
                        preparedStatement.setString(16, advisorRecon.getProductCategory());
                        preparedStatement.setString(17, advisorRecon.getProductCategoryFoundFSCA());
                        preparedStatement.setString(18, advisorRecon.getProductCategoryFoundWorkday());
                        preparedStatement.setString(19, advisorRecon.getAdvisorWithCOB());
                        preparedStatement.setString(20, advisorRecon.getAdvisorWithRE());
                        preparedStatement.setString(21, advisorRecon.getAdvisorWithQualifications());
                        preparedStatement.setString(22, advisorRecon.getComments());
                        preparedStatement.addBatch();
                    }
                    int[] countOfInsertedRows = preparedStatement.executeBatch();
                    log.info("File uploaded successfully!!!");
                    log.info("Total "+ countOfInsertedRows.length +" rows inserted.");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
