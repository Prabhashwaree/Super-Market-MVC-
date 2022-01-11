package dao.custome.impl;

import dao.CrudUtil;
import dao.custome.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)",c.getCustomerId(),c.getTitle(),c.getName()
                ,c.getAddress(),c.getCity(),c.getProvince(),c.getPostalCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustId=?", id);


    }

    @Override
    public boolean update(Customer c) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=? , City=?, Povince=?, PostCode=?WHERE CustID=?",c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalCode(),c.getCustomerId());
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustId=?",s);
        rst.next();
        return new Customer(s, rst.getString("CustTitle"), rst.getString("CustName"),

                rst.getString("CustAddress"), rst.getString("City"), rst.getString("Povince"), rst.getString("PostCode"));
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString("CustID"), rst.getString("CustTitle"), rst.getString("CustName"), rst.getString("CustAddress"), rst.getString("City"), rst.getString("Povince"), rst.getString("PostCode")));
        }
        return allCustomers;
    }

    @Override
    public boolean ifCustomerExit(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT CustID FROM Customer WHERE CustID=?",id).next();
    }
}
