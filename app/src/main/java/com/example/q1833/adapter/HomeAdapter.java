package com.example.q1833.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.q1833.HouseActivity;
import com.example.q1833.HouseTable;
import com.example.q1833.R;
import com.example.q1833.model.Home;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    List<Home> homes;
    Context context;

    public HomeAdapter(List<Home> h, Context cntx) {
        homes = h;
        context = cntx;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View home_item = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        return new HomeViewHolder(home_item);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        position =  holder.getAdapterPosition();
        int idResourse = context.getResources()
                .getIdentifier(
                        homes.get(position).getImg(),
                        "drawable",
                        context.getPackageName());

        holder.img.setImageResource(idResourse);

        int finalPosition = position;
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homes.get(finalPosition).getImg() == "house1_opened") {
                    Intent intent = new Intent (context, HouseActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageButton img;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageButton);
        }
    }
}
