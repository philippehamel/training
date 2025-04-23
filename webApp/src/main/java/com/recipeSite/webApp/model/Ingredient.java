package com.recipeSite.webApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private UnitOfMeasurement uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(String description, BigDecimal bigDecimal, UnitOfMeasurement uom) {
        this.description = description;
        this.amount = bigDecimal;
        this.uom = uom;
    }
}

