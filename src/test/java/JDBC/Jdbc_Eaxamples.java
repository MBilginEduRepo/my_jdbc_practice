package JDBC;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class Jdbc_Eaxamples {

    String dbUrl = "jdbc:oracle:thin:@3.91.68.84:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from Departments");

        //move to first row
        //resultSet.next();

        //System.out.println(resultSet.getString(2));
        //display departments table in 10 - Administration - 200 - 1700 format

        //System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from Departments ");

        /*
        resultSet.next();
        resultSet.previous();
        resultSet.beforeFirst();
        resultSet.afterLast();
         resultSet.absolute();
        resultSet.getRow();*/


        //how to find how many rows we have for the query
        //System.out.println(resultSet.getInt(1));

        //move to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //to move before first row after we use .last method
        resultSet.beforeFirst();

        //print all second column information
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


    @DisplayName("ResultSet Methods")
    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from Employees ");

        //get the database related data inside the dbMetedata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        //get the resulsetmetadata //rsmd
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have ?
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //getting column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        //
        int i = 1;
        while (rsMetadata.getColumnCount() == (i + 1)) {
            // if (i==(rsMetadata.getColumnCount()+1)){break;}


            System.out.println("rsMetadata.getColumnName(i) = " + rsMetadata.getColumnName(rsMetadata.getColumnCount() + i));
            i++;
        }

        //print all  column information
        for (int j = 1; j <= colCount; j++) {
            System.out.println(rsMetadata.getColumnName(j));
        }



        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


}









