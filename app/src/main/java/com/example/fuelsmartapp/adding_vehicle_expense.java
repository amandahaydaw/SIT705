package com.example.fuelsmartapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;

public class adding_vehicle_expense extends AppCompatActivity {

    // creating a variable
    // for our graph view.
    GraphView graphView;
    Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_vehicle_expense);
        spinner1 = (Spinner) findViewById(R.id.spinner1);



        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(adding_vehicle_expense.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        // on below line we are initializing our graph view.
//        graphView = findViewById(R.id.idGraphView);

        // on below line we are adding data to our graph view.
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
//                // on below line we are adding
//                // each point on our x and y axis.
//                new DataPoint(0, 1),
//                new DataPoint(1, 3),
//                new DataPoint(2, 4),
//                new DataPoint(3, 9),
//                new DataPoint(4, 6),
//                new DataPoint(5, 3),
//                new DataPoint(6, 6),
//                new DataPoint(7, 1),
//                new DataPoint(8, 2)
//        });
//
//        // after adding data to our line graph series.
//        // on below line we are setting
//        // title for our graph view.
//        graphView.setTitle("My Graph View");
//
//        // on below line we are setting
//        // text color to our graph view.
//        graphView.setTitleColor(R.color.purple_200);
//
//        // on below line we are setting
//        // our title text size.
//        graphView.setTitleTextSize(10);
//
//        // on below line we are adding
//        // data series to our graph view.
//        graphView.addSeries(series);
    }
}
