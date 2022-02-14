package MVC.Controller;

import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class updateAppointmentController {

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
    private TextField userIDTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endStartPicker;

    @FXML
    private Button updateButton;

    @FXML
    private Button cancelButton;

    public void addButtonOnAction(ActionEvent event) {

    }

    public void updateButtonOnAction(ActionEvent event) {

    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
    }

}
