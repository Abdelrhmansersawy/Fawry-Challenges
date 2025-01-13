package org.example.jdbc;

import org.example.jdbc.exampleOfCreateSqlStatement.PreparedStatementExample;
import org.example.jdbc.exampleOfCreateSqlStatement.SQLStatementExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JDBC (Java Database Connectivity)
 *
 * JDBC Life Cycle:
 * 1. Load the JDBC driver.
 * 2. Open a database connection.
 * 3. Create a `Statement`, `PreparedStatement`, or `CallableStatement`.
 * 4. Execute SQL statements.
 * 5. Retrieve and potentially process the results of any SQL query.
 * 6. Close the database connection.
 */
public class JDBCExample {
    private static final Logger logger = Logger.getLogger(JDBCExample.class.getName());

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/fawry";
        String username = "root";
        String password = "root";

        // Step 1: Load the JDBC driver (automatically handled in modern JDBC versions)
        // Step 2: Open a database connection
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            logger.log(Level.INFO, "Successfully established the database connection");

            // Step 3: Create and execute SQL statements
            runSQLExamples(connection);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to connect to the database", e);
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }


    private static void runSQLExamples(Connection connection) throws SQLException {
        // 2.1 Create and execute a Statement object
        SQLStatementExample sqlStatementExample = new SQLStatementExample(connection);
        sqlStatementExample.runSampleSQLExample();
        sqlStatementExample.runSqlInjectionExample();

        // 2.2 Create and execute a PreparedStatement object
        PreparedStatementExample preparedStatementExample = new PreparedStatementExample(connection);
        preparedStatementExample.runSampleSQLExample();
        preparedStatementExample.runSqlInjectionExample();
        

        // Close resources
        sqlStatementExample.close();
        preparedStatementExample.close();
    }
}