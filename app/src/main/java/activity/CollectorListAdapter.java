package activity;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shoppingapplication.R;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class CollectorListAdapter extends ArrayAdapter<Collector> {

    private static final String TAG = "ShopListAdapter";
    private Context mContext;
    private int mResource;



    String format(float distanceInMetres) {
        float kms = distanceInMetres / 1000;
        NumberFormat formatter = NumberFormat.getInstance();
        return formatter.format(kms);
    }

    private static String CurrencyFormatting(String number){

        DecimalFormat Formatter =new DecimalFormat("###,###,##0.00");
        return Formatter.format(Double.parseDouble(number));
    }



    public CollectorListAdapter(Context context, int resource, ArrayList<Collector> objects) {

        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String Shopname = getItem(position).getName();
        String Specialoffers = getItem(position).getSpecialoffers();
        int Price = getItem(position).getPrice();

        Double startLatitude = getItem(position).getLatitude();
        Double startLongitude = getItem(position).getLongitude();



        float[] results = new float[3];
        Location.distanceBetween(startLatitude,
                startLongitude,
                LocationActivity.currentLocation.getLatitude(),
                LocationActivity.currentLocation.getLatitude(),
                results);

        Log.d(TAG, Arrays.toString(results));


        //Create the person object with the information
        Product Product = new Product();
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


        return convertView;
    }
}