package com.recipeSite.webApp.model;

public enum Difficulty {

    EASY, MODERATE, HARD;

    public static Difficulty fromString(String difficulty) {
        return Difficulty.valueOf(difficulty.toUpperCase());
    }

    public static String toString(Difficulty difficulty) {
        return difficulty.name().toLowerCase();
    }
}
