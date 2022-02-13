package Controller;

import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class newCustomerController<Divison> implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private ComboBox<Division> divisionIDCombobox;

    @FXML
    private ComboBox<Country> countryCombobox;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button cancelCustomerButton;


    public void addCustomerButtonOnAction() {
        try {


           /* Customer customer = new Customer(nameTextfield.getText(),addressTextfield.getText(), postalCodeTextfield.getText(),
                    phoneTextfield.getText(), (Division)divisionIDTextfield.getText(), countryTextfield.getText());*/
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
