select
    ETL_OMCD_BI_CF_IND_Wizard_Scenarios.ETL_OMCD_BI_CF_IND_Wizard_Scenarios,OMCD_BI_CF_IND_Wizard_Scenarios.OMCD_BI_CF_IND_Wizard_Scenarios,
    ETL_OMCD_BI_CF_user_base.ETL_OMCD_BI_CF_user_base,OMCD_BI_CF_user_base.OMCD_BI_CF_user_base,
    OMCD_advisor_recon_report.OMCD_advisor_recon_report,OMCD_PF_RBA_blue_prism_output.OMCD_PF_RBA_blue_prism_output,
    OMCD_WORK_DAY_FSCA_RECO.OMCD_WORK_DAY_FSCA_RECO, OMCD_WS2_master.OMCD_WS2_master,OMCD_WS3_master.OMCD_WS3_master

    from
(SELECT count(1) as ETL_OMCD_BI_CF_IND_Wizard_Scenarios FROM ETL_OMCD_BI_CF_IND_Wizard_Scenarios) as ETL_OMCD_BI_CF_IND_Wizard_Scenarios,
(SELECT count(1) as OMCD_BI_CF_IND_Wizard_Scenarios FROM OMCD_BI_CF_IND_Wizard_Scenarios) as OMCD_BI_CF_IND_Wizard_Scenarios,
(SELECT count(1) as ETL_OMCD_BI_CF_user_base FROM ETL_OMCD_BI_CF_user_base) as ETL_OMCD_BI_CF_user_base,
(SELECT count(1) as OMCD_BI_CF_user_base FROM OMCD_BI_CF_user_base) as OMCD_BI_CF_user_base,
(SELECT count(1) as OMCD_advisor_recon_report FROM OMCD_advisor_recon_report) as OMCD_advisor_recon_report,
(SELECT count(1) as OMCD_PF_RBA_blue_prism_output FROM OMCD_PF_RBA_blue_prism_output) as OMCD_PF_RBA_blue_prism_output,
(SELECT count(1) as OMCD_WORK_DAY_FSCA_RECO FROM OMCD_WORK_DAY_FSCA_RECO) as OMCD_WORK_DAY_FSCA_RECO,
(SELECT count(1) as OMCD_WS2_master FROM OMCD_WS2_master) as OMCD_WS2_master,
(SELECT count(1) as OMCD_WS3_master FROM OMCD_WS3_master) as OMCD_WS3_master

select * from OMCD_advisor_recon_report

---Counts
select count(1) records_count from ETL_OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-04-14'
select count(1) records_count from OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-04-14'
select count(1) records_count from ETL_OMCD_BI_CF_user_base where cast(audit_created_at as date) = '2022-04-14'
select count(1) records_count from OMCD_BI_CF_user_base where cast(audit_created_at as date) = '2022-04-14'
select count(1) records_count from OMCD_advisor_recon_report where cast(audit_created_at as date) = '2022-04-14'
select count(1) records_count from OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-04-14'
select count(1) records_count from OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-04-25'
select count(1) records_count from OMCD_BI_CF_IND_Wizard_Scenarios where cast(audit_created_at as date) = '2022-04-24'


--Counts By Date
--FOR ETL_OMCD_BI_CF_IND_Wizard_Scenarios
with ETL_OMCD_Wizard_counts as (select cast(audit_created_at as date) as insert_date, count(1) records_count 
		from ETL_OMCD_BI_CF_IND_Wizard_Scenarios 
		group by cast(audit_created_at as date)
),
OMCD_Wizard_counts as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_BI_CF_IND_Wizard_Scenarios 
	group by cast(audit_created_at as date)
),
ETL_user_base_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from ETL_OMCD_BI_CF_user_base 
	group by cast(audit_created_at as date)
),
OMCD_user_base_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_BI_CF_user_base 
	group by cast(audit_created_at as date)
),
OMCD_advisor_recon_report_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_advisor_recon_report 
	group by cast(audit_created_at as date)
),
OMCD_RBA_blue_prism_output_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_PF_RBA_blue_prism_output 
	group by cast(audit_created_at as date)
),
OMCD_WORK_DAY_FSCA_RECO_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_WORK_DAY_FSCA_RECO 
	group by cast(audit_created_at as date)
),
OMCD_WS2_master_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_WS2_master 
	group by cast(audit_created_at as date)
),
OMCD_WS3_master_count as (
	select cast(audit_created_at as date) as insert_date, count(1) records_count 
	from OMCD_WS3_master 
	group by cast(audit_created_at as date)
)

