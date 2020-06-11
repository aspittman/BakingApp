package com.affinityapps.bakingapp.steps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.instructions.RecipeDetailActivity;

public class RecipeMasterActivity extends AppCompatActivity
        implements RecipeListFragment.RecipeListFragmentTransfer {

    public static final String EXTRA_DESCRIPTION = "full_description";
    public static final String EXTRA_VIDEO_URL = "video_link";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_master);

        RecipeListFragment recipeListFragment = new RecipeListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_list_container, recipeListFragment)
                .commit();
    }

    @Override
    public void recipeListInputSent(int position, String description, String videoUrl) {
        Intent intent1 = new Intent(this, RecipeDetailActivity.class);
        intent1.putExtra(EXTRA_DESCRIPTION, description);
        intent1.putExtra(EXTRA_VIDEO_URL, videoUrl);
        startActivity(intent1);
    }

}
