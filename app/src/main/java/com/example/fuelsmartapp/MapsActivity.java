package com.example.fuelsmartapp;

import static com.example.fuelsmartapp.signup.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.fuelsmartapp.databinding.ActivityMapsBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
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
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int PERMISSIONS_REQUEST_ENABLE_GPS = 9002;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 9003;
    private GoogleMap mMap;
    private boolean mLocationPermissionGranted = false;
    GoogleApiClient mGoogleApiClient;
    private Button btn;
    private Marker marker;
    private ActivityMapsBinding binding;
    SearchView searchView;
    String line = "";
    String[] tokens;
    LatLng coord;
    LatLng mylocation;
    String title, title2, name;
    String[] token;
    String fuel_type, img, address, suburb, state,titles;
    double lat, lon, cost, postcode;
    LatLng pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        passData();
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

    private boolean checkMapServices() {
        if (isServicesOK()) {
            if (isMapsEnabled()) {
                System.out.println("reach1");
                return true;
            }
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS Required. Enable GPS otherwise location tracking won’t work! ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            buildAlertMessageNoGps();
            return false;
        } else {
            System.out.println("reach2");

            readDataFromCSV();
        }
        return true;

    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
//            getChatrooms();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapsActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        if (mLocationPermissionGranted) {
            Log.d(TAG, "readDataFromCSV: called.");
            readDataFromCSV();
        } else {
            getLocationPermission();
        }
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_ENABLE_GPS: {
//                if (mLocationPermissionGranted) {
//                    Log.d(TAG, "readDataFromCSV: called.");
//                    readDataFromCSV();
//                } else {
//                    getLocationPermission();
//                }
//            }
//        }

    }

    public void passData() {
        TextView display = findViewById(R.id.fuel_type_text);
        Bundle bn = getIntent().getExtras();
        name = bn.getString("text5");
        display.setText(String.valueOf(name));
    }


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


        googleMap.setOnInfoWindowClickListener(this::onInfoWindowClick);

        checkMapServices();

    }


    private void readDataFromCSV() {
        // Read the raw csv file
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        boolean header = true;
        List<String> list = new ArrayList<>();
        List<LatLng> latLngList = new ArrayList<LatLng>();
        List<String> imglist = new ArrayList<>();

        // Initialization
        try {
            reader.readLine();
//            while ((line = reader.readLine()) != null) {
//                tokens = line.split(",");
//                if (header || tokens[9].contains(name)) {
//                    header = false;
//                    list.add(line);
//
//                }
//                for (int i = 0; i < list.size(); i++) {
//                    System.out.println("here" + list.get(i));
//                }

//                coord = new LatLng(Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]));
//                mylocation = new LatLng(-27.923659,153.403602);
//                title = tokens[2] + " \n" + "Address: " + tokens[3] + " ," + " " + tokens[4] + " ," + tokens[5] + " ," + tokens[6] + " \n" + tokens[9] + " ," + " $" + tokens[10];
//                title2 = "Fuel selected as " + tokens[9] + " is costing" + " $" + tokens[10] + " per liter";
//                mMap.addMarker(new MarkerOptions().position(mylocation).title("Current Position"));
//
//                mMap.addMarker(new MarkerOptions().position(coord).title(title).icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.seveneleven)));
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 18f));
//                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));


//            }

            mylocation = new LatLng(-27.923659, 153.403702);

            while ((line = reader.readLine()) != null) // Read until end of file
            {


                if(line.contains(name)){
                System.out.println("fuel here" + list);
                lat = Double.parseDouble(line.split(",")[7]);
                lon = Double.parseDouble(line.split(",")[8]);
                fuel_type = new String(line.split(",")[9]);




                img = new String(line.split(",")[2]);
                 pos = new LatLng(lat, lon);
                imglist.add(img);
                address = new String(line.split(",")[3]);
                suburb = new String(line.split(",")[4]);
                state = new String(line.split(",")[5]);
                postcode = Double.parseDouble(line.split(",")[6]);
                cost = Double.parseDouble(line.split(",")[10]);
                titles = img.toUpperCase() + " \n" + "Address: " + address + " ," + " " + suburb + " ," + state + " ," + postcode + " \n" + fuel_type + " ," + " $" + cost;

                    mMap.addMarker(new MarkerOptions().position(mylocation).title("Current Position"));
                    mMap.addMarker(new MarkerOptions().position(pos).title(String.valueOf(titles)).icon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier(img, "drawable", getPackageName()))));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 18f));
                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));

            }
                else
                {
                    mMap.addMarker(new MarkerOptions().position(mylocation).title("Current Position"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 18f));
                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
                    Toast.makeText(this, "Somethings went wrong , currently no Fuel station available for your fuel type", Toast.LENGTH_SHORT).show();
                }


// Add them to map
//            for (LatLng pos : latLngList) {
//
//                for (int i = 0; i < imglist.size(); i++) {
//
//                    mMap.addMarker(new MarkerOptions().position(pos).title(title).icon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier(imglist.get(i), "drawable", getPackageName()))));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 18f));
//                    mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
//                }
//            }
//            coord = new LatLng(Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]));
//            mylocation = new LatLng(-27.923659, 153.403702);
//            title = tokens[2] + " \n" + "Address: " + tokens[3] + " ," + " " + tokens[4] + " ," + tokens[5] + " ," + tokens[6] + " \n" + tokens[9] + " ," + " $" + tokens[10];
//            title2 = "Fuel selected as " + tokens[9] + " is costing" + " $" + tokens[10] + " per liter";
//            mMap.addMarker(new MarkerOptions().position(mylocation).title("Current Position"));
//
//            mMap.addMarker(new MarkerOptions().position(coord).title(title).icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.seveneleven)));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 18f));
//            mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
        }
        }catch (IOException e) {
            Log.wtf("MapsActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, title2, Toast.LENGTH_LONG).show();
    }
//display text once user click

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public void profile_nav(View view) {
        Intent intend = new Intent(this, profile.class);
        startActivity(intend);
    }


    public void adding_vehicle_expenses(View view) {
//        Intent intend = new Intent(this, adding_vehicle_expense.class);
//        startActivity(intend);
        Toast.makeText(this, "Service is not available !", Toast.LENGTH_LONG).show();

    }

    public void changing_fuel_type(View view) {
        Intent intend = new Intent(this, FuelType.class);
        startActivity(intend);
    }

    public void change_location(View view) {
        Toast.makeText(this, "Service is not available !", Toast.LENGTH_LONG).show();

    }

    public void notification(View view) {
        Toast.makeText(this, "Service is not available !", Toast.LENGTH_LONG).show();
    }


}