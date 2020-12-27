package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class SavedShopsActivity extends AppCompatActivity {


    private static final String TAG = "DetailedProducts";
    public ArrayList<SavedShops> SavedShopsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_shops);
        final ListView mListView = (ListView) findViewById(R.id.listViewSavedShops);
        SharedPreferences Preferences = PreferenceManager.getDefaultSharedPreferences(SavedShopsActivity.this);
        final int id = Preferences.getInt("user_id", 1);

//SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS//
//SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS//
//SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS/SAVED_SHOPS//

        // Get the SAVED SHOPS Table Data and Store it in SavedShopsList list

        StringRequest stringRequest5 = new StringRequest(Request.Method.GET, AppConfig.URL_GET_SAVED_SHOPS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.getInt("user_id") == id) {
                            SavedShops pz = new SavedShops();
                            pz.setShopName(jsonObject.getString("4"));
                            pz.setProductName(jsonObject.getString("name"));
                            SavedShopsList.add(pz);
                            Log.d(TAG, "GET_SAVED_SHOPS :" + jsonObject);
                        }
                        filterSavedShop();
                        SavedShopsListAdapter adapter = new SavedShopsListAdapter(SavedShopsActivity.this, R.layout.activity_saved_shops_list_adapter, SavedShopsList);
                        mListView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SavedShopsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(SavedShopsActivity.this).add(stringRequest5);
    }

    void filterSavedShop() {
        ArrayList<SavedShops> cs = new ArrayList<SavedShops>();

        for (SavedShops c : SavedShopsList) {
            boolean exist = false;
            for (SavedShops old : cs) {
                if (c.getProductName().equals(old.getProductName()) && c.getShopName().equals(old.getShopName())) {
                    exist = true;
                }
            }
            if (!exist) {
                cs.add(c);
            }
        }

        SavedShopsList = cs;
    }

}