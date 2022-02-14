package MVC.Controller;

import Utilities.JDBC;
import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class loginScreenController {

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label languageLabel;

    @FXML
    private Label loginLabel;

    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextfield.getText().isBlank() || passwordTextfield.getText().isBlank()) {
            loginLabel.setText("Username & Password cannot be blank");
        } else {

            Connection connect = JDBC.getConnection();
            String validateLogin = "SELECT count(1) FROM users WHERE User_Name = '" + usernameTextfield.getText() + "' AND Password = '" + passwordTextfield.getText() + "'";

            try {

                Statement statement = connect.createStatement();
                ResultSet queryResult = statement.executeQuery(validateLogin);

                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        loginLabel.setText("Logging in...");
                        SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
                    } else {
                        loginLabel.setText("Match Not Found");
                    }
                }
                usernameTextfield.clear();
                passwordTextfield.clear();

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
