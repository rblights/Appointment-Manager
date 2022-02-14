package DBaccess;

import MVC.Model.Appointment;
import MVC.Model.Division;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class DBappointments {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, " +
                    "appointments.Contact_ID, contacts.Contact_Name FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                Appointment appointment = new Appointment(appointmentID, appointmentTitle, appointmentDescription,
                        appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerID, userID, contactID, contactName);

                appointmentList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentList;
    }

    public static void insertAppointment(String Title, String Description, String Location, String Type, Date Start,
                                      Date End, Date Create_Date, String Created_By, String Last_Update, String Last_Updated_By,
                                         int Customer_ID, int User_ID, int Contact_ID) throws SQLException {

        try {

            String sql = "Insert INTO appointments VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Location);
            ps.setString(4, Type);
            ps.setDate(5, (java.sql.Date) Start);
            ps.setDate(6, (java.sql.Date) End);
            ps.setDate(7, (java.sql.Date) Create_Date);
            ps.setString(8, Created_By);
            ps.setDate(9, java.sql.Date.valueOf(Last_Update));
            ps.setString(10, Last_Updated_By);
            ps.setInt(11, Customer_ID);
            ps.setInt(12, User_ID);
            ps.setInt(13, Contact_ID);

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
