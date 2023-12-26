USE compliance_datastore
CREATE TABLE
    Analytics_OMCD_daily_load
    ( 
        id bigint IDENTITY(1,1) NOT NULL,
        ETL_OMCD_BI_CF_IND_Wizard_Scenarios varchar(32) default NULL,
        OMCD_BI_CF_IND_Wizard_Scenarios varchar(32) default NULL,
        ETL_user_base varchar(32) default NULL,
        OMCD_user_base varchar(32) default NULL,
        OMCD_advisor_recon_report varchar(32) default NULL,
        OMCD_PF_RBA_blue_prism_output varchar(32) default NULL,
        OMCD_WORK_DAY_FSCA_RECO varchar(32) default NULL,
        OMCD_WS2_master varchar(32) default NULL,
        OMCD_WS3_master varchar(32) default NULL,
        audit_created_at datetime default CURRENT_TIMESTAMP,
        audit_created_by varchar(156) default CURRENT_USER,
        CONSTRAINT PK_Analytics_OMCD_daily_load PRIMARY KEY CLUSTERED
        (
                [id] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

CREATE INDEX Analytics_OMCD_daily_load_audit_created_at ON Analytics_OMCD_daily_load(audit_created_at);
