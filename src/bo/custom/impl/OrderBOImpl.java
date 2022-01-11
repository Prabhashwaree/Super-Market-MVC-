package bo.custom.impl;

import View.tm.ItemTm;
import View.tm.OrderTm;
import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custome.ItemDAO;
import dao.custome.OrderDAO;
import entity.Item;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);


    @Override
    public ObservableList<OrderTm> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrderDAO=orderDAO.getAll();
        ObservableList<OrderTm> observableList= FXCollections.observableArrayList();
        allOrderDAO.forEach(e->observableList.addAll(new OrderTm(e.getOrderId(),e.getOrderDate(),e.getCustomerId())));
        return observableList;
    }
}
