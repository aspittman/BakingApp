package com.affinityapps.bakingapp.MasterDetailScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.bakingapp.IngredientsScreen.IngredientsListActivity;
import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static com.affinityapps.bakingapp.home.FragmentHome.EXTRA_ID;


public class RecipeListFragment extends Fragment
        implements View.OnClickListener {

    private Context context;
    private RecipeCard recipeCard;
    private RequestQueue requestQueue;
    private TextView recipeIngredientsTitle;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecipeCard> recipeFragmentArrayList;
    private ArrayList<String> recipeDescriptionArrayList;
    private ArrayList<String> recipeVideoUrlArrayList;
    private RecipeListFragmentTransfer listFragmentTransfer;
    private RecipeListFragmentAdapter recipeListFragmentAdapter;
    public static final String EXTRA_TRANSFER = "dataTransfer";
    public static final String EXTRA_DESCRIPTION = "full_description";
    public static final String EXTRA_VIDEO_URL = "video_link";
    private static final String bakingUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    public RecipeListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        recipeIngredientsTitle = rootView.findViewById(R.id.recipe_ingredients_text);
        recipeIngredientsTitle.setOnClickListener(this);

        recipeFragmentArrayList = new ArrayList<>();
        recipeDescriptionArrayList = new ArrayList<>();
        recipeVideoUrlArrayList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.recipe_list_fragment_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recipeListFragmentAdapter = new RecipeListFragmentAdapter(context, recipeFragmentArrayList);
        recyclerView.setAdapter(recipeListFragmentAdapter);

        requestQueue = Volley.newRequestQueue(requireActivity());
        parseStepsListData();

        return rootView;
    }

    public interface RecipeListFragmentTransfer {
        void recipeListInputSent(int position, String description, String videoUrl);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), IngredientsListActivity.class);
        intent.putExtra(EXTRA_TRANSFER, recipeCard.getRecipeCardTransfer());
        startActivity(intent);
    }



    private void parseStepsListData() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, RecipeListFragment.bakingUrl, null,
                response -> {
                    try {
                        Intent intent = requireActivity().getIntent();

                        int recipeCardIdClicked = intent.getIntExtra(EXTRA_ID, 0);

                        recipeCard = new RecipeCard(recipeCardIdClicked);

                        JSONObject jsonObject = response.getJSONObject(recipeCardIdClicked);

                        JSONArray jsonArrayIngredients = jsonObject.getJSONArray("steps");

                        for (int j = 0; j < jsonArrayIngredients.length(); j++) {

                            JSONObject secondJsonObject = jsonArrayIngredients.getJSONObject(j);

                            String shortDescription = secondJsonObject.getString("shortDescription");
                            String description = secondJsonObject.getString("description");
                            String videoUrl = secondJsonObject.getString("videoURL");

                            recipeFragmentArrayList.add(new RecipeCard(shortDescription));
                            recipeDescriptionArrayList.add(description);
                            recipeVideoUrlArrayList.add(videoUrl);
                        }

                        recipeListFragmentAdapter = new RecipeListFragmentAdapter(getActivity(), recipeFragmentArrayList);
                        recyclerView.setAdapter(recipeListFragmentAdapter);
                        recipeListFragmentAdapter.setOnRecipeFragmentClickListener(new RecipeListFragmentAdapter.OnRecipeFragmentClickListener() {
                            @Override
                            public void onRecipeFragmentClick(int position) {
                                listFragmentTransfer.recipeListInputSent(position, recipeDescriptionArrayList.get(position), recipeVideoUrlArrayList.get(position));
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(arrayRequest);
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        if(context instanceof RecipeListFragmentTransfer) {
            listFragmentTransfer = (RecipeListFragmentTransfer) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listFragmentTransfer = null;
    }
}
