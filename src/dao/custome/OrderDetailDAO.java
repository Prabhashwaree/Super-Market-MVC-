package dao.custome;

import dao.CrudDAO;
import entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {

    OrderDetail getOrder(String id) throws SQLException, ClassNotFoundException;


}
