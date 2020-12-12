package activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.shoppingapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.AppConfig;

public class DetailedProductsActivity extends AppCompatActivity {
    private static final String TAG = "DetailedProducts";
    public ArrayList<Shop> ShopList = new ArrayList<>();
    public ArrayList<offers> Shop_ProductList = new ArrayList<>();
    public ArrayList<Collector> CollectorList = new ArrayList<>();

    private TextView mname, mdescription;
    private ImageView mImage;

    Collector cn = new Collector();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_products);
        mname = findViewById(R.id.Productname);
        mdescription = findViewById(R.id.description);
        mImage = findViewById(R.id.imgView);

        // Catching incoming intent
        Intent intent = getIntent();
        mname.setText(intent.getStringExtra("name"));
        mdescription.setText(intent.getStringExtra("description"));
        Glide.with(DetailedProductsActivity.this).load(intent.getStringExtra("image_url")).into(mImage);
        final ListView mListView = (ListView) findViewById(R.id.listView10);


//SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT//
//SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT//
//SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT/SHOP_PRODUCT//

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, AppConfig.URL_SHOP_PRODUCT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    Intent intent = getIntent();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.getInt("product_id") == intent.getIntExtra("id", 0)) {

                            offers pz = new offers();
                            pz.setId(jsonObject.getInt("id"));
                            pz.setShopid(jsonObject.getInt("shop_id"));
                            pz.setProductid(jsonObject.getInt("product_id"));
                            pz.setPrice(jsonObject.getInt("price"));
                            pz.setSpecialoffers(jsonObject.getString("special_offers"));
                            Shop_ProductList.add(pz);

                            cn.setPrice(jsonObject.getInt("price"));
                            cn.setSpecialoffers(jsonObject.getString("special_offers"));

                            Log.d(TAG, "onResponseShop_ProductListzzzzzzzzzzzzzzzzzzzzzz:" + cn.toString());


                            Shop_ProductList.add(pz);
                            //Log.d(TAG, "onResponseShop_ProductListzzzzzzzzzzzzzzzzzzzzzz:" + Shop_ProductList.toString());

                            Log.d(TAG, "onResponseShop_Product:" + jsonObject);
                            // JSONObject j1 =jsonObject.getInt("product_id");
                        }
//                        Log.d(TAG, "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm:" + jsonObject);

                    }
//                    ShopListAdapter adapter = new ShopListAdapter(DetailedProductsActivity.this, R.layout.activity_shop_list_adapter, Shop_ProductList);
//                    mListView.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailedProductsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
        };
        Volley.newRequestQueue(this).add(stringRequest1);

        // Log.d(TAG, "hhhhhhhhhhhhhhhh   " + findoffers((intent.getIntExtra("id",0)),Shop_ProductList));


//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/ SHOP//SHOP//
//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/ SHOP//SHOP//
//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/ SHOP//SHOP//

        StringRequest stringRequest10 = new StringRequest(Request.Method.GET, AppConfig.URL_SHOP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Thread.sleep(100);
                    JSONArray jsonArray = new JSONArray(response);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //if (jsonObject.getInt("id") == intent.getIntExtra("id", 0)) {
                        Shop pn = new Shop();
                        pn.setId(jsonObject.getInt("id"));
                        pn.setName(jsonObject.getString("name"));
                        pn.setLatitude(jsonObject.getDouble("latitude"));
                        pn.setLongitude(jsonObject.getDouble("longitude"));
                        if (isValidProduct(pn)) {
                            ShopList.add(pn);
                            Log.d(TAG, "Shop added: " + jsonObject);
                        } else {
                            Log.d(TAG, "Cant add: " + pn.getId());

                        }
                    }

                    Log.d(TAG, "Count: " + ShopList.size());
                    filterCollectors();
                    CollectorListAdapter adapter = new CollectorListAdapter(DetailedProductsActivity.this, R.layout.activity_shop_list_adapter, CollectorList);
                    mListView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailedProductsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
        };
        Volley.newRequestQueue(this).add(stringRequest10);
    }

    boolean isValidProduct(Shop shop) {
        boolean result = false;
        for (offers offer : Shop_ProductList) {
            if (offer.getShopid() == shop.getId()) {
                Collector cn = new Collector();
                cn.setName(shop.getName());
                cn.setLatitude(shop.getLatitude());
                cn.setLongitude(shop.getLongitude());
                cn.setPrice(offer.getPrice());
                cn.setSpecialoffers(offer.getSpecialoffers());
                Log.d(TAG, "Collrctor " + cn.toString());
                CollectorList.add(cn);
                Log.d(TAG, "onResponseCollectorListCollectorListCollectorListCollectorList:" + CollectorList.toString());

                result = true;
            }
        }
        return result;
    }

    void filterCollectors() {
        ArrayList<Collector> cs = new ArrayList<Collector>();

        for (Collector c : CollectorList) {
            boolean exist = false;
            for (Collector old : cs) {
                if (c.getName().equals(old.getName()) && c.getPrice() == old.getPrice() && c.getSpecialoffers().equals(old.getSpecialoffers())) {
                    exist = true;
                }
            }
            if (!exist) {
                cs.add(c);
            }
        }

        CollectorList = cs;
    }

}