package com.affinityapps.bakingapp.ingredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.affinityapps.bakingapp.R;

import static com.affinityapps.bakingapp.master.RecipeListActivity.EXTRA_TRANSFER;

public class IngredientsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_list);

        Intent intent = getIntent();
        int transferPosition = intent.getIntExtra(EXTRA_TRANSFER, 0);

        IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_ingredients_list_container, ingredientsListFragment)
                .commit();

        Bundle bundle = new Bundle();
        bundle.putInt("transferPosition", transferPosition);
        ingredientsListFragment.setArguments(bundle);
    }

}
