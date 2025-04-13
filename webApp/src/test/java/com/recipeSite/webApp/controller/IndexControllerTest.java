package com.recipeSite.webApp.controller;

import com.recipeSite.webApp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Mock
    IndexController indexController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        String expectedViewName = "index";

        String actualViewName = indexController.getIndexPage(model);

        assertEquals(expectedViewName, actualViewName);
        verify(recipeService).getRecipes();
        verify(model).addAttribute("recipes", recipeService.getRecipes());
    }
}