//package com.example.mygrocerystore;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.example.mygrocerystore.activities.OrderFormActivity;
//import com.example.mygrocerystore.adapters.MyCartAdapter;
//import com.example.mygrocerystore.models.MyCartModel;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyCartsFragment extends Fragment {
//
//    TextView overTotalAmount;
//    RecyclerView recyclerView;
//    MyCartAdapter cartAdapter;
//    List<MyCartModel> cartModelList;
//    Button buyNow;
//    int totalBill;
//    ProgressBar progressBar;
//
//    public MyCartsFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);
//
//        progressBar = root.findViewById(R.id.progressbar);
//        progressBar.setVisibility(View.VISIBLE);
//
//        recyclerView = root.findViewById(R.id.recyclerView);
//        recyclerView.setVisibility(View.GONE);
//        buyNow = root.findViewById(R.id.buy_now);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        overTotalAmount = root.findViewById(R.id.textView6);
//
//        // Register the receiver for adding items to cart
//        LocalBroadcastManager.getInstance(getActivity())
//                .registerReceiver(addToCartReceiver, new IntentFilter("AddToCart"));
//
//        cartModelList = new ArrayList<>();
//        cartAdapter = new MyCartAdapter(getActivity(), cartModelList);
//        recyclerView.setAdapter(cartAdapter);
//
//        buyNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), OrderFormActivity.class);
//                intent.putExtra("itemList", (Serializable) cartModelList);
//                startActivity(intent);
//                clearCart();
//            }
//        });
//
//        return root;
//    }
//
//    private BroadcastReceiver addToCartReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // Get the data from the intent
//            String productName = intent.getStringExtra("productName");
//            int productPrice = intent.getIntExtra("productPrice", 0);
//            int totalQuantity = intent.getIntExtra("totalQuantity", 1);
//            int totalPrice = intent.getIntExtra("totalPrice", 0);
//
//            // Create a new MyCartModel object
//            MyCartModel newCartItem = new MyCartModel(productName, productPrice, totalQuantity, totalPrice);
//            cartModelList.add(newCartItem); // Add to the local cart list
//            cartAdapter.notifyDataSetChanged(); // Notify adapter of the change
//
//            // Update the total bill
//            totalBill += totalPrice; // Update total bill
//            overTotalAmount.setText("Total Bill :" + totalBill + "$");
//        }
//    };
//
//    @Override
//    public void onDestroyView() {
//        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(addToCartReceiver);
//        super.onDestroyView();
//    }
//
//    private void clearCart() {
//        // Clear local cart list
//        cartModelList.clear();
//        cartAdapter.notifyDataSetChanged(); // Update adapter
//    }
//}
