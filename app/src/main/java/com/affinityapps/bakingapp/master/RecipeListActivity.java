package com.affinityapps.bakingapp.master;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;
import com.affinityapps.bakingapp.ingredients.IngredientsListActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.affinityapps.bakingapp.recipes.RecipeCardFragment.EXTRA_ID;

public class RecipeListActivity extends AppCompatActivity
        implements View.OnClickListener {

    private RecipeCard recipeCard;
    private Boolean twoPaneController;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecipeListActivity parentActivity;
    private ArrayList<RecipeCard> recipeFragmentArrayList;
    private ArrayList<RecipeCard> recipeDescriptionArrayList;
    private ArrayList<RecipeCard> recipeVideoUrlArrayList;
    private RecipeListAdapter recipeListAdapter;
    public static final String EXTRA_TRANSFER = "dataTransfer";
    public static final String EXTRA_DESCRIPTION = "full_description";
    public static final String EXTRA_VIDEO_URL = "video_link";
    private static final String bakingUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            twoPaneController = true;
        }

        recyclerView = findViewById(R.id.recipe_master_recyclerview);
        assert recyclerView != null;

        TextView recipeIngredientsTitle = findViewById(R.id.recipe_ingredients_text);
        recipeIngredientsTitle.setOnClickListener(this);

        recipeFragmentArrayList = new ArrayList<>();
        recipeDescriptionArrayList = new ArrayList<>();
        recipeVideoUrlArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recipe_master_recyclerview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recipeListAdapter = new RecipeListAdapter(this, recipeFragmentArrayList, twoPaneController);
        recyclerView.setAdapter(recipeListAdapter);

        requestQueue = Volley.newRequestQueue(this);
        parseStepsListData();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, IngredientsListActivity.class);
        intent.putExtra(EXTRA_TRANSFER, recipeCard.getRecipeCardTransfer());
        startActivity(intent);
    }


    private void parseStepsListData() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, RecipeListActivity.bakingUrl, null,
                response -> {
                    try {
                        Intent intent = getIntent();

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
                            recipeDescriptionArrayList.add(new RecipeCard(description));
                            recipeVideoUrlArrayList.add(new RecipeCard(videoUrl));
                        }

                        recipeListAdapter = new RecipeListAdapter(this, recipeFragmentArrayList, twoPaneController);
                        recyclerView.setAdapter(recipeListAdapter);
                        recipeListAdapter.setOnRecipeFragmentClickListener(position -> {

                            if (twoPaneController) {
                                Bundle arguments = new Bundle();
                                arguments.putInt(RecipeDetailFragment.TRANSFER_ID, position);
                                RecipeDetailFragment fragment = new RecipeDetailFragment();
                                fragment.setArguments(arguments);
                                parentActivity.getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();
                            } else {
                                Intent intent1 = new Intent(this, RecipeDetailActivity.class);
                                RecipeCard recipeCard1 = recipeDescriptionArrayList.get(position);
                                RecipeCard recipeCard2 = recipeVideoUrlArrayList.get(position);

                                intent1.putExtra(EXTRA_DESCRIPTION, recipeCard1.getRecipeCardTitles());
                                intent1.putExtra(EXTRA_VIDEO_URL, recipeCard2.getRecipeCardTitles());
                                startActivity(intent1);

                            }

                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(arrayRequest);
    }

}
