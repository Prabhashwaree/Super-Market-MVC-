package bo.custom.impl;

import bo.custom.UpdateOrderBO;
import dao.DAOFactory;
import dao.custome.OrderDetailDAO;
import dto.OrderDetailsDTO;
import entity.OrderDetail;

import java.sql.SQLException;

public class UpdateOrderBOImpl implements UpdateOrderBO {

    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public OrderDetailsDTO getOrder(String id) throws SQLException, ClassNotFoundException {
        OrderDetail order = orderDetailDAO.getOrder(id);
        return new OrderDetailsDTO(order.getOrderId(),order.getItemCode(),order.getOrderQTY(),order.getDiscount(),order.getPrize());
    }
}
