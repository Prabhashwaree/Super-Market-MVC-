package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public AnchorPane mainPane;

    public void OrderButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/OrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void customerBttonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/CustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/EnterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) mainPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void OrderDetailButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/OrderDetailForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void OpenOrderDetails(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/OrderDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }
}
