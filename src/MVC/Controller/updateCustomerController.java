package MVC.Controller;

import DBaccess.DBcountries;
import DBaccess.DBcustomers;
import DBaccess.DBdivisions;
import MVC.Model.Country;
import MVC.Model.Customer;
import MVC.Model.Division;
import Utilities.SceneSwitcher;
import Utilities.Selector;
import javafx.collections.FXCollections;
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

        Customer selectedCustomer = Selector.getSelectedCustomer();

        IDTextfield.setText(Integer.toString(selectedCustomer.getCustomer_ID()));
        nameTextfield.setText(selectedCustomer.getCustomer_Name());
        addressTextfield.setText(selectedCustomer.getAddress());
        postalCodeTextfield.setText(selectedCustomer.getPostal_Code());
        phoneTextfield.setText(selectedCustomer.getPhone());
        for (Division division : DBdivisions.getAllDivisions())
            if (division.getDivision_ID() == selectedCustomer.getDivision_ID()) {
                divisionCombobox.setValue(division);
                for (Country country : DBcountries.getAllCountries())
                    if (country.getCountry_ID() == division.getCountry_ID())
                    countryCombobox.setValue(country);
            }
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

    /** Filters the divisionComboBox by countryComboBox selection.
     * @param event */
    public void countryComboboxOnAction(ActionEvent event) {
        ObservableList<Division> filteredDivisions = FXCollections.observableArrayList();
        for (Division division : DBdivisions.getAllDivisions()) {
            if (division.getCountry_ID() == countryCombobox.getSelectionModel().getSelectedItem().getCountry_ID())
                filteredDivisions.add(division);
        }
        divisionCombobox.valueProperty().set(null);
        divisionCombobox.getItems().clear();
        divisionCombobox.getItems().addAll(filteredDivisions);
    }

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }

    /** Updates customer to the DB.
     * @param event */
    public void updateButtonOnAction(ActionEvent event) throws IOException {

        int updatedDivisionID = 0;

        for (Division division : DBdivisions.getAllDivisions())
            if (division == divisionCombobox.getSelectionModel().getSelectedItem())
                updatedDivisionID = division.getDivision_ID();

        DBcustomers.updateCustomer(Integer.parseInt(IDTextfield.getText()),
                                    nameTextfield.getText(),
                                    addressTextfield.getText(),
                                    postalCodeTextfield.getText(),
                                    phoneTextfield.getText(),
                                    updatedDivisionID);

        SceneSwitcher.switchScene(event, "../MVC/View/customersScreen.fxml", "Customer View");
    }
}
