package com.example.fuelsmartapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class adding_vehicle_expense extends AppCompatActivity {

    // creating a variable
    // for our graph view.
    GraphView graphView;
    Spinner spinner1;
    Intent intend;
    DatePickerDialog picker;
    EditText eText, date, vehicle, cost, liter;
    Button btnGet, saves;
    TextView tvw;


    public static final String TAG = "TAG";

    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_vehicle_expense);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        date = findViewById(R.id.date);
        cost = findViewById(R.id.cost);
        liter = findViewById(R.id.liter);
        vehicle = findViewById(R.id.vehicle);
        saves = findViewById(R.id.check);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(adding_vehicle_expense.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        tvw = (TextView) findViewById(R.id.calenderView);
        eText = (EditText) findViewById(R.id.date);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(adding_vehicle_expense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnGet = (Button) findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Date: " + eText.getText());
            }
        });


        saves.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final String dates = date.getText().toString().trim();
                                        String vehicle_number = vehicle.getText().toString().trim();
                                        String costs = cost.getText().toString().trim();
                                        String liters = liter.getText().toString().trim();
                                        String sp = spinner1.getSelectedItem().toString();

                                        View selectedView = spinner1.getSelectedView();
                                        if (spinner1.getSelectedItem().equals("Select the type of Fuel")) {
                                            TextView selectedTextView = (TextView) selectedView;
                                            selectedTextView.setError("error");
                                            spinner1.performClick();
                                        }

                                        if (TextUtils.isEmpty(dates)) {
                                            date.setError("Date is Required!");
                                            return;
                                        }

                                        if (TextUtils.isEmpty(vehicle_number)) {
                                            vehicle.setError("vehicle number is Required!");
                                            return;
                                        }

                                        if (TextUtils.isEmpty(costs)) {
                                            cost.setError("Fuel cost is Required!");
                                            return;
                                        }

                                        if (TextUtils.isEmpty(liters)) {
                                            liter.setError("Total liters is Required!");
                                            return;
                                        }

                                        progressBar.setVisibility(View.VISIBLE);
                                        userID = fAuth.getCurrentUser().getUid();
                                        DocumentReference documentReference = fStore.collection("data").document(userID);
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("date", dates);
                                        user.put("vehicle_number", vehicle_number);
                                        user.put("cost", costs);
                                        user.put("liters", liters);
                                        user.put("fuel_type", sp);


                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("here1" + TAG, "onSuccess: Data inserted for " + userID);
                                                Toast.makeText(adding_vehicle_expense.this, "Data inserted Successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);

                                                startActivity(new Intent(getApplicationContext(), MapsActivity.class));


                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("here2" + TAG, "onFailure: " + e.toString());
                                            }
                                        });





                                    }

                                }
        );
    }

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
