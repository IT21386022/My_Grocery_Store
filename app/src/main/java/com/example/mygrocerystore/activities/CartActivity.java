package com.example.mygrocerystore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.adapters.CartAdapter;
import com.example.mygrocerystore.models.MyCartModel;
import com.example.mygrocerystore.storage.CartStorage;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView totalPrice, emptyCartMessage;
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    CartStorage cartStorage;
    Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        totalPrice = findViewById(R.id.total_price);
        emptyCartMessage = findViewById(R.id.empty_cart_message);  // TextView for showing empty cart message
        checkoutButton = findViewById(R.id.checkout_button);

        cartStorage = new CartStorage(this);
        List<MyCartModel> cartItems = cartStorage.getCartItems();

        // Check if the cart is empty
        if (cartItems == null || cartItems.isEmpty()) {
            // If the cart is empty, show a message
            emptyCartMessage.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
            totalPrice.setVisibility(View.GONE);
            checkoutButton.setVisibility(View.GONE);
        } else {
            // If the cart has items, proceed to display them
            emptyCartMessage.setVisibility(View.GONE);
            cartRecyclerView.setVisibility(View.VISIBLE);
            totalPrice.setVisibility(View.VISIBLE);
            checkoutButton.setVisibility(View.VISIBLE);

            // Set up RecyclerView
            cartAdapter = new CartAdapter(cartItems, this);
            cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            cartRecyclerView.setAdapter(cartAdapter);

            // Calculate total price
            updateTotalPrice();
        }

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, OrderFormActivity.class));
            }
        });
    }

    public void updateTotalPrice() {
        List<MyCartModel> cartItems = cartStorage.getCartItems();
        int total = 0;
        for (MyCartModel item : cartItems) {
            total += item.getTotalPrice();
        }
        totalPrice.setText("Total Price: $" + total);
    }
}
