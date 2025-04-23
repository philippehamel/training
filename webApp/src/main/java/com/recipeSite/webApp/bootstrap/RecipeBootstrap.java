package com.recipeSite.webApp.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.recipeSite.webApp.model.*;
import com.recipeSite.webApp.repository.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasurementRepository unitOfMeasurementRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasurementRepository unitOfMeasurementRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private String formatDirections(String... steps) {
        return String.join("<br/>", steps);
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(8);

        // Get UOMs
        Optional<UnitOfMeasurement> eachUomOptional = unitOfMeasurementRepository.findByDescription("Each");
        if (eachUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasurement eachUom = eachUomOptional.get();

        Optional<UnitOfMeasurement> tableSpoonUomOptional = unitOfMeasurementRepository.findByDescription("Tablespoon");
        if (tableSpoonUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasurement tableSpoonUom = tableSpoonUomOptional.get();

        Optional<UnitOfMeasurement> pintUomOptional = unitOfMeasurementRepository.findByDescription("Pint");
        if (pintUomOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM Not Found");
        }
        UnitOfMeasurement pintUom = pintUomOptional.get();

        // Get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (americanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }
        Category americanCategory = americanCategoryOptional.get();

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (italianCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }
        Category italianCategory = italianCategoryOptional.get();

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (mexicanCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();

        Optional<Category> frenchCategoryOptional = categoryRepository.findByDescription("French");
        if (frenchCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }
        Category frenchCategory = frenchCategoryOptional.get();

        Optional<Category> japaneseCategoryOptional = categoryRepository.findByDescription("Japanese");
        if (japaneseCategoryOptional.isEmpty()) {
            throw new RuntimeException("Expected Category Not Found");
        }
        Category japaneseCategory = japaneseCategoryOptional.get();

        // Recipe 1: Perfect Guacamole
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPreparationTime(10);
        guacamoleRecipe.setCookingTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections(formatDirections(
                "1. Cut avocados in half, remove pit",
                "2. Scoop out avocado, mash with fork",
                "3. Add salt, lime juice, and onion",
                "4. Mix in tomatoes, cilantro, and garlic",
                "5. Cover and chill for 1 hour",
                "6. Serve with tortilla chips"
        ));
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Mexican Cuisine");
        guacamoleRecipe.setURL("https://www.mexicancuisine.com/guacamole");
        guacamoleRecipe.getCategory().add(mexicanCategory);

        Note guacNote = new Note();
        guacNote.setRecipeNote("Be sure to use ripe avocados and fresh lime juice for best results.");
        guacamoleRecipe.setNote(guacNote);

        Ingredient avocados = new Ingredient("Ripe Avocados", new BigDecimal(3), eachUom);
        avocados.setRecipe(guacamoleRecipe);
        guacamoleRecipe.getIngredient().add(avocados);

        Ingredient limeJuice = new Ingredient("Lime Juice", new BigDecimal(2), tableSpoonUom);
        limeJuice.setRecipe(guacamoleRecipe);
        guacamoleRecipe.getIngredient().add(limeJuice);

        recipes.add(guacamoleRecipe);

        // Recipe 2: Classic Spaghetti Carbonara
        Recipe carbonaraRecipe = new Recipe();
        carbonaraRecipe.setDescription("Classic Spaghetti Carbonara");
        carbonaraRecipe.setPreparationTime(15);
        carbonaraRecipe.setCookingTime(20);
        carbonaraRecipe.setDifficulty(Difficulty.MODERATE);
        carbonaraRecipe.setDirections(formatDirections(
                "1. Cook spaghetti in salted water",
                "2. Fry pancetta until crispy",
                "3. Whisk eggs, cheese, and pepper",
                "4. Toss hot pasta with egg mixture",
                "5. Add pancetta and serve immediately"
        ));
        carbonaraRecipe.setServings(4);
        carbonaraRecipe.setSource("Italian Cookbook");
        carbonaraRecipe.setURL("https://www.italiancuisine.com/carbonara");
        carbonaraRecipe.getCategory().add(italianCategory);

        Note carbonaraNote = new Note();
        carbonaraNote.setRecipeNote("Never add cream to authentic carbonara. The creaminess comes from eggs and cheese.");
        carbonaraRecipe.setNote(carbonaraNote);

        Ingredient spaghetti = new Ingredient("Spaghetti", new BigDecimal(1), pintUom);
        spaghetti.setRecipe(carbonaraRecipe);
        carbonaraRecipe.getIngredient().add(spaghetti);

        Ingredient eggs = new Ingredient("Eggs", new BigDecimal(4), eachUom);
        eggs.setRecipe(carbonaraRecipe);
        carbonaraRecipe.getIngredient().add(eggs);

        recipes.add(carbonaraRecipe);

        // Recipe 3: French Onion Soup
        Recipe onionSoupRecipe = new Recipe();
        onionSoupRecipe.setDescription("Classic French Onion Soup");
        onionSoupRecipe.setPreparationTime(20);
        onionSoupRecipe.setCookingTime(60);
        onionSoupRecipe.setDifficulty(Difficulty.MODERATE);
        onionSoupRecipe.setDirections(formatDirections(
                "1. Caramelize onions slowly",
                "2. Add beef broth and wine",
                "3. Simmer with herbs",
                "4. Top with bread and cheese",
                "5. Broil until golden"
        ));
        onionSoupRecipe.setServings(6);
        onionSoupRecipe.setSource("French Cooking");
        onionSoupRecipe.setURL("https://www.frenchcuisine.com/onionsoup");
        onionSoupRecipe.getCategory().add(frenchCategory);

        Note onionSoupNote = new Note();
        onionSoupNote.setRecipeNote("Use Gruyere cheese for authentic taste.");
        onionSoupRecipe.setNote(onionSoupNote);

        Ingredient onions = new Ingredient("Onions", new BigDecimal(6), eachUom);
        onions.setRecipe(onionSoupRecipe);
        onionSoupRecipe.getIngredient().add(onions);

        Ingredient beefBroth = new Ingredient("Beef Broth", new BigDecimal(6), pintUom);
        beefBroth.setRecipe(onionSoupRecipe);
        onionSoupRecipe.getIngredient().add(beefBroth);

        recipes.add(onionSoupRecipe);

        // Recipe 4: Japanese Miso Soup
        Recipe misoSoupRecipe = new Recipe();
        misoSoupRecipe.setDescription("Traditional Miso Soup");
        misoSoupRecipe.setPreparationTime(10);
        misoSoupRecipe.setCookingTime(15);
        misoSoupRecipe.setDifficulty(Difficulty.EASY);
        misoSoupRecipe.setDirections(formatDirections(
                "1. Make dashi stock",
                "2. Add tofu cubes",
                "3. Dissolve miso paste",
                "4. Add green onions",
                "5. Serve hot"
        ));
        misoSoupRecipe.setServings(4);
        misoSoupRecipe.setSource("Japanese Home Cooking");
        misoSoupRecipe.setURL("https://www.japanesecuisine.com/misosoup");
        misoSoupRecipe.getCategory().add(japaneseCategory);

        Note misoNote = new Note();
        misoNote.setRecipeNote("Never boil miso paste as it kills beneficial bacteria.");
        misoSoupRecipe.setNote(misoNote);

        Ingredient misoPaste = new Ingredient("Miso Paste", new BigDecimal(3), tableSpoonUom);
        misoPaste.setRecipe(misoSoupRecipe);
        misoSoupRecipe.getIngredient().add(misoPaste);

        Ingredient tofu = new Ingredient("Tofu", new BigDecimal(8), eachUom);
        tofu.setRecipe(misoSoupRecipe);
        misoSoupRecipe.getIngredient().add(tofu);

        recipes.add(misoSoupRecipe);

        // Recipe 5: American Burger
        Recipe burgerRecipe = new Recipe();
        burgerRecipe.setDescription("Classic American Cheeseburger");
        burgerRecipe.setPreparationTime(15);
        burgerRecipe.setCookingTime(10);
        burgerRecipe.setDifficulty(Difficulty.EASY);
        burgerRecipe.setDirections(formatDirections(
                "1. Form beef patties",
                "2. Season with salt and pepper",
                "3. Grill to desired doneness",
                "4. Add cheese to melt",
                "5. Assemble with toppings"
        ));
        burgerRecipe.setServings(4);
        burgerRecipe.setSource("American Classics");
        burgerRecipe.setURL("https://www.americancuisine.com/burger");
        burgerRecipe.getCategory().add(americanCategory);

        Note burgerNote = new Note();
        burgerNote.setRecipeNote("Use 80/20 ground beef for juiciest results.");
        burgerRecipe.setNote(burgerNote);

        Ingredient groundBeef = new Ingredient("Ground Beef", new BigDecimal(1), pintUom);
        groundBeef.setRecipe(burgerRecipe);
        burgerRecipe.getIngredient().add(groundBeef);

        Ingredient burgerBuns = new Ingredient("Burger Buns", new BigDecimal(4), eachUom);
        burgerBuns.setRecipe(burgerRecipe);
        burgerRecipe.getIngredient().add(burgerBuns);

        recipes.add(burgerRecipe);

        // Recipe 6: Italian Tiramisu
        Recipe tiramisuRecipe = new Recipe();
        tiramisuRecipe.setDescription("Classic Tiramisu");
        tiramisuRecipe.setPreparationTime(30);
        tiramisuRecipe.setCookingTime(0);
        tiramisuRecipe.setDifficulty(Difficulty.MODERATE);
        tiramisuRecipe.setDirections(formatDirections(
                "1. Make coffee and cool",
                "2. Mix mascarpone filling",
                "3. Dip ladyfingers in coffee",
                "4. Layer with filling",
                "5. Dust with cocoa"
        ));
        tiramisuRecipe.setServings(8);
        tiramisuRecipe.setSource("Italian Desserts");
        tiramisuRecipe.setURL("https://www.italiandesserts.com/tiramisu");
        tiramisuRecipe.getCategory().add(italianCategory);

        Note tiramisuNote = new Note();
        tiramisuNote.setRecipeNote("Let set in refrigerator for at least 4 hours.");
        tiramisuRecipe.setNote(tiramisuNote);

        Ingredient mascarpone = new Ingredient("Mascarpone", new BigDecimal(16), eachUom);
        mascarpone.setRecipe(tiramisuRecipe);
        tiramisuRecipe.getIngredient().add(mascarpone);

        Ingredient ladyfingers = new Ingredient("Ladyfingers", new BigDecimal(24), eachUom);
        ladyfingers.setRecipe(tiramisuRecipe);
        tiramisuRecipe.getIngredient().add(ladyfingers);

        recipes.add(tiramisuRecipe);

        // Recipe 7: Mexican Enchiladas
        Recipe enchiladasRecipe = new Recipe();
        enchiladasRecipe.setDescription("Chicken Enchiladas");
        enchiladasRecipe.setPreparationTime(25);
        enchiladasRecipe.setCookingTime(20);
        enchiladasRecipe.setDifficulty(Difficulty.MODERATE);
        enchiladasRecipe.setDirections(formatDirections(
                "1. Cook and shred chicken",
                "2. Make enchilada sauce",
                "3. Dip tortillas in sauce",
                "4. Fill and roll tortillas",
                "5. Bake with cheese"
        ));
        enchiladasRecipe.setServings(6);
        enchiladasRecipe.setSource("Mexican Kitchen");
        enchiladasRecipe.setURL("https://www.mexicancuisine.com/enchiladas");
        enchiladasRecipe.getCategory().add(mexicanCategory);

        Note enchiladasNote = new Note();
        enchiladasNote.setRecipeNote("Use corn tortillas for authentic taste.");
        enchiladasRecipe.setNote(enchiladasNote);

        Ingredient chickenBreast = new Ingredient("Chicken Breast", new BigDecimal(3), eachUom);
        chickenBreast.setRecipe(enchiladasRecipe);
        enchiladasRecipe.getIngredient().add(chickenBreast);

        Ingredient cornTortillas = new Ingredient("Corn Tortillas", new BigDecimal(12), eachUom);
        cornTortillas.setRecipe(enchiladasRecipe);
        enchiladasRecipe.getIngredient().add(cornTortillas);

        recipes.add(enchiladasRecipe);

        // Recipe 8: French Crepes
        Recipe crepesRecipe = new Recipe();
        crepesRecipe.setDescription("Sweet French Crepes");
        crepesRecipe.setPreparationTime(10);
        crepesRecipe.setCookingTime(20);
        crepesRecipe.setDifficulty(Difficulty.MODERATE);
        crepesRecipe.setDirections(formatDirections(
                "1. Blend batter ingredients",
                "2. Rest batter for 30 minutes",
                "3. Heat crepe pan",
                "4. Cook crepes until lacy",
                "5. Fill and serve"
        ));
        crepesRecipe.setServings(4);
        crepesRecipe.setSource("French Pastry");
        crepesRecipe.setURL("https://www.frenchcuisine.com/crepes");
        crepesRecipe.getCategory().add(frenchCategory);

        Note crepesNote = new Note();
        crepesNote.setRecipeNote("Let batter rest for lighter crepes.");
        crepesRecipe.setNote(crepesNote);

        Ingredient flour = new Ingredient("Flour", new BigDecimal(1), pintUom);
        flour.setRecipe(crepesRecipe);
        crepesRecipe.getIngredient().add(flour);

        Ingredient crepeEggs = new Ingredient("Eggs", new BigDecimal(3), eachUom);
        crepeEggs.setRecipe(crepesRecipe);
        crepesRecipe.getIngredient().add(crepeEggs);

        recipes.add(crepesRecipe);

        return recipes;
    }
}
