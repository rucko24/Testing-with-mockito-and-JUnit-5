package com.example.mockitobasicuse.classtotest;

import java.util.List;

/**
 * Simple CupCake class for @InyectMocks
 */
public class CupCake {

    public static final String INGREDIENT_FOR_CUPCAKE = "Ingredient is: ";

    private final Ingredient ingredient;
    private List<String> ingredients;
    private final SpyMePlease spyMePlease;

    public CupCake(final Ingredient ingredient, final List<String> ingredients, final SpyMePlease spyMePlease) {
        this.ingredient = ingredient;
        this.ingredients = ingredients;
        this.spyMePlease = spyMePlease;
    }

    public String getIngredient() {
        return INGREDIENT_FOR_CUPCAKE.concat(ingredient.getFlavor());
    }

    public int getIngredients() {
        return ingredients.size();
    }

    /**
    *
    * Aquí podemos obtener un resultado diferente al usar @Mock o @Spy
    *
    * @return boolean
    */
    public boolean canISpyThere() {
        System.out.println("Can I Spy there ?");
        spyMePlease.hola();
        return true;
    }
}
