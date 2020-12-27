package activity;

import android.location.Location;

import com.like.LikeButton;

import java.util.Comparator;

public class Collector implements java.io.Serializable {
    private String specialoffers, name;
    private Double latitude, longitude;
    private int price, userid, shopid, productid;
    private Boolean isLiked;

    public Collector() {

    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    //Sorting by Price method
    public static Comparator<Collector> PriceSortLowToHigh = new Comparator<Collector>() {

        public int compare(Collector s1, Collector s2) {

            int rollno1 = s1.getPrice();
            int rollno2 = s2.getPrice();

            /*For ascending order*/
            return rollno1 - rollno2;

            /*For descending order*/
            //rollno2-rollno1;
        }
    };

    public static Comparator<Collector> PriceSortHighToLow = new Comparator<Collector>() {

        public int compare(Collector s1, Collector s2) {

            int rollno1 = s1.getPrice();
            int rollno2 = s2.getPrice();

            /*For ascending order*/
//            return rollno1 - rollno2;

            /*For descending order*/
            return rollno2-rollno1;
        }
    };


    //Sorting by Distance method
    public static Comparator<Collector> DistanceSortLowToHigh = new Comparator<Collector>() {

        public int compare(Collector s1, Collector s2) {

            float[] results1 = new float[3];
            Location.distanceBetween(
                    LocationActivity.currentLocation.getLatitude(),
                    LocationActivity.currentLocation.getLongitude(), s1.getLatitude(),
                    s1.getLongitude(),
                    results1);

            float[] results2 = new float[3];
            Location.distanceBetween(
                    LocationActivity.currentLocation.getLatitude(),
                    LocationActivity.currentLocation.getLongitude(), s2.getLatitude(),
                    s2.getLongitude(),
                    results2);

            /*For ascending order*/
            return Float.compare(results1[0], results2[0]);

            /*For descending order*/
            //rollno2-rollno1;
        }
    };


    public static Comparator<Collector> DistanceSortHighToLow = new Comparator<Collector>() {

        public int compare(Collector s1, Collector s2) {

            float[] results1 = new float[3];
            Location.distanceBetween(
                    LocationActivity.currentLocation.getLatitude(),
                    LocationActivity.currentLocation.getLongitude(), s1.getLatitude(),
                    s1.getLongitude(),
                    results1);

            float[] results2 = new float[3];
            Location.distanceBetween(
                    LocationActivity.currentLocation.getLatitude(),
                    LocationActivity.currentLocation.getLongitude(), s2.getLatitude(),
                    s2.getLongitude(),
                    results2);

            /*For ascending order*/
//            return Float.compare(results1[0], results2[0]);

            /*For descending order*/
            return Float.compare(results2[0], results1[0]);
        }
    };




    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
