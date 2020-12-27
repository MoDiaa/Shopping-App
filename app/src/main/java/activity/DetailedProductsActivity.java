package activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.Collections;

import app.AppConfig;

public class DetailedProductsActivity extends AppCompatActivity {
    private static final String TAG = "DetailedProducts";
    public ArrayList<Shop> ShopList = new ArrayList<>();
    public ArrayList<offers> Shop_ProductList = new ArrayList<>();
    public ArrayList<Collector> CollectorList = new ArrayList<>();
    private TextView mname, mdescription;
    private ImageView mImage;
    CollectorListAdapter adapter;
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

        // Get the Offers(SHOP_PRODUCT) Table Data and Store it in Shop_ProductList list

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, AppConfig.URL_SHOP_PRODUCT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Intent intent = getIntent();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // If Condition to Only Put the Data with the same  Product Id that we Get From Home Activity

                        if (jsonObject.getInt("product_id") == intent.getIntExtra("id", 0)) {

                            offers pz = new offers();
                            pz.setId(jsonObject.getInt("id"));
                            pz.setShopid(jsonObject.getInt("shop_id"));
                            pz.setProductid(jsonObject.getInt("product_id"));
                            pz.setPrice(jsonObject.getInt("price"));
                            pz.setSpecialoffers(jsonObject.getString("special_offers"));
                            cn.setPrice(jsonObject.getInt("price"));
                            cn.setSpecialoffers(jsonObject.getString("special_offers"));
                            Shop_ProductList.add(pz);

                        }
                    }
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

//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/ SHOP//SHOP//
//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/ SHOP//SHOP//
//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/SHOP//SHOP//SHOP/SHOP//SHOP//SHOP//SHOP//SHOP//SHOP/ SHOP//SHOP//

        // Get the Shop Table Data and Store it in ShopList list
        // then Store it in CollectorList List to Show it in the ListView

        StringRequest stringRequest10 = new StringRequest(Request.Method.GET, AppConfig.URL_SHOP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Thread.sleep(100);
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Shop pn = new Shop();
                        pn.setId(jsonObject.getInt("id"));
                        pn.setName(jsonObject.getString("name"));
                        pn.setLatitude(jsonObject.getDouble("latitude"));
                        pn.setLongitude(jsonObject.getDouble("longitude"));
                        if (isValidProduct(pn)) {
                            ShopList.add(pn);
                        } else {
                        }
                    }
//                    filterCollectors();
                    adapter = new CollectorListAdapter(DetailedProductsActivity.this, R.layout.activity_collector_list_adapter, CollectorList);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.sort1:
                //perform any action;
                Collections.sort(CollectorList, Collector.PriceSortLowToHigh);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.sort11:
                //perform any action;
                Collections.sort(CollectorList, Collector.PriceSortHighToLow);
                adapter.notifyDataSetChanged();
                return true;

            case R.id.sort2:
                //perform any action;
                Collections.sort(CollectorList, Collector.DistanceSortLowToHigh);
                adapter.notifyDataSetChanged();
                return true;

            case R.id.sort22:
                //perform any action;
                Collections.sort(CollectorList, Collector.DistanceSortHighToLow);
                adapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // isValidProduct to Only Put the Data with the same Shop Id that we Get From ShopList list
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

                SharedPreferences Preferences = PreferenceManager.getDefaultSharedPreferences(DetailedProductsActivity.this);
                int id = Preferences.getInt("user_id", 1);

                cn.setUserid(id);
                cn.setShopid(offer.getShopid());
                cn.setProductid(offer.getProductid());

                CollectorList.add(cn);
                Log.d(TAG, "CollectorList  :" + CollectorList.toString());
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