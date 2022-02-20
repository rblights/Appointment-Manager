package DBaccess;

import MVC.Model.Appointment;
import MVC.Model.User;
import Utilities.DTFormatter;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DBappointments {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";
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

                Appointment appointment = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType,
                        DTFormatter.format.format((appointmentStart.toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(User.getCurrentUserZoneID())).toLocalDateTime()),
                        DTFormatter.format.format((appointmentEnd.toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(User.getCurrentUserZoneID())).toLocalDateTime()),
                        customerID, userID, contactID);

                appointmentList.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentList;
    }

    public static void insertAppointment(String Title, String Description, String Location, String Type, ZonedDateTime Start,
                                      ZonedDateTime End, int Customer_ID, int Contact_ID) throws SQLException {

        try {

            String sql = "Insert INTO appointments VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Location);
            ps.setString(4, Type);
            ps.setTimestamp(5, Timestamp.valueOf(Start.toLocalDateTime()));
            ps.setTimestamp(6, Timestamp.valueOf(End.toLocalDateTime()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()) );
            ps.setString(8, User.getCurrentUser());
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(10, User.getCurrentUser());
            ps.setInt(11, Customer_ID);
            ps.setInt(12, User.getCurrentUserID());
            ps.setInt(13, Contact_ID);

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAppointment (int appointmentID,String Title, String Description, String Location, String Type,
                                          ZonedDateTime Start, ZonedDateTime End, int Customer_ID, int User_ID, int Contact_ID) {

        try {

            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, " +
                    "Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Location);
            ps.setString(4, Type);
            ps.setTimestamp(5, Timestamp.valueOf(Start.toLocalDateTime()));
            ps.setTimestamp(6, Timestamp.valueOf(End.toLocalDateTime()));
            ps.setString(7, String.valueOf(LocalDateTime.now()));
            ps.setString(8, User.getCurrentUser());
            ps.setInt(9, Customer_ID);
            ps.setInt(10, User_ID);
            ps.setInt(11, Contact_ID);
            ps.setInt(12, appointmentID);



            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void deleteAppointment(Appointment appointment) {

        try {

            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, appointment.getAppointment_ID());

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
