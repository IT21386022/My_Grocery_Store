package com.example.mygrocerystore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mygrocerystore.R;

public class OrderFormActivity extends AppCompatActivity {

    EditText name, mobile, address;
    Button placeOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        name = findViewById(R.id.order_user_name);
        mobile = findViewById(R.id.order_user_number);
        address = findViewById(R.id.order_user_address);
        placeOrderBtn = findViewById(R.id.order);

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        String userName = name.getText().toString();
        String userMobile = mobile.getText().toString();
        String userAddress = address.getText().toString();

        if (userName.isEmpty() || userMobile.isEmpty() || userAddress.isEmpty()) {
            Toast.makeText(OrderFormActivity.this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(OrderFormActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
            // Redirect to the PlacedOrderActivity
            Intent intent = new Intent(OrderFormActivity.this, PlacedOrderActivity.class);
            startActivity(intent);
        }
    }
}
