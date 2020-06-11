package com.affinityapps.bakingapp.IngredientsScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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

import static com.affinityapps.bakingapp.MasterDetailScreen.RecipeListFragment.EXTRA_TRANSFER;

public class IngredientsListActivity extends AppCompatActivity {

    private Context context;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private IngredientsListAdapter ingredientsListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<RecipeCard> ingredientsArrayList;
    private static final String bakingUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_list);

        ingredientsArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.ingredients_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(IngredientsListActivity.this);
        parseIngredientsListData();
    }

    public void parseIngredientsListData() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, IngredientsListActivity.bakingUrl, null,
                response -> {
                    try {
                            Intent intent = getIntent();

                            int recipeCardTransfer = intent.getIntExtra(EXTRA_TRANSFER, 0);

                            JSONObject jsonObject = response.getJSONObject(recipeCardTransfer);

                            JSONArray jsonArrayIngredients = jsonObject.getJSONArray("ingredients");

                            for(int j=0; j < jsonArrayIngredients.length(); j++) {

                                JSONObject secondJsonObject = jsonArrayIngredients.getJSONObject(j);

                                int quantity = secondJsonObject.getInt("quantity");
                                String measureType = secondJsonObject.getString("measure");
                                String ingredientName = secondJsonObject.getString("ingredient");
                                ingredientsArrayList.add(new RecipeCard(quantity, measureType, ingredientName));
                            }

                        ingredientsListAdapter = new IngredientsListAdapter(IngredientsListActivity.this, ingredientsArrayList);
                        recyclerView.setAdapter(ingredientsListAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        requestQueue.add(arrayRequest);
    }


}
