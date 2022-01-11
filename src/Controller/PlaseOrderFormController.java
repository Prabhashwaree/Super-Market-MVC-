package Controller;

import View.tm.CartTm;
import bo.BoFactory;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import util.Validation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

public class PlaseOrderFormController {
    public Label lblId;
    public Label lblDate;
    public Label lblTime;
    
    public JFXComboBox <String>cmbCustomerId;
    public JFXComboBox <String>cmbItemCode;
    public JFXTextField txtName;
    public JFXTextField txtCity;
    public JFXTextField txtAddress;
    public JFXTextField txtUnitPrize;
    public JFXTextField txtQTYOnHand;
    public JFXTextField txtDiscription;
    public TextField txtQTY;
    public TextField txtDiscount;
    public Button btnUpdate;
    public TableColumn colOrderId;
    public TableColumn colItemCode;
    public TableColumn colQTY;
    public TableColumn colPrize;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public Label lblTotal;
    public TableView <CartTm>tblOrder;
    public AnchorPane orderDitailsPane;

    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);
    public Button btnAddToCart;

    int cartSelectedRowForRemove = -1;

    LinkedHashMap<TextField, Pattern> validation = new LinkedHashMap<>();
    Pattern qty = Pattern.compile("^[0-9]{1,1000}$");
    Pattern discount = Pattern.compile("^[0-9]{1,100}$");

    private  void Validate(){
        btnAddToCart.setDisable(false);
        validation.put(txtQTY,qty);
        validation.put(txtDiscount,discount);
    }
    public void validateKeyReleasedOnAction(KeyEvent keyEvent) {
        Object response = Validation.validate(validation,btnAddToCart);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }

    public void initialize() {
        Validate();
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrize.setCellValueFactory(new PropertyValueFactory<>("unitPrize"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));


        loadDateAndTime();
        setOrderId();

        try {

            loadCustomerIds();
            loadItemIds();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData((String) newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData( newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

    }

    private void setOrderId() {
        try {
            lblId.setText(purchaseOrderBO.generateNewOrderId());

        } catch (SQLException throwables) {
            //new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        ItemDTO i1 = purchaseOrderBO.searchItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDiscription.setText(i1.getDiscription());
            txtQTYOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrize.setText(String.valueOf(i1.getPrize()));
        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = purchaseOrderBO.searchCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(c1.getName());
            txtAddress.setText(c1.getAddress());
            txtCity.setText(c1.getCity());
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        try {
            List<ItemDTO> all = purchaseOrderBO.getAllItem();
            for (ItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getCode());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        try {
            List<CustomerDTO> all = purchaseOrderBO.getAllCustomer();
            for (CustomerDTO customerDTO : all) {
                cmbCustomerId.getItems().add(customerDTO.getCustomerId());
            }
        } catch (SQLException e) {
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    ObservableList<CartTm> obList= FXCollections.observableArrayList();

    public void moveToDiscription(ActionEvent actionEvent) {
    }

    public void moveToCity(ActionEvent actionEvent) {
    }

    public void moveToUnitPrize(ActionEvent actionEvent) {
    }

    public void moveToQTYOnHand(ActionEvent actionEvent) {
    }

    public void removeBtnOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblOrder.refresh();
        }
    }

    public void addToCartOnAction(ActionEvent actionEvent) {
        int qtyOnHand=Integer.parseInt(txtQTYOnHand.getText());
        int qty= Integer.parseInt(txtQTY.getText());
        double unitPrice = Double.parseDouble(txtUnitPrize.getText());
        double discount = Double.parseDouble(txtDiscount.getText());
        double z=(unitPrice * qty);
        double total =z-((z/100)*discount);

        if (qtyOnHand<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                lblId.getText(),
                cmbItemCode.getValue(),
                qty,
                unitPrice,
                discount,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getOrderId(),
                    temp.getCode(),
                    temp.getQty()+qty,
                    unitPrice,
                    discount,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblOrder.setItems(obList);
        calculateCost();
    }
    private int isExists(CartTm tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getCode().equals(obList.get(i).getCode())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double ttl=0;
        for (CartTm tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        lblTotal.setText(ttl+" /=");
    }

    public void updateBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../View/ModifyOrderForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public boolean comfirmOrderBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> items= new ArrayList<>();
        for (CartTm tempTm:obList) {
            items.add(new OrderDetailsDTO
                    (tempTm.getOrderId(),tempTm.getCode(),tempTm.getQty(),tempTm.getDiscount(),
                    tempTm.getUnitPrize()));
        }

            OrderDTO orderDTO =new OrderDTO(
                lblId.getText(),lblDate.getText(),cmbCustomerId.getValue(),items);

        if (purchaseOrderBO.purchaseOrder(orderDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        return false;
    }

    public void moveToAddress(ActionEvent actionEvent) {
    }
}
