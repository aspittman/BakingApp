package com.affinityapps.bakingapp.IngredientsScreen;

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

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientsListViewHolder> {

    private ArrayList<RecipeCard> ingredientsArrayList;
    private Context context;


    public IngredientsListAdapter(Context context, ArrayList<RecipeCard> ingredientsArrayList) {
        this.ingredientsArrayList = ingredientsArrayList;
        this.context = context;
    }


    public static class IngredientsListViewHolder extends RecyclerView.ViewHolder {

        private ArrayList<RecipeCard> ingredientsArrayList;
        private TextView quantity;
        private TextView measureType;
        private TextView ingredientName;

        public IngredientsListViewHolder(View itemView, ArrayList<RecipeCard> ingredientsArrayList) {
            super(itemView);

            quantity = itemView.findViewById(R.id.quantity_text);
            measureType = itemView.findViewById(R.id.measure_text);
            ingredientName = itemView.findViewById(R.id.ingredients_text);

        }
    }

    @NonNull
    @Override
    public IngredientsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list_items, parent, false);

        return new IngredientsListViewHolder(view, ingredientsArrayList);
    }

    @Override
    public void onBindViewHolder(IngredientsListViewHolder holder, int position) {

        RecipeCard recipeCard = ingredientsArrayList.get(position);

        holder.quantity.setText(String.valueOf(recipeCard.getRecipeIngredientQuantity()));
        holder.measureType.setText(recipeCard.getRecipeIngredientMeasure());
        holder.ingredientName.setText(recipeCard.getRecipeIngredientName());
    }

    @Override
    public int getItemCount() {
        return ingredientsArrayList.size();
    }
}
