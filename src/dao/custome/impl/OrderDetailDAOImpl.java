package dao.custome.impl;

import dao.CrudUtil;
import dao.custome.OrderDetailDAO;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean add(OrderDetail od) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order Details` VALUES(?,?,?,?,?)",od.getOrderId(),od.getItemCode(),od.getOrderQTY(),od.getDiscount(),od.getPrize());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM `Order Details` WHERE OrderID=?", s);
    }

    @Override
    public boolean update(OrderDetail od) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate("UPDATE `Order Details` SET  ItemCode=?,Orderqty=?, Discount=? ,Price=? WHERE OrderID=?",od.getItemCode(),od.getOrderQTY(),od.getDiscount(),od.getPrize(),od.getOrderId());
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> allOrderDetail = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order Details`");
        while (rst.next()) {
            allOrderDetail.add(new OrderDetail(rst.getString("OrderID"), rst.getString("ItemCode"), rst.getInt("Orderqty"), rst.getInt("Discount"), rst.getInt("Price")));
        }
        return allOrderDetail;
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetail getOrder(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  `Order Details` WHERE OrderID=?", id);
        if(rst.next()){
          return   new OrderDetail(rst.getString("OrderID"), rst.getString("ItemCode"), rst.getInt("Orderqty"), rst.getInt("Discount"), rst.getInt("Price"));
        }
        return null;
    }
}
