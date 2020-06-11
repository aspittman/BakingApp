package com.affinityapps.bakingapp.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.RecipeCard;

import java.util.ArrayList;


public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardViewHolder> {

    private ArrayList<RecipeCard> recipeCardArrayList;
    private Context context;
    private OnRecipeClickListener onRecipeClickListener;


    public interface OnRecipeClickListener {
        void onRecipeClick(int position);
    }

    public void setOnRecipeClickListener(OnRecipeClickListener listener) {
        onRecipeClickListener = listener;
    }


    public RecipeCardAdapter(Context context, ArrayList<RecipeCard> recipeCardArrayList) {
        this.recipeCardArrayList = recipeCardArrayList;
        this.context = context;
    }


    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder {

        private ArrayList<RecipeCard> recipeCardArrayList;
        TextView recipeCardTitle;


        public RecipeCardViewHolder(View itemView, OnRecipeClickListener listener) {
            super(itemView);

            recipeCardTitle = itemView.findViewById(R.id.recipe_title_text);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onRecipeClick(position);
                    }
                }

            });
        }
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card_items, parent, false);

        return new RecipeCardViewHolder(view, onRecipeClickListener);
    }

    @Override
    public void onBindViewHolder(RecipeCardViewHolder holder, int position) {

        RecipeCard recipeCard = recipeCardArrayList.get(position);

        holder.recipeCardTitle.setText(recipeCard.getRecipeCardTitles());
    }

    @Override
    public int getItemCount() {
        return recipeCardArrayList.size();
    }
}




