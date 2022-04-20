package com.example.fuelsmartapp;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fuelsmartapp.R;
import com.example.fuelsmartapp.main.MainFragment;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

    }
    public void open_registration(View view) {
        Intent intend = new Intent(this,signup.class);
        startActivity(intend);
    }


}