select 
etl_omcd_count.insert_date as insert_date, 
etl_omcd_count.records_count as ETL_OMCD_BI_CF_IND_Wizard_Scenarios,
omcd_count.records_count as OMCD_BI_CF_IND_Wizard_Scenarios,
etl_user_base_count.records_count as ETL_user_base,
omcd_user_base_count.records_count as OMCD_user_base,
omcd_advisor_recon_count.records_count as OMCD_advisor_recon_report,
omcd_blue_prism_output_count.records_count as OMCD_PF_RBA_blue_prism_output,
omcd_workday_reco_count.records_count as OMCD_WORK_DAY_FSCA_RECO,
omcd_ws2_count.records_count as OMCD_WS2_master,
omcd_ws3_count.records_count as OMCD_WS3_master
from ETL_OMCD_Wizard_counts etl_omcd_count
left join OMCD_Wizard_counts omcd_count on (etl_omcd_count.insert_date=omcd_count.insert_date)
left join ETL_user_base_count etl_user_base_count on (etl_omcd_count.insert_date=etl_user_base_count.insert_date)
left join OMCD_user_base_count omcd_user_base_count on (etl_omcd_count.insert_date=omcd_user_base_count.insert_date)
left join OMCD_advisor_recon_report_count omcd_advisor_recon_count on (etl_omcd_count.insert_date=omcd_advisor_recon_count.insert_date)
left join OMCD_RBA_blue_prism_output_count omcd_blue_prism_output_count on (etl_omcd_count.insert_date=omcd_blue_prism_output_count.insert_date)
left join OMCD_WORK_DAY_FSCA_RECO_count omcd_workday_reco_count on (etl_omcd_count.insert_date=omcd_workday_reco_count.insert_date)
left join OMCD_WS2_master_count omcd_ws2_count on (etl_omcd_count.insert_date=omcd_ws2_count.insert_date)
left join OMCD_WS3_master_count omcd_ws3_count on (etl_omcd_count.insert_date=omcd_ws3_count.insert_date)
order by cast(etl_omcd_count.insert_date as date)



select cast(audit_created_at as date) as insert_date, count(1) records_count 
from OMCD_BI_CF_user_base 
group by cast(audit_created_at as date)

select cast(audit_created_at as date) as insert_date, count(1) records_count 
from OMCD_advisor_recon_report 
group by cast(audit_created_at as date)



select cast(audit_created_at as date) as insert_date, count(1) records_count 
from OMCD_advisor_recon_report 
group by cast(audit_created_at as date)
where cast(audit_created_at as date) = '2022-04-14'






--DIGI2
select top 10 * from OMCD_PF_RBA_blue_prism_output order by audit_created_at desc;
--SqlAuthRep2
select top 10 * from OMCD_advisor_recon_report order by audit_created_at desc;

--sqladmin
select top 10 * from OMCD_BI_CF_IND_Wizard_Scenarios order by audit_created_at desc;

--sqladmin
select top 10 * from OMCD_BI_CF_user_base order by audit_created_at desc;

--sqlauthrep
select top 10 * from ETL_OMCD_BI_CF_IND_Wizard_Scenarios order by audit_created_at desc;

--sqlauthrep
select top 10 * from ETL_OMCD_BI_CF_user_base order by audit_created_at desc;


select * from OMCD_PF_RBA_blue_prism_output