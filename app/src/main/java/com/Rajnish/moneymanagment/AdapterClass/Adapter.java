package com.Rajnish.moneymanagment.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.Rajnish.moneymanagment.Post;
import com.Rajnish.moneymanagment.R;

import java.util.ArrayList;

public class Adapter extends  RecyclerView.Adapter<Adapter.viewHolder> {

    ArrayList<Post> list;
            Context context;

    public Adapter(ArrayList<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Post post = list.get(position);
        holder.amount.setText("₹ "+post.getTodayEarning());
        holder.mill.setText(post.getType());
        holder.date.setText(post.getDate());
        holder.time.setText(post.getTime());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  viewHolder extends RecyclerView.ViewHolder {
        TextView mill,amount,date,time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mill = itemView.findViewById(R.id.milltext);
            amount = itemView.findViewById(R.id.amounttext);
            date = itemView.findViewById(R.id.datetext);
            time = itemView.findViewById(R.id.timetext);



        }
    }


}
