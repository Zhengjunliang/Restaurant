package com.restaurant.DBUtil;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DBUtilTest {

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        // This method is executed before each test.
        // Ensure the database is connected before each test.
        DBUtil.dbConnect();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // This method is executed after each test.
        // Ensure the database is disconnected after each test.
        DBUtil.dbDisconnect();
    }

    @Test
    public void testDbConnect() {
        // Test the database connection
        try (Connection conn = DBUtil.getConnection()) {
            assertNotNull(conn, "Connection should not be null");
            assertFalse(conn.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("Connection to database should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testDbDisconnect() {
        // Test the database disconnection
        try {
            DBUtil.dbDisconnect();
            try (Connection conn = DBUtil.getConnection()) {
                assertTrue(conn.isClosed(), "Connection should be closed");
            }
        } catch (SQLException e) {
            fail("Disconnection from database should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testDbExecuteQuery() {
        // Test executing an update query
        String createTableSQL = "CREATE TABLE IF NOT EXISTS test_table (id INT PRIMARY KEY, name VARCHAR(255))";
        String insertSQL = "INSERT INTO test_table (id, name) VALUES (1, 'Test')";
        String dropTableSQL = "DROP TABLE IF EXISTS test_table";

        try {
            DBUtil.dbExecuteQuery(createTableSQL);
            DBUtil.dbExecuteQuery(insertSQL);

            ResultSet rs = DBUtil.dbExecute("SELECT * FROM test_table WHERE id = 1");
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test", rs.getString("name"), "Name should be 'Test'");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Executing query should not fail: " + e.getMessage());
        } finally {
            try {
                DBUtil.dbExecuteQuery(dropTableSQL);
            } catch (SQLException e) {
                fail("Cleaning up test database should not fail: " + e.getMessage());
            }
        }
    }

    @Test
    public void testDbExecute() {
        // Test executing a select query
        String createTableSQL = "CREATE TABLE IF NOT EXISTS test_table (id INT PRIMARY KEY, name VARCHAR(255))";
        String insertSQL = "INSERT INTO test_table (id, name) VALUES (1, 'Test')";
        String selectSQL = "SELECT * FROM test_table WHERE id = 1";
        String dropTableSQL = "DROP TABLE IF EXISTS test_table";

        try {
            DBUtil.dbExecuteQuery(createTableSQL);
            DBUtil.dbExecuteQuery(insertSQL);

            ResultSet rs = DBUtil.dbExecute(selectSQL);
            assertTrue(rs.next(), "ResultSet should have at least one row");
            assertEquals("Test", rs.getString("name"), "Name should be 'Test'");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Executing query should not fail: " + e.getMessage());
        } finally {
            try {
                DBUtil.dbExecuteQuery(dropTableSQL);
            } catch (SQLException e) {
                fail("Cleaning up test database should not fail: " + e.getMessage());
            }
        }
    }
}
