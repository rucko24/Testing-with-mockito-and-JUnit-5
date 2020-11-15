package com.example.mockitobasicuse;

import com.example.mockitobasicuse.classtotest.CupCake;
import com.example.mockitobasicuse.classtotest.Ingredient;
import com.example.mockitobasicuse.classtotest.SpyMePlease;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * En este caso las dependencias como Ingredient e Ingredients serán nuestros mocks anotadas con
 * @Mock donde esta creara las implementaciones que necesitemos.
 *
 * Mientras que nuestra clase CupCake sera la que vamos a testear, anotada con @InjectMock sin necesidad
 * de instanciarla porque el framework lo hace por nosotros, luego esta inyectara todos los mocks que están marcados con @Mock, @Spy etc
 */
@ExtendWith(MockitoExtension.class)
class MockingAndSpyTest {

    private static final String BLACK_BERRY = "BlackBerry";

    @Mock
    private Ingredient ingredient;

    /**
     * Si cambiamos esta anotacion a @Mock no inspeccionaremos internamente al método es decir
     * no se invocara el método hola()
     */
    @Spy
    private SpyMePlease spyMePlease;

    @Spy
    private List<String> ingredients = new ArrayList<>();

    @InjectMocks
    private CupCake cupCake;

    @Test
    void mockAndSpy() {
        Mockito.when(ingredient.getFlavor()).thenReturn(BLACK_BERRY);

        ingredients.add("Banana");
        ingredients.add("Orange");
        ingredients.add("BlueBerry");

        final int expectedIngredients = 3;
        assertThat(expectedIngredients, is(cupCake.getIngredients()));

        final String expectedFlavor = CupCake.INGREDIENT_FOR_CUPCAKE.concat(BLACK_BERRY);
        assertThat(expectedFlavor, is(cupCake.getIngredient()));

        assertThat(true, is(cupCake.canISpyThere()));
    }
}
