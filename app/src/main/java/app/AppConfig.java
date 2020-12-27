package app;

public class AppConfig {

    public static String  urlpart ="http://192.168.1.5";
    // Server user login url
    public static String URL_LOGIN = urlpart+"//android_login_api/login.php";

    // Server user register url
    public static String URL_REGISTER = urlpart+"/android_login_api/register.php";

    // Server user Product url
    public static String URL_PRODUCT = urlpart+"/android_login_api/productlist.php";

    // Server user shop url
    public static String URL_SHOP = urlpart+"/android_login_api/shoplist.php";

    // Server user SHOP_PRODUCT url
    public static String URL_SHOP_PRODUCT = urlpart+"/android_login_api/shop_product.php";

    // Server user GetSavedShops url
    public static String URL_GET_SAVED_SHOPS = urlpart+"/android_login_api/savedshop.php";

    // Server user InsertSavedShops url
    public static String URL_SAVED_SHOPS = urlpart+"/android_login_api/insertsavedshops.php";

    // Server user DeleteSavedShops url
    public static String URL_Delete_SAVED_SHOPS = urlpart+"/android_login_api/deletesavedshops.php";


}
