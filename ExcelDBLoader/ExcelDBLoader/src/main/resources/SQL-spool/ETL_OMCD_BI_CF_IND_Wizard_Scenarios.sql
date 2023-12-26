--Thumb rules:

--Rule 1:
--Whenever we are not performing any computation/aggregation on INT/BIGINT columns, we are keeping them as varchar.
--Reason for VARCHAR - it saves space as compare to nvarchar(as we are storing numeric data, unicode is not required, hence VARCHAR).
--example: contact, client_id, intermediary_number,contract_number


--Confirmations:

--Confirmation: Column "Provide my loved ones with sufficient capital" is defined twice, Column AC and AP.
--Decision I took: I have kept Column AC as the values in AP are either blank or N/A.

--Confirmation: Column "Key Details | Client Adviser" is defined twice, Column F and BO.
--Decision I took: I have kept Column F as  the values in AP are either blank or N/A.

--DROP table ETL_OMCD_BI_CF_IND_Wizard_Scenarios
USE compliance_datastore
CREATE TABLE
    ETL_OMCD_BI_CF_IND_Wizard_Scenarios
    ( 
        id bigint IDENTITY(1,1) NOT NULL,
        client_entity_id varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        client_surname varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        client_first_name varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        client_initials varchar(16) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        client_date_of_birth DATE NULL,
        key_details_or_client_adviser_xplan_id varchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        GDM_or_GDM_client_segment varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        GDM_or_advice_process varchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        scenario_name varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        created_at datetime NULL,
        updated_at datetime NULL,
        scenario_id varchar(32) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_death varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_disability varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_severe_illness varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_emergency_fund varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_for_retirement varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_savings varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        adviser_priority_death varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        adviser_priority_disability varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        adviser_priority_severe_illness varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        adviser_priority_emergency_fund varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        adviser_priority_for_retirement varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        adviser_priority_savings varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_need_to_budget_for_my_daily_expenses varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_need_to_reduce_my_short_term_debt varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_need_a_bank_account_with_affordable_charges varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_want_to_maximise_my_tax_deduction_options varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        have_an_up_to_date_will_and_or_estate_plan varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        provide_my_loved_ones_with_sufficient_capital varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        provide_my_loved_ones_with_sufficient_income_to_maintain_their_current_lifestyle varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        ensure_that_my_loved_ones_and_i_have_funeral_plans_in_place varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_minor_childrens_inheritance_or_those_unable_to_look_after_themselves varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_my_income_if_i_become_disabled varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        provide_a_lump_sum_if_i_become_disabled varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        provide_protection_against_severe_illness varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_my_income_against_retrenchment varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_my_ability_to_get_cover_in_the_future varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        provide_travel_insurance varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_my_income_from_day_to_day_medical_expenses_medical_aid varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_my_income_from_specialist_medical_expenses_gap_cover varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        protect_what_i_own varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_want_to_make_the_most_of_my_retirement_by_saving_enough_today varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        educating_myself varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        educating_my_child_ren varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        educating_someone_else varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        create_an_emergency_fund varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        buy_a_home varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        taking_a_holiday varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        buy_a_car varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        what_do_you_want_to_do varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        a_new_addition_to_the_family varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        starting_a_business varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        anything_else varchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        my_capital_is_from varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        have_you_taken_any_retirement_funds_available_before varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        what_is_the_minimum_income_amount_you_need varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_need_my_income_investment_to varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_still_have_some_retirement_funds_due_to_mature_help_me_to_boost_them_now_by_increasing_my_contributions varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_want_to_reconsider_where_i_live_and_may_downscale varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        i_am_considering_part_time_work_to_enhance_my_retirement_income varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        review_my_existing_income_generating_products varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        customer_priority_income varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        buy_and_sell_agreements varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        life_cover_protection_for_the_cost_of_replacing_a_key_person_in_my_business varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        client_rsa_id varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        contingent_liability varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
---        user_detail_or_adviser_code varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
---        key_details_or_client_adviser_name varchar(MAX) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
---        adviser_id varchar(512) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        audit_created_at datetime default CURRENT_TIMESTAMP,
        audit_created_by varchar(156) default CURRENT_USER,
        CONSTRAINT PK_ETL_OMCD_BI_CF_IND_Wizard_Scenarios PRIMARY KEY CLUSTERED
        (
                [id] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

CREATE INDEX ETL_OMCD_BI_CF_IND_Wizard_Scenarios_audit_created_at ON ETL_OMCD_BI_CF_IND_Wizard_Scenarios (audit_created_at);

ALTER TABLE ETL_OMCD_BI_CF_IND_Wizard_Scenarios
    ALTER COLUMN client_initials varchar(32);