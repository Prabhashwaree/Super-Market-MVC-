package dao.custome.impl;

import dao.CrudUtil;
import dao.custome.OrderDAO;
import dao.custome.OrderDetailDAO;
import entity.Item;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto.getOrderId());
        System.out.println(dto.getCustomerId());
        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES (?,?,?)", dto.getOrderId(),
                dto.getOrderDate(),dto.getCustomerId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Order> allOrder = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order`");
        while (rst.next()) {
            allOrder.add(new Order(rst.getString("OrderID"), rst.getString("OrderDate"), rst.getString("CustID")));
        }
        return allOrder;

    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1");
        if (rst.next()) {
            String orderId = rst.getString("OrderID");
            int newOrderId = Integer.parseInt(orderId.replace("O-", "")) + 1;
            return String.format("O-%03d", newOrderId);

        }else {
            return "O-001";
        }
    }
}
