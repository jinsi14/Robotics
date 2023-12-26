--DROP TABLE IF EXISTS [dbo].[product_category_FAIS_mapping];
USE [compliance_datastore]
CREATE TABLE product_category_FAIS_mapping (
	id int IDENTITY(1,1),
	category_no int,
	sub_category_no varchar(16),
	description varchar(256),
	CONSTRAINT UC_FAIS_category UNIQUE (category_no,sub_category_no),
	CONSTRAINT PK_product_category_FAIS_mapping PRIMARY KEY CLUSTERED
        (
                [id] ASC
        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','1','Long-Term Insurance subcategory A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','2','Short-Term Insurance Personal Lines');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','3','Long-Term Insurance subcategory B1');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','20','Long-term insurance subcategory B2');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','21','Long-term Insurance subcategory B2-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','22','Long-term Insurance subcategory B1-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','23','Short-term Insurance Personal Lines A1');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','24','Structured Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','25','Securities and Instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','26','Participatory interest in a hedge fund');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','4','Long-Term Insurance subcategory C');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','5','Retail Pension Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','6','Short-Term Insurance Commercial Lines');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','7','Pension Funds Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','8','Shares');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','9','Money market instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','10','Debentures and securitised debt');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','11','Warrants, certificates and other instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','12','Bonds');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','13','Derivative instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','14','Participatory interests in a collective investment scheme');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','15','Forex investment');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','16','Health Service Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','17','Long-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','18','Short-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('1','19','Friendly Society Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','1','Long-term Insurance subcategory B1');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','16','Long-Term Insurance : Category B2-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','17','Long-Term Insurance : Category B1-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','18','Structured Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','19','Securities and instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','20','Participatory interest in a hedge fund');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','2','Long-term Insurance subcategory C');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','3','Retail Pension Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','4','Pension Funds Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','5','Shares');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','6','Money market instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','7','Debentures and securitised debt');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','8','Warrants, certificates and other instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','9','Bonds');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','10','Derivative instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','11','Participatory interests in one or more collective investment schemes');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','12','Forex investment');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','13','Long-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','14','Short-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('2','A','Hedge Fund Manager');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','1','Long-term Insurance subcategory B1');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','15','Long-term insurance subcatory B2');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','16','Long-Term Insurance : Category B2-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','17','Long-Term Insurance : Category B1-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','18','Structured Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','19','Securities and instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','20','Participatory interest in a hedge fund');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','99','General Category III experience');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','2','Long-term Insurance subcategory C');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','3','Retail Pension Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','4','Pension Funds Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','5','Shares');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','6','Money market instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','7','Debentures and securitised debt');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','8','Warrants, certificates and other instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','9','Bonds');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','10','Derivative instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','11','Participatory interests in one or more collective investment schemes');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','12','Forex investment');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','13','Long-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('3','14','Short-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('4','1','Assistance business FSP');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','1','Long-term Insurance subcategory B1');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','2','Long-term Insurance subcategory C');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','3','Retail Pension Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','4','Pension Funds Benefits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','5','Shares');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','6','Money market instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','7','Debentures and securitised debt');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','8','Warrants, certificates and other instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','9','Bonds');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','10','Derivative instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','11','Participatory interests in one or more collective investment schemes');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','12','Forex investment');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','13','Long-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','14','Short-term Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','15','Long-term insurance subcatory B2');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','16','Long-Term Insurance : Category B2-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','17','Long-Term Insurance : Category B1-A');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','18','Structured Deposits');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','19','Securities and instruments');
insert into compliance_datastore.dbo.product_category_FAIS_mapping values('20','20','Participatory interest in a hedge fund');
