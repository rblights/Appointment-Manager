package MVC.Controller;

import DBaccess.DBcustomers;
import MVC.Model.Customer;
import Utilities.Languages.RBundle;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class customersScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Customer> customers = DBcustomers.getAllCustomers();
        customerTableview.getItems().addAll(customers);

        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customer_ID"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer_Name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        postalColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("postal_Code"));
        postalColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        divisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("division_ID"));
        divisionColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

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
    private TableColumn<Customer, Integer> divisionColumn;

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

    /** Checks for selected customer, deletes them and delivers a message depending on language.
     * @param event */
    public void deleteButtonOnAction(ActionEvent event) throws SQLException {
        if (customerTableview.getSelectionModel().getSelectedItem() != null) {
            DBcustomers.deleteCustomer(customerTableview.getSelectionModel().getSelectedItem());
            customerTableview.setItems(DBcustomers.getAllCustomers());
            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(RBundle.getrBundle().getString("customerDeleted"));
                alert.showAndWait();
            }
        }
    }

    public void reportsButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/reportsScreen.fxml", "Reports");
    }

}
