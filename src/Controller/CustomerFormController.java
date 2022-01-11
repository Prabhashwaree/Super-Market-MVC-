package Controller;

import View.tm.CustomerTm;
import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import dto.CustomerDTO;
import util.Validation;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class CustomerFormController {
    public AnchorPane customerPane;
    public JFXTextField txtId;
    public JFXTextField txtCity;
    public JFXTextField txtAddress;
    public JFXTextField txtProvince;
    public JFXTextField txtPostal;
    public JFXTextField txtName;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostal;
    public JFXTextField txtTitle;

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);
    public Button btnDelete;
    public Button btnSave;

    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern id = Pattern.compile("^[C][-][0-9]{3}$");
    Pattern title = Pattern.compile("^[A-z]{1,30}$");
    Pattern name = Pattern.compile("[A-z]{3,30}[ ][A-z]{3,30}");
    Pattern address = Pattern.compile("[A-z]{3,30}[ ][A-z]{3,30}");
    Pattern city = Pattern.compile("[A-z]{2,30}");
    Pattern province = Pattern.compile("[A-z]{2,30}");
    Pattern postal = Pattern.compile("^[0-9]{1,1000}$");

    public void moveToName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    }

    private  void Validate(){
        btnSave.setDisable(false);
        validation.put(txtId,id);
        validation.put(txtTitle,title);
        validation.put(txtName,name);
        validation.put(txtAddress,address);
        validation.put(txtCity,city);
        validation.put(txtProvince,province);
        validation.put(txtPostal,postal);
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

    public void moveToProvince(ActionEvent actionEvent) {
        txtProvince.requestFocus();
    }

    public void moveToCity(ActionEvent actionEvent) {
        txtCity.requestFocus();
    }

    public void moveToPostal(ActionEvent actionEvent) {
        txtPostal.requestFocus();
    }

    public void moveToAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void clear(){
        txtId.clear();
        txtTitle.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostal.clear();
    }
    void setData(CustomerDTO c){
        txtId.setText(c.getCustomerId());
        txtTitle.setText(c.getTitle());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostal.setText(c.getPostalCode());
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        customerBO.deleteCustomer(selectedItem.getCustomerId());
        setCustomersToTable();
        customerBO.getAllCustomer();
        clear();
    }
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.ifCustomerExit(id);
    }

    public void updateBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId=txtId.getText();
        String title=txtTitle.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String postalCode=txtPostal.getText();
        try{
            if(!existCustomer(customerId)){}
            CustomerDTO customerDTO = new CustomerDTO(customerId,title,name,address,city,province,postalCode);

            if( customerBO.updateCustomer(customerDTO)){
                setCustomersToTable();
                new Alert(Alert.AlertType.CONFIRMATION,"Update Customer").show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + customerId + e.getMessage()).show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        tblCustomer.refresh();

    }

    public void saveBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO= new CustomerDTO(
                txtId.getText(),txtTitle.getText(),txtName.getText(),
                txtAddress.getText(),txtCity.getText(),txtProvince.getText(),txtPostal.getText()
        );
        try {
            if(customerBO.addCustomer(customerDTO)){
                setCustomersToTable();
                new Alert(Alert.AlertType.CONFIRMATION, "Saved the Customer....").show();
                clear();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void initialize() {
        Validate();
            colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
            colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
            colPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        try {
            setCustomersToTable();
            getLastCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtId.setText(newValue.getCustomerId());
                txtTitle.setText(newValue.getTitle());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostal.setText(newValue.getPostalCode());

                txtId.setDisable(false);
                txtTitle.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtCity.setDisable(false);
                txtProvince.setDisable(false);
                txtPostal.setDisable(false);

            }
        }));
        txtAddress.setOnAction(event -> btnSave.fire());
    }
    private void setCustomersToTable() throws SQLException, ClassNotFoundException {
        tblCustomer.setItems(customerBO.getAllCustomer());
    }
    private String getLastCustomer(){
        List<CustomerTm> list = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(list);
        return list.get(list.size() -1).getCustomerId();
    }


    public void mouseClickOnAction(MouseEvent mouseEvent) {
    }

}
