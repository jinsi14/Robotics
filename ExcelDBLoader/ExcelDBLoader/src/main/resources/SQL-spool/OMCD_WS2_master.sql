USE compliance_datastore
CREATE TABLE OMCD_WS2_master(
	id bigint IDENTITY(1,1) NOT NULL,
	transaction_date DATE,
	client_full_name VARCHAR(MAX),
	client_surname VARCHAR(MAX) NOT NULL,
	client_initials VARCHAR(10),
	client_id_or_passport_number VARCHAR(13) NOT NULL,
	product_provider VARCHAR(MAX),
	contract_number VARCHAR(64) PRIMARY KEY,
	advisor_match_to_oracle VARCHAR(5),
	advisor_full_name VARCHAR(MAX),
	advisor_surname VARCHAR(MAX) NOT NULL,
	advisor_initials  VARCHAR(10),
	advisor_sales_code_number VARCHAR(MAX) NOT NULL,
	advisor_identity_or_passport_number VARCHAR(13) NOT NULL,
	documents_found_in_xplan_document_vault VARCHAR(MAX),
	documents_not_verified_in_xplan_document_vault VARCHAR (MAX),
	audit_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    audit_created_by VARCHAR(156) DEFAULT CURRENT_USER,
) ON [primary]
    
