package com.example.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.models.MyCartModel;
import com.example.mygrocerystore.models.MyOrderModel;
import com.example.mygrocerystore.models.MyOrdersModel;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    Context context;
    List<MyOrderModel> orderModelList;

    public MyOrdersAdapter(Context context, List<MyOrderModel> orderModelList) {
        this.context = context;
        this.orderModelList = orderModelList;
    }

    @NonNull
    @Override
    public MyOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(orderModelList.get(position).getProductNames());
        holder.date.setText(orderModelList.get(position).getCurrentDate());
        holder.totalAmount.setText(orderModelList.get(position).getTotalPrices());
        holder.status.setText(orderModelList.get(position).getStatus());

        switch (orderModelList.get(position).getStatus()) {
            case "Placed":
                holder.timelineCirclePlaced.setBackgroundResource(R.drawable.timeline_circle_active);
                holder.timelineCirclePending.setBackgroundResource(R.drawable.timeline_circle);
                holder.timelineCircleDelivered.setBackgroundResource(R.drawable.timeline_circle);
                break;
            case "Pending":
                holder.timelineCirclePlaced.setBackgroundResource(R.drawable.timeline_circle_active);
                holder.timelineCirclePending.setBackgroundResource(R.drawable.timeline_circle_active);
                holder.timelineCircleDelivered.setBackgroundResource(R.drawable.timeline_circle);
                break;
            case "Delivered":
                holder.timelineCirclePlaced.setBackgroundResource(R.drawable.timeline_circle_active);
                holder.timelineCirclePending.setBackgroundResource(R.drawable.timeline_circle_active);
                holder.timelineCircleDelivered.setBackgroundResource(R.drawable.timeline_circle_active);
                break;
            default:
                holder.timelineCirclePlaced.setBackgroundResource(R.drawable.timeline_circle);
                holder.timelineCirclePending.setBackgroundResource(R.drawable.timeline_circle);
                holder.timelineCircleDelivered.setBackgroundResource(R.drawable.timeline_circle);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date,totalAmount,status;
        View timelineCirclePlaced, timelineCirclePending, timelineCircleDelivered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.order_product_name);
            date = itemView.findViewById(R.id.order_date);
            totalAmount = itemView.findViewById(R.id.order_total);
            status= itemView.findViewById(R.id.order_status);

            timelineCirclePlaced = itemView.findViewById(R.id.timeline_circle_placed);
            timelineCirclePending = itemView.findViewById(R.id.timeline_circle_pending);
            timelineCircleDelivered = itemView.findViewById(R.id.timeline_circle_delivered);
        }
    }
}