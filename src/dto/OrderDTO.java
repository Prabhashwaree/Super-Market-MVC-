package dto;


import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String orderDate;
    private String customerId;
    private ArrayList <OrderDetailsDTO>item;

    public OrderDTO() {
    }

    public OrderDTO(String text, String lblDateText, String value) {
        this.orderId=text;
        this.orderDate=lblDateText;
        this.customerId=value;
    }

    public OrderDTO(String orderId, String orderDate, String customerId, ArrayList<OrderDetailsDTO> item) {
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
        this.setCustomerId(customerId);
        this.setItem(item);
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", item=" + item +
                '}';
    }

}
