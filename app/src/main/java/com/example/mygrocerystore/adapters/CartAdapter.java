package com.example.mygrocerystore.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.models.MyCartModel;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<MyCartModel> cartItems;

    public CartAdapter(List<MyCartModel> cartItems) {
        this.cartItems = cartItems;
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
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, quantity, price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.product_quantity);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}
