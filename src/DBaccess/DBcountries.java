package DBaccess;

import MVC.Model.Country;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class that accesses the countries table of the DB. */
public class DBcountries {

    /** Gets the DB connection to query DB and return list of countries.
     @return countryList*/
    public static ObservableList<Country> getAllCountries() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country country = new Country(countryID, countryName);
                countryList.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countryList;
    }
}
