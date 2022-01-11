package dao.custome;

import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    boolean updateQTY(String id,int qty)throws SQLException, ClassNotFoundException;

    boolean ifItemExit(String id)throws SQLException, ClassNotFoundException;
}
