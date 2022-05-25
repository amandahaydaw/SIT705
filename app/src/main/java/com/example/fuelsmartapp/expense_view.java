package com.example.fuelsmartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class expense_view extends AppCompatActivity {
    TextView spent;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Spinner spinner2;

    //    final double[] total = {0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        spent = (TextView) findViewById(R.id.spent);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(expense_view.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.month));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter);

        fStore.collection("data").whereEqualTo("ID", userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int total = 0;
                    String itemCost = null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        itemCost = document.getString("cost");
                        total += Integer.parseInt(itemCost);

                        System.out.println(document.getId() + " => " + document.getData());
                    }
                    spent.setText("$ " + total);


                } else {
                    spent.setText("$ " + "0");
                    System.out.println("Error getting document: " + task.getException());
                }
            }

        });


        myAdapter.notifyDataSetChanged();

        spinner2.setOnItemSelectedListener(onItemSelectedListener1);
    }


    final AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    if (spinner2.getSelectedItem().equals("January")) {
                        String greater_date_April = "01/01/2022 23:59:59";
                        String less_date_April = "31/01/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("February")) {
                        String greater_date_April = "01/02/2022 23:59:59";
                        String less_date_April = "31/02/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("March")) {
                        String greater_date_April = "01/03/2022 23:59:59";
                        String less_date_April = "31/03/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    System.out.println("yess " + task.isSuccessful());
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("April")) {
                        String greater_date_April = "01/04/2022 23:59:59";
                        String less_date_April = "31/04/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    System.out.println("yess " + task.isSuccessful());
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("May")) {
                        String greater_date_April = "01/05/2022 23:59:59";
                        String less_date_April = "31/05/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("June")) {
                        String greater_date_April = "01/06/2022 23:59:59";
                        String less_date_April = "31/06/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("July")) {
                        String greater_date_April = "01/07/2022 23:59:59";
                        String less_date_April = "31/07/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("August")) {
                        String greater_date_April = "01/08/2022 23:59:59";
                        String less_date_April = "31/08/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("September")) {
                        String greater_date_April = "01/09/2022 23:59:59";
                        String less_date_April = "31/09/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("October")) {
                        String greater_date_April = "01/10/2022 23:59:59";
                        String less_date_April = "31/10/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("November")) {
                        String greater_date_April = "01/11/2022 23:59:59";
                        String less_date_April = "31/11/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (spinner2.getSelectedItem().equals("December")) {
                        String greater_date_April = "01/12/2022 23:59:59";
                        String less_date_April = "31/12/2022 23:59:59";
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
                        try {
                            Date GDate = dateFormat.parse(greater_date_April);
                            Date LDate = dateFormat.parse(less_date_April);
                            fStore.collection("data").whereEqualTo("ID", userId).whereGreaterThanOrEqualTo("date", new Timestamp(GDate)).whereLessThanOrEqualTo("date", new Timestamp(LDate)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int total = 0;
                                        String itemCost = null;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            itemCost = document.getString("cost");
                                            total += Integer.parseInt(itemCost);

                                            System.out.println(document.getId() + " => " + document.getData());
                                        }
                                        spent.setText("$ " + total);


                                    } else {
                                        spent.setText("$ " + "0");
                                        System.out.println("Error getting documents: " + task.getException());
                                    }
                                }

                            });
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    System.out.println("no data ");
                }

            };

    public void add(View view) {
        Intent intend = new Intent(this, adding_vehicle_expense.class);
        startActivity(intend);
    }


}
