package MVC.Controller;

import MVC.Model.User;
import Utilities.ActivityLog;
import Utilities.JDBC;
import Utilities.Languages.RBundle;
import Utilities.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class loginScreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ActivityLog.createLog();

        //Locale.setDefault(Locale.FRANCE);

        ObservableList<String> languages = FXCollections.observableArrayList("English", "Français");
        languageCombobox.setItems(languages);

        if (Locale.getDefault() == Locale.FRANCE)
            languageCombobox.setValue("Français");
        else
            languageCombobox.setValue("English");

        //languageCombobox.setOnAction(event);


        //TimeZone.setDefault(TimeZone.getTimeZone("Europe/Sofia"));
        User.setCurrentUserZoneID(ZoneId.of(TimeZone.getDefault().getID()));
        zoneIDLabel.setText(User.getCurrentUserZoneID().toString());

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            titleLabel.setText(RBundle.getrBundle().getString("appointmentManager"));
            usernameTextfield.setPromptText(RBundle.getrBundle().getString("username"));
            passwordTextfield.setPromptText(RBundle.getrBundle().getString("password"));
            loginButton.setText(RBundle.getrBundle().getString("login"));
            exitButton.setText(RBundle.getrBundle().getString("exit"));

        }

    }

    @FXML
    private Label titleLabel;

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
    private ComboBox<String> languageCombobox;

    /*EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            switch (languageCombobox.getValue()) {
                case "English":
                    Locale.setDefault(Locale.US);
                    titleLabel.setText(RBundle.getrBundle().getString("appointmentManager"));
                    usernameTextfield.setPromptText(RBundle.getrBundle().getString("username"));
                    passwordTextfield.setPromptText(RBundle.getrBundle().getString("password"));
                    loginButton.setText(RBundle.getrBundle().getString("login"));
                    exitButton.setText(RBundle.getrBundle().getString("exit"));
                    break;
                case "Français":
                    Locale.setDefault(Locale.FRANCE);
                    titleLabel.setText(RBundle.getrBundle().getString("appointmentManager"));
                    usernameTextfield.setPromptText(RBundle.getrBundle().getString("username"));
                    passwordTextfield.setPromptText(RBundle.getrBundle().getString("password"));
                    loginButton.setText(RBundle.getrBundle().getString("login"));
                    exitButton.setText(RBundle.getrBundle().getString("exit"));
                    break;
            }

        }
    };*/



    public void languageComboboxOnAction(ActionEvent event) {

        switch (languageCombobox.getValue()) {
            case "English":
                Locale.setDefault(Locale.US);
                titleLabel.setText(RBundle.getrBundle().getString("appointmentManager"));
                usernameTextfield.setPromptText(RBundle.getrBundle().getString("username"));
                passwordTextfield.setPromptText(RBundle.getrBundle().getString("password"));
                loginButton.setText(RBundle.getrBundle().getString("login"));
                exitButton.setText(RBundle.getrBundle().getString("exit"));
                break;
            case "Français":
                Locale.setDefault(Locale.FRANCE);
                titleLabel.setText(RBundle.getrBundle().getString("appointmentManager"));
                usernameTextfield.setPromptText(RBundle.getrBundle().getString("username"));
                passwordTextfield.setPromptText(RBundle.getrBundle().getString("password"));
                loginButton.setText(RBundle.getrBundle().getString("login"));
                exitButton.setText(RBundle.getrBundle().getString("exit"));
                break;
        }
    }

    /** Validates login info, displays error messages.
     * @param event */
    public void loginButtonOnAction(ActionEvent event) throws SQLException {

        if (usernameTextfield.getText().isBlank() || passwordTextfield.getText().isBlank()) {
            if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(RBundle.getrBundle().getString("error"));
                alert.setContentText(RBundle.getrBundle().getString("blankNamePW"));
                alert.showAndWait();
            }
            ActivityLog.getLogger().info(ActivityLog.getCurrentTime() + " FAILED - Username: " + usernameTextfield.getText() + " Password: " + passwordTextfield.getText());

        } else {

            Connection connect = JDBC.getConnection();
            String sql = "SELECT User_Name, User_ID FROM users WHERE User_Name = '" + usernameTextfield.getText() +
                        "' AND Password = '" + passwordTextfield.getText() + "'";

            try {

                Statement statement = connect.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        User.setCurrentUser(rs.getString("User_Name"));
                        User.setCurrentUserID(rs.getInt("User_ID"));
                        ActivityLog.getLogger().info(ActivityLog.getCurrentTime() + " SUCCESS - Username: " + usernameTextfield.getText() + " Password: " + passwordTextfield.getText());
                    }
                    SceneSwitcher.switchScene(event, "../MVC/View/appointmentsScreen.fxml", "Appointment View");
                } else {
                    ActivityLog.getLogger().info(ActivityLog.getCurrentTime() + " FAILED - Username: " + usernameTextfield.getText() + " Password: " + passwordTextfield.getText());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(RBundle.getrBundle().getString("error"));
                    alert.setContentText(RBundle.getrBundle().getString("noMatch"));
                    alert.showAndWait();
                }

                usernameTextfield.clear();
                passwordTextfield.clear();

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** Closes the program.
     * @param event*/
    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
