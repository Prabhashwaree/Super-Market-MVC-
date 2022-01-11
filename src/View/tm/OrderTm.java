package View.tm;

public class OrderTm {
    private String orderId;
    private String date;
    private String customerId;

    public OrderTm() {
    }

    public OrderTm(String orderId, String date, String customerId) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
