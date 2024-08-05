package com.restaurant.administrator;

import com.restaurant.DBUtil.StaffDAO;
import com.restaurant.model.Staff;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class StaffController {

    @FXML private TextField searchId;

    @FXML private TableView<Staff> staffTable;
    @FXML private TableColumn<Staff, Integer> colStaffId;
    @FXML private TableColumn<Staff, String> colStaffName;
    @FXML private TableColumn<Staff, String> colStaffGender;
    @FXML private TableColumn<Staff, Integer> colStaffAge;
    @FXML private TableColumn<Staff, String> colStaffRole;
    @FXML private TableColumn<Staff, String> colStaffSalary;
    @FXML private TableColumn<Staff, String> colStaffDate;
    @FXML private ImageView returnButton;

    @FXML
    public void initialize() throws Exception{
        colStaffId.setCellValueFactory(cellData -> cellData.getValue().getStaffId().asObject());
        colStaffName.setCellValueFactory(cellData -> cellData.getValue().getStaffName());
        colStaffGender.setCellValueFactory(cellData -> cellData.getValue().getStaffGender());
        colStaffAge.setCellValueFactory(cellData -> cellData.getValue().getStaffAge().asObject());
        colStaffRole.setCellValueFactory(cellData -> cellData.getValue().getStaffRole());
        colStaffSalary.setCellValueFactory(cellData -> cellData.getValue().getStaffSalary());
        colStaffDate.setCellValueFactory(cellData -> cellData.getValue().getStaffDate());
        ObservableList<Staff> staffList = StaffDAO.getAllRecords();
        populateTable(staffList);
    }

    private void populateTable(ObservableList<Staff> staffList) {
        staffTable.setItems(staffList);
    }


    @FXML
    public void refreshStaff() throws SQLException, ClassNotFoundException {
        ObservableList<Staff> staffList = StaffDAO.getAllRecords();
        populateTable(staffList);
    }

    @FXML
    public void addStaff() throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffAdd.fxml")));
            Scene admStaff = new Scene(root);
            //get stage information
            Stage window = new Stage();
            window.setScene(admStaff);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening addStaff page");
            throw e;
        }
    }

    @FXML
    public void updateStaff() throws Exception {
        try {
            Staff staff = staffTable.getSelectionModel().getSelectedItem();
            if(staff != null) {
                StaffUpdateController.setCurrent(staff);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StaffUpdate.fxml")));

                //get stage information
                Stage window = new Stage();
                window.setScene(new Scene(root));
                window.show();
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening updateStaff page");
            throw e;
        }
    }

    @FXML
    private void deleteStaff(ActionEvent event) throws ClassNotFoundException, SQLException {
        try{
            Staff staff = staffTable.getSelectionModel().getSelectedItem();
            if(staff != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Dismissal");
                alert.setHeaderText("Do you really want to fire this employee?");
                alert.setContentText("Press OK to fire the current employee");

                if (alert.showAndWait().get() == ButtonType.OK) {
                StaffDAO.deleteStaffByID(staff.getId());
                ObservableList<Staff> staffList = StaffDAO.getAllRecords();
                populateTable(staffList);
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error occurred while deleting staff");
            throw e;
        }
    }

    @FXML
    public void searchStaff() throws ClassNotFoundException, SQLException {
        if(searchId.getText().isEmpty()){
            ObservableList<Staff> staffList = StaffDAO.getAllRecords();
            populateTable(staffList);
        }
        else{
            ObservableList<Staff> staffList = StaffDAO.searchStaffData(searchId.getText());

            if(!staffList.isEmpty()){
                populateTable(staffList);
            }
            else
            {
                System.out.println("Staff not found");
            }
        }
    }

    public void return_back(MouseEvent event)  throws Exception{
        try {
            // Ottieni il riferimento alla finestra corrente
            Stage stage = (Stage) returnButton.getScene().getWindow();

            // Carica la nuova vista
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorView.fxml")));
            Scene admStaff = new Scene(root);

            // Imposta la nuova scena alla finestra corrente
            stage.setScene(admStaff);
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Admin page");
            throw e;
        }
    }
}
