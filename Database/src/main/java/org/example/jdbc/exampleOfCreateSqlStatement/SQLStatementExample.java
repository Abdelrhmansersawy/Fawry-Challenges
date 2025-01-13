package org.example.jdbc.exampleOfCreateSqlStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class SQLStatementExample {
    private static final Logger logger = Logger.getLogger(SQLStatementExample.class.getName());
    private final Connection connection;

    public SQLStatementExample(Connection connection) {
        this.connection = connection;
    }

    public void runSampleSQLExample() throws SQLException {
        // Create a Statement object (not recommended due to SQL injection risks)
        Statement statement = connection.createStatement(); // Create a Statement object
        String sqlQuery = "SELECT name FROM Employee WHERE role = 'employee'"; // Define the SQL query
        ResultSet resultSet = statement.executeQuery(sqlQuery); // Execute the query and get the ResultSet

        // Process the ResultSet
        System.out.println("Sample sql query Result set:");
        while (resultSet.next()) {
            // Note: In JDBC, column indexes are 1-based, not 0-based.
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                System.out.println(resultSet.getMetaData().getColumnName(i+1) + ' ' + resultSet.getString(i + 1));
            }
        }
    }


    public void runSqlInjectionExample() throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT name FROM Employee WHERE role = 'employee' or 1 = 1";
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        System.out.println("Sample sql injection query Result set:");
        while (resultSet.next()) {
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                System.out.println(resultSet.getMetaData().getColumnName(i+1) + ' ' + resultSet.getString(i + 1));
            }
        }
    }

    public void close() throws SQLException {
        connection.close();
    }
}
