package MVC.Controller;

import DBaccess.DBappointments;
import DBaccess.DBcontacts;
import DBaccess.DBcountries;
import MVC.Model.Appointment;
import MVC.Model.Contact;
import MVC.Model.Country;
import Utilities.DTFormatter;
import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class reportsScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int jan = 0, feb = 0, mar = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;

        int planning = 0, debriefing = 0;

        try {
            for (Appointment appointment : DBappointments.getAllAppointments()) {
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).getMonth().equals(Month.JANUARY))
                    jan++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.FEBRUARY))
                    feb++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.MARCH))
                    mar++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.APRIL))
                    apr++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.MAY))
                    may++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.JUNE))
                    jun++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.JULY))
                    jul++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.AUGUST))
                    aug++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.SEPTEMBER))
                    sep++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.OCTOBER))
                    oct++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.NOVEMBER))
                    nov++;
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate().getMonth().equals(Month.DECEMBER))
                    dec++;

                if (appointment.getType() == "Planning Session")
                    planning++;
                if (appointment.getType() == "De-Briefing")
                    debriefing++;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        janLabel.setText(String.valueOf(jan));
        febLabel.setText(String.valueOf(feb));
        marLabel.setText(String.valueOf(mar));
        aprLabel.setText(String.valueOf(apr));
        mayLabel.setText(String.valueOf(may));
        junLabel.setText(String.valueOf(jun));
        julLabel.setText(String.valueOf(jul));
        augLabel.setText(String.valueOf(aug));
        sepLabel.setText(String.valueOf(sep));
        octLabel.setText(String.valueOf(oct));
        novLabel.setText(String.valueOf(nov));
        decLabel.setText(String.valueOf(dec));

        planningLabel.setText(String.valueOf(planning));
        debriefingLabel.setText(String.valueOf(debriefing));

    }


    @FXML
    private Button customersButton;

    @FXML
    private Button contactButton;

    @FXML
    private Label planningLabel;

    @FXML
    private Label debriefingLabel;

    @FXML
    private Label janLabel;

    @FXML
    private Label febLabel;

    @FXML
    private Label marLabel;

    @FXML
    private Label aprLabel;

    @FXML
    private Label mayLabel;

    @FXML
    private Label junLabel;

    @FXML
    private Label julLabel;

    @FXML
    private Label augLabel;

    @FXML
    private Label sepLabel;

    @FXML
    private Label octLabel;

    @FXML
    private Label novLabel;

    @FXML
    private Label decLabel;

    @FXML
    private Label usLabel;

    @FXML
    private Label canLabel;

    @FXML
    private Label ukLabel;

    public void customersButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customers View");
    }

    public void contactButtonOnAction(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact Schedule");

        String contactSchedule = "";
        for (Contact contact : DBcontacts.getAllContacts()) {
            contactSchedule += contact.getContactName() + System.lineSeparator();
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

}
