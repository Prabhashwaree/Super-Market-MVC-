package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagementDashBoardFormController {
    public AnchorPane manageMainPane;

    public void ItemButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/ItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageMainPane.getChildren().clear();
        manageMainPane.getChildren().add(load);
    }

    public void AllDetailsButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/AllDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        manageMainPane.getChildren().clear();
        manageMainPane.getChildren().add(load);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/EnterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageMainPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
