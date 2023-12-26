USE compliance_datastore
CREATE TABLE
    OMCD_BI_CF_user_base
    (
        id INT NOT NULL IDENTITY,
        key_details_entity_id VARCHAR(MAX),
        key_details_entity_name VARCHAR(MAX),
        key_details_created_date DATE NULL,
        key_details_last_login_time DATETIME,
        user_detail_access_level VARCHAR(MAX),
        user_detail_adviser_code VARCHAR(MAX),
        user_access_user_id VARCHAR(MAX),
        group_detail_primary_user_group VARCHAR(MAX),
        key_details_id VARCHAR(MAX),
        user_detail_distribution_channel VARCHAR(MAX),
        user_detail_user_group_channel VARCHAR(MAX),
        contact_preferred_email VARCHAR(MAX),
        groups_parent_user_group VARCHAR(MAX),
		audit_created_at datetime default CURRENT_TIMESTAMP,
        audit_created_by varchar(156) default CURRENT_USER,
        CONSTRAINT PK_OMCD_BI_CF_user_base PRIMARY KEY CLUSTERED
        (
            [id] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

