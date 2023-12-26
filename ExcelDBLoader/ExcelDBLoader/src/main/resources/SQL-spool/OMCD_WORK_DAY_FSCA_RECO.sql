USE compliance_datastore
create table OMCD_WORK_DAY_FSCA_RECO
(
    id                                                 int NOT NULL IDENTITY,
    id_number_or_passport_number                       varchar(32),
    initials                                           varchar(8),
    first_name                                         varchar(64),
    surname                                            varchar(64),
    sales_code                                         varchar(50),
    business_partner_type                              varchar(64),
    channel                                            varchar(MAX),
    role                                               varchar(64),
    employee_number                                    varchar(64),
    id_type                                            varchar(64),
    advisor_registrered_on_FSCA_register               varchar(255),
    advisor_appears_on_workday_rep_register            varchar(20),
    product_category                                   varchar(50),
    product_category_found_on_FSCA_register            varchar(255),
    product_category_found_on_workday_rep_register     varchar(255),
    advisor_under_supervision_or_not_under_supervision varchar(255),
    advisor_with_qualifications_due_and_not_completed  varchar(255),
    comments                                           varchar(MAX),
    audit_created_at datetime default CURRENT_TIMESTAMP,
    audit_created_by varchar(156) default CURRENT_USER

    CONSTRAINT OMCD_WORK_DAY_FSCA_RECO_pk PRIMARY KEY CLUSTERED
    (
             id ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON,
               ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
           )
go


