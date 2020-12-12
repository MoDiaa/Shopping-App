package activity;

public class Collector implements java.io.Serializable {
    private String specialoffers, name;
    private Double latitude, longitude;
    private int price;

    public Collector() {

    }


    public String toString() {
        return ("Shop Name:" + getName() +
                " Price : " + getPrice() +
                " SpecialOffers : " + getSpecialoffers() +
                " latitude : " + getLatitude()) +
                " longitude : " + getLongitude();
    }


    public String getSpecialoffers() {
        return specialoffers;
    }

    public void setSpecialoffers(String specialoffers) {
        this.specialoffers = specialoffers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
