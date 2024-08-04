package com.restaurant.DBUtil;

import com.restaurant.model.Staff;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class StaffDAOTest {

    @BeforeAll
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        // Connect to the database before running any tests
        DBUtil.dbConnect();
        // Create the table if it does not exist
        String createTableSQL = "CREATE TABLE IF NOT EXISTS staff (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "gender VARCHAR(50) NOT NULL, " +
                "age INT NOT NULL, " +
                "role VARCHAR(255) NOT NULL, " +
                "salary VARCHAR(255) NOT NULL, " +
                "date VARCHAR(255) NOT NULL)";
        DBUtil.dbExecuteQuery(createTableSQL);
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        // Clean up test data before each test
        String deleteTestStaffSQL = "DELETE FROM staff";
        DBUtil.dbExecuteQuery(deleteTestStaffSQL);
    }


    @AfterAll
    public static void tearDownClass() throws SQLException, ClassNotFoundException {
        // Clean up all data in the table after all tests are done
        String deleteAllStaffSQL = "DELETE FROM staff";
        DBUtil.dbExecuteQuery(deleteAllStaffSQL);
        // Disconnect from the database
        DBUtil.dbDisconnect();
    }

    @Test
    public void testInsertStaffData() {
        try {
            // Insert a test staff
            StaffDAO.insertStaffData(1, "testStaff", "Male", 30, "Manager", "5000", "2023-08-01");

            // Verify the staff was inserted
            ObservableList<Staff> staffList = StaffDAO.searchStaffData("1");
            assertFalse(staffList.isEmpty(), "Staff should be inserted");

            Staff staff = staffList.getFirst();
            assertEquals("testStaff", staff.getName(), "Staff name should match");
            assertEquals("Male", staff.getGender(), "Staff gender should match");
            assertEquals(30, staff.getAge(), "Staff age should match");
            assertEquals("Manager", staff.getRole(), "Staff role should match");
            assertEquals("5000", staff.getSalary(), "Staff salary should match");
            assertEquals("2023-08-01", staff.getDate(), "Staff date should match");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllRecords() {
        try {
            // Insert a test staff
            StaffDAO.insertStaffData(1, "testStaff", "Male", 30, "Manager", "5000", "2023-08-01");

            // Verify the staff is present in all records
            ObservableList<Staff> staffList = StaffDAO.getAllRecords();
            boolean found = staffList.stream().anyMatch(staff -> "testStaff".equals(staff.getName()));
            assertTrue(found, "Inserted staff should be in all records");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSumSalaryData() {
        try {
            // Insert test staff
            StaffDAO.insertStaffData(1, "testStaff", "Male", 30, "Manager", "5000", "2023-08-01");
            StaffDAO.insertStaffData(2, "testStaff", "Female", 25, "Chef", "3000", "2023-08-02");

            // Verify the sum of salaries
            double totalSalary = StaffDAO.sumSalaryData();
            assertEquals(8000.0, totalSalary, 0.01, "Total salary should be the sum of inserted staff salaries");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSearchStaffData() {
        try {
            // Insert a test staff
            StaffDAO.insertStaffData(1, "testStaff", "Male", 30, "Manager", "5000", "2023-08-01");

            // Verify the staff can be found by ID
            ObservableList<Staff> staffList = StaffDAO.searchStaffData("1");
            assertFalse(staffList.isEmpty(), "Staff should be found by ID");

            Staff staff = staffList.getFirst();
            assertEquals("testStaff", staff.getName(), "Staff name should match");
            assertEquals("Male", staff.getGender(), "Staff gender should match");
            assertEquals(30, staff.getAge(), "Staff age should match");
            assertEquals("Manager", staff.getRole(), "Staff role should match");
            assertEquals("5000", staff.getSalary(), "Staff salary should match");
            assertEquals("2023-08-01", staff.getDate(), "Staff date should match");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
