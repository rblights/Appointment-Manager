package Controller;

import Utilities.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void loginButtonOnAction(ActionEvent e) {
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
                    } else {

                    }
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void exitButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
