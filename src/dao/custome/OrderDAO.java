package dao.custome;

import dao.CrudDAO;
import entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}
