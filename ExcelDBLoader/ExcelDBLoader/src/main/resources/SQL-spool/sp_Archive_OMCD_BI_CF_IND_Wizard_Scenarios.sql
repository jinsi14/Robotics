
/*
Usage: exec sp_OMCDGetDailyGDMWizardScenario
       go
*/

USE [compliance_datastore]
CREATE PROCEDURE sp_Archive_OMCD_BI_CF_IND_Wizard_Scenarios
AS

BEGIN TRANSACTION
	BEGIN TRY

INSERT into Archive_OMCD_BI_CF_IND_Wizard_Scenarios (
client_entity_id,client_surname,client_first_name,client_initials,client_date_of_birth,key_details_or_client_adviser_xplan_id,
			GDM_or_GDM_client_segment,scenario_name,created_at,updated_at,scenario_id,customer_priority_death,customer_priority_disability,
			customer_priority_severe_illness,customer_priority_emergency_fund,customer_priority_for_retirement,customer_priority_savings,
			adviser_priority_death,adviser_priority_disability,adviser_priority_severe_illness,adviser_priority_emergency_fund,
			adviser_priority_for_retirement,adviser_priority_savings,i_need_to_budget_for_my_daily_expenses,i_need_to_reduce_my_short_term_debt,
			i_need_a_bank_account_with_affordable_charges,i_want_to_maximise_my_tax_deduction_options,have_an_up_to_date_will_and_or_estate_plan,
			provide_my_loved_ones_with_sufficient_capital,provide_my_loved_ones_with_sufficient_income_to_maintain_their_current_lifestyle,
			ensure_that_my_loved_ones_and_i_have_funeral_plans_in_place,
			protect_minor_childrens_inheritance_or_those_unable_to_look_after_themselves,protect_my_income_if_i_become_disabled,
			provide_a_lump_sum_if_i_become_disabled,provide_protection_against_severe_illness,protect_my_income_against_retrenchment,
			protect_my_ability_to_get_cover_in_the_future,provide_travel_insurance,
			protect_my_income_from_day_to_day_medical_expenses_medical_aid,protect_my_income_from_specialist_medical_expenses_gap_cover,
			protect_what_i_own,i_want_to_make_the_most_of_my_retirement_by_saving_enough_today,educating_myself,educating_my_child_ren,
			educating_someone_else,create_an_emergency_fund,buy_a_home,taking_a_holiday,buy_a_car,a_new_addition_to_the_family,
			starting_a_business,my_capital_is_from,have_you_taken_any_retirement_funds_available_before,
			what_is_the_minimum_income_amount_you_need,i_need_my_income_investment_to,
			i_still_have_some_retirement_funds_due_to_mature_help_me_to_boost_them_now_by_increasing_my_contributions,
			i_want_to_reconsider_where_i_live_and_may_downscale,i_am_considering_part_time_work_to_enhance_my_retirement_income,
			review_my_existing_income_generating_products,customer_priority_income,buy_and_sell_agreements,
			life_cover_protection_for_the_cost_of_replacing_a_key_person_in_my_business,contingent_liability,
			client_rsa_id,what_do_you_want_to_do,anything_else
)

SELECT
client_entity_id,client_surname,client_first_name,client_initials,client_date_of_birth,key_details_or_client_adviser_xplan_id,
			GDM_or_GDM_client_segment,scenario_name,created_at,updated_at,scenario_id,customer_priority_death,customer_priority_disability,
			customer_priority_severe_illness,customer_priority_emergency_fund,customer_priority_for_retirement,customer_priority_savings,
			adviser_priority_death,adviser_priority_disability,adviser_priority_severe_illness,adviser_priority_emergency_fund,
			adviser_priority_for_retirement,adviser_priority_savings,i_need_to_budget_for_my_daily_expenses,i_need_to_reduce_my_short_term_debt,
			i_need_a_bank_account_with_affordable_charges,i_want_to_maximise_my_tax_deduction_options,have_an_up_to_date_will_and_or_estate_plan,
			provide_my_loved_ones_with_sufficient_capital,provide_my_loved_ones_with_sufficient_income_to_maintain_their_current_lifestyle,
			ensure_that_my_loved_ones_and_i_have_funeral_plans_in_place,
			protect_minor_childrens_inheritance_or_those_unable_to_look_after_themselves,protect_my_income_if_i_become_disabled,
			provide_a_lump_sum_if_i_become_disabled,provide_protection_against_severe_illness,protect_my_income_against_retrenchment,
			protect_my_ability_to_get_cover_in_the_future,provide_travel_insurance,
			protect_my_income_from_day_to_day_medical_expenses_medical_aid,protect_my_income_from_specialist_medical_expenses_gap_cover,
			protect_what_i_own,i_want_to_make_the_most_of_my_retirement_by_saving_enough_today,educating_myself,educating_my_child_ren,
			educating_someone_else,create_an_emergency_fund,buy_a_home,taking_a_holiday,buy_a_car,a_new_addition_to_the_family,
			starting_a_business,my_capital_is_from,have_you_taken_any_retirement_funds_available_before,
			what_is_the_minimum_income_amount_you_need,i_need_my_income_investment_to,
			i_still_have_some_retirement_funds_due_to_mature_help_me_to_boost_them_now_by_increasing_my_contributions,
			i_want_to_reconsider_where_i_live_and_may_downscale,i_am_considering_part_time_work_to_enhance_my_retirement_income,
			review_my_existing_income_generating_products,customer_priority_income,buy_and_sell_agreements,
			life_cover_protection_for_the_cost_of_replacing_a_key_person_in_my_business,contingent_liability,
			client_rsa_id,what_do_you_want_to_do,anything_else

from ETL_OMCD_BI_CF_IND_Wizard_Scenarios eobciws
WHERE (MONTH(audit_created_at)) = (MONTH(GETDATE())) - 2;

DELETE from ETL_OMCD_BI_CF_IND_Wizard_Scenarios
WHERE (MONTH(audit_created_at)) = (MONTH(GETDATE())) - 2

	COMMIT TRANSACTION

  END TRY

  BEGIN CATCH
    INSERT INTO dbo.OMCDErrors
    VALUES
          (SUSER_SNAME(),
           ERROR_NUMBER(),
           ERROR_STATE(),
           ERROR_SEVERITY(),
           ERROR_LINE(),
           ERROR_PROCEDURE(),
           ERROR_MESSAGE(),
           GETDATE())

    -- Transaction uncommittable
    IF (XACT_STATE()) = -1
      ROLLBACK TRANSACTION

    -- Transaction committable
    IF (XACT_STATE()) = 1
      COMMIT TRANSACTION
  END CATCH