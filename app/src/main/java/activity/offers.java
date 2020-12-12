package activity;

public class offers implements java.io.Serializable {
    private String specialoffers;
    private int id, shopid, productid, price;

    public offers() {

    }


    public String toString() {
        return ("id:" + getId() +
                " shopid " + getShopid() +
                " productid " + getProductid() +
                " price " + getPrice()) +
                " specialoffers " + getSpecialoffers();
    }


    public String getSpecialoffers() {
        return specialoffers;
    }

    public void setSpecialoffers(String specialoffers) {
        this.specialoffers = specialoffers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
