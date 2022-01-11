package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Validation;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManagementLoginFormController {
    public AnchorPane managementLogPane;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button butLogin;
    private String name;
    private String password;
    AnchorPane closePane;

    LinkedHashMap<TextField, Pattern> validations = new LinkedHashMap<>();
    Pattern userName = Pattern.compile("^[A-z]{3,30}$");
    Pattern passwords = Pattern.compile("^[A-z]{3,8}[@][0-9]{2,30}$");

    private  void Validates(){
        butLogin.setDisable(false);
        validations.put(txtUserName,userName);
        validations.put(txtPassword,passwords);

    }
    public void LoginKeyReleasedOnAction(KeyEvent keyEvent) {
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

    public ManagementLoginFormController() {
    }

    public ManagementLoginFormController(String name, String password) {
        this.setName(name);
        this.setPassword(password);
    }

    public void loginAccountOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("nethmini")&&txtPassword.getText().equals("admin@123")){
            txtUserName.clear();
            txtPassword.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully......", ButtonType.CLOSE).show();
            URL resource = getClass().getResource("../view/ManagementDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) managementLogPane.getScene().getWindow();
            window.setScene(new Scene(load));
            toClose();

        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again.......", ButtonType.CLOSE).show();
        }
    }
    public void setPane(AnchorPane pane){
        closePane=pane;
    }
    public void toClose(){
        Stage stage = (Stage) closePane.getScene().getWindow();
        stage.close();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}
