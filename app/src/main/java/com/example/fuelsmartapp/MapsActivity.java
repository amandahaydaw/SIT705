package com.example.fuelsmartapp;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.fuelsmartapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn;
    private Marker marker;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

        // Add a marker in Sydney and move the camera
        LatLng Melb = new LatLng(-37.5971198,144.9037784);
        mMap.addMarker(new MarkerOptions().position(Melb).title("7 Eleven").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.seveneleven)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Melb,15f));
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