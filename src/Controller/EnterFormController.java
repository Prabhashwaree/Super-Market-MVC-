package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Validation;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class EnterFormController {
    public AnchorPane enterFormPane;
    public TextField txtUserName;
    public TextField txtPassword;
    public JFXButton butLogin;
    public Label lblWrongLongin;
    private String name;
    private String password;

    LinkedHashMap<TextField, Pattern> validations = new LinkedHashMap<>();
    Pattern userName = Pattern.compile("^[A-z]{3,30}$");
    Pattern passwords = Pattern.compile("^[A-z]{3,8}[@][0-9]{2,30}$");

    private  void Validates(){
        butLogin.setDisable(false);
        validations.put(txtUserName,userName);
        validations.put(txtPassword,passwords);

    }
    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validations,butLogin);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }
    public void initialize(){
        Validates();
    }
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("Gayathree")&&txtPassword.getText().equals("gaya@123")){
            txtUserName.clear();
            txtPassword.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully......", ButtonType.CLOSE).show();
            URL resource = getClass().getResource("../View/DashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) enterFormPane.getScene().getWindow();
            window.setScene(new Scene(load));
       }else {
            lblWrongLongin.setText("Please enter your correct Password or user Name.");
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void OpenCreateAccount(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/CreateAccountForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) enterFormPane.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void managementLoginButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManagementLoginForm.fxml"));
        Parent load = fxmlLoader.load();
        ManagementLoginFormController managementLoginFormController = fxmlLoader.getController();
        managementLoginFormController.setPane(enterFormPane);
        Scene scene = new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
