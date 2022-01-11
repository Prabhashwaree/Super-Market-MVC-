package Controller;


import View.tm.ItemTm;
import bo.BoFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.ItemDTO;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.Validation;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ItemFormController {
    public JFXTextField txtCode;
    public JFXTextField txtPack;
    public JFXTextField txtPrize;
    public JFXTextField txtQTY;
    public JFXTextField txtDiscription;
    public TableView<ItemTm> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDiscription;
    public TableColumn colPack;
    public TableColumn colPrize;
    public TableColumn colQTY;
    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);
    public Button btnSave;
    public Button btnDelete;

    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern code = Pattern.compile("^[I][-][0-9]{3}$");
    Pattern discription = Pattern.compile("^[A-z]{3,30}$");
    Pattern pack = Pattern.compile("[0-9]{1,100}");
    Pattern prices = Pattern.compile("^[0-9]{3,100000}$");
    Pattern qty = Pattern.compile("[0-9]{1,100}");


    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtCode,code);
        validation.put(txtDiscription,discription);
        validation.put(txtPack,pack);
        validation.put(txtPrize,prices);
        validation.put(txtQTY,qty);
    }
    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validation, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO i1 = new ItemDTO(
                txtCode.getText(),txtDiscription.getText(),txtPack.getText(),
                Double.parseDouble(txtPrize.getText()),Integer.parseInt(txtQTY.getText())
        );

        if(itemBO.addItem(i1)) {
            setItemToTable();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Item...").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
        clear();

    }

    public void deleteBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       ItemTm itemTm = tblItem.getSelectionModel().getSelectedItem();
        itemBO.deleteItem(itemTm.getCode());
        setItemToTable();
        itemBO.getAllItem();
        clear();
    }

    boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemBO.ifItemExit(id);
    }

    public void updateBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String code=txtCode.getText();
        String discription=txtDiscription.getText();
        String pack=txtPack.getText();
        double prize=Double.parseDouble(txtPrize.getText());
        int qtyOnHand=Integer.parseInt(txtQTY.getText());

        try{
            if(!existItem(code)){
                setItemToTable();

            }


            ItemDTO itemDTO = new ItemDTO(code,discription,pack,prize,qtyOnHand);
            itemBO.updateItem(itemDTO);
            setItemToTable();
            new Alert(Alert.AlertType.CONFIRMATION,"Update Item...").show();
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + code + e.getMessage()).show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        ItemTm itemTm = tblItem.getSelectionModel().getSelectedItem();
        itemTm.setCode(code);
       tblItem.refresh();
    }

    void setData(ItemDTO i){
        txtCode.setText(i.getCode());
        txtDiscription.setText(i.getDiscription());
        txtPack.setText(i.getPack());
        txtPrize.setText(String.valueOf(i.getPrize()));
        txtQTY.setText(String.valueOf(i.getQtyOnHand()));

    }
    public void moveToDiscription(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    }
    public void clear(){
        txtCode.clear();
        txtDiscription.clear();
        txtPack.clear();
        txtPrize.clear();
        txtQTY.clear();
    }

    public void moveToPrize(ActionEvent actionEvent) {
        txtPrize.requestFocus();
    }

    public void moveToQTY(ActionEvent actionEvent) {
        txtQTY.requestFocus();
    }

    public void moveToPack(ActionEvent actionEvent) {
        txtPack.requestFocus();
    }

    public void initialize() {
        Validate();
           colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colDiscription.setCellValueFactory(new PropertyValueFactory<>("discription"));
            colPack.setCellValueFactory(new PropertyValueFactory<>("pack"));
            colPrize.setCellValueFactory(new PropertyValueFactory<>("prize"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        try {
            setItemToTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblItem.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDiscription.setText(newValue.getDiscription());
                txtPack.setText(newValue.getPack());
                txtPrize.setText(String.valueOf(newValue.getPrize()));
                txtQTY.setText(String.valueOf(newValue.getQtyOnHand()));

                txtCode.setDisable(false);
                txtDiscription.setDisable(false);
                txtPack.setDisable(false);
                txtPrize.setDisable(false);
                txtQTY.setDisable(false);
            }
        }));
        txtQTY.setOnAction(event -> btnSave.fire());
    }
    private void setItemToTable() throws SQLException, ClassNotFoundException {
        tblItem.setItems(itemBO.getAllItem());
    }


}
