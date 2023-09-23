package com.example.mockitobasicuse.classtotest;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Simple CupCake class for @InyectMocks
 */
@Slf4j
public class CupCake {

    public static final String INGREDIENT_FOR_CUPCAKE = "Ingredient is: ";

    private final Ingredient ingredient;
    private List<Ingredient> ingredients;
    private final SpyMePlease spyMePlease;

    public CupCake(final Ingredient ingredient, final List<Ingredient> ingredients, final SpyMePlease spyMePlease) {
        this.ingredient = ingredient;
        this.ingredients = ingredients;
        this.spyMePlease = spyMePlease;
    }

    public String getIngredient() {
        return INGREDIENT_FOR_CUPCAKE.concat(ingredient.getFlavor());
    }

    public int getSizeOfAllIngredients() {
        return ingredients.size();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
    *
    * Aquí podemos obtener un resultado diferente al usar @Mock o @Spy
    *
    * @return boolean
    */
    public boolean canISpyThere() {
        log.info("Can I Spy there ? {}", "spy");
        spyMePlease.hola();
        return true;
    }
}
