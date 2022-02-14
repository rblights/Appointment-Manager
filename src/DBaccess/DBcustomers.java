package DBaccess;

import MVC.Model.Customer;
import MVC.Model.Division;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBcustomers {

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, " +
                    "first_level_divisions.Division FROM customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                for (Division division : DBdivisions.getAllDivisions())
                    if (division.getDivision_ID() == divisionID) {
                        Customer customer = new Customer(customerID, customerName, customerAddress, customerPostal, customerPhone, division, divisionName);
                        customerList.add(customer);
                    }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public static void insertCustomer(String Customer_Name, String Address, String Postal_Code, String Phone, String Created_By,
                                      String Last_Updated_By, Division Division_ID) throws SQLException {

        try {

            String sql = "Insert INTO customers VALUES(NULL,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, Customer_Name);
            ps.setString(2, Address);
            ps.setString(3, Postal_Code);
            ps.setString(4, Phone);
            ps.setString(5, String.valueOf(LocalDateTime.now()));
            ps.setString(6, Created_By);
            ps.setString(7, String.valueOf(LocalDateTime.now()));
            ps.setString(8, Last_Updated_By);
            ps.setInt(9, Division_ID.getDivision_ID());

            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}



