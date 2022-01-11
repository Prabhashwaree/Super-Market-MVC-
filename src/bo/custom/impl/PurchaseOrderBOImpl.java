package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custome.CustomerDAO;
import dao.custome.ItemDAO;
import dao.custome.OrderDAO;
import dao.custome.OrderDetailDAO;
import db.DbConnection;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        if(orderDAO.add(new Order(
               dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId()
        ))){
            for(OrderDetailsDTO orderDetailsDTO : dto.getItem()){
                if(!orderDetailDAO.add(new OrderDetail(orderDetailsDTO.getOrderId(),orderDetailsDTO.getItemCode(),orderDetailsDTO.getOrderQTY(),orderDetailsDTO.getDiscount(),orderDetailsDTO.getPrize()))){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                if(!itemDAO.updateQTY(orderDetailsDTO.getItemCode(),orderDetailsDTO.getOrderQTY())){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }


    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItems.add(new ItemDTO(i.getCode(),i.getDiscription(),i.getPack(),i.getPrize(),i.getQtyOnHand()));
        }
        return allItems;
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item i = itemDAO.search(code);
        return new ItemDTO(i.getCode(),i.getDiscription(),i.getPack(),i.getPrize(),i.getQtyOnHand());
    }

    @Override
    public CustomerDTO searchCustomer(String s) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(s);
        return new CustomerDTO(c.getCustomerId(),c.getTitle(),c.getName(),c.getAddress(),c.getCity(),c.getProvince()
                ,c.getPostalCode());
    }

}
