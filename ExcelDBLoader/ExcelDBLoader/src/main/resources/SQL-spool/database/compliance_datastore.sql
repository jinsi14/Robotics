USE [master]
GO

CREATE DATABASE [compliance_datastore]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'compliance_datastore', FILENAME = N'D:\rdsdbdata\DATA\compliance_datastore.mdf' , SIZE = 149568KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON
( NAME = N'compliance_datastore_log', FILENAME = N'D:\rdsdbdata\DATA\compliance_datastore_log.ldf' , SIZE = 12352KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [compliance_datastore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [compliance_datastore] SET ANSI_NULL_DEFAULT OFF
GO

ALTER DATABASE [compliance_datastore] SET ANSI_NULLS OFF
GO

ALTER DATABASE [compliance_datastore] SET ANSI_PADDING OFF
GO

ALTER DATABASE [compliance_datastore] SET ANSI_WARNINGS OFF
GO

ALTER DATABASE [compliance_datastore] SET ARITHABORT OFF
GO

ALTER DATABASE [compliance_datastore] SET AUTO_CLOSE OFF
GO

ALTER DATABASE [compliance_datastore] SET AUTO_SHRINK OFF
GO

ALTER DATABASE [compliance_datastore] SET AUTO_UPDATE_STATISTICS ON
GO

ALTER DATABASE [compliance_datastore] SET CURSOR_CLOSE_ON_COMMIT OFF
GO

ALTER DATABASE [compliance_datastore] SET CURSOR_DEFAULT  GLOBAL
GO

ALTER DATABASE [compliance_datastore] SET CONCAT_NULL_YIELDS_NULL OFF
GO

ALTER DATABASE [compliance_datastore] SET NUMERIC_ROUNDABORT OFF
GO

ALTER DATABASE [compliance_datastore] SET QUOTED_IDENTIFIER OFF
GO

ALTER DATABASE [compliance_datastore] SET RECURSIVE_TRIGGERS OFF
GO

ALTER DATABASE [compliance_datastore] SET  DISABLE_BROKER
GO

ALTER DATABASE [compliance_datastore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO

ALTER DATABASE [compliance_datastore] SET DATE_CORRELATION_OPTIMIZATION OFF
GO

ALTER DATABASE [compliance_datastore] SET TRUSTWORTHY OFF
GO

ALTER DATABASE [compliance_datastore] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO

ALTER DATABASE [compliance_datastore] SET PARAMETERIZATION SIMPLE
GO

ALTER DATABASE [compliance_datastore] SET READ_COMMITTED_SNAPSHOT OFF
GO

ALTER DATABASE [compliance_datastore] SET HONOR_BROKER_PRIORITY OFF
GO

ALTER DATABASE [compliance_datastore] SET RECOVERY FULL
GO

ALTER DATABASE [compliance_datastore] SET  MULTI_USER
GO

ALTER DATABASE [compliance_datastore] SET PAGE_VERIFY CHECKSUM
GO

ALTER DATABASE [compliance_datastore] SET DB_CHAINING OFF
GO

ALTER DATABASE [compliance_datastore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO

ALTER DATABASE [compliance_datastore] SET TARGET_RECOVERY_TIME = 0 SECONDS
GO

ALTER DATABASE [compliance_datastore] SET DELAYED_DURABILITY = DISABLED
GO

ALTER DATABASE [compliance_datastore] SET  READ_WRITE
GO


