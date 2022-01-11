package bo.custom;

import View.tm.OrderTm;
import bo.SuperBO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    ObservableList<OrderTm> getAllOrder()throws SQLException, ClassNotFoundException;
}
