package bo.custom.impl;

import View.tm.ItemTm;
import View.tm.OrderDetailsTm;
import bo.custom.OrderDetailBO;
import dao.CrudUtil;
import dao.DAOFactory;
import dao.custome.OrderDetailDAO;
import dto.OrderDetailsDTO;
import entity.Item;
import entity.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDetailBOImpl implements OrderDetailBO {

    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);


    @Override
    public ObservableList<OrderDetailsTm> getAllOrderDetail() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> allorderDetailsDAO=orderDetailDAO.getAll();
        ObservableList<OrderDetailsTm> observableList= FXCollections.observableArrayList();
        allorderDetailsDAO.forEach(e->observableList.addAll(new OrderDetailsTm(e.getOrderId(),e.getItemCode(),e.getOrderQTY(),e.getDiscount(),e.getPrize())));
        return observableList;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.update(new OrderDetail(dto.getOrderId(),dto.getItemCode(),dto.getOrderQTY(),dto.getDiscount(),dto.getPrize()));
    }

    @Override
    public boolean deleteOrderDetail(String id) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.delete(id);
    }


}
