package com.affinityapps.bakingapp;

import android.content.Intent;
import java.util.ArrayList;

public class RecipeCard {

    private String recipeCardTitles;
    private int recipeCardId;
    private int recipeCardTransfer;
    private String recipeIngredientName;
    private String recipeIngredientMeasure;
    private int recipeIngredientQuantity;
    private String recipeStepsShortDescription;
    private String recipeStepsDescription;
    private String recipeVideoUrl;


    public RecipeCard(int recipeCardTransfer) {
        this.recipeCardTransfer = recipeCardTransfer;
    }

    public RecipeCard(String recipeCardTitles) {
        this.recipeCardTitles = recipeCardTitles;
    }

    public RecipeCard(String recipeStepsDescription, String recipeVideoUrl) {
        this.recipeStepsDescription = recipeStepsDescription;
        this.recipeVideoUrl = recipeVideoUrl;
    }

    public RecipeCard(String recipeCardTitles, int recipeCardId) {
        this.recipeCardTitles = recipeCardTitles;
        this.recipeCardId = recipeCardId;
    }

    public RecipeCard(int recipeIngredientQuantity, String recipeIngredientMeasure, String recipeIngredientName) {
        this.recipeIngredientQuantity = recipeIngredientQuantity;
        this.recipeIngredientMeasure = recipeIngredientMeasure;
        this.recipeIngredientName = recipeIngredientName;
    }


    public String getRecipeCardTitles() {
        return recipeCardTitles;
    }

    public void setRecipeCardTitles(String recipeCardTitles) {
        this.recipeCardTitles = recipeCardTitles;
    }

    public int getRecipeCardId() {
        return recipeCardId;
    }

    public void setRecipeCardId(int recipeCardId) {
        this.recipeCardId = recipeCardId;
    }

    public int getRecipeCardTransfer() {
        return recipeCardTransfer;
    }

    public void setRecipeCardTransfer(int recipeCardTransfer) {
        this.recipeCardTransfer = recipeCardTransfer;
    }

    public String getRecipeIngredientName() {
        return recipeIngredientName;
    }

    public void setRecipeIngredientName(String recipeIngredientName) {
        this.recipeIngredientName = recipeIngredientName;
    }

    public String getRecipeIngredientMeasure() {
        return recipeIngredientMeasure;
    }

    public void setRecipeIngredientMeasure(String recipeIngredientMeasure) {
        this.recipeIngredientMeasure = recipeIngredientMeasure;
    }

    public int getRecipeIngredientQuantity() {
        return recipeIngredientQuantity;
    }

    public void setRecipeIngredientQuantity(int recipeIngredientQuantity) {
        this.recipeIngredientQuantity = recipeIngredientQuantity;
    }

    public String getRecipeStepsShortDescription() {
        return recipeStepsShortDescription;
    }

    public void setRecipeStepsShortDescription(String recipeStepsShortDescription) {
        this.recipeStepsShortDescription = recipeStepsShortDescription;
    }

    public String getRecipeStepsDescription() {
        return recipeStepsDescription;
    }

    public void setRecipeStepsDescription(String recipeStepsDescription) {
        this.recipeStepsDescription = recipeStepsDescription;
    }

    public String getRecipeVideoUrl() {
        return recipeVideoUrl;
    }

    public void setRecipeVideoUrl(String recipeVideoUrl) {
        this.recipeVideoUrl = recipeVideoUrl;
    }
}
