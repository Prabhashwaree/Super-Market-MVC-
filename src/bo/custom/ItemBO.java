package bo.custom;

import View.tm.ItemTm;
import bo.SuperBO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ItemBO extends SuperBO {
    ObservableList<ItemTm> getAllItem()throws SQLException, ClassNotFoundException;

    boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    boolean ifItemExit(String id)throws SQLException, ClassNotFoundException;
}
