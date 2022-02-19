package MVC.Controller;

import MVC.Model.User;
import Utilities.JDBC;
import Utilities.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class loginScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //TimeZone.setDefault(TimeZone.getTimeZone("Europe/Sofia"));
        User.setCurrentUserZoneID(ZoneId.of(TimeZone.getDefault().getID()));
        zoneIDLabel.setText(User.getCurrentUserZoneID().toString());

    }

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label zoneIDLabel;

    @FXML
    private Label loginLabel;

    public void loginButtonOnAction(ActionEvent event) throws SQLException {
        if (usernameTextfield.getText().isBlank() || passwordTextfield.getText().isBlank()) {
            loginLabel.setText("Username & Password cannot be blank");
        } else {

            Connection connect = JDBC.getConnection();
            String sql = "SELECT User_Name, User_ID FROM users WHERE User_Name = '" + usernameTextfield.getText() + "' AND Password = '" + passwordTextfield.getText() + "'";

            try {

                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        User.setCurrentUser(rs.getString("User_Name"));
                        User.setCurrentUserID(rs.getInt("User_ID"));
                    }
                    loginLabel.setText("Logging in...");
                    SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
                } else {
                    loginLabel.setText("Match Not Found");
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
