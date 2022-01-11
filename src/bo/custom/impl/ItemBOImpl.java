package bo.custom.impl;

import View.tm.CustomerTm;
import View.tm.ItemTm;
import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custome.ItemDAO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ObservableList<ItemTm> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItemDAO=itemDAO.getAll();
        ObservableList<ItemTm> observableList= FXCollections.observableArrayList();
        allItemDAO.forEach(e->observableList.addAll(new ItemTm(e.getCode(),e.getDiscription(),e.getPack(),e.getPrize(),e.getQtyOnHand())));
        return observableList;
    }

    @Override
    public boolean addItem(ItemDTO i) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(i.getCode(),i.getDiscription(),i.getPack(),i.getPrize(),i.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO i) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(i.getCode(),i.getDiscription(),i.getPack(),i.getPrize(),i.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean ifItemExit(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.ifItemExit(id);
    }
}
