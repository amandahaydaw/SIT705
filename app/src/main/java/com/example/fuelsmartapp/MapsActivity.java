package com.example.fuelsmartapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.fuelsmartapp.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    private Button btn;
    private Marker marker;
    private ActivityMapsBinding binding;
    SearchView searchView;
    String line = "";
    String[] tokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        searchView = findViewById(R.id.idSearchView);
            // If you need to read the whole file row by row

//        btn = findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                marker.hideInfoWindow();
//            }
//        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //adding on query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);

    }

    // Defining ordered collection as WeatherSample class
    private List<ReadData> readData = new ArrayList<>();
    List<List<Double>> Latitudelines = new ArrayList<>();
    List<List<Double>> Longitude = new ArrayList<>();
    List<List<String>> Site_Brand = new ArrayList<>();



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(false);

        // Add a marker in Sydney and move the camera

//        for(List<Double> line: Latitudelines) {
//            for (List<Double> line2 : Longitude) {
//                for (List<String> line3 : Site_Brand) {
//                    for (Double Latitudelines_value : line) {
//                        for (Double Longitude_value : line2) {
//                            for (String Site_Brand_value : line3) {
//                                System.out.println("Values are >>>>>> " + Latitudelines_value +Longitude_value+Site_Brand_value);
//                                LatLng Melb = new LatLng(Latitudelines_value,Longitude_value);
//                                mMap.addMarker(new MarkerOptions().position(Melb).title(Site_Brand_value).icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.seveneleven)));
//                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Melb,20f));
//
//                            }
//                        }
//                    }
//
//                }
//            }
//        }

//        List<Double> Latitudelines1 = new ArrayList<Double>();
//        List<Double> Longitude1 = new ArrayList<Double>();
//        List<String> Site_Brand1 = new ArrayList<String>();
//
//        System.out.println("before 1 " + Latitudelines);
//        System.out.println("before 1 " + Longitude);
//        System.out.println("before 1 " + Site_Brand);
//
//        for (List<Double> line : Latitudelines) {
//            Latitudelines1.addAll(line);
//        }
//
//        for (List<Double> line2 : Longitude) {
//            Longitude1.addAll(line2);
//
//        }
//
//        for (List<String> line3 : Site_Brand) {
//            Site_Brand1.addAll(line3);
//        }
//
//        System.out.println("after 1 " + Latitudelines1);
//        System.out.println("after 2 " + Longitude1);
//        System.out.println("after 3 " + Site_Brand1);

//        for (Double Latitudelines_value : Latitudelines1) {
//            for (Double Longitude_value : Longitude1) {
//                for (String Site_Brand_value : Site_Brand1) {
//                    System.out.println("Values are >>>>>> " + Latitudelines_value + Longitude_value + Site_Brand_value);
//                    LatLng Melb = new LatLng(Latitudelines_value, Longitude_value);
//                    mMap.addMarker(new MarkerOptions().position(Melb).title(Site_Brand_value).icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.seveneleven)));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Melb, 20f));
//
//                }
//            }
//        }


//        System.out.println("this >>>>>> after " + tokens[1] );
//        double lat = 0;
//        double longi = 0;
////        for (int i = 0; i < tokens.length(); i++) {
//            lat = Double.parseDouble(tokens[7]);
//            longi = Double.parseDouble(tokens[8]);
//
//            LatLng Melb = new LatLng(lat, longi);
//            mMap.addMarker(new MarkerOptions().position(Melb).title(tokens[1]).icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.seveneleven)));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Melb, 10f));

//        }

//        System.out.println("this >>>>>> "+lines);
//        LatLng Melb = new LatLng(sample.getSite_Latitude(),sample.getSite_Longitude());
//        mMap.addMarker(new MarkerOptions().position(Melb).title(Site_Brand_value).icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.seveneleven)));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Melb,15f));
        // Setting a custom info window adapter for the google map
//        InfoWindowAdapter markerInfoWindowAdapter = new InfoWindowAdapter(getApplicationContext());
//        googleMap.setInfoWindowAdapter(markerInfoWindowAdapter);

        // Adding and showing marker when the map is touched

//        mMap.clear();
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(Melb);
//        mMap.animateCamera(CameraUpdateFactory.newLatLng(Melb));
//        marker = mMap.addMarker(markerOptions);
//         marker.showInfoWindow();

//        googleMap.setOnInfoWindowClickListener(this::onInfoWindowClick);
        readWeatherData();
    }

    ReadData sample = new ReadData();

    private void readWeatherData() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        // Initialization
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                tokens = line.split(",");
                LatLng Melb = new LatLng(Double.parseDouble(tokens[7]),  Double.parseDouble(tokens[8]));
                mMap.addMarker(new MarkerOptions().position(Melb).title(tokens[1]).icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.seveneleven)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Melb, 18f));
            }

        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Gir Forest Clicked!!!!", Toast.LENGTH_SHORT).show();
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

}