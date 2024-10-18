package com.example.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.activities.CartActivity;
import com.example.mygrocerystore.activities.LoginActivity;
import com.example.mygrocerystore.activities.PlacedOrderActivity;
import com.example.mygrocerystore.activities.RegistrationActivity;
import com.example.mygrocerystore.models.MyCartModel;
import com.example.mygrocerystore.storage.CartStorage;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<MyCartModel> cartItems;
    private CartStorage cartStorage;
    private Context context;

    public CartAdapter(List<MyCartModel> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
        this.cartStorage = new CartStorage(context);
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        MyCartModel cartItem = cartItems.get(position);
        holder.productName.setText(cartItem.getProductName());
        holder.quantity.setText("Quantity: " + cartItem.getTotalQuantity());
        holder.price.setText("Price: $" + cartItem.getTotalPrice());
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the item from the cart storage
                cartStorage.removeCartItem(cartItem);
                // Remove the item from the adapter's list
                cartItems.remove(position);
                // Notify the adapter about the removal
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartItems.size());
                // Optionally, update the cart total price in CartActivity
                if (context instanceof CartActivity) {
                    ((CartActivity) context).updateTotalPrice();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity, price;
        Button removeButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.product_quantity);
            price = itemView.findViewById(R.id.product_price);
            removeButton = itemView.findViewById(R.id.remove_button);
        }
    }
}
