package com.oldmutual;

import java.sql.*;
import java.util.regex.Pattern;

public class Util {
    public static final Pattern MATCH_BI_CF_WIZARD = Pattern.compile("BI_CF_IND_Wizard_Scenarios.*");
    public static void main(String[] args) {

        Connection conn = null;
        ResultSet resultSet = null;
        try {

            String dbURL = "jdbc:sqlserver://HFWDBHRCDVVS003.hrcc.ad.hilton.com;databaseName=dbResMaxKB";
//            String dbURL = "jdbc:sqlserver://HFWDBHRCDVVS003.hrcc.ad.hilton.com"; //DEV
//            String dbURL = "jdbc:sqlserver://HFWDBHRCPRPS003.hrcc.ad.hilton.com"; //PROD
            String user = "";
            String pass = "";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                Statement statement = conn.createStatement();
                // Create and execute a SELECT SQL statement.
                String selectSql = "SELECT TOP 100 * from dbResMaxKB.dbo.ResmaxImport";
                resultSet = statement.executeQuery(selectSql);

                // Print results from select statement
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
                }

        /*        System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());*/
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
