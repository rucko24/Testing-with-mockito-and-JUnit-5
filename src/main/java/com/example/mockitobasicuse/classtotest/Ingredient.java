package com.example.mockitobasicuse.classtotest;

/**
 * Simple class Ingredient to @Mock
 */
public class Ingredient {

    private String flavor;

    public Ingredient(final String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }
}
