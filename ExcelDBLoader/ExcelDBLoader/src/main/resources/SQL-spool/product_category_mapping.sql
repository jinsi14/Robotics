
CREATE TABLE compliance_datastore.dbo.[product_category_mapping] (
	id int IDENTITY(1,1) NOT NULL,
	product_code  varchar(16) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	product_type varchar(256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	product_code_desc varchar(256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	FAIS_sub_categories varchar(256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	FAIS_sub_categories_no varchar(8) NULL,
	CONSTRAINT PK_product_category_mapping PRIMARY KEY (id),
	CONSTRAINT UC_category UNIQUE (product_code,FAIS_sub_categories)
);

INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('ANNCOM','MAX Compulsory Annuity','Max Income Compulsory Ann','1.20 Long-term Insurance subcategory B2','1.20');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('ANNVOL','Max Voluntary Annuity','Max Income Voluntary Annu','1.20 Long-term Insurance subcategory B2','1.20');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('CPANNCOM','MAX Capital Preservation','Max Income Capital Preser','1.20 Long-term Insurance subcategory B2','1.20');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('CPLSD','CPO LS Death Benefit','Max Income CPO LS Death B','1.03 Long-term Insurance subcategory B1','1.03');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FLEXINV','Flexible Investment LIFE','FlexInv Pure LIFE','1.20 Long-term Insurance subcategory B2','1.20');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FLEXINV','Flexible Investment LIFE','FlexInv Pure LIFE','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FLEXINV','Flexible Investment LIFE','FlexInv Pure LIFE','1.4 Long-term Insurance subcategory C','1.4');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FLEXRA','Flexible Investment LIFE','FlexInv RA LIFE','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FLEXRA','Flexible Investment LIFE','FlexInv RA LIFE','1.5 Retail Pension Benefits','1.5');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FLEXT','Flexible Investment LISP','FlexInv Pure LISP','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FOCUSINV','Focused Investment LIFE','FocusInv Pure LIFE','1.20 Long-term Insurance subcategory B2','1.20');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FOCUSINV','Focused Investment LIFE','FocusInv Pure LIFE','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FOCUSINV','Focused Investment LIFE','FocusInv Pure LIFE','1.4 Long-term Insurance subcategory C','1.4');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('FOCUST','Focused Investment LISP','FocusInv Pure LISP','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('OPTIRA','Optimal Investment LIFE','Optimal Investment Plan RA','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('OPTIRA','Optimal Investment LIFE','Optimal Investment Plan RA','1.5 Retail Pension Benefits','1.5');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('OMINVT','OM Invest LISP','Old Mutual Invest Flexible Plan','1.14 Participatory interest in a collective investment scheme','1.14');
INSERT INTO compliance_datastore.dbo.product_category_mapping (product_code, product_type, product_code_desc, FAIS_sub_categories, FAIS_sub_categories_no) VALUES('OMINV','OM Invest LIFE','Old Mutual Invest Tax Free Plan','1.14 Participatory interest in a collective investment scheme','1.14');
