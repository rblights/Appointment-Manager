package MVC.Controller;

import DBaccess.DBappointments;
import DBaccess.DBcontacts;
import DBaccess.DBusers;
import MVC.Model.Appointment;
import MVC.Model.Contact;
import MVC.Model.User;
import Utilities.DTFormatter;
import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;

public class reportsScreenController {

    @FXML
    private Button cancelCustomerButton;

    @FXML
    private Button appointmentMonthButton;

    @FXML
    private Button appointmentTypeButton;

    @FXML
    private Button appointmentTypeButton1;

    @FXML
    private Button contactButton;


    /** Switches scenes.
     * @param event */
    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customers View");
    }

    /** Generates Contact Schedule Report.
     * Loops through contacts to append name to string with inner loop to append appointment info.
     * @param event */
    public void contactButtonOnAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact Schedule");

        String contactSchedule = "";
        for (Contact contact : DBcontacts.getAllContacts()) {
            contactSchedule += "Name: " + contact.getContactName() + System.lineSeparator();
            for (Appointment appointment : DBappointments.getAllAppointments()) {
                if (contact.getContactID() == appointment.getContact_ID()) {
                    contactSchedule += "ID: " + appointment.getAppointment_ID() +
                                        ", Title: " + appointment.getTitle() +
                                        ", Type:  " + appointment.getType() +
                                        ", Start: " + appointment.getStart() +
                                        ", End: " + appointment.getEnd() +
                                        ", Customer ID: " + appointment.getCustomer_ID()
                                        + System.lineSeparator();

                }
            }
        }
        alert.setContentText(contactSchedule);
        alert.setResizable(true);
        alert.showAndWait();
    }

    /** Generates User Schedule Report.
     * Loops through users to append name to string with inner loop to append appointment info.
     * @param event */
    public void userButtonOnAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Schedule");

        String userSchedule = "";
        for (User user : DBusers.getAllUsers()) {
            userSchedule += "Name: " + user.getUser_Name() + System.lineSeparator();
            for (Appointment appointment : DBappointments.getAllAppointments()) {
                if (user.getUser_ID() == appointment.getUser_ID()) {
                    userSchedule += "ID: " + appointment.getAppointment_ID() +
                            ", Title: " + appointment.getTitle() +
                            ", Type:  " + appointment.getType() +
                            ", Start: " + appointment.getStart() +
                            ", End: " + appointment.getEnd() +
                            ", Customer ID: " + appointment.getCustomer_ID()
                            + System.lineSeparator();

                }
            }
        }
        alert.setContentText(userSchedule);
        alert.setResizable(true);
        alert.showAndWait();
    }

    /** Generates Appointments By Month Report.
     * Loops through months to append names to string with inner loop that increments a counter variable to track # of appointments per month.
     * @param event */
    public void appointmentMonthButtonOnAction(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointments By Month");

        String months = "";

        for (int i = 1; i <= 12; i++) {
            int monthCounter = 0;
            months += Month.of(i)+ " - ";
            for (Appointment appointment : DBappointments.getAllAppointments()) {
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).getMonth().equals(Month.of(i))) {
                    monthCounter++;
                }
            }
            months += monthCounter + System.lineSeparator();
        }
        alert.setContentText(months);
        alert.setResizable(true);
        alert.showAndWait();
    }

    /** Generates Appointments By Month Report.
     * Loops through types to append names to string with inner loop that increments a counter variable to track # of appointments per type.
     * @param event */
    public void appointmentTypeButtonOnAction(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointments By Type");

        String[] types = new String[] { "Planning Session", "De-Briefing"};
        String type = "";

        for (int i = 0; i < types.length; i++) {
            int typeCounter = 0;
            type += types[i] + " - ";
            for (Appointment appointment : DBappointments.getAllAppointments()) {
                if (appointment.getType().equals(types[i]))
                    typeCounter++;
            }
            type += typeCounter + System.lineSeparator();
        }
        alert.setContentText(type);
        alert.setResizable(true);
        alert.showAndWait();
    }

}
