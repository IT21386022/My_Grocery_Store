package com.example.mygrocerystore.activities;

import android.os.Bundle;
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
    TextView totalPrice;
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    CartStorage cartStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cart_recycler_view);
        totalPrice = findViewById(R.id.total_price);

        cartStorage = new CartStorage(this);
        List<MyCartModel> cartItems = cartStorage.getCartItems();

        // Set up RecyclerView
        cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        // Calculate total price
        int total = 0;
        for (MyCartModel item : cartItems) {
            total += item.getTotalPrice();
        }

        totalPrice.setText("Total Price: $" + total);
    }
}
