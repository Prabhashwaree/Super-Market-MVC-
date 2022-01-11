package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CreateAccountFormController {
    public AnchorPane CreateAccount;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtComfirm;

    public void BackToLogin(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/EnterForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) CreateAccount.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        CreateAccount.getChildren().clear();
        CreateAccount.getChildren().add(load);
    }

    public void moveToLastName(ActionEvent actionEvent) {
        txtLastName.requestFocus();
    }

    public void moveToEmail(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void moveToComfirm(ActionEvent actionEvent) {
        txtComfirm.requestFocus();
    }
}
