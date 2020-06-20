package com.affinityapps.bakingapp.ingredients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IngredientsListViewModel extends ViewModel {

    private MutableLiveData<String> ingredientsList = new MutableLiveData<>();

    public void setIngredientsList(String ingredients) {
        ingredientsList.setValue(ingredients);
    }

    public LiveData<String> getIngredientsList() {
        return ingredientsList;
    }
}
