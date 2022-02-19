package MVC.Controller;

import DBaccess.*;
import MVC.Model.*;
import Utilities.DTFormatter;
import Utilities.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class newAppointmentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contact> contacts = DBcontacts.getAllContacts();
        contactCombobox.setItems(contacts);

        ObservableList<Customer> customers = DBcustomers.getAllCustomers();
        customerIDCombobox.setItems(customers);

        userIDTextfield.setText(Integer.toString(User.getCurrentUserID()));

        LocalTime localTimeStart = LocalTime.of(8, 0);
        LocalTime localTimeEnd = LocalTime.of(22,0);
        while (localTimeStart.isBefore(localTimeEnd.plusSeconds(1))) {
            startCombobox.getItems().add(localTimeStart);
            endCombobox.getItems().add(localTimeStart);
            localTimeStart = localTimeStart.plusMinutes(15);

        }
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
    private DatePicker startDatepicker;

    @FXML
    private DatePicker endDatepicker;

    @FXML
    private ComboBox<LocalTime> startCombobox;

    @FXML
    private ComboBox<LocalTime> endCombobox;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    public void addButtonOnAction(ActionEvent event) throws IOException, SQLException {

        String chosenContact = String.valueOf(contactCombobox.getValue());
        int chosenContactID = 0;

        for (Contact contact : DBcontacts.getAllContacts()) {
            if (contact.getContactName().equals(chosenContact)) {
                chosenContactID = contact.getContactID();
            }
        }
        DBappointments.insertAppointment(titleTextfield.getText(),
                                         descriptionTextfield.getText(),
                                         locationTextfield.getText(),
                                         typeTextfield.getText(),
                                         LocalDateTime.of(startDatepicker.getValue(), startCombobox.getValue()).atZone(User.getCurrentUserZoneID()).withZoneSameInstant(ZoneId.of("UTC")),
                                         LocalDateTime.of(endDatepicker.getValue(), endCombobox.getValue()).atZone(User.getCurrentUserZoneID()).withZoneSameInstant(ZoneId.of("UTC")),
                                         Integer.parseInt(String.valueOf(customerIDCombobox.getValue())),
                                         chosenContactID);

        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Customer View");
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

}
