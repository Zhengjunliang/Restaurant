package com.restaurant.DBUtil;

import com.restaurant.model.Dish;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DishDAOTest {

    @BeforeAll
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        // Connect to the database before running any tests
        DBUtil.dbConnect();
        // Create the table if it does not exist
        String createTableSQL = "CREATE TABLE IF NOT EXISTS dish (" +
                "id INT PRIMARY KEY, " +
                "dish VARCHAR(255), " +
                "course VARCHAR(255), " +
                "price DOUBLE, " +
                "date VARCHAR(255))";
        DBUtil.dbExecuteQuery(createTableSQL);
    }

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        // Clean up the table before each test
        String clearTableSQL = "DELETE FROM dish";
        DBUtil.dbExecuteQuery(clearTableSQL);
    }

//    @AfterAll
//    public static void tearDownClass() throws SQLException, ClassNotFoundException {
//        // Drop the table after all tests are run
//        String dropTableSQL = "DROP TABLE IF EXISTS dish";
//        DBUtil.dbExecuteQuery(dropTableSQL);
//        // Disconnect from the database
//        DBUtil.dbDisconnect();
//    }

    @Test
    public void testInsertDishData() {
        try {
            DishDAO.insertDishData(1, "Test Dish", "Main Course", 10.99, "2023-01-01");
            ObservableList<Dish> dishes = DishDAO.getAllRecords();
            assertEquals(1, dishes.size(), "There should be one dish in the database");
            Dish dish = dishes.getFirst();
            assertEquals(1, dish.getId(), "ID should be 1");
            assertEquals("Test Dish", dish.getDish(), "Dish name should be 'Test Dish'");
            assertEquals("Main Course", dish.getCourse(), "Course should be 'Main Course'");
            assertEquals(10.99, dish.getPrice(), "Price should be 10.99");
            assertEquals("2023-01-01", dish.getDate(), "Date should be '2023-01-01'");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Inserting dish data should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateDishData() {
        try {
            DishDAO.insertDishData(1, "Test Dish", "Main Course", 10.99, "2023-01-01");
            DishDAO.updateDishData(1, "Updated Dish", "Appetizer", 12.99, "2023-02-02");
            ObservableList<Dish> dishes = DishDAO.getAllRecords();
            assertEquals(1, dishes.size(), "There should be one dish in the database");
            Dish dish = dishes.getFirst();
            assertEquals(1, dish.getId(), "ID should be 1");
            assertEquals("Updated Dish", dish.getDish(), "Dish name should be 'Updated Dish'");
            assertEquals("Appetizer", dish.getCourse(), "Course should be 'Appetizer'");
            assertEquals(12.99, dish.getPrice(), "Price should be 12.99");
            assertEquals("2023-02-02", dish.getDate(), "Date should be '2023-02-02'");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Updating dish data should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteDishData() {
        try {
            DishDAO.insertDishData(1, "Test Dish", "Main Course", 10.99, "2023-01-01");
            DishDAO.deleteDishData(1);
            ObservableList<Dish> dishes = DishDAO.getAllRecords();
            assertEquals(0, dishes.size(), "There should be no dishes in the database");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Deleting dish data should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllRecords() {
        try {
            DishDAO.insertDishData(1, "Test Dish 1", "Main Course", 10.99, "2023-01-01");
            DishDAO.insertDishData(2, "Test Dish 2", "Appetizer", 5.99, "2023-01-02");
            ObservableList<Dish> dishes = DishDAO.getAllRecords();
            assertEquals(2, dishes.size(), "There should be two dishes in the database");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Retrieving all records should not fail: " + e.getMessage());
        }
    }

    @Test
    public void testSearchDishData() {
        try {
            DishDAO.insertDishData(1, "Test Dish", "Main Course", 10.99, "2023-01-01");
            ObservableList<Dish> dishes = DishDAO.searchDishData("1");
            assertEquals(1, dishes.size(), "There should be one dish in the database");
            Dish dish = dishes.getFirst();
            assertEquals(1, dish.getId(), "ID should be 1");
            assertEquals("Test Dish", dish.getDish(), "Dish name should be 'Test Dish'");
            assertEquals("Main Course", dish.getCourse(), "Course should be 'Main Course'");
            assertEquals(10.99, dish.getPrice(), "Price should be 10.99");
            assertEquals("2023-01-01", dish.getDate(), "Date should be '2023-01-01'");
        } catch (SQLException | ClassNotFoundException e) {
            fail("Searching dish data should not fail: " + e.getMessage());
        }
    }
}
