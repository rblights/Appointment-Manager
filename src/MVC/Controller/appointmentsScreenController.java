package MVC.Controller;

import DBaccess.DBappointments;
import DBaccess.DBcontacts;
import MVC.Model.Appointment;
import MVC.Model.Contact;
import Utilities.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class appointmentsScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Appointment> appointments;

        try {
            appointments = DBappointments.getAllAppointments();
            appointmentTableview.getItems().addAll(appointments);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointment_ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("end"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customer_ID"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("user_ID"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("contactName"));


    }

    @FXML
    private Button customerViewButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Appointment> appointmentTableview;

    @FXML
    private TableColumn<Appointment, Integer> idColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, LocalDate> startColumn;

    @FXML
    private TableColumn<Appointment, LocalDate> endColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerColumn;

    @FXML
    private TableColumn<Appointment, Integer> userColumn;

    @FXML
    private TableColumn<Contact, String> contactColumn;

    public void customerViewButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    public void addButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/newAppointment.fxml", "Add Appointments");
    }

    public void updateButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/updateAppointment.fxml", "Update Appointments");
    }

    public void deleteButtonOnAction(ActionEvent event) {}
}
