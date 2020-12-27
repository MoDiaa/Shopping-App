package activity;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoppingapplication.R;
import com.google.android.gms.maps.model.LatLng;
import com.like.LikeButton;
import com.like.OnAnimationEndListener;
import com.like.OnLikeListener;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import app.AppConfig;

import static android.content.Context.MODE_PRIVATE;

public class CollectorListAdapter extends ArrayAdapter<Collector> {

    private static final String TAG = "CollectorListAdapter";
    private Context mContext;
    private int mResource;
    private Button ProductsButton;
    private LatLng start, end;
    LikeButton heart;


    String format(float distanceInMetres) {
        float kms = distanceInMetres / 1000;
        NumberFormat formatter = NumberFormat.getInstance();
        return formatter.format(kms);
    }

    private static String CurrencyFormatting(String number) {

        DecimalFormat Formatter = new DecimalFormat("###,###,##0.00");
        return Formatter.format(Double.parseDouble(number));
    }

    public CollectorListAdapter(Context context, int resource, ArrayList<Collector> objects) {

        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {


        //Get the Shop information
        String Shopname = getItem(position).getName();
        String Specialoffers = getItem(position).getSpecialoffers();
        int Price = getItem(position).getPrice();
        final Double startLatitude = getItem(position).getLatitude();
        final Double startLongitude = getItem(position).getLongitude();
        final String user_id = String.valueOf(getItem(position).getUserid());
        final String shop_id = String.valueOf(getItem(position).getShopid());
        final String product_id = String.valueOf(getItem(position).getProductid());
        final Boolean isLiked = getItem(position).getLiked();


        // Calculate  Distance Between User and Shop
        float[] results = new float[3];
        Location.distanceBetween(
                LocationActivity.currentLocation.getLatitude(),
                LocationActivity.currentLocation.getLongitude(), startLatitude,
                startLongitude,
                results);

        //create the view result for showing the animation
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView sname = (TextView) convertView.findViewById(R.id.textView);
        TextView tvname = (TextView) convertView.findViewById(R.id.textView7);
        TextView Location = (TextView) convertView.findViewById(R.id.textView9);
        TextView tvdescription = (TextView) convertView.findViewById(R.id.textView10);

        sname.setText(Shopname);
        tvdescription.setText(Specialoffers);
        tvname.setText(CurrencyFormatting(Integer.toString(Price)) + " EGP");
        Location.setText(format(results[0]) + " km");

        ProductsButton = (Button) convertView.findViewById(R.id.buttonloc);
        ProductsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            start = new LatLng(LocationActivity.currentLocation.getLatitude(), LocationActivity.currentLocation.getLongitude());
                            end = new LatLng(startLatitude, startLongitude);
                            double[] StartArray = new double[]{start.latitude, start.longitude};
                            double[] EndArray = new double[]{end.latitude, end.longitude};
                            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + Arrays.toString(StartArray).replace("[", "(").replace("]", ")") + "/"
                                    + Arrays.toString(EndArray).replace("[", "(").replace("]", ")"));

                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps" + start + "/"
                                    + end);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    }
                }
        );
        heart = convertView.findViewById(R.id.favBtn);

        heart.setOnLikeListener(new OnLikeListener() {

            // Add Data to the Saved Shop Table by like
            @Override
            public void liked(LikeButton likeButton) {

                StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_SAVED_SHOPS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user_id", user_id);
                        params.put("shop_id", shop_id);
                        params.put("product_id", product_id);
                        return params;
                    }
                };
                Volley.newRequestQueue(getContext()).add(strReq);
                Toast.makeText(getContext(),
                        "Shop Saved Successfully", Toast.LENGTH_LONG).show();

            }

            // Delete Data to the Saved Shop Table by Unlike
            @Override
            public void unLiked(LikeButton likeButton) {

                StringRequest strReq10 = new StringRequest(Request.Method.POST, AppConfig.URL_Delete_SAVED_SHOPS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response: " + response.toString());
                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean error = jObj.getBoolean("error");
                            if (!error) {
                            } else {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Posting params to register url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user_id", user_id);
                        params.put("shop_id", shop_id);
                        params.put("product_id", product_id);
                        return params;
                    }
                };
                Volley.newRequestQueue(getContext()).add(strReq10);
                Toast.makeText(getContext(),
                        "Shop Saved Deleted Successfully", Toast.LENGTH_LONG).show();

            }
        });
        return convertView;
    }
}