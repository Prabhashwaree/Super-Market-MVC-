package entity;

import dto.OrderDetailsDTO;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderDate;
    private String customerId;
    private ArrayList<OrderDetailsDTO> item;

    public Order() {
    }

    public Order(String orderId, String orderDate, String customerId, ArrayList<OrderDetailsDTO> item) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCustomerId(customerId);
        this.setItem(item);
    }

    public Order(String orderId, String orderDate, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<OrderDetailsDTO> getItem() {
        return item;
    }

    public void setItem(ArrayList<OrderDetailsDTO> item) {
        this.item = item;
    }
}
