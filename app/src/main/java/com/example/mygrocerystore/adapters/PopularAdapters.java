package com.example.mygrocerystore.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.activities.ViewAllActivity;
import com.example.mygrocerystore.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(popularModelList.get(position).getImageUrl()).into(holder.popImg);
        holder.name.setText(popularModelList.get(position).getProductName());
        //holder.rating.setText(popularModelList.get(position).getRating());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.price.setText(String.valueOf(popularModelList.get(position).getProductPrice()));


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, ViewAllActivity.class);
//                intent.putExtra("type",popularModelList.get(position).getType());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, description, rating, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg =itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            price = itemView.findViewById(R.id.pop_price);
//            rating = itemView.findViewById(R.id.pop_rating);
        }
    }
}
