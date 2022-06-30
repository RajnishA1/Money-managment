package com.Rajnish.moneymanagment;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {



    Button addbutton , withdrawal;
    EditText incomeedittext , expenseedittext;
    TextView todayincometextview,todoyexpense,Totalcash,MonthlyIncome, MonthlyExpense;
    CheckBox photocopy , moneywithdrawal,moneytransfer,otherincome ,Home ,Business,study,other,millexpense , millincome;
    Post post;

    DatabaseReference testdatarefrense, databaseReferenceTodayExpense ,databaseReferenceTodayIncome ,databaseReferenceThisMonthIncome,databaseReferenceThisMonthExpense;


    int cashfromfirebase = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        todayincometextview = view.findViewById(R.id.todayincometextview);
        todoyexpense = view.findViewById(R.id.todoyexpense);
        MonthlyIncome = view.findViewById(R.id.MonthlyIncome);
        MonthlyExpense = view.findViewById(R.id.MonthlyExpanse);
        Totalcash = view.findViewById(R.id.Totalcash);
        addbutton = view.findViewById(R.id.addbutton);
        withdrawal = view.findViewById(R.id.WithdrawalButton);
        incomeedittext = view.findViewById(R.id.incomeedittext);
        expenseedittext = view.findViewById(R.id.expenseedittext);
        /// checkbox for income
        photocopy = view.findViewById(R.id.photocopy);
        millincome = view.findViewById(R.id.Millincome);
        moneywithdrawal = view.findViewById(R.id.moneywithdrawal);
        moneytransfer = view.findViewById(R.id.moneytransfer);
        otherincome = view.findViewById(R.id.otherincome);
        //checkbox for expense
        Home = view.findViewById(R.id.Home);
        Business = view.findViewById(R.id.Business);
        study = view.findViewById(R.id.Study);
        other = view.findViewById(R.id.Other);
        millexpense = view.findViewById(R.id.Millexpense);








        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String datetostr = format.format(today);

        Date month = new Date();
        SimpleDateFormat formatformonth = new SimpleDateFormat("MM-yyyy");
        String datetostrformont = formatformonth.format(month);

        TodayIncome(datetostr);
        ThisMonthIncome(datetostrformont);

        TodayExpense(datetostr);
        ThisMonthExpense(datetostrformont);






        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference1 = db.collection("NewCash").document("cash");

        documentReference1.addSnapshotListener((value, error) -> {
            if(value.exists()){
                String value1 =  value.getString("cash");
                int value2 = Integer.parseInt(String.valueOf(value1));
                cashfromfirebase = value2;


            }
        });


        //// Add value to Database data base ......................................


        post = new Post();

        databaseReferenceTodayIncome = FirebaseDatabase.getInstance().getReference().child("TodayIncome").child(datetostr);
        databaseReferenceThisMonthIncome = FirebaseDatabase.getInstance().getReference().child("ThisMonthIncome").child(datetostrformont);
        databaseReferenceTodayExpense = FirebaseDatabase.getInstance().getReference().child("TodayExpense").child(datetostr);
        databaseReferenceThisMonthExpense = FirebaseDatabase.getInstance().getReference().child("ThisMonthExpense").child(datetostrformont);
        testdatarefrense = FirebaseDatabase.getInstance().getReference().child("Income");



       addbutton.setOnClickListener(new View.OnClickListener() {
       Post postadd = new Post();
        @Override
        public void onClick(View view) {
            Date today1 = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String datetostr1 = format1.format(today1);
            SimpleDateFormat timeformage = new SimpleDateFormat("hh:mm:a");
            String time = timeformage.format(today1);

            if (checkConnection()) {

                if (photocopy.isChecked() || moneytransfer.isChecked() || moneywithdrawal.isChecked()|| otherincome.isChecked() || millincome.isChecked()) {

                    if (!TextUtils.isEmpty(incomeedittext.getText().toString())) {


                        if (moneytransfer.isChecked()) {

                            postadd.setType("Money transfer");
                            moneytransfer.setChecked(false);

                        }
                        if (photocopy.isChecked()) {

                            postadd.setType("Photocopy");
                            photocopy.setChecked(false);
                        }
                        if (moneywithdrawal.isChecked()) {
                            postadd.setType("Money withdrawal");
                            moneywithdrawal.setChecked(false);
                        }
                        if (otherincome.isChecked()) {
                            postadd.setType("Other income");
                            otherincome.setChecked(false);
                        }
                        if (millincome.isChecked()) {
                            postadd.setType("Mill income");
                            millincome.setChecked(false);
                        }
                        postadd.setTodayEarning(incomeedittext.getText().toString());
                        postadd.setDate( datetostr1);
                        postadd.setMonthdate("monthdate", datetostrformont);

                        postadd.setTime(time);

                        String id = databaseReferenceTodayIncome.push().getKey();
                        databaseReferenceTodayIncome.child(id).setValue(postadd);
                        String id1 = databaseReferenceThisMonthIncome.push().getKey();
                        databaseReferenceThisMonthIncome.child(id1).setValue(postadd);


                        // Create a new user with a first, middle, and last name
                        int incomeint = cashfromfirebase + Integer.parseInt(String.valueOf(incomeedittext.getText().toString()));
                        Map<String, Object> user = new HashMap<>();
                        user.put("cash", String.valueOf(incomeint));


                        // Add a new document with a generated ID

                        db.collection("NewCash")
                                .document("cash")
                                .set(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                        incomeedittext.setText(null);
                        cashfromfirebase = 0;

                    } else {
                        incomeedittext.setHintTextColor(Color.RED);
                    }

                }
            }
            else {
                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }


    }
    });


        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new user with a first and last name

                Date today2 = new Date();
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
                String datetostr2 = format2.format(today2);

                String time = timeformage.format(today2);
                if (checkConnection()) {
                    if(Home.isChecked() || Business.isChecked() || study.isChecked() || other.isChecked() || millexpense.isChecked())


                        if (!TextUtils.isEmpty(expenseedittext.getText().toString() ) ) {

                            if (Home.isChecked()) {
                                post.setType("Home");
                                Home.setChecked(false);
                            }
                            if (Business.isChecked()) {
                                post.setType("Business");
                                Business.setChecked(false);
                            }
                            if (study.isChecked()) {
                                post.setType("Study");
                                study.setChecked(false);
                            }
                            if (other.isChecked()) {
                                post.setType("Other");
                                other.setChecked(false);
                            }
                            if (millexpense.isChecked()) {
                                post.setType("Mill expense");
                                millexpense.setChecked(false);
                            }

                            post.setDate(datetostr2);
                            post.setTime(time);

                            post.setMonthdate("monthdate", datetostrformont);
                            post.setTodayExpense("expense", expenseedittext.getText().toString());
                            post.setThisMontExpense("ThisMonthExpense", expenseedittext.getText().toString());

                            String id3 = databaseReferenceTodayExpense.push().getKey();
                            databaseReferenceTodayExpense.child(id3).setValue(post);
                            String id4 = databaseReferenceThisMonthExpense.push().getKey();
                            databaseReferenceThisMonthExpense.child(id4).setValue(post);


                            // Create a new user with a first, middle, and last name
                            int incomeint1 = cashfromfirebase - Integer.parseInt(String.valueOf(expenseedittext.getText().toString()));
                            Map<String, Object> user11 = new HashMap<>();
                            user11.put("cash", String.valueOf(incomeint1));


// Add a new document with a generated ID


                            db.collection("NewCash")
                                    .document("cash")
                                    .set(user11)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });


                            expenseedittext.setText(null);
                            cashfromfirebase = 0;

                        } else {
                            expenseedittext.setHintTextColor(Color.RED);
                            photocopy.setHighlightColor(Color.RED);
                        }


                }
                else {
                    Toast.makeText(getContext(), "No Internet connected", Toast.LENGTH_SHORT).show();
                }

            }
        });




        return view;
    }



    ///// AllDataRetriveFromFirebase.....................................................

    public void TodayIncome(String date) {

        databaseReferenceTodayIncome = FirebaseDatabase.getInstance().getReference().child("TodayIncome").child(date);
        databaseReferenceTodayIncome.orderByChild("date").equalTo(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayEarning");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    todayincometextview.setText("₹ " + sum);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void ThisMonthIncome(String date ) {

        databaseReferenceThisMonthIncome = FirebaseDatabase.getInstance().getReference().child("ThisMonthIncome").child(date);
        databaseReferenceThisMonthIncome.orderByChild("monthdate").equalTo(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayEarning");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    MonthlyIncome.setText("₹ " + sum);




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void TodayExpense(String date ) {

        databaseReferenceTodayExpense = FirebaseDatabase.getInstance().getReference().child("TodayExpense").child(date);
        databaseReferenceTodayExpense.orderByChild("date").equalTo(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayExpense");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    todoyexpense.setText("₹ " + sum);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ThisMonthExpense(String date) {

        databaseReferenceThisMonthExpense = FirebaseDatabase.getInstance().getReference().child("ThisMonthExpense").child(date);
        databaseReferenceThisMonthExpense.orderByChild("monthdate").equalTo(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {


                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;

                    MonthlyExpense.setText("₹ " + sum);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }




    boolean checkConnection() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenetwork = manager.getActiveNetworkInfo();
        if (null != activenetwork) {
            return activenetwork.getType() == ConnectivityManager.TYPE_WIFI || activenetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        else {
            return false;
        }
    }


}