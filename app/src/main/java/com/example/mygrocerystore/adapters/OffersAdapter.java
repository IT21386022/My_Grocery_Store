package com.example.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.models.FeedbackModel;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder>{

    private Context context;
    private List<FeedbackModel> feedbackModelList;

    public OffersAdapter(Context context, List<FeedbackModel> feedbackModelList) {
        this.context = context;
        this.feedbackModelList = feedbackModelList;
    }

    @NonNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OffersAdapter.ViewHolder holder, int position) {
        holder.firstName.setText(feedbackModelList.get(position).getFirstName());
        holder.lastName.setText(feedbackModelList.get(position).getLastName());
        holder.feedbackText.setText(feedbackModelList.get(position).getCustomerFeedbackText());
        holder.ratingBar.setRating(feedbackModelList.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return feedbackModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, feedbackText;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.feedback_firstname);
            lastName = itemView.findViewById(R.id.feedback_lastname);
            feedbackText = itemView.findViewById(R.id.feedback_comment);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
