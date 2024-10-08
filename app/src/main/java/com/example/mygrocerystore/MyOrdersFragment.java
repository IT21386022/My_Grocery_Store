package com.example.mygrocerystore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mygrocerystore.adapters.MyOrdersAdapter;
import com.example.mygrocerystore.models.MyCartModel;
import com.example.mygrocerystore.models.MyOrderModel;
import com.example.mygrocerystore.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyOrdersAdapter myOrdersAdapter;
    private List<MyOrderModel> orderModelList;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_orders, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        orderModelList = new ArrayList<>();

        recyclerView = root.findViewById(R.id.recyclerView);
        myOrdersAdapter = new MyOrdersAdapter(getContext(), orderModelList);
        recyclerView.setAdapter(myOrdersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                String documentId = documentSnapshot.getId();
                                MyOrderModel orderModel = documentSnapshot.toObject(MyOrderModel.class);
                                orderModel.setId(documentId);
                                orderModelList.add(orderModel);
                                myOrdersAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        return root;
    }


}
