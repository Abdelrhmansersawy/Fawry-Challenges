package org.example.jdbc.exampleOfCreateSqlStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PreparedStatementExample {
    private static final Logger logger = Logger.getLogger(PreparedStatementExample.class.getName());
    private final Connection connection;

    public PreparedStatementExample(Connection connection) {
        this.connection = connection;
    }

    public void runSampleSQLExample() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE role = ?");
        preparedStatement.setString(1, "employee");
        ResultSet resultset = preparedStatement.executeQuery();
        while (resultset.next()) {
            for (int i = 0; i < resultset.getMetaData().getColumnCount(); i++) {
                System.out.println(resultset.getMetaData().getColumnName(i+1) + ' ' + resultset.getString(i + 1));
            }
        }
    }

    public void runSqlInjectionExample() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE role = ?");
        // Simulating an SQL injection attempt
        // Even if the input contains malicious SQL (e.g., "employee or 1 = 1"),
        // PreparedStatement treats it as a literal string, not executable SQL code
        preparedStatement.setString(1, "employee or 1 = 1");
        ResultSet resultset = preparedStatement.executeQuery(); // Safe from SQL injection
        while (resultset.next()) {
            for (int i = 0; i < resultset.getMetaData().getColumnCount(); i++) {
                System.out.println(resultset.getMetaData().getColumnName(i+1) + ' ' + resultset.getString(i + 1));
            }
        }
    }
    public void close() throws SQLException {
        connection.close();
    }
}
