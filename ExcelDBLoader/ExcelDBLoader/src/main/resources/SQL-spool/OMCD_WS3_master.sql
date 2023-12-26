USE compliance_datastore
CREATE TABLE OMCD_WS3_master(
    id bigint IDENTITY(1,1) NOT NULL,
	id_or_passport_number VARCHAR(13) NOT NULL,
	client_full_name VARCHAR(MAX) NOT NULL,
	client_surname VARCHAR(MAX) NOT NULL,
	transaction_date DATE NOT NULL,
	product_provider VARCHAR(MAX) NOT NULL,
	contract_number  VARCHAR(64) PRIMARY KEY,
	date_of_birth DATE NOT NULL,
	refugee_or_asylum_number VARCHAR (MAX),
	issue_date DATE,
	expiry_date DATE,
	nationality VARCHAR (MAX),
	contact_number VARCHAR (MAX),
	physical_or_residential_address VARCHAR (MAX),
	occupation_or_employment VARCHAR (MAX),
	source_of_funds VARCHAR (MAX),
	person_acting_on_behalf VARCHAR (3),
	full_names VARCHAR (MAX),
	surname VARCHAR (MAX),
	sa_id_number VARCHAR (13),
	api_call_successful VARCHAR (3),
	notes_or_remarks VARCHAR (MAX),
	audit_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    audit_created_by VARCHAR(156) DEFAULT CURRENT_USER
) ON [primary]


