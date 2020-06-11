package com.affinityapps.bakingapp.instructions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.affinityapps.bakingapp.R;

import static com.affinityapps.bakingapp.steps.RecipeMasterActivity.EXTRA_DESCRIPTION;
import static com.affinityapps.bakingapp.steps.RecipeMasterActivity.EXTRA_VIDEO_URL;

public class RecipeDetailActivity extends AppCompatActivity {

    private String description;
    private String videoUrl;
    private Button previousButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();

        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_details_view, recipeDetailFragment)
                .commit();

        description = intent.getStringExtra(EXTRA_DESCRIPTION);
        videoUrl = intent.getStringExtra(EXTRA_VIDEO_URL);

        Bundle bundle = new Bundle();
        bundle.putString("descriptionTransfer", description);
        bundle.putString("videoUrlTransfer", videoUrl);
        recipeDetailFragment.setArguments(bundle);

//        navigationSetUp();
    }

    public void navigationSetUp() {
        previousButton = findViewById(R.id.previous_button);
        previousButton.setOnClickListener(v -> {
            Bundle arguments = new Bundle();
            arguments.putString(RecipeDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(RecipeDetailFragment.ARG_ITEM_ID));

            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details_view, fragment)
                    .commit();
        });
        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            Bundle arguments = new Bundle();
            arguments.putString(RecipeDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(RecipeDetailFragment.ARG_ITEM_ID));

            RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details_view, fragment)
                    .commit();
        });
    }
}
