USE [compliance_datastore]
GO

/****** Object:  StoredProcedure [dbo].[sp_OMCDGetDailyGDMWizardScenario]    Script Date: 3/21/2022 9:37:41 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[sp_OMCDGetDailyGDMWizardScenario]
AS

BEGIN TRANSACTION
	BEGIN TRY

IF OBJECT_ID('tempdb..#temp_ETL_OMCD_BI_CF_user_base') IS NOT NULL
 BEGIN
     DROP TABLE #temp_ETL_OMCD_BI_CF_user_base;
 END

CREATE  TABLE  #temp_ETL_OMCD_BI_CF_user_base (
	id bigint IDENTITY(1,1) NOT NULL,
	row_num int null,
	key_details_entity_id varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	key_details_entity_name varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	key_details_created_date date NULL,
	key_details_last_login_time datetime NULL,
	user_detail_access_level varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	user_detail_adviser_code varchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	user_access_user_id varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	group_detail_primary_user_group varchar(128) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	key_details_id varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	user_detail_distribution_channel varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	user_detail_user_group_channel varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	contact_preferred_email varchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	groups_parent_user_group varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	audit_created_at datetime DEFAULT getdate() NULL,
	audit_created_by varchar(156) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT user_name() NULL,
	CONSTRAINT PK_temp_ETL_OMCD_BI_CF_user_base PRIMARY KEY (id)
);

	insert into #temp_ETL_OMCD_BI_CF_user_base (
		row_num,key_details_entity_id,key_details_entity_name,key_details_created_date,key_details_last_login_time,user_detail_access_level,user_detail_adviser_code,user_access_user_id,group_detail_primary_user_group,key_details_id,user_detail_distribution_channel,user_detail_user_group_channel,contact_preferred_email,groups_parent_user_group
	)
	SELECT ROW_NUMBER() over (Partition BY id order by CAST(audit_created_at AS date) desc) as row_num,key_details_entity_id,key_details_entity_name,key_details_created_date,key_details_last_login_time,user_detail_access_level,user_detail_adviser_code,user_access_user_id,group_detail_primary_user_group,key_details_id,user_detail_distribution_channel,user_detail_user_group_channel,contact_preferred_email,groups_parent_user_group
	from ETL_OMCD_BI_CF_user_base
	where CAST(audit_created_at AS date) = DATEADD(day, -1, CAST(GETDATE() AS date));



		WITH latestRows as (
			SELECT ROW_NUMBER() over (Partition BY scenario_id order by CAST(updated_at AS date) desc) as row_num, *
			from ETL_OMCD_BI_CF_IND_Wizard_Scenarios
			where CAST(audit_created_at AS date) = DATEADD(day, -1, CAST(GETDATE() AS date))
		)

		insert into OMCD_BI_CF_IND_Wizard_Scenarios (
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
			client_rsa_id,user_detail_or_adviser_code,key_details_or_client_adviser_name,adviser_id,what_do_you_want_to_do,anything_else
		)
		select
			lr.client_entity_id,lr.client_surname,lr.client_first_name,lr.client_initials,lr.client_date_of_birth,
			lr.key_details_or_client_adviser_xplan_id,lr.GDM_or_GDM_client_segment,lr.scenario_name,lr.created_at,
			lr.updated_at,lr.scenario_id,lr.customer_priority_death,lr.customer_priority_disability,
			lr.customer_priority_severe_illness,lr.customer_priority_emergency_fund,lr.customer_priority_for_retirement,
			lr.customer_priority_savings,lr.adviser_priority_death,lr.adviser_priority_disability,
			lr.adviser_priority_severe_illness,lr.adviser_priority_emergency_fund,lr.adviser_priority_for_retirement,
			lr.adviser_priority_savings,lr.i_need_to_budget_for_my_daily_expenses,lr.i_need_to_reduce_my_short_term_debt,
			lr.i_need_a_bank_account_with_affordable_charges,lr.i_want_to_maximise_my_tax_deduction_options,
			lr.have_an_up_to_date_will_and_or_estate_plan,lr.provide_my_loved_ones_with_sufficient_capital,
			lr.provide_my_loved_ones_with_sufficient_income_to_maintain_their_current_lifestyle,lr.ensure_that_my_loved_ones_and_i_have_funeral_plans_in_place,lr.protect_minor_childrens_inheritance_or_those_unable_to_look_after_themselves,lr.protect_my_income_if_i_become_disabled,lr.provide_a_lump_sum_if_i_become_disabled,lr.provide_protection_against_severe_illness,lr.protect_my_income_against_retrenchment,lr.protect_my_ability_to_get_cover_in_the_future,lr.provide_travel_insurance,lr.protect_my_income_from_day_to_day_medical_expenses_medical_aid,lr.protect_my_income_from_specialist_medical_expenses_gap_cover,lr.protect_what_i_own,lr.i_want_to_make_the_most_of_my_retirement_by_saving_enough_today,lr.educating_myself,lr.educating_my_child_ren,lr.educating_someone_else,lr.create_an_emergency_fund,lr.buy_a_home,lr.taking_a_holiday,lr.buy_a_car,lr.a_new_addition_to_the_family,lr.starting_a_business,lr.my_capital_is_from,lr.have_you_taken_any_retirement_funds_available_before,lr.what_is_the_minimum_income_amount_you_need,lr.i_need_my_income_investment_to,lr.i_still_have_some_retirement_funds_due_to_mature_help_me_to_boost_them_now_by_increasing_my_contributions,lr.i_want_to_reconsider_where_i_live_and_may_downscale,lr.i_am_considering_part_time_work_to_enhance_my_retirement_income,lr.review_my_existing_income_generating_products,lr.customer_priority_income,lr.buy_and_sell_agreements,lr.life_cover_protection_for_the_cost_of_replacing_a_key_person_in_my_business,lr.contingent_liability,lr.client_rsa_id,
			lub.user_detail_adviser_code,lub.key_details_entity_name , lub.key_details_id,lr.what_do_you_want_to_do,lr.anything_else
		from latestRows as lr
		inner join #temp_ETL_OMCD_BI_CF_user_base as lub
		on lr.key_details_or_client_adviser_xplan_id = lub.key_details_entity_id
		WHERE lr.row_num = 1 and lub.row_num = 1 ;


		insert into OMCD_BI_CF_user_base (
		key_details_entity_id,key_details_entity_name,key_details_created_date,key_details_last_login_time,user_detail_access_level,user_detail_adviser_code,user_access_user_id,group_detail_primary_user_group,key_details_id,user_detail_distribution_channel,user_detail_user_group_channel,contact_preferred_email,groups_parent_user_group
		)

		select
		key_details_entity_id,key_details_entity_name,key_details_created_date,key_details_last_login_time,user_detail_access_level,user_detail_adviser_code,user_access_user_id,group_detail_primary_user_group,key_details_id,user_detail_distribution_channel,user_detail_user_group_channel,contact_preferred_email,groups_parent_user_group
		from #temp_ETL_OMCD_BI_CF_user_base



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
GO


