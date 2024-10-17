package com.example.mygrocerystore.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.adapters.PopularAdapters;
import com.example.mygrocerystore.api.ProductApi;
import com.example.mygrocerystore.client.RetrofitClient;
import com.example.mygrocerystore.models.PopularModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView popularRec;
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;
    ProductApi productApi;

    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        popularRec = root.findViewById(R.id.pop_rec);
        scrollView = root.findViewById(R.id.scroll_view);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(), popularModelList);
        popularRec.setAdapter(popularAdapters);

        // Initialize API service
        productApi = RetrofitClient.getRetrofitInstance().create(ProductApi.class);

        // Fetch popular products
        fetchPopularProducts();

        return root;
    }

    private void fetchPopularProducts() {
        productApi.getProducts().enqueue(new Callback<List<PopularModel>>() {
            @Override
            public void onResponse(Call<List<PopularModel>> call, Response<List<PopularModel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<PopularModel> products = response.body();
                    if (products != null) {
                        populateProductList(products);
                        scrollView.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getActivity(), "No products available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PopularModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateProductList(List<PopularModel> products) {
        popularModelList.addAll(products);
        popularAdapters.notifyDataSetChanged();
    }
}
