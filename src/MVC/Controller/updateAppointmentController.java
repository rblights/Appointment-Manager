package MVC.Controller;

import DBaccess.DBappointments;
import DBaccess.DBcontacts;
import DBaccess.DBcustomers;
import MVC.Model.*;
import Utilities.DTFormatter;
import Utilities.Languages.RBundle;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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

    /** Checks for schedule conflicts, and updates appointment to the DB.
     * Uses a lambda function to loop through each appointment to look for conflicts, triggers alert if conflictFound is true or adds appointment switches scene if false.
     * @param event */
    public void updateButtonOnAction(ActionEvent event) throws IOException, SQLException {

        AtomicBoolean conflictFound = new AtomicBoolean(false);
        ObservableList<Appointment> appointments = DBappointments.getAllAppointments();
        appointments.forEach ((appointment) -> {
            LocalDateTime oldStart = LocalDateTime.parse(appointment.getStart(), DTFormatter.format).atZone(ZoneId.of("UTC")).toLocalDateTime();
            LocalDateTime oldEnd = LocalDateTime.parse(appointment.getEnd(), DTFormatter.format).atZone(ZoneId.of("UTC")).toLocalDateTime();
            LocalDateTime newStart = LocalDateTime.of(startDatepicker.getValue(), startCombobox.getValue()).atZone(ZoneId.of("UTC")).toLocalDateTime();
            LocalDateTime newEnd = LocalDateTime.of(endDatepicker.getValue(), endCombobox.getValue()).atZone(ZoneId.of("UTC")).toLocalDateTime();

            if (customerIDCombobox.getValue().getCustomer_ID() == appointment.getCustomer_ID() && Integer.parseInt(IDTextfield.getText()) != appointment.getAppointment_ID()) {
                if ((newStart.isAfter(oldStart) || newStart.isEqual(oldStart)) && (newStart.isBefore(oldEnd)))
                    conflictFound.set(true);
                if ((newEnd.isAfter(oldStart)) && (newEnd.isBefore(oldEnd) || newEnd.isEqual(oldEnd)))
                    conflictFound.set(true);
                if ((newStart.isBefore(oldStart) || newStart.isEqual(oldStart)) && (newEnd.isAfter(oldEnd) || newEnd.isEqual(oldEnd)))
                    conflictFound.set(true);
            }
        });
        if (conflictFound.get()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(RBundle.getrBundle().getString("conflictFound"));
            alert.showAndWait();
        } else {
            if (LocalDateTime.of(startDatepicker.getValue(), startCombobox.getValue()).isBefore(LocalDateTime.of(endDatepicker.getValue(), endCombobox.getValue()))) {
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
        }
    }

    /** Switches scenes.
     * @param event */
    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointments View");
    }
}
