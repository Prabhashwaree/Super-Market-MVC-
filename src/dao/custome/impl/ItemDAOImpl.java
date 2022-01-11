package dao.custome.impl;

import dao.CrudUtil;
import dao.custome.ItemDAO;
import entity.Customer;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?)",i.getCode(),i.getDiscription(),i.getPack()
                ,i.getPrize(),i.getQtyOnHand());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?", code);
    }

    @Override
    public boolean update(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET Description=?, PackSize=?, UnitPrice=? , QtyOnHand=? WHERE ItemCode=?",i.getDiscription(),i.getPack()
                ,i.getPrize(),i.getQtyOnHand(),i.getCode());
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()) {
            allItem.add(new Item(rst.getString("ItemCode"), rst.getString("Description"), rst.getString("PackSize"), rst.getDouble("UnitPrice"), rst.getInt("QtyOnHand")));
        }
        return allItem;
    }


    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
       ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?",s);
       rst.next();
       return  new Item(s, rst.getString("Description"), rst.getString("PackSize"),
               rst.getDouble("UnitPrice"), rst.getInt("QtyOnHand"));
    }

    @Override
    public boolean updateQTY(String id, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=(QtyOnHand-" + qty
                + ") WHERE ItemCode='" + id + "'");
    }

    @Override
    public boolean ifItemExit(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT ItemCode FROM Item WHERE ItemCode=?",id).next();
    }
}
