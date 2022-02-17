package MVC.Controller;

import DBaccess.DBcontacts;
import DBaccess.DBcustomers;
import MVC.Model.*;
import Utilities.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class updateAppointmentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contact> contacts = DBcontacts.getAllContacts();
        contactCombobox.setItems(contacts);

        ObservableList<Customer> customers = DBcustomers.getAllCustomers();
        customerIDCombobox.setItems(customers);
/*
        Appointment selectedAppointment = Selector.getSelectedAppointment();
        Contact selectedContact = selectedAppointment.getContct;
        Division customerDivision = selectedCustomer.getDivisionID();

        IDTextfield.setText(Integer.toString(selectedCustomer.getCustomer_ID()));
        nameTextfield.setText(selectedCustomer.getCustomer_Name());
        addressTextfield.setText(selectedCustomer.getAddress());
        postalCodeTextfield.setText(selectedCustomer.getPostal_Code());
        phoneTextfield.setText(selectedCustomer.getPhone());
        for (Country country : countryCombobox.getItems())
            if (country.getCountry_ID() == customerCountry.getCountry_ID())
                countryCombobox.setValue(customerCountry);
        for (Division division : divisionCombobox.getItems())
            if (division.getDivision_ID() == customerDivision.getDivision_ID())
                divisionCombobox.setValue(customerDivision);*/

    }

    @FXML
    private TextField IDTextfield;

    @FXML
    private TextField titleTextfield;

    @FXML
    private TextField descriptionTextfield;

    @FXML
    private TextField locationTextfield;

    @FXML
    private ComboBox<Contact> contactCombobox;

    @FXML
    private TextField typeTextfield;

    @FXML
    private ComboBox<Customer> customerIDCombobox;

    @FXML
    private TextField userIDTextfield;

    @FXML
    private TextField startTextfield;

    @FXML
    private TextField endTextfield;

    @FXML
    private Button updateButton;

    @FXML
    private Button cancelButton;

    public void updateButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointments View");
    }
}
