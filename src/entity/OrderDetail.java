package entity;

public class OrderDetail {
    private String orderId;
    private String itemCode;
    private int orderQTY;
    private double discount;
    private double prize;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String itemCode, int orderQTY, double discount, double prize) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setOrderQTY(orderQTY);
        this.setDiscount(discount);
        this.setPrize(prize);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }
}
