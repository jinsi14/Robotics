USE compliance_datastore
CREATE TABLE OMPM_jurisdiction_master(
	id bigint IDENTITY(1,1) NOT NULL,
	name varchar(256),
	audit_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    audit_created_by VARCHAR(156) DEFAULT CURRENT_USER,
) ON [primary]
    
insert into OMPM_jurisdiction_master (name) values ('South Africa')
insert into OMPM_jurisdiction_master (name) values ('Namibia')
