package com.example.mygrocerystore.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.bumptech.glide.Glide;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.models.MyCartModel;
import com.example.mygrocerystore.models.PopularModel;
import com.example.mygrocerystore.models.ViewAllModel;
import com.example.mygrocerystore.storage.CartStorage;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    double totalPrice = 0;
    ImageView detailedImg;
    TextView price, rating, description;
    AppCompatButton addToCart,viewCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;
    PopularModel popularModel = null;
    CartStorage cartStorage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartStorage = new CartStorage(this);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof PopularModel) {
            popularModel = (PopularModel) object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_dec);

        if (popularModel != null) {
            Glide.with(getApplicationContext()).load(popularModel.getImageUrl()).into(detailedImg);
//            rating.setText(viewAllModel.getRating());
            description.setText(popularModel.getDescription());
            price.setText("Price :$" + popularModel.getProductPrice());
            totalPrice = (popularModel.getProductPrice() * totalQuantity);
        }

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedToCart();
            }
        });

        viewCart = findViewById(R.id.view_cart);
        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open cart activity
                Intent intent = new Intent(DetailedActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice =  (popularModel.getProductPrice() * totalQuantity);
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (popularModel.getProductPrice() * totalQuantity);
                }
            }
        });
    }

    private void addedToCart() {
        MyCartModel cartItem = new MyCartModel(
                totalPrice,
                totalQuantity,
                popularModel.getProductPrice(),
                popularModel.getProductName()
        );
        cartStorage.addCartItem(cartItem);
        Toast.makeText(DetailedActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
        finish();
    }
}
