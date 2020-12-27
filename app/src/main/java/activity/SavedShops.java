package activity;

public class SavedShops implements java.io.Serializable {

    private String specialoffers, ShopName, ProductName;
    private int price;


    public SavedShops() {

    }

    public String toString() {
        return ("Shop Name:" + getShopName() +
                " ProductName : " + getProductName() +
                " Price : " + getPrice() +
                " SpecialOffers : " + getSpecialoffers());
    }

    public String getSpecialoffers() {
        return specialoffers;
    }

    public void setSpecialoffers(String specialoffers) {
        this.specialoffers = specialoffers;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
