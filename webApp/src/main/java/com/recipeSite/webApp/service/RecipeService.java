package com.recipeSite.webApp.service;

import com.recipeSite.webApp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
