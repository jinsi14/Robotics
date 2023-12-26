package com.om.gc.dao;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.om.gc.common.CommonUtils;
import com.om.gc.config.SQLServerConnection;
import com.om.gc.model.ETLBICFINDWizardScenario;
import com.om.gc.service.impl.EmailSend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ETLBICFWizardScenarioDAO {
    private static final String DATE_FORMAT = "dd MMM yyyy";
    private static final String TIMESTAMP_FORMAT = "dd MMM yyyy, hh:mm:ss a";
    private static final int BATCH_SIZE = 1000;

    public void save(List<ETLBICFINDWizardScenario> etlbicfindWizardScenarioList, LambdaLogger logger) {
        Connection connection = null;

        final int DATA_SIZE = etlbicfindWizardScenarioList.size();
        try{
            SQLServerConnection sqlServerConnection = new SQLServerConnection();
            connection = sqlServerConnection.getConnection();

            if (connection != null){
                String sql = "INSERT INTO ETL_OMCD_BI_CF_IND_Wizard_Scenarios (client_entity_id, client_surname, client_first_name, client_initials, client_date_of_birth, key_details_or_client_adviser_xplan_id, " +
                        "GDM_or_GDM_client_segment,GDM_or_advice_process, scenario_name, created_at, updated_at, scenario_id, customer_priority_death, customer_priority_disability, customer_priority_severe_illness, " +
                        "customer_priority_emergency_fund, customer_priority_for_retirement, customer_priority_savings, adviser_priority_death, adviser_priority_disability, adviser_priority_severe_illness, " +
                        "adviser_priority_emergency_fund, adviser_priority_for_retirement, adviser_priority_savings, i_need_to_budget_for_my_daily_expenses, i_need_to_reduce_my_short_term_debt, " +
                        "i_need_a_bank_account_with_affordable_charges, i_want_to_maximise_my_tax_deduction_options, have_an_up_to_date_will_and_or_estate_plan, provide_my_loved_ones_with_sufficient_capital, " +
                        "provide_my_loved_ones_with_sufficient_income_to_maintain_their_current_lifestyle, ensure_that_my_loved_ones_and_i_have_funeral_plans_in_place, " +
                        "protect_minor_childrens_inheritance_or_those_unable_to_look_after_themselves, protect_my_income_if_i_become_disabled, provide_a_lump_sum_if_i_become_disabled, " +
                        "provide_protection_against_severe_illness, protect_my_income_against_retrenchment, protect_my_ability_to_get_cover_in_the_future, provide_travel_insurance, " +
                        "protect_my_income_from_day_to_day_medical_expenses_medical_aid, protect_my_income_from_specialist_medical_expenses_gap_cover, protect_what_i_own, " +
                        "i_want_to_make_the_most_of_my_retirement_by_saving_enough_today, educating_myself, educating_my_child_ren, educating_someone_else, create_an_emergency_fund, buy_a_home, " +
                        "taking_a_holiday, buy_a_car,what_do_you_want_to_do ,a_new_addition_to_the_family, starting_a_business,anything_else, my_capital_is_from, have_you_taken_any_retirement_funds_available_before, " +
                        "what_is_the_minimum_income_amount_you_need, i_need_my_income_investment_to, i_still_have_some_retirement_funds_due_to_mature_help_me_to_boost_them_now_by_increasing_my_contributions, " +
                        "i_want_to_reconsider_where_i_live_and_may_downscale, i_am_considering_part_time_work_to_enhance_my_retirement_income, review_my_existing_income_generating_products, " +
                        "customer_priority_income, buy_and_sell_agreements, life_cover_protection_for_the_cost_of_replacing_a_key_person_in_my_business, client_rsa_id, contingent_liability " +
                        ") " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                        "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                connection.setAutoCommit(false);
                try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    int index = 0;
                    logger.log("DB insertion started");
                    for (ETLBICFINDWizardScenario ETLBICFINDWizardScenario : etlbicfindWizardScenarioList) {
                        index++;
                        preparedStatement.setString(1, ETLBICFINDWizardScenario.getClientEntityId());
                        preparedStatement.setString(2, ETLBICFINDWizardScenario.getClientSurname());
                        preparedStatement.setString(3, ETLBICFINDWizardScenario.getClientFirstName());
                        preparedStatement.setString(4, ETLBICFINDWizardScenario.getClientInitials());
                        preparedStatement.setDate(5, CommonUtils.getSQLDate(ETLBICFINDWizardScenario.getClientDateOfBirth(), DATE_FORMAT));
                        preparedStatement.setString(6, ETLBICFINDWizardScenario.getClientAdviserXplanId());
                        preparedStatement.setString(7, ETLBICFINDWizardScenario.getGdmClientSegment());
                        preparedStatement.setString(8, ETLBICFINDWizardScenario.getGdmAdviceProcess());
                        preparedStatement.setString(9, ETLBICFINDWizardScenario.getScenarioName());
                        preparedStatement.setTimestamp(10, CommonUtils.getSQLDateTime(ETLBICFINDWizardScenario.getCreatedAt(), TIMESTAMP_FORMAT));
                        preparedStatement.setTimestamp(11, CommonUtils.getSQLDateTime(ETLBICFINDWizardScenario.getUpdatedAt(), TIMESTAMP_FORMAT));
                        preparedStatement.setString(12, ETLBICFINDWizardScenario.getScenarioId());
                        preparedStatement.setString(13, ETLBICFINDWizardScenario.getCustomerPriorityDeath());
                        preparedStatement.setString(14, ETLBICFINDWizardScenario.getCustomerPriorityDisability());
                        preparedStatement.setString(15, ETLBICFINDWizardScenario.getCustomerPrioritySevereIllness());
                        preparedStatement.setString(16, ETLBICFINDWizardScenario.getCustomerPriorityEmergencyFund());
                        preparedStatement.setString(17, ETLBICFINDWizardScenario.getCustomerPriorityForRetirement());
                        preparedStatement.setString(18, ETLBICFINDWizardScenario.getCustomerPrioritySavings());
                        preparedStatement.setString(19, ETLBICFINDWizardScenario.getAdviserPriorityDeath());
                        preparedStatement.setString(20, ETLBICFINDWizardScenario.getAdviserPriorityDisability());
                        preparedStatement.setString(21, ETLBICFINDWizardScenario.getAdviserPrioritySevereIllness());
                        preparedStatement.setString(22, ETLBICFINDWizardScenario.getAdviserPriorityEmergencyFund());
                        preparedStatement.setString(23, ETLBICFINDWizardScenario.getAdviserPriorityForRetirement());
                        preparedStatement.setString(24, ETLBICFINDWizardScenario.getAdviserPrioritySavings());
                        preparedStatement.setString(25, ETLBICFINDWizardScenario.getBudgetForMyDailyExpenses());
                        preparedStatement.setString(26, ETLBICFINDWizardScenario.getReduceMyShortTermDebt());
                        preparedStatement.setString(27, ETLBICFINDWizardScenario.getBankAccountWithAffordableCharges());
                        preparedStatement.setString(28, ETLBICFINDWizardScenario.getMaximiseMyTaxDeductionOptions());
                        preparedStatement.setString(29, ETLBICFINDWizardScenario.getEstatePlan());
                        preparedStatement.setString(30, ETLBICFINDWizardScenario.getProvideLovedOnesSufficientCapital());
                        preparedStatement.setString(31, ETLBICFINDWizardScenario.getProvideSufficientIncome());
                        preparedStatement.setString(32, ETLBICFINDWizardScenario.getEnsureThatIHaveFuneralPlans());
                        preparedStatement.setString(33, ETLBICFINDWizardScenario.getProtectMinorChildrensInheritance());
                        preparedStatement.setString(34, ETLBICFINDWizardScenario.getProtectMyIncomeIfIBeDisabled());
                        preparedStatement.setString(35, ETLBICFINDWizardScenario.getProvideALumpSumIfIBeDisabled());
                        preparedStatement.setString(36, ETLBICFINDWizardScenario.getProvideProtectionAgainstSevereIllness());
                        preparedStatement.setString(37, ETLBICFINDWizardScenario.getProtectIncomeAgainstRetrenchment());
                        preparedStatement.setString(38, ETLBICFINDWizardScenario.getProtectAbilityToGetCoverInTheFuture());
                        preparedStatement.setString(39, ETLBICFINDWizardScenario.getProvideTravelInsurance());
                        preparedStatement.setString(40, ETLBICFINDWizardScenario.getProtectIncomeMedicalExpenses());
                        preparedStatement.setString(41, ETLBICFINDWizardScenario.getProtectIncomeSpecialistMedicalExpenses());
                        preparedStatement.setString(42, ETLBICFINDWizardScenario.getProtectWhatIOwn());
//                        preparedStatement.setString(43, ETLBICFINDWizardScenario.getProvideLovedOnesSufficientCapital());
                        preparedStatement.setString(43, ETLBICFINDWizardScenario.getMakeMostOfMyRetirement());
                        preparedStatement.setString(44, ETLBICFINDWizardScenario.getEducatingMyself());
                        preparedStatement.setString(45, ETLBICFINDWizardScenario.getEducatingMyChildRen());
                        preparedStatement.setString(46, ETLBICFINDWizardScenario.getEducatingSomeoneElse());
                        preparedStatement.setString(47, ETLBICFINDWizardScenario.getCreateAnEmergencyFund());
                        preparedStatement.setString(48, ETLBICFINDWizardScenario.getBuyAHome());
                        preparedStatement.setString(49, ETLBICFINDWizardScenario.getTakingAHoliday());
                        preparedStatement.setString(50, ETLBICFINDWizardScenario.getBuyACar());
                        preparedStatement.setString(51, ETLBICFINDWizardScenario.getWhatDoYouWantToDo());
                        preparedStatement.setString(52, ETLBICFINDWizardScenario.getaNewAdditionToTheFamily());
                        preparedStatement.setString(53, ETLBICFINDWizardScenario.getStartingABusiness());
                        preparedStatement.setString(54, ETLBICFINDWizardScenario.getAnythingElse());
                        preparedStatement.setString(55, ETLBICFINDWizardScenario.getMyCapitalIsFrom());
                        preparedStatement.setString(56, ETLBICFINDWizardScenario.getTakenAnyRetirementFunds());
                        preparedStatement.setString(57, ETLBICFINDWizardScenario.getMinimumIncomeAmountYouNeed());
                        preparedStatement.setString(58, ETLBICFINDWizardScenario.getNeedIncomeInvestmentTo());
                        preparedStatement.setString(59, ETLBICFINDWizardScenario.getStillHaveSomeRetirementFunds());
                        preparedStatement.setString(60, ETLBICFINDWizardScenario.getReconsiderWhereILive());
                        preparedStatement.setString(61, ETLBICFINDWizardScenario.getConsideringPartTimeWork());
                        preparedStatement.setString(62, ETLBICFINDWizardScenario.getReviewIncomeGeneratingProducts());
                        preparedStatement.setString(63, ETLBICFINDWizardScenario.getCustomerPriorityIncome());
                        preparedStatement.setString(64, ETLBICFINDWizardScenario.getBuyAndSellAgreements());
                        preparedStatement.setString(65, ETLBICFINDWizardScenario.getLifeCoverProtectionForTheCost());
                        preparedStatement.setString(66, ETLBICFINDWizardScenario.getClientRsaId());
                        preparedStatement.setString(67, ETLBICFINDWizardScenario.getContingentLiability());

                        /*preparedStatement.setString(69, ETLBICFINDWizardScenario.getUserDetailOrAdviserCode());
                        preparedStatement.setString(70, ETLBICFINDWizardScenario.getKeyDetailsOrClientAdviserName());
                        preparedStatement.setString(71, ETLBICFINDWizardScenario.getAdviserId());*/
                        preparedStatement.addBatch();
                        EmailSend.etlbicfindWizardScenarioListCount++;

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
                }
            }
//            EmailSend.emailSendOnLambdaSuccess("");
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
