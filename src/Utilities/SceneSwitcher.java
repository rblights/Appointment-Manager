package Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Class with method for switching scenes. */
public class SceneSwitcher {

    /** Method to switch scenes.
     * @param event
     * @param title
     * @param view */
    public static void switchScene(ActionEvent event, String view, String title) throws IOException {
        try {
            Parent parent = FXMLLoader.load(SceneSwitcher.class.getResource(view));
            Scene scene = new Scene(parent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
