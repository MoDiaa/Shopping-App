package activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.example.shoppingapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Distance extends FragmentActivity {
    //        GoogleApiClient.OnConnectionFailedListener, RoutingListener
    private static final String TAG = "Distance";


    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    private List<Polyline> polylines;
    private LatLng start;
    private LatLng end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps2);
        Intent intent = getIntent();
        start = new LatLng(LocationActivity.currentLocation.getLatitude(), LocationActivity.currentLocation.getLongitude());
        end = new LatLng(intent.getDoubleExtra("LatitudeShop", 0), intent.getDoubleExtra("LongitudeShop", 0));

        Log.d(TAG, "startstartstartstartstartstartstartstartstartstart          " + start);
        Log.d(TAG, "endendendendendendendendendendendendendendendendend         " + end);
        DisplayTrack();

//        place1 = new MarkerOptions().position(new LatLng(LocationActivity.currentLocation.getLatitude(), LocationActivity.currentLocation.getLongitude())).title("Location 1");
//        place2 = new MarkerOptions().position(new LatLng(intent.getDoubleExtra("LatitudeShop", 0), intent.getDoubleExtra("LongitudeShop", 0))).title("Location 2");
//        //Findroutes(start, end);
//
//        MapFragment mapFragment = (MapFragment) getFragmentManager()
//                .findFragmentById(R.id.mapNearBy);
//        mapFragment.getMapAsync(this);
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        Log.d("mylog", "Added Markers");
//        mMap.addMarker(place1);
//        mMap.addMarker(place2);
//        LatLng latLng = new LatLng(LocationActivity.currentLocation.getLatitude(), LocationActivity.currentLocation.getLongitude());
//        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
//        googleMap.addMarker(place1);
//    }

//    public void Findroutes(LatLng Start, LatLng End) {
//        if (Start == null || End == null) {
//            Toast.makeText(Distance.this, "Unable to get location", Toast.LENGTH_LONG).show();
//        } else {
//
//            Routing routing = new Routing.Builder()
//                    .travelMode(AbstractRouting.TravelMode.DRIVING)
//                    .withListener(this)
//                    .alternativeRoutes(true)
//                    .waypoints(Start, End)
//                    .key("AIzaSyC6nb3GqrQcSX-9cQdhZ9fY-buVvYZxFj4")  //also define your api key here.
//                    .build();
//            routing.execute();
//        }
//    }
//
//    @Override
//    public void onRoutingFailure(RouteException e) {
//        View parentLayout = findViewById(android.R.id.content);
//        Snackbar snackbar = Snackbar.make(parentLayout, e.toString(), Snackbar.LENGTH_LONG);
//        snackbar.show();
//    }
//
//    @Override
//    public void onRoutingStart() {
//        Toast.makeText(Distance.this, "Finding Route...", Toast.LENGTH_LONG).show();
//    }
//
//    //If Route finding success..
//    @Override
//    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
//
//        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
//        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//        if (polylines != null) {
//            polylines.clear();
//        }
//        PolylineOptions polyOptions = new PolylineOptions();
//        LatLng polylineStartLatLng = null;
//        LatLng polylineEndLatLng = null;
//
//
//        polylines = new ArrayList<>();
//        //add route(s) to the map using polyline
//        for (int i = 0; i < route.size(); i++) {
//
//            if (i == shortestRouteIndex) {
//                polyOptions.color(getResources().getColor(R.color.colorPrimary));
//                polyOptions.width(7);
//                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
//                Polyline polyline = mMap.addPolyline(polyOptions);
//                polylineStartLatLng = polyline.getPoints().get(0);
//                int k = polyline.getPoints().size();
//                polylineEndLatLng = polyline.getPoints().get(k - 1);
//                polylines.add(polyline);
//
//            } else {
//
//            }
//
//        }
//
//        //Add Marker on route starting position
//        MarkerOptions startMarker = new MarkerOptions();
//        startMarker.position(polylineStartLatLng);
//        startMarker.title("My Location");
//        mMap.addMarker(startMarker);
//
//        //Add Marker on route ending position
//        MarkerOptions endMarker = new MarkerOptions();
//        endMarker.position(polylineEndLatLng);
//        endMarker.title("Destination");
//        mMap.addMarker(endMarker);
//    }
//
//    @Override
//    public void onRoutingCancelled() {
//        Findroutes(start, end);
//    }

//    public void onTaskDone(Object... values) {
//        if (currentPolyline != null)
//            currentPolyline.remove();
//        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
//    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Findroutes(start, end);
//
//    }


    private void DisplayTrack() {
        try {
            double[] StartArray = new double[]{start.latitude, start.longitude};
            double[] EndArray = new double[]{end.latitude, end.longitude};

            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + Arrays.toString(StartArray).replace("[", "(").replace("]", ")") + "/"
                    + Arrays.toString(EndArray).replace("[", "(").replace("]", ")"));

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps" + start + "/"
                    + end);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }
}

