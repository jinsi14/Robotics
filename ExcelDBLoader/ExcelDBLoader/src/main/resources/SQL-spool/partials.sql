CREATE DATABASE compliance_datastore;
USE compliance_datastore;
select count(1) from
select top 10 * from ETL_OMCD_BI_CF_IND_Wizard_Scenarios order by audit_created_at desc;
select top 10 * from OMCD_BI_CF_IND_Wizard_Scenarios order by audit_created_at desc;

select top 10 * from OMCD_PF_RBA_blue_prism_output order by audit_created_at desc;


 select
    ETL_OMCD_BI_CF_IND_Wizard_Scenarios.ETL_OMCD_BI_CF_IND_Wizard_Scenarios,OMCD_BI_CF_IND_Wizard_Scenarios.OMCD_BI_CF_IND_Wizard_Scenarios,
    ETL_OMCD_BI_CF_user_base.ETL_OMCD_BI_CF_user_base,OMCD_BI_CF_user_base.OMCD_BI_CF_user_base,
    OMCD_advisor_recon_report.OMCD_advisor_recon_report,OMCD_PF_RBA_blue_prism_output.OMCD_PF_RBA_blue_prism_output,
    OMCD_WORK_DAY_FSCA_RECO.OMCD_WORK_DAY_FSCA_RECO

    from
(SELECT count(1) as ETL_OMCD_BI_CF_IND_Wizard_Scenarios FROM ETL_OMCD_BI_CF_IND_Wizard_Scenarios) as ETL_OMCD_BI_CF_IND_Wizard_Scenarios,
(SELECT count(1) as OMCD_BI_CF_IND_Wizard_Scenarios FROM OMCD_BI_CF_IND_Wizard_Scenarios) as OMCD_BI_CF_IND_Wizard_Scenarios,
(SELECT count(1) as ETL_OMCD_BI_CF_user_base FROM ETL_OMCD_BI_CF_user_base) as ETL_OMCD_BI_CF_user_base,
(SELECT count(1) as OMCD_BI_CF_user_base FROM OMCD_BI_CF_user_base) as OMCD_BI_CF_user_base,
(SELECT count(1) as OMCD_advisor_recon_report FROM OMCD_advisor_recon_report) as OMCD_advisor_recon_report,
(SELECT count(1) as OMCD_PF_RBA_blue_prism_output FROM OMCD_PF_RBA_blue_prism_output) as OMCD_PF_RBA_blue_prism_output,
(SELECT count(1) as OMCD_WORK_DAY_FSCA_RECO FROM OMCD_WORK_DAY_FSCA_RECO) as OMCD_WORK_DAY_FSCA_RECO




select * from ETL_OMCD_BI_CF_user_base where cast(audit_created_at as date) = '2022-03-23'
select * from ETL_OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-03-23'

select count(1) from ETL_OMCD_BI_CF_user_base where cast(audit_created_at as date) = '2022-03-23'
select count(1) from ETL_OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-03-23'

select count(1) from OMCD_BI_CF_user_base where cast(audit_created_at as date) = '2022-03-23'
select count(1) from OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-03-23'

delete from OMCD_BI_CF_user_base where cast(audit_created_at as date) = '2022-03-23'
delete from OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-03-23'


SELECT *,
[Event Logged] = CASE is_event_logged WHEN 0 THEN 'No' ELSE 'Yes' END,
text AS [Description]
FROM sys.messages
WHERE language_id = 1033 /* replace 1040 with the desired language ID, such as 1033 for US English*/
ORDER BY message_id


	SELECT ROW_NUMBER() over (Partition BY id order by CAST(audit_created_at AS date) desc) as row_num,key_details_entity_id,key_details_entity_name,key_details_created_date,key_details_last_login_time,user_detail_access_level,user_detail_adviser_code,user_access_user_id,group_detail_primary_user_group,key_details_id,user_detail_distribution_channel,user_detail_user_group_channel,contact_preferred_email,groups_parent_user_group,audit_created_at
	from ETL_OMCD_BI_CF_user_base
	where CAST(audit_created_at AS date) = DATEADD(day, -1, CAST(GETDATE() AS date));


