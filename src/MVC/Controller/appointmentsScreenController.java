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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        contactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contact_ID"));
        contactColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        typeColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        startColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
        startColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        endColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
        endColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        customerColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customer_ID"));
        customerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        userColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("user_ID"));
        customerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


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
    private TableColumn<Appointment, String> startColumn;

    @FXML
    private TableColumn<Appointment, String> endColumn;

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
