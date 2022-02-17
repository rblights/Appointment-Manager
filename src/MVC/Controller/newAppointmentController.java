package MVC.Controller;

import DBaccess.DBappointments;
import MVC.Model.User;
import Utilities.DTFormatter;
import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class newAppointmentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        startTextfield.setPromptText(DTFormatter.format.format(LocalDateTime.now().plusHours(1)));
        endTextfield.setPromptText(DTFormatter.format.format(LocalDateTime.now().plusHours(2)));

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
    private TextField contactTextfield;

    @FXML
    private TextField typeTextfield;

    @FXML
    private TextField customerIDTextfield;

    @FXML
    private TextField userIDTextfield;

    @FXML
    private TextField startTextfield;

    @FXML
    private TextField endTextfield;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    public void addButtonOnAction(ActionEvent event) throws IOException, SQLException {
        DBappointments.insertAppointment(titleTextfield.getText(),
                                         descriptionTextfield.getText(),
                                         locationTextfield.getText(),
                                         typeTextfield.getText(),
                                         LocalDateTime.parse(startTextfield.getText(), DTFormatter.format),
                                         LocalDateTime.parse(endTextfield.getText(), DTFormatter.format),
                                         Integer.parseInt(customerIDTextfield.getText()),
                                         Integer.parseInt(contactTextfield.getText()));

        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

}
