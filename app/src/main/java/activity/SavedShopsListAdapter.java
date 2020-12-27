package activity;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shoppingapplication.R;
import com.like.LikeButton;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SavedShopsListAdapter extends ArrayAdapter<SavedShops> {

    LikeButton heart;

    private static String CurrencyFormatting(String number) {

        DecimalFormat Formatter = new DecimalFormat("###,###,##0.00");
        return Formatter.format(Double.parseDouble(number));
    }

    private static final String TAG = "SavedShopsListAdapter";
    private Context mContext;
    private int mResource;

    /**
     * Default constructor for the SavedShopsListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public SavedShopsListAdapter(Context context, int resource, ArrayList<SavedShops> objects) {

        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String shopname = getItem(position).getShopName();
        String productname = getItem(position).getProductName();
//        int Price = 100;
//        String Specialoffers = "saddddddddddd";


        //Create the person object with the information
        //SavedShops savedshops = new SavedShops(shopname,productname,Specialoffers,Price);
        //create the view result for showing the animation
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView SavShop = (TextView) convertView.findViewById(R.id.SavShopName);
        TextView SavProduct = (TextView) convertView.findViewById(R.id.SavProductName);
//        TextView Sprice = (TextView) convertView.findViewById(R.id.SavPrice);
//        TextView Offer = (TextView) convertView.findViewById(R.id.SavOffer);


        SavShop.setText(shopname);
        SavProduct.setText(productname);
//        Sprice.setText(CurrencyFormatting(Integer.toString(Price)) + " EGP");
//        Offer.setText(Specialoffers);


        return convertView;
    }

}