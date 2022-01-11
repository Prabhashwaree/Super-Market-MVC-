package Controller;

import bo.BoFactory;
import bo.custom.OrderBO;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;

public class OrderFormController {
    public TableColumn colOrderId;
    public TableColumn colDate;
    public TableColumn colCustomerId;
    public TableView tblOrder;

    private final OrderBO orderBO = (OrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDER);

    private void loadAllOrder() throws SQLException, ClassNotFoundException {
        tblOrder.setItems(orderBO.getAllOrder());

    }
    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        try {
            loadAllOrder();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
