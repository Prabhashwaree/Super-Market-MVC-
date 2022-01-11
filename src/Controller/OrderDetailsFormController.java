package Controller;

import View.tm.OrderDetailsTm;
import bo.BoFactory;
import bo.custom.OrderDetailBO;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.SQLException;

public class OrderDetailsFormController {
    public TableView<OrderDetailsTm> tblOrderDetails;
    public TableColumn colOrderId;
    public TableColumn colItemCode;
    public TableColumn colOredrQty;
    public TableColumn colDicount;
    public TableColumn colPrice;

    private final OrderDetailBO orderDetailBO = (OrderDetailBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDERDETAIL);

    private void loadAllOrderDetails() throws SQLException, ClassNotFoundException {
        tblOrderDetails.setItems(orderDetailBO.getAllOrderDetail());
    }
    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOredrQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDicount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            loadAllOrderDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteBtnOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        OrderDetailsTm selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();
        orderDetailBO.deleteOrderDetail(selectedItem.getOrderId());
        setOrderDetailToTable();
        orderDetailBO.getAllOrderDetail();
        clear();
}

    private void clear() {
        colOrderId.getColumns().clear();
        colItemCode.getColumns().clear();
        colOredrQty.getColumns().clear();
        colDicount.getColumns().clear();
        colPrice.getColumns().clear();
    }

    private void setOrderDetailToTable() throws SQLException, ClassNotFoundException {
        tblOrderDetails.setItems(orderDetailBO.getAllOrderDetail());
    }
    }
