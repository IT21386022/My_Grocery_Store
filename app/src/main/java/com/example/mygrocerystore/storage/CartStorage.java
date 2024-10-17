package com.example.mygrocerystore.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.mygrocerystore.models.MyCartModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartStorage {
    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_ITEMS_KEY = "cart_items";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public CartStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addCartItem(MyCartModel cartItem) {
        List<MyCartModel> cartItems = getCartItems();
        cartItems.add(cartItem);
        saveCartItems(cartItems);
    }

    public List<MyCartModel> getCartItems() {
        String json = sharedPreferences.getString(CART_ITEMS_KEY, null);
        Type type = new TypeToken<List<MyCartModel>>() {}.getType();
        List<MyCartModel> cartItems = gson.fromJson(json, type);
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        return cartItems;
    }

    private void saveCartItems(List<MyCartModel> cartItems) {
        String json = gson.toJson(cartItems);
        sharedPreferences.edit().putString(CART_ITEMS_KEY, json).apply();
    }
}
