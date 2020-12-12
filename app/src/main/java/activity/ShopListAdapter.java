package activity;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shoppingapplication.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopListAdapter extends ArrayAdapter<offers> {

    private static final String TAG = "ShopListAdapter";
    private Context mContext;
    private int mResource;


    public ShopListAdapter(Context context, int resource, ArrayList<offers> objects) {

        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        int Price = getItem(position).getPrice();
        String Specialoffers = getItem(position).getSpecialoffers();

        //Create the person object with the information
        Product Product = new Product();
        //create the view result for showing the animation
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname = (TextView) convertView.findViewById(R.id.textView7);
        TextView tvdescription = (TextView) convertView.findViewById(R.id.textView10);

        tvname.setText(Integer.toString(Price));
        tvdescription.setText(Specialoffers);

        return convertView;
    }
}