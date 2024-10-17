//package com.example.mygrocerystore;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.example.mygrocerystore.adapters.MyCartAdapter;
//import com.example.mygrocerystore.adapters.MyOrdersAdapter;
//import com.example.mygrocerystore.adapters.OffersAdapter;
//import com.example.mygrocerystore.adapters.PopularAdapters;
//import com.example.mygrocerystore.models.FeedbackModel;
//import com.example.mygrocerystore.models.MyOrderModel;
//import com.example.mygrocerystore.models.PopularModel;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class OffersFragment extends Fragment {
//
//    RecyclerView recyclerView;
//    FirebaseFirestore db;
//
//    List<FeedbackModel> feedbackModelList;
//    OffersAdapter offersAdapter;
//
//    public OffersFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View root = inflater.inflate(R.layout.fragment_offers, container, false);
//
//        db = FirebaseFirestore.getInstance();
//        recyclerView = root.findViewById(R.id.recyclerView);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
//        feedbackModelList = new ArrayList<>();
//        offersAdapter = new OffersAdapter(getActivity(), feedbackModelList);
//        recyclerView.setAdapter(offersAdapter);
//
//        db.collection("CustomerFeedbacks")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                FeedbackModel feedbackModel = document.toObject(FeedbackModel.class);
//                                feedbackModelList.add(feedbackModel);
//                                offersAdapter.notifyDataSetChanged();
//                            }
//
//                        } else {
//                            Toast.makeText(getActivity(), "Error"+task.getException(),Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//        return root;
//    }
//}