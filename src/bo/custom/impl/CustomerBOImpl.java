package bo.custom.impl;

import View.tm.CustomerTm;
import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custome.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ObservableList<CustomerTm> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomerDAO=customerDAO.getAll();
       ObservableList<CustomerTm> observableList= FXCollections.observableArrayList();
       allCustomerDAO.forEach(e->observableList.addAll(new CustomerTm(
               e.getCustomerId(),e.getTitle(),e.getName(),e.getAddress(),e.getCity(),e.getProvince(),e.getPostalCode())));
       return observableList;
    }

    @Override
    public boolean addCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(c.getCustomerId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO c) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(c.getCustomerId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean ifCustomerExit(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.ifCustomerExit(id);
    }
}
