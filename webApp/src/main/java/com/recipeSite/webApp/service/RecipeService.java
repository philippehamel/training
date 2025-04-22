package com.recipeSite.webApp.service;

import java.util.Set;

import com.recipeSite.webApp.model.Recipe;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);

}
