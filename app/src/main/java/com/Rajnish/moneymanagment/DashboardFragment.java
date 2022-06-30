package com.Rajnish.moneymanagment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Rajnish.moneymanagment.databinding.FragmentDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class DashboardFragment extends Fragment {

FragmentDashboardBinding binding;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceTodayExpense ,databaseReferenceTodayIncome ,databaseReferenceThisMonthIncome,databaseReferenceThisMonthExpense;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(getLayoutInflater());


        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String datetostr = format.format(today);

        Date month = new Date();
        SimpleDateFormat formatformonth = new SimpleDateFormat("MM-yyyy");
        String datetostrformont = formatformonth.format(month);

        databaseReferenceTodayIncome = firebaseDatabase.getInstance().getReference().child("TodayIncome").child(datetostr);
        databaseReferenceThisMonthIncome = firebaseDatabase.getInstance().getReference().child("ThisMonthIncome");
        databaseReferenceTodayExpense = firebaseDatabase.getInstance().getReference().child("TodayExpense").child(datetostr);
        databaseReferenceThisMonthExpense = firebaseDatabase.getInstance().getReference().child("ThisMonthExpense");
        // today expense
        TodayIncome(datetostr,"type","Mill income");
        TodayIncome(datetostr,"type","Money withdrawal");
        TodayIncome(datetostr,"type","Money transfer");
        TodayIncome(datetostr,"type","Other income");
        TodayIncome(datetostr,"type","Photocopy");
        // ThisMonthIncome
        ThisMonthIncome(datetostrformont,"type","Mill income");
        ThisMonthIncome(datetostrformont,"type","Money withdrawal");
        ThisMonthIncome(datetostrformont,"type","Money transfer");
        ThisMonthIncome(datetostrformont,"type","Other income");
        ThisMonthIncome(datetostrformont,"type","Photocopy");
        ////// Today expense
        TodayExpense(datetostr,"type","Study");
        TodayExpense(datetostr,"type","Home");
        TodayExpense(datetostr,"type","Business");
        TodayExpense(datetostr,"type","Other");
        TodayExpense(datetostr,"type","Mill expense");
        // ThisMonthExpense
        ThisMonthExpense(datetostrformont,"type","Study");
        ThisMonthExpense(datetostrformont,"type","Home");
        ThisMonthExpense(datetostrformont,"type","Business");
        ThisMonthExpense(datetostrformont,"type","Other");
        ThisMonthExpense(datetostrformont,"type","Mill expense");





        return binding.getRoot();
    }


    public void TodayIncome(String date,String orderby ,String equals) {

        databaseReferenceTodayIncome = FirebaseDatabase.getInstance().getReference().child("TodayIncome").child(date);
        databaseReferenceTodayIncome.orderByChild(orderby).equalTo(equals).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayEarning");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    if(equals.equals("Mill income")) {
                        binding.MillIncomeTextVew.setText("₹ " + sum);
                    }
                    if(equals.equals("Photocopy")) {
                        binding.photocopy.setText("₹ " + sum);
                    }
                    if(equals.equals("Other income")) {
                        binding.otherincome.setText("₹ " + sum);
                    }
                    if(equals.equals("Money withdrawal")) {
                        binding.moneywithdrawal.setText("₹ " + sum);
                    }
                    if(equals.equals("Money transfer")) {
                        binding.moneytransfer.setText("₹ " + sum);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void ThisMonthIncome(String date ,String orderby ,String equals) {

        databaseReferenceThisMonthIncome = FirebaseDatabase.getInstance().getReference().child("ThisMonthIncome").child(date);
        databaseReferenceThisMonthIncome.orderByChild(orderby).equalTo(equals).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayEarning");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    if(equals.equals("Mill income")) {
                        binding.millmonthlyincome.setText("₹ " + sum);
                    }
                    if(equals.equals("Photocopy")) {
                        binding.monthlyphotocopy.setText("₹ " + sum);
                    }
                    if(equals.equals("Other income")) {
                        binding.monthlyothers.setText("₹ " + sum);
                    }
                    if(equals.equals("Money withdrawal")) {
                        binding.monthlymoneywidthdrawal.setText("₹ " + sum);
                    }
                    if(equals.equals("Money transfer")) {
                        binding.monthlymoneytransfer.setText("₹ " + sum);
                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void TodayExpense(String date,String orderby ,String equals) {

        databaseReferenceTodayExpense = FirebaseDatabase.getInstance().getReference().child("TodayExpense").child(date);
        databaseReferenceTodayExpense.orderByChild(orderby).equalTo(equals).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayExpense");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    if(equals.equals("Study")) {
                        binding.studyexpensetoday.setText("₹ " + sum);
                    }
                    if(equals.equals("Home")) {
                        binding.gharexpensetoday.setText("₹ " + sum);
                    }
                    if(equals.equals("Business")) {
                        binding.budinessexpensetoday.setText("₹ " + sum);
                    }
                    if(equals.equals("Other")) {
                        binding.intrestexpensetoday.setText("₹ " + sum);
                    }
                    if(equals.equals("Mill expense")) {
                        binding.millexpensetoday.setText("₹ " + sum);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void ThisMonthExpense(String date ,String orderby ,String equals) {

        databaseReferenceThisMonthExpense = FirebaseDatabase.getInstance().getReference().child("ThisMonthExpense").child(date);
        databaseReferenceThisMonthExpense.orderByChild(orderby).equalTo(equals).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) data.getValue();
                    Object todayIncome = map.get("todayExpense");

                    int todayIncomevalue = Integer.parseInt(String.valueOf(todayIncome));
                    sum += todayIncomevalue;
                    if(equals.equals("Mill expense")) {
                        binding.millexpensemonth.setText("₹ " + sum);
                    }
                    if(equals.equals("Study")) {
                        binding.studyexpensemonth.setText("₹ " + sum);
                    }
                    if(equals.equals("Other")) {
                        binding.intrestexpensemonth.setText("₹ " + sum);
                    }
                    if(equals.equals("Business")) {
                        binding.businessexpensemonth.setText("₹ " + sum);
                    }
                    if(equals.equals("Home")) {
                        binding.gharexpensemonth.setText("₹ " + sum);
                    }




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}