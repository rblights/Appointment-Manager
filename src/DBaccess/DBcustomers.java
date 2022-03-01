package DBaccess;

import MVC.Model.Appointment;
import MVC.Model.Customer;
import MVC.Model.Division;
import MVC.Model.User;
import Utilities.JDBC;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/** Class that accesses the customers table of the DB. */
public class DBcustomers {

    /** Gets the DB connection to query DB and return list of customers.
     @return customerList*/
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");

                Customer customer = new Customer(customerID, customerName, customerAddress, customerPostal, customerPhone, divisionID);
                customerList.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    /** Uses a PreparedStatement to insert a customer into the DB.
     * @param Address
     * @param Customer_Name
     * @param Division_ID
     * @param Phone
     * @param Postal_Code */
    public static void insertCustomer(String Customer_Name, String Address, String Postal_Code, String Phone, Division Division_ID) throws SQLException {

        try {

            String sql = "Insert INTO customers VALUES(NULL,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, User.getCurrentUser());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, User.getCurrentUser());
            ps.setInt(9, Division_ID.getDivision_ID());

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Uses a PreparedStatement to update a customer in the DB.
     * @param Postal_Code
     * @param Division_ID
     * @param Phone
     * @param Customer_Name
     * @param Address
     * @param Customer_ID */
    public static void updateCustomer(int Customer_ID,String Customer_Name, String Address, String Postal_Code, String Phone, int Division_ID) {

        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, " +
                    "Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, User.getCurrentUser());
            ps.setInt(7, Division_ID);
            ps.setInt(8, Customer_ID);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Uses a PreparedStatement to delete a customer from the DB.
     * @param customer */
    public static void deleteCustomer(Customer customer) throws SQLException {
        for (Appointment appointment : DBappointments.getAllAppointments()) {
            if (appointment.getCustomer_ID() == customer.getCustomer_ID())
                DBappointments.deleteAppointment(appointment);

        }
        try {

            String sql = "DELETE FROM customers WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customer.getCustomer_ID());

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



