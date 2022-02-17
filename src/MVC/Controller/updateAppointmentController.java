package MVC.Controller;

import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField userIDTextfield;

    @FXML
    private TextField startTextfield;

    @FXML
    private TextField endTextfield;

    @FXML
    private Button updateButton;

    @FXML
    private Button cancelButton;

    public void addButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    public void updateButtonOnAction(ActionEvent event) throws IOException {

        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

}
