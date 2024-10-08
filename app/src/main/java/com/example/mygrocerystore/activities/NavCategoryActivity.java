package com.example.mygrocerystore.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.adapters.NavCategoryDetailedAdapter;
import com.example.mygrocerystore.models.HomeCategory;
import com.example.mygrocerystore.models.NavCategoryDetailedModel;
import com.example.mygrocerystore.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCategoryDetailedAdapter adapter;
    FirebaseFirestore db;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);

//        if (type != null) {
//            switch (type.toLowerCase()) {
//                case "drink":
//                    fetchCategoryData("drink");
//                    break;
//                case "sweet":
//                    fetchCategoryData("sweet");
//                    break;
//                default:
//                    Toast.makeText(this, "Unknown category", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//            }

        db.collection("NavCategoryDetailed").whereEqualTo("type", "drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);  // Unhide the recyclerView after loading the data
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(NavCategoryActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

//    private void fetchCategoryData(String categoryType) {
//        db.collection("NavCategoryDetailed").whereEqualTo("type", categoryType).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
//                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
//                        list.add(viewAllModel);
//                    }
//                    adapter.notifyDataSetChanged();
//                    progressBar.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.VISIBLE);
//                } else {
//                    Toast.makeText(NavCategoryActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//        });
//    }
