package activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.example.shoppingapplication.R;


public class ProductListAdapter extends ArrayAdapter<Product> {

    private static final String TAG = "ProductListAdapter";
    private Context mContext;
    private int mResource;

    /**
     * Default constructor for the PersonListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public ProductListAdapter(Context context, int resource, ArrayList<Product> objects) {

        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        int id = getItem(position).getId();
        String name = getItem(position).getName();
        String description = getItem(position).getDescription();
        String image_url = getItem(position).getImage_url();

        //Create the person object with the information
        Product Product = new Product();
        //create the view result for showing the animation
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView tvimage_url = (ImageView) convertView.findViewById(R.id.ImgView);
        TextView tvname = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvdescription = (TextView) convertView.findViewById(R.id.textView3);

        tvname.setText(name);
        tvdescription.setText(description);
        Glide.with(mContext).load(image_url).into(tvimage_url);

        return convertView;
    }


}