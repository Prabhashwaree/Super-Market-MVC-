package View.tm;

public class CartTm {
    private String orderId;
    private String code;
    private int qty;
    private double unitPrize;
    private double discount;
    private double total;

    public CartTm() {
    }

    public CartTm(String orderId, String code, int qty, double unitPrize, double discount, double total) {
        this.setOrderId(orderId);
        this.setCode(code);
        this.setQty(qty);
        this.setUnitPrize(unitPrize);
        this.setDiscount(discount);
        this.setTotal(total);
    }


    @Override
    public String toString() {
        return "CartTm{" +
                "code='" + getCode() + '\'' +
                ", qty=" + getQty() +
                ", unitPrize=" + getUnitPrize() +
                ", discount=" + getDiscount() +
                ", total=" + getTotal() +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrize() {
        return unitPrize;
    }

    public void setUnitPrize(double unitPrize) {
        this.unitPrize = unitPrize;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
