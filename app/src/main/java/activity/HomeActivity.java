package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppingapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.AppConfig;


public class HomeActivity extends Activity {

    private static final String TAG = "HomeActivity";
    public ArrayList<Product> ProductList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: Started.");
        final ListView mListView = (ListView) findViewById(R.id.listView);
        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                // Sending Data throw intent

                Intent intent = new Intent(HomeActivity.this, DetailedProductsActivity.class);
                intent.putExtra("id", ProductList.get(position).getId());
                intent.putExtra("name", ProductList.get(position).getName());
                intent.putExtra("description", ProductList.get(position).getDescription());
                intent.putExtra("image_url", ProductList.get(position).getImage_url());

                startActivity(intent);
            }
        });


//PRODUCT//PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT//
//PRODUCT//PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT//
//PRODUCT//PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT/PRODUCT//

        // Get the PRODUCT Table Data and Store it in ProductList list

        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_PRODUCT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Product p = new Product();
                        p.setId(jsonObject.getInt("id"));
                        p.setDescription(jsonObject.getString("description"));
                        p.setImage_url(jsonObject.getString("image_url"));
                        p.setName(jsonObject.getString("name"));
                        ProductList.add(p);
                    }
                    ProductListAdapter adapter = new ProductListAdapter(HomeActivity.this, R.layout.adapter_view_layout, ProductList);
                    mListView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
        startActivity(new Intent(this, LocationActivity.class));
    }
}