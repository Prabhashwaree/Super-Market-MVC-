package Controller;

import bo.BoFactory;
import bo.custom.OrderDetailBO;
import bo.custom.UpdateOrderBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import dto.OrderDetailsDTO;

import java.sql.SQLException;

public class ModifyOrderFormController {
    public TextField txtDiscount;
    public AnchorPane updatePane;
    public TextField txtOrderId;
    public JFXTextField txtCode2;
    public JFXTextField txtPrize1;
    public JFXTextField txtQTY2;

    private  final UpdateOrderBO updateOrderBO = (UpdateOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.UPDATE_ORDER);
    private final OrderDetailBO orderDetailBO = (OrderDetailBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDERDETAIL
    );

    public void orderIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = txtOrderId.getText();
            OrderDetailsDTO o1= updateOrderBO.getOrder(orderId);
        if (o1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(o1);
        }
    }

    public void moveToDiscription(ActionEvent actionEvent) {
    }

    public void moveToQTY(ActionEvent actionEvent) {
    }

    void setData(OrderDetailsDTO o1){
        txtOrderId.setText(o1.getOrderId());
        txtCode2.setText(o1.getItemCode());
        txtQTY2.setText(String.valueOf(o1.getOrderQTY()));
        txtDiscount.setText(String.valueOf(o1.getDiscount()));
        txtPrize1.setText(String.valueOf(o1.getPrize()));
    }

    public void updateOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderDetailsDTO orderDetailsDTO= new OrderDetailsDTO(
                txtOrderId.getText(),txtCode2.getText(),Integer.parseInt(txtQTY2.getText()),
                Double.parseDouble(txtDiscount.getText()),Double.parseDouble(txtPrize1.getText())
        );
        if (orderDetailBO.updateOrderDetail(orderDetailsDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


    }
}
