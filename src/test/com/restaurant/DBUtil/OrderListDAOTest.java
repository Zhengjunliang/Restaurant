package com.restaurant.DBUtil;

import com.restaurant.model.Order;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class OrderListDAOTest {

    @BeforeAll
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        // Connect to the database before running any tests
        DBUtil.dbConnect();
        // Create the table if it does not exist
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orders (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "date VARCHAR(255) NOT NULL)";
        DBUtil.dbExecuteQuery(createTableSQL);
    }

    @BeforeEach
    public void setUp() throws SQLException {
        // Clean up test data before each test
        String deleteTestOrdersSQL = "DELETE FROM orders";
        DBUtil.dbExecuteQuery(deleteTestOrdersSQL);
    }

    @AfterAll
    public static void tearDownClass() throws SQLException {
        //Clean up all data in the table after all tests are done
        String deleteTestOrdersSQL = "DELETE FROM orders";
        DBUtil.dbExecuteQuery(deleteTestOrdersSQL);
        // Disconnect from the database
        DBUtil.dbDisconnect();
    }

    @Test
    public void testInsertOrderData() {
        try {
            // Insert a test order
            OrderListDAO.insertOrderData("testOrder", 100.0, "2023-08-01");

            // Verify the order was inserted
            ObservableList<Order> orders = OrderListDAO.searchOrderDataByName("testOrder");
            assertFalse(orders.isEmpty(), "Order should be inserted");

            Order order = orders.getFirst();
            assertEquals("testOrder", order.getCustomerProperty(), "Order name should match");
            assertEquals(100.0, order.getTotalPriceProperty(), "Order price should match");
            assertEquals("2023-08-01", order.getDataProperty(), "Order date should match");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllRecords() {
        try {
            // Insert a test order
            OrderListDAO.insertOrderData("testOrder", 100.0, "2023-08-01");

            // Verify the order is present in all records
            ObservableList<Order> orders = OrderListDAO.getAllRecords();
            boolean found = orders.stream().anyMatch(order -> "testOrder".equals(order.getCustomerProperty()));
            assertTrue(found, "Inserted order should be in all records");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSumPriceData() {
        try {
            // Insert test orders
            OrderListDAO.insertOrderData("testOrder", 100.0, "2023-08-01");
            OrderListDAO.insertOrderData("testOrder", 200.0, "2023-08-02");

            // Verify the sum of prices
            double totalPrice = OrderListDAO.sumPriceData();
            assertEquals(300.0, totalPrice, 0.01, "Total price should be the sum of inserted orders");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSearchOrderData() {
        try {
            // Insert a test order
            OrderListDAO.insertOrderData("testOrder", 100.0, "2023-08-01");

            // Verify the order can be found by ID
            ObservableList<Order> orders = OrderListDAO.searchOrderDataByName("testOrder");
            assertFalse(orders.isEmpty(), "Order should be found by ID");

            Order order = orders.getFirst();
            assertEquals("testOrder", order.getCustomerProperty(), "Order name should match");
            assertEquals(100.0, order.getTotalPriceProperty(), "Order price should match");
            assertEquals("2023-08-01", order.getDataProperty(), "Order date should match");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
