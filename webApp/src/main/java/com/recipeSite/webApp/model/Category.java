package com.recipeSite.webApp.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

    @Id
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "category")
    private Set<Recipe> recipes;
}
