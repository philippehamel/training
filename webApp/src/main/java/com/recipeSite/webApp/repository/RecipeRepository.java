package com.recipeSite.webApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.recipeSite.webApp.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
