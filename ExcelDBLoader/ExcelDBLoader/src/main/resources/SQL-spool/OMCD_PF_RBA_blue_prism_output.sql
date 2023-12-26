--Thumb rules:

--Rule 1:
--Whenever we are not performing any computation/aggregation on INT/BIGINT columns, we are keeping them as varchar.
--Reason for VARCHAR - it saves space as compare to nvarchar(as we are storing numeric data, unicode is not required, hence VARCHAR).
--example: contact, client_id, intermediary_number,contract_number

--DROP table OMCD_PF_RBA_blue_prism_output
USE compliance_datastore
CREATE TABLE
    OMCD_PF_RBA_blue_prism_output
    ( 
        id INT NOT NULL IDENTITY, 
		transaction_date DATE,
		intermediary_number	VARCHAR(16), 
        intermediary_channel VARCHAR(128),
        client_surname VARCHAR(64),
        client_name VARCHAR(64),
        client_id VARCHAR(16), 
        client_initials VARCHAR(6),
		product_provider varchar(256),
		product_code varchar(256),
		contract_number varchar(16),
		business_exception varchar(512),
		audit_created_at datetime default CURRENT_TIMESTAMP,
        audit_created_by varchar(156) default CURRENT_USER,
        CONSTRAINT PK_OMCD_PF_RBA_blue_prism_output PRIMARY KEY CLUSTERED
        (
                [id] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
