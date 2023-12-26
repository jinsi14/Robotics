USE [master]
GO

/* For security reasons the login is created disabled and with a random password. */
CREATE LOGIN [sqlauthrep] WITH PASSWORD=N'', DEFAULT_DATABASE=[compliance_datastore], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

ALTER LOGIN [sqlauthrep] DISABLE
GO


USE [master]
GO

/* For security reasons the login is created disabled and with a random password. */
CREATE LOGIN [DIGI2] WITH PASSWORD=N'', DEFAULT_DATABASE=[compliance_datastore], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

ALTER LOGIN [DIGI2] DISABLE
GO



USE [master]
GO

/* For security reasons the login is created disabled and with a random password. */
CREATE LOGIN [SqlAuthRep2] WITH PASSWORD=N'', DEFAULT_DATABASE=[compliance_datastore], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO

ALTER LOGIN [SqlAuthRep2] DISABLE
GO
