package com.example.mockitobasicuse.classtotest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Usamos @InyectMocks y pasamos las dependencias vía constructor")
@ExtendWith(MockitoExtension.class)
class CupCakeTest {

    @InjectMocks
    private CupCake cupCake;

    @Mock
    private Ingredient ingredient;

    private List<Ingredient> ingredientList;

    @Mock
    private SpyMePlease spyMePlease;

    @BeforeEach
    @DisplayName("Se llena la lista con 3 frutas y se pasan via contructor al cupCake")
    void setup() {

        this.ingredientList = Arrays.asList(
                new Ingredient("Banana"),
                new Ingredient("Fresa"),
                new Ingredient("Coco"));

        cupCake = new CupCake(ingredient, ingredientList, spyMePlease);
    }

    @Test
    @DisplayName("el ingrediente tomate es comprobado aquí")
    void getIngredient() {
        Mockito.when(ingredient.getFlavor()).thenReturn("tomate");

        assertThat("Ingredient is: tomate").isEqualTo(cupCake.getIngredient());

        assertThat("Banana").isEqualTo(cupCake.getIngredients().get(0).getFlavor());
        assertThat("Fresa").isEqualTo(cupCake.getIngredients().get(1).getFlavor());
        assertThat("Coco").isEqualTo(cupCake.getIngredients().get(2).getFlavor());
    }

    @Test
    @DisplayName("la lista de ingrediente tendra un tamaño de 3 ingredientes")
    void getSizeOfAllIngredients() {

        assertThat(3).isEqualTo(cupCake.getSizeOfAllIngredients());
    }

    @Test
    @DisplayName("La lista deberia ser igual a nuestro cupCake")
    void getIngredients() {

        assertThat(ingredientList)
                .usingRecursiveComparison()
                .isEqualTo(cupCake.getIngredients());
    }

    @Test
    @DisplayName("El metodo devolvere true estupidamente")
    void canISpyThere() {

        assertThat(cupCake.canISpyThere()).isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(CupCakeArgumentsProvider.class)
    @DisplayName("Usando muchos mas ingredientes")
    void masFrutas(final List<Ingredient> ingredientsList, Ingredient fruta1, Ingredient fruta2, Ingredient fruta3) {

        cupCake = new CupCake(ingredient, ingredientsList, spyMePlease);

        assertThat(fruta1.getFlavor()).isEqualTo(cupCake.getIngredients().get(0).getFlavor());
        assertThat(fruta2.getFlavor()).isEqualTo(cupCake.getIngredients().get(1).getFlavor());
        assertThat(fruta3.getFlavor()).isEqualTo(cupCake.getIngredients().get(2).getFlavor());

        assertThat(ingredientsList)
                .usingRecursiveComparison()
                .isEqualTo(cupCake.getIngredients());
    }

}