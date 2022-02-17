package MVC.Controller;

import DBaccess.DBcustomers;
import MVC.Model.Customer;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class customersScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customer> customers;

        customers = DBcustomers.getAllCustomers();
        customerTableview.getItems().addAll(customers);

        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customer_ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_Name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postal_Code"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("divisionName"));

    }

    @FXML
    private Button appointmentsViewButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button reportsButton;

    @FXML
    private TableView<Customer> customerTableview;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> postalColumn;

    @FXML
    private TableColumn<Customer, String> divisionColumn;

    public void appointmentViewButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

    public void addButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/newCustomer.fxml", "Add Customer");
    }

    public void updateButtonOnAction(ActionEvent event) throws IOException {
        Selector.setSelectedCustomer(customerTableview.getSelectionModel().getSelectedItem());
        SceneSwitcher.switchScene(event, "../MVC/View/updateCustomer.fxml", "Update Customer");
    }

    public void deleteButtonOnAction(ActionEvent event) throws SQLException {
        DBcustomers.deleteCustomer(customerTableview.getSelectionModel().getSelectedItem());
        customerTableview.setItems(DBcustomers.getAllCustomers());
    }

}
