package com.recipeSite.webApp.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    static Category category;

    @BeforeAll
    public static void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }

}
