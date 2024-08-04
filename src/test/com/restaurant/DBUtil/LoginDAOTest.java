package com.restaurant.DBUtil;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDAOTest {

    @BeforeAll
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        // Connect to the database before running any tests
        DBUtil.dbConnect();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        // Clean up the specific test user before each test
        String deleteTestUserSQL = "DELETE FROM utente WHERE username = 'testUser'";
        DBUtil.dbExecuteQuery(deleteTestUserSQL);
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        // Disconnect from the database
        DBUtil.dbDisconnect();
    }

    @Test
    public void testIsLoginSuccess() {
        try {
            // Insert a test user
            String insertUserSQL = "INSERT INTO utente (username, password) VALUES ('testUser', 'testPass')";
            DBUtil.dbExecuteQuery(insertUserSQL);

            // Test login with correct credentials
            boolean loginSuccess = LoginDAO.isLogin("testUser", "testPass");
            assertTrue(loginSuccess, "Login should succeed with correct credentials");
        } catch (SQLException e) {
            fail("Login should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testIsLoginFailure() {
        try {
            // Insert a test user
            String insertUserSQL = "INSERT INTO utente (username, password) VALUES ('testUser', 'testPass')";
            DBUtil.dbExecuteQuery(insertUserSQL);

            // Test login with incorrect credentials
            boolean loginSuccess = LoginDAO.isLogin("testUser", "wrongPass");
            assertFalse(loginSuccess, "Login should fail with incorrect credentials");
        } catch (SQLException e) {
            fail("Login should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testIsLoginNonExistentUser() {
        try {
            // Test login with non-existent user
            boolean loginSuccess = LoginDAO.isLogin("nonExistentUser", "somePass");
            assertFalse(loginSuccess, "Login should fail for non-existent user");
        } catch (SQLException e) {
            fail("Login should not fail: " + e.getMessage());
        }
    }
}
