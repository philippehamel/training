package com.recipeSite.webApp.controller;

import com.recipeSite.webApp.model.Recipe;
import com.recipeSite.webApp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        // Arrange
        String expectedViewName = "index";
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(new Recipe());
        recipeSet.add(new Recipe());

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        // Act
        String actualViewName = indexController.getIndexPage(model);

        // Assert
        assertEquals(expectedViewName, actualViewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        assertEquals(2, argumentCaptor.getValue().size());
    }
}