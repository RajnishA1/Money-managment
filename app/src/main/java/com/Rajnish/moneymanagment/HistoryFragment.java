package com.Rajnish.moneymanagment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Rajnish.moneymanagment.AdapterClass.Adapter;
import com.Rajnish.moneymanagment.databinding.FragmentHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HistoryFragment extends Fragment {

   FragmentHistoryBinding binding;
  DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryBinding.inflate(getLayoutInflater());

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String datetostr = format.format(today);

        Date month = new Date();
        SimpleDateFormat formatformonth = new SimpleDateFormat("MM-yyyy");
        String datetostrformont = formatformonth.format(month);

        binding.UserListRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        binding.UserListRecycleView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ThisMonthIncome").child(datetostrformont);
        ArrayList<Post> list = new ArrayList<>();


        Adapter adapter = new Adapter(list,getContext());
        binding.UserListRecycleView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            Post post = dataSnapshot.getValue(Post.class);
                            list.add(post);
                        }
                        adapter.notifyDataSetChanged();
                        binding.UserListRecycleView.scrollToPosition(list.size()-1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





        return binding.getRoot();
    }
}