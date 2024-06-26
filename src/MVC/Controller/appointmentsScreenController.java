package MVC.Controller;

import DBaccess.DBappointments;
import MVC.Model.Appointment;
import MVC.Model.User;
import Utilities.DTFormatter;
import Utilities.Languages.RBundle;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** Controls the Appointment Screen. */
public class appointmentsScreenController implements Initializable {

    ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            appointments = DBappointments.getAllAppointments();
            appointmentTableview.getItems().addAll(appointments);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        boolean alertShown = false;
        int upcomingAppointments = 0;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (User.isFirstLogin()) {
            
            int upcomingID = 0;
            LocalDate upcomingDate = null;
            LocalTime upcomingTime = null;
            
            for (Appointment appointment : appointments)
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).isBefore(LocalDateTime.now().plusMinutes(15)) && LocalDateTime.parse(appointment.getStart(), DTFormatter.format).isAfter(LocalDateTime.now()) && alertShown == false) {
                    upcomingAppointments++;
                    upcomingID = appointment.getAppointment_ID();
                    upcomingDate = LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalDate();
                    upcomingTime = LocalDateTime.parse(appointment.getStart(), DTFormatter.format).toLocalTime();
                }
            if (upcomingAppointments == 0)
                alert.setContentText(RBundle.getrBundle().getString("noUpcomingAppointment"));
            else {
                alert.setTitle(RBundle.getrBundle().getString("upcomingAppointment"));
                alert.setContentText("ID: " + upcomingID + ", Date: " + upcomingDate + ", Time: " + upcomingTime);
            }

            alert.showAndWait();
            alertShown = true;
            User.firstLogin = false;
        }


        idColumn.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointment_ID"));
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        locationColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        contactColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact_Name"));
        contactColumn.setCellFactory(TextFieldTableCell.forTableColumn());

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
    private TableColumn<Appointment, String> contactColumn;

    /** Switches scenes to the Customer View.
     * @param event  */
    public void customerViewButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    /** Switches screens to the Add Appointment screen.
     * @param event */
    public void addButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/newAppointment.fxml", "Add Appointments");
    }

    /** Sets selected appointment and calls switchScene method to open the Update Appointment Screen.
     * @param event */
    public void updateButtonOnAction(ActionEvent event) throws IOException {
        Selector.setSelectedAppointment(appointmentTableview.getSelectionModel().getSelectedItem());
        SceneSwitcher.switchScene(event, "../MVC/View/updateAppointment.fxml", "Update Appointments");
    }

    /** Checks for a selected appointment, then deletes it and delivers a message.
     * @param event */
    public void deleteButtonOnAction(ActionEvent event) throws SQLException {

        if (appointmentTableview.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(RBundle.getrBundle().getString("appointmentDeleted"));
            alert.setContentText("ID: " + Integer.toString(appointmentTableview.getSelectionModel().getSelectedItem().getAppointment_ID()) + ", Type: " + appointmentTableview.getSelectionModel().getSelectedItem().getType());
            alert.showAndWait();
            DBappointments.deleteAppointment(appointmentTableview.getSelectionModel().getSelectedItem());
            appointmentTableview.setItems(DBappointments.getAllAppointments());
            weekRadioButton.setSelected(false);
            monthRadioButton.setSelected(false);
        }
    }

    /** Controls the Week/Month View toggle for upcoming week and current month.
     * Uses a lambda function to filter for upcoming week.
     * @param event */
    public void radioButtonOnAction(ActionEvent event) throws SQLException {
        if (monthRadioButton.isSelected()) {
            ObservableList<Appointment> monthAppointments = FXCollections.observableArrayList();
            for (Appointment appointment : DBappointments.getAllAppointments()) {
                if (LocalDateTime.parse(appointment.getStart(), DTFormatter.format).getMonth() == LocalDateTime.now().getMonth())
                    monthAppointments.add(appointment);
            }
            appointmentTableview.setItems(monthAppointments);
            appointmentTableview.refresh();
        }
        if (weekRadioButton.isSelected()) {
            FilteredList<Appointment> filteredAppointments = new FilteredList<>(appointments);
            filteredAppointments.setPredicate(row -> {

                LocalDateTime rowDate = LocalDateTime.parse(row.getStart(), DTFormatter.format);

                return rowDate.isAfter(LocalDateTime.now()) && rowDate.isBefore(LocalDateTime.now().plusDays(7));
            });
            appointmentTableview.setItems(filteredAppointments);
            appointmentTableview.refresh();
        }

    }
}
