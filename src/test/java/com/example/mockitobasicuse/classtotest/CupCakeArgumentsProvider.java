package com.example.mockitobasicuse.classtotest;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * CupCakeArgumentsProvider con parametros
 */
public class CupCakeArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        final Ingredient pera = new Ingredient("pera");
        final Ingredient manzana = new Ingredient("manzana");
        final Ingredient coco = new Ingredient("coco");
        final List<Ingredient> ingredientList = Arrays.asList(pera, manzana, coco);

        final Ingredient pina = new Ingredient("piña");
        final Ingredient frutaDelDragon = new Ingredient("Fruta del dragon");
        final Ingredient uva = new Ingredient("uva");
        final List<Ingredient> ingredientList1 = Arrays.asList(pina, frutaDelDragon, uva);

        return Stream.of(
                Arguments.of(ingredientList, pera, manzana, coco),
                Arguments.of(ingredientList1, pina, frutaDelDragon, uva));
    }
}
