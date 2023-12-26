--Thumb rules:

--Rule 1:
--Whenever we are not performing any computation/aggregation on INT/BIGINT columns, we are keeping them as varchar.
--Reason for VARCHAR - it saves space as compare to nvarchar(as we are storing numeric data, unicode is not required, hence VARCHAR).
--example: contact, client_id, intermediary_number,contract_number

--DROP table OMCD_advisor_recon_report


--In the sample data,  
--Confirmation: Column Sales Code (Column E in Excel), Business Partner Type (Column F in Excel) are always N/A,
--Decision I took: 
--Created columns with non numeric datatype as follows,	
--	sales_code varchar(16), 
--	business_partner_type varchar(32)
USE compliance_datastore
CREATE TABLE
    OMCD_advisor_recon_report
    ( 
        id INT NOT NULL IDENTITY, 
        id_or_passport_number VARCHAR(16), 
		initials VARCHAR(6), 
		first_name VARCHAR(32),  
		surname VARCHAR(32), 
		sales_code varchar(16), 
		business_partner_type varchar(32),
		channel  varchar(MAX),
		employee_number varchar(16),
		id_type  varchar(6),
		advisor_registered_on_FSCA_register varchar(16),
		advisor_apears_on_plumbline_extract varchar(16),
		CPD_to_fail_due_in_3_months varchar(6),
		advisor_on_register_with_no_CPD_indicator varchar(3),
		advisor_with_0_CPD_hours_for_this_cycle varchar(3),
		advisor_appears_on_workday_rep_register varchar(3),
		product_category varchar(7),
		product_category_found_on_FSCA_register varchar(3),
		product_category_found_on_workday_rep_register varchar(3),
		advisor_with_COB_due_and_not_completed varchar(32),
		advisor_with_RE_due_and_not_completed varchar(32),
		advisor_with_qualifications_due_and_not_completed varchar(32),
		comments varchar(MAX),
        audit_created_at datetime default CURRENT_TIMESTAMP,
        audit_created_by varchar(156) default CURRENT_USER,
        CONSTRAINT PK_OMCD_advisor_recon_report PRIMARY KEY CLUSTERED 
        (
                [id] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]






