package MVC.Controller;

import DBaccess.DBappointments;
import DBaccess.DBcontacts;
import DBaccess.DBcustomers;
import MVC.Model.*;
import Utilities.DTFormatter;
import Utilities.SceneSwitcher;
import Utilities.Selector;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class updateAppointmentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contact> contacts = DBcontacts.getAllContacts();
        contactCombobox.setItems(contacts);

        ObservableList<Customer> customers = DBcustomers.getAllCustomers();
        customerIDCombobox.setItems(customers);

        LocalTime localTimeStart = LocalTime.of(8, 0);
        LocalTime localTimeEnd = LocalTime.of(22,0);
        while (localTimeStart.isBefore(localTimeEnd.plusSeconds(1))) {
            startCombobox.getItems().add(localTimeStart);
            endCombobox.getItems().add(localTimeStart);
            localTimeStart = localTimeStart.plusMinutes(15);

        }

        Appointment selectedAppointment = Selector.getSelectedAppointment();

        IDTextfield.setText(Integer.toString(selectedAppointment.getAppointment_ID()));
        titleTextfield.setText(selectedAppointment.getTitle());
        descriptionTextfield.setText(selectedAppointment.getDescription());
        locationTextfield.setText(selectedAppointment.getLocation());

        for (Contact contact : DBcontacts.getAllContacts())
            if (contact.getContactID() == selectedAppointment.getContact_ID())
                contactCombobox.setValue(contact);

        typeTextfield.setText(selectedAppointment.getType());

        startDatepicker.setValue(LocalDateTime.parse(selectedAppointment.getStart(), DTFormatter.format).toLocalDate());
        startCombobox.getSelectionModel().select(LocalDateTime.parse(selectedAppointment.getStart(), DTFormatter.format).toLocalTime());

        endDatepicker.setValue(LocalDateTime.parse(selectedAppointment.getEnd(), DTFormatter.format).toLocalDate());
        endCombobox.getSelectionModel().select(LocalDateTime.parse(selectedAppointment.getEnd(), DTFormatter.format).toLocalTime());


        for (Customer customer : DBcustomers.getAllCustomers())
            if (customer.getCustomer_ID() == selectedAppointment.getCustomer_ID())
                customerIDCombobox.setValue(customer);

        userIDTextfield.setText(Integer.toString(User.getCurrentUserID()));

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
    private Button updateButton;

    @FXML
    private Button cancelButton;

    public void updateButtonOnAction(ActionEvent event) throws IOException {
        DBappointments.updateAppointment(Integer.parseInt(IDTextfield.getText()),
                                        titleTextfield.getText(),
                                        descriptionTextfield.getText(),
                                        locationTextfield.getText(),
                                        typeTextfield.getText(),
                                        LocalDateTime.of(startDatepicker.getValue(), startCombobox.getValue()).atZone(User.getCurrentUserZoneID()).withZoneSameInstant(ZoneId.of("UTC")),
                                        LocalDateTime.of(endDatepicker.getValue(), endCombobox.getValue()).atZone(User.getCurrentUserZoneID()).withZoneSameInstant(ZoneId.of("UTC")),
                                        customerIDCombobox.getSelectionModel().getSelectedItem().getCustomer_ID(),
                                        Integer.parseInt(userIDTextfield.getText()),
                                        contactCombobox.getSelectionModel().getSelectedItem().getContactID());

        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointments View");
    }
}
