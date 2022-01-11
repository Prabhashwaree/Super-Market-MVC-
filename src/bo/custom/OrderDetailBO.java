package bo.custom;

import View.tm.OrderDetailsTm;
import bo.SuperBO;
import dto.OrderDetailsDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface OrderDetailBO extends SuperBO {
    ObservableList<OrderDetailsTm> getAllOrderDetail()throws SQLException, ClassNotFoundException;

    boolean updateOrderDetail(OrderDetailsDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteOrderDetail(String id) throws SQLException, ClassNotFoundException;


}
