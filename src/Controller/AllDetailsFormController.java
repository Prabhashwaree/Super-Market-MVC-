package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AllDetailsFormController {
    public AnchorPane allDetailPane;

    public void ItemViewTblOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/ItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        allDetailPane.getChildren().clear();
        allDetailPane.getChildren().add(load);

    }

    public void orderTblViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/OrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        allDetailPane.getChildren().clear();
        allDetailPane.getChildren().add(load);

    }

    public void CustomerTvlViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/CustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        allDetailPane.getChildren().clear();
        allDetailPane.getChildren().add(load);


    }
}
