package MVC.Controller;

import DBaccess.*;
import MVC.Model.*;
import Utilities.DTFormatter;
import Utilities.Languages.RBundle;
import Utilities.SceneSwitcher;
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

        AtomicBoolean conflictFound = new AtomicBoolean(false);
        ObservableList<Appointment> appointments = DBappointments.getAllAppointments();
        appointments.forEach((appointment) -> {
                    LocalDateTime oldStart = LocalDateTime.parse(appointment.getStart(), DTFormatter.format);
                    LocalDateTime oldEnd = LocalDateTime.parse(appointment.getEnd(), DTFormatter.format);
                    LocalDateTime newStart = LocalDateTime.of(startDatepicker.getValue(), startCombobox.getValue());
                    LocalDateTime newEnd = LocalDateTime.of(endDatepicker.getValue(), endCombobox.getValue());

                    if (customerIDCombobox.getValue().getCustomer_ID() == appointment.getCustomer_ID()) {
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
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

}
