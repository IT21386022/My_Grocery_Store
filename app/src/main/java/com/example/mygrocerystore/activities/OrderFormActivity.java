package com.example.mygrocerystore.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderFormActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore db;
    EditText name, mobile, address;
    Button placeOrderBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        name = findViewById(R.id.order_user_name);
        mobile = findViewById(R.id.order_user_number);
        address = findViewById(R.id.order_user_address);
        placeOrderBtn = findViewById(R.id.order);

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
                Intent intent = new Intent(OrderFormActivity.this, PlacedOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    private void placeOrder() {
        String userName = name.getText().toString();
        String userMobile = mobile.getText().toString();
        String userAddress = address.getText().toString();

        if (userName.isEmpty() || userMobile.isEmpty() || userAddress.isEmpty()) {
            Toast.makeText(OrderFormActivity.this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get cart items from the intent
        List<MyCartModel> cartList = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if (cartList != null && cartList.size() > 0) {
            // Create a HashMap to store the order details (form data + cart items)
            final HashMap<String, Object> orderMap = new HashMap<>();

            // Add form data (name, mobile, address)
            orderMap.put("userName", userName);
            orderMap.put("userMobile", userMobile);
            orderMap.put("userAddress", userAddress);
            orderMap.put("status", "Placed");

            // Initialize variables to hold values for orderMap
            StringBuilder productNames = new StringBuilder();
            StringBuilder totalPrices = new StringBuilder();
            String currentDate = ""; // To hold the current date

            // Add cart items
            List<HashMap<String, Object>> cartItemList = new ArrayList<>();
            for (MyCartModel model : cartList) {
                HashMap<String, Object> cartItemMap = new HashMap<>();
                cartItemMap.put("productName", model.getProductName());
                cartItemMap.put("productPrice", model.getProductPrice());
//                cartItemMap.put("currentDate", model.getCurrentDate());
//                cartItemMap.put("currentTime", model.getCurrentTime());
//                cartItemMap.put("totalQuantity", model.getTotalQuantity());
//                cartItemMap.put("totalPrice", model.getTotalPrice());
                cartItemList.add(cartItemMap);

                // Add to productNames and totalPrices for the orderMap
                productNames.append(model.getProductName()).append(", "); // Append product names
//                totalPrices.append(model.getTotalPrice()).append(", "); // Append total prices

                // Get current date from the first cart item
//                if (currentDate.isEmpty()) {
//                    currentDate = model.getCurrentDate();
//                }
            }

            // Clean up the strings by removing the last comma and space if they exist
            if (productNames.length() > 0) {
                productNames.setLength(productNames.length() - 2); // Remove the last ", "
            }
            if (totalPrices.length() > 0) {
                totalPrices.setLength(totalPrices.length() - 2); // Remove the last ", "
            }

            // Put these values into the orderMap
            orderMap.put("productNames", productNames.toString()); // Save all product names as a string
            orderMap.put("totalPrices", totalPrices.toString()); // Save all total prices as a string
            orderMap.put("currentDate", currentDate); // Save current date as a string

            orderMap.put("cartItems", cartItemList); // Add the cart items

            // Save the order in Firestore
            db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                    .collection("MyOrder").add(orderMap)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(OrderFormActivity.this, "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();
                                finish();  // Close the activity after placing the order
                            } else {
                                Toast.makeText(OrderFormActivity.this, "Failed to place the order", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(OrderFormActivity.this, "No items in cart", Toast.LENGTH_SHORT).show();
        }
    }



}
