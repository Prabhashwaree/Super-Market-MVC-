package View.tm;

public class ItemTm implements Comparable<ItemTm>{
    private String code;
    private String discription;
    private String pack;
    private double prize;
    private int qtyOnHand;

    public ItemTm() {
    }

    public ItemTm(String code, String discription, String pack, double prize, int qtyOnHand) {
        this.setCode(code);
        this.setDiscription(discription);
        this.setPack(pack);
        this.setPrize(prize);
        this.setQtyOnHand(qtyOnHand);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "code='" + code + '\'' +
                ", discription='" + discription + '\'' +
                ", pack='" + pack + '\'' +
                ", prize=" + prize +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }

    @Override
    public int compareTo(ItemTm o) {
        return code.compareTo(o.getCode());
    }
}
