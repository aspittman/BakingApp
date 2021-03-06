package com.affinityapps.bakingapp.recipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.bakingapp.databinding.FragmentRecipeCardBinding;
import com.affinityapps.bakingapp.master.RecipeListActivity;
import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeCardFragment extends Fragment
        implements RecipeCardAdapter.OnRecipeClickListener {

    private Context context;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private FragmentRecipeCardBinding binding;
    private RecipeCardAdapter recipeCardAdapter;
    private ArrayList<RecipeCard> recipeCardArrayList;
    public static final String EXTRA_ID = "recipeCardId";
    private static final String bakingUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentRecipeCardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recipeCardArrayList = new ArrayList<>();

        recyclerView = binding.recipeRecyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        requestQueue = Volley.newRequestQueue(requireActivity());
        parseRecipeCardData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void parseRecipeCardData() {

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, RecipeCardFragment.bakingUrl, null,
                response -> {
                    try {

                        for (int i = 0; i < response.length(); i++) {

                            JSONObject jsonObject = response.getJSONObject(i);

                            String recipeCardNames = jsonObject.getString("name");
                            int recipeCardNumber = jsonObject.getInt("id");
                            recipeCardArrayList.add(new RecipeCard(recipeCardNames, recipeCardNumber));
                        }

                        recipeCardAdapter = new RecipeCardAdapter(getActivity(), recipeCardArrayList);
                        recyclerView.setAdapter(recipeCardAdapter);
                        recipeCardAdapter.setOnRecipeClickListener(this);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(arrayRequest);
    }

    @Override
    public void onRecipeClick(int position) {
        Intent intent = new Intent(getActivity(), RecipeListActivity.class);
        RecipeCard recipeCard = recipeCardArrayList.get(position);

        intent.putExtra(EXTRA_ID, recipeCard.getRecipeCardId() -1);
        startActivity(intent);
    }
}
