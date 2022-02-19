package MVC.Controller;

import DBaccess.DBappointments;
import MVC.Model.Appointment;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
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
        contactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contact_ID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, ZonedDateTime>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDate>("end"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customer_ID"));
        userColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("user_ID"));


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
    private RadioButton weekRadioButton;

    @FXML
    private ToggleGroup sort;

    @FXML
    private RadioButton monthRadioButton;

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
    private TableColumn<Appointment, ZonedDateTime> startColumn;

    @FXML
    private TableColumn<Appointment, LocalDate> endColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerColumn;

    @FXML
    private TableColumn<Appointment, Integer> userColumn;

    @FXML
    private TableColumn<Appointment, Integer> contactColumn;

    public void customerViewButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    public void addButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/newAppointment.fxml", "Add Appointments");
    }

    public void updateButtonOnAction(ActionEvent event) throws IOException {
        Selector.setSelectedAppointment(appointmentTableview.getSelectionModel().getSelectedItem());
        SceneSwitcher.switchScene(event, "../MVC/View/updateAppointment.fxml", "Update Appointments");
    }

    public void deleteButtonOnAction(ActionEvent event) throws SQLException {
        DBappointments.deleteAppointment(appointmentTableview.getSelectionModel().getSelectedItem());
        appointmentTableview.setItems(DBappointments.getAllAppointments());
    }
}
