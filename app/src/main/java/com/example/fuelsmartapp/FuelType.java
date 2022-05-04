package com.example.fuelsmartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FuelType extends AppCompatActivity {
    TextView hi;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId, text;
    Spinner spinner1;
    ProgressBar progressBar;
    Intent intend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_type);

        hi = findViewById(R.id.hi);
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                hi.setText(documentSnapshot.getString("fName"));
            }
        });

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(FuelType.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


    }


    public void fuel_Check(View view) {
        View selectedView = spinner1.getSelectedView();
        if (spinner1.getSelectedItem().equals("Select the type of Fuel")) {
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error");
            spinner1.performClick();
        } else {
            text = spinner1.getSelectedItem().toString();
            progressBar.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            intend = new Intent(this, MapsActivity.class);
            intend.putExtra("text5",text);
            startActivity(intend);
        }
    }

}


