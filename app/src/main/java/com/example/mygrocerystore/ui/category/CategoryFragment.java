package com.example.mygrocerystore.ui.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygrocerystore.R;
import com.example.mygrocerystore.adapters.NavCategoryAdapter;
import com.example.mygrocerystore.adapters.PopularAdapters;
import com.example.mygrocerystore.models.NavCategoryModel;
import com.example.mygrocerystore.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<NavCategoryModel> navCategoryModelList;
    NavCategoryAdapter navCategoryAdapter;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = root.findViewById(R.id.cat_rec);

        //popular items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        navCategoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(), navCategoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                navCategoryModelList.add(navCategoryModel);
                                navCategoryAdapter.notifyDataSetChanged();
                            }

                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        return root;
    }


}