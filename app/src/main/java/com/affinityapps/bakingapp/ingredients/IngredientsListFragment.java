package com.affinityapps.bakingapp.ingredients;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;
import com.affinityapps.bakingapp.databinding.FragmentIngredientsListBinding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.affinityapps.bakingapp.master.RecipeListActivity.EXTRA_TRANSFER;

public class IngredientsListFragment extends Fragment {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private FragmentIngredientsListBinding binding;
    private IngredientsListAdapter ingredientsListAdapter;
    private ArrayList<RecipeCard> ingredientsArrayList;
    private static final String bakingUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentIngredientsListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ingredientsArrayList = new ArrayList<>();

        recyclerView = binding.ingredientsRecyclerview;
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(requireActivity());
        parseIngredientsListData();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void parseIngredientsListData() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, IngredientsListFragment.bakingUrl, null,
                response -> {
                    try {

                        assert getArguments() != null;
                        int recipeCardTransfer = getArguments().getInt("transferPosition", 0);

                        JSONObject jsonObject = response.getJSONObject(recipeCardTransfer);

                        JSONArray jsonArrayIngredients = jsonObject.getJSONArray("ingredients");

                        for (int j = 0; j < jsonArrayIngredients.length(); j++) {

                            JSONObject secondJsonObject = jsonArrayIngredients.getJSONObject(j);

                            int quantity = secondJsonObject.getInt("quantity");
                            String measureType = secondJsonObject.getString("measure");
                            String ingredientName = secondJsonObject.getString("ingredient");
                            ingredientsArrayList.add(new RecipeCard(quantity, measureType, ingredientName));
                        }

                        ingredientsListAdapter = new IngredientsListAdapter(getActivity(), ingredientsArrayList);
                        recyclerView.setAdapter(ingredientsListAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(arrayRequest);
    }

}
