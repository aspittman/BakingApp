package com.affinityapps.bakingapp.master;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListFragmentViewHolder> {

    private ArrayList<RecipeCard> recipeFragmentArrayList;
    private Context context;
    private OnRecipeFragmentClickListener listener;
    private List<RecipeCard> recipeCardList;
    private boolean twoPaneController;

    public interface OnRecipeFragmentClickListener {
        void onRecipeFragmentClick(int position);
    }

    public void setOnRecipeFragmentClickListener(OnRecipeFragmentClickListener listener) {
        this.listener = listener;
    }


    public RecipeListAdapter(Context context, List<RecipeCard> recipeCardList, boolean twoPaneController) {

        this.context = context;
        this.recipeCardList = recipeCardList;
        this.twoPaneController = twoPaneController;
    }


    public static class RecipeListFragmentViewHolder extends RecyclerView.ViewHolder {

        private ArrayList<RecipeCard> recipeFragmentArrayList;
        private TextView recipeDescriptionTitle;


        public RecipeListFragmentViewHolder(View itemView, OnRecipeFragmentClickListener listener) {
            super(itemView);

            recipeDescriptionTitle = itemView.findViewById(R.id.recipe_description_text);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onRecipeFragmentClick(position);
                    }
                }

            });
        }
    }

    @NonNull
    @Override
    public RecipeListFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_list_items, parent, false);

        return new RecipeListFragmentViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RecipeListFragmentViewHolder holder, int position) {

        RecipeCard recipeCard = recipeFragmentArrayList.get(position);

        holder.recipeDescriptionTitle.setText(recipeCard.getRecipeCardTitles());
    }

    @Override
    public int getItemCount() {
        return recipeFragmentArrayList.size();
    }
}

