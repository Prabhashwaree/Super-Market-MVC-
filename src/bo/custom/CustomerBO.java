package bo.custom;

import View.tm.CustomerTm;
import bo.SuperBO;
import dto.CustomerDTO;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ObservableList<CustomerTm> getAllCustomer()throws SQLException, ClassNotFoundException;

    boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean ifCustomerExit(String id)throws SQLException, ClassNotFoundException;

}
