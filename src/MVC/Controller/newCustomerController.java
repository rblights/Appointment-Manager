package MVC.Controller;

import DBaccess.DBcountries;
import DBaccess.DBcustomers;
import DBaccess.DBdivisions;
import MVC.Model.Country;
import MVC.Model.Division;
import MVC.Model.User;
import Utilities.SceneSwitcher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class newCustomerController<Divison> implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Country> countries = DBcountries.getAllCountries();
        countryCombobox.setItems(countries);

        ObservableList<Division> divisions = DBdivisions.getAllDivisions();
        divisionCombobox.setItems(divisions);

    }

    @FXML
    private TextField IDTextfield;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField addressTextfield;

    @FXML
    private TextField postalCodeTextfield;

    @FXML
    private TextField phoneTextfield;

    @FXML
    private ComboBox<Country> countryCombobox;

    @FXML
    private ComboBox<Division> divisionCombobox;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button cancelCustomerButton;


    public void addCustomerButtonOnAction() throws SQLException {
        DBcustomers.insertCustomer(nameTextfield.getText(), addressTextfield.getText(), postalCodeTextfield.getText(),
                phoneTextfield.getText(), "user", "user", divisionCombobox.getValue());

        nameTextfield.clear();
        addressTextfield.clear();
        postalCodeTextfield.clear();
        phoneTextfield.clear();
        countryCombobox.getSelectionModel().clearSelection();
        divisionCombobox.getSelectionModel().clearSelection();

    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }
}
