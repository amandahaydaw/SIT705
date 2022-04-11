package com.example.fuelsmartapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.fuelsmartapp.R;

public class FuelType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_type);
Spinner mySpinner =(Spinner) findViewById(R.id.spinner1);
ArrayAdapter<String>myAdapter=new ArrayAdapter<String>(FuelType.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mySpinner.setAdapter(myAdapter);
    }


}


