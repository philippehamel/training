package com.recipeSite.webApp.command;

import java.util.HashSet;
import java.util.Set;

import com.recipeSite.webApp.model.Difficulty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private String preparationTime;
    private String cookingTime;
    private String servings;
    private String source;
    private String URL;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NoteCommand note;
    private Set<CategoryCommand> categories = new HashSet<>();
}
