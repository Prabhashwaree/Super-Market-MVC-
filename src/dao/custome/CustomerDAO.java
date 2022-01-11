package dao.custome;

import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    boolean ifCustomerExit(String id)throws SQLException, ClassNotFoundException;
}
