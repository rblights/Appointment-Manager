package MVC.Controller;

import DBaccess.DBcountries;
import DBaccess.DBdivisions;
import MVC.Model.Country;
import MVC.Model.Customer;
import MVC.Model.Division;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class updateCustomerController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Country> countries = DBcountries.getAllCountries();
        countryCombobox.setItems(countries);

        ObservableList<Division> divisions = DBdivisions.getAllDivisions();
        divisionCombobox.setItems(divisions);

        Customer selectedCustomer = Selector.getSelectedCustomer();
        Country customerCountry = selectedCustomer.getDivisionID().getCountry_ID();
        Division customerDivision = selectedCustomer.getDivisionID();

        IDTextfield.setText(Integer.toString(selectedCustomer.getCustomer_ID()));
        nameTextfield.setText(selectedCustomer.getCustomer_Name());
        addressTextfield.setText(selectedCustomer.getAddress());
        postalCodeTextfield.setText(selectedCustomer.getPostal_Code());
        phoneTextfield.setText(selectedCustomer.getPhone());
        for (Country country : countryCombobox.getItems())
            if (country.getCountry_ID() == customerCountry.getCountry_ID())
                countryCombobox.setValue(customerCountry);
        for (Division division : divisionCombobox.getItems())
            if (division.getDivision_ID() == customerDivision.getDivision_ID())
                divisionCombobox.setValue(customerDivision);
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
    private Button updateCustomerButton;

    @FXML
    private Button cancelCustomerButton;

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }
}
