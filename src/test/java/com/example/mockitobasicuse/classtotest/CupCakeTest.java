package com.example.mockitobasicuse.classtotest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Usamos @InyectMocks y pasamos las dependencias vía constructor")
@ExtendWith(MockitoExtension.class)
class CupCakeTest {

    @InjectMocks
    private CupCake cupCake;

    @Mock
    private Ingredient ingredient;

    @Mock
    private List<Ingredient> ingredientList;

    @Mock
    private SpyMePlease spyMePlease;

    @Test
    @DisplayName("el ingrediente tomate es comprobado aquí y otros ingredientes distintos")
    void getIngredient() {

        when(ingredient.getFlavor()).thenReturn("tomate");
        when(ingredientList.get(0)).thenReturn(new Ingredient("Banana"));
        when(ingredientList.get(1)).thenReturn(new Ingredient("Fresa"));
        when(ingredientList.get(2)).thenReturn(new Ingredient("Coco"));

        assertThat("Ingredient is: tomate").isEqualTo(cupCake.getIngredient());
        assertThat("Banana").isEqualTo(cupCake.getIngredients().get(0).getFlavor());
        assertThat("Fresa").isEqualTo(cupCake.getIngredients().get(1).getFlavor());
        assertThat("Coco").isEqualTo(cupCake.getIngredients().get(2).getFlavor());

        verify(ingredient).getFlavor();

    }

    @Test
    @DisplayName("la lista de ingrediente tendra un tamaño de 3 ingredientes")
    void getSizeOfAllIngredients() {
        when(ingredientList.size()).thenReturn(3);
        assertThat(3).isEqualTo(cupCake.getSizeOfAllIngredients());
    }

    @Test
    @DisplayName("La lista deberia ser igual a nuestro cupCake, y contener un Ingrediente con sabor Banana")
    void getIngredients() {

        when(this.ingredientList.size()).thenReturn(1);
        when(this.ingredientList.get(0)).thenReturn(new Ingredient("Banana"));

        assertAll(
                () -> assertEquals(this.ingredientList.size(), cupCake.getIngredients().size()),
                () -> assertEquals(this.ingredientList.get(0), this.cupCake.getIngredients().get(0))
        );

        verify(this.ingredientList, times(2)).size();
        verify(this.ingredientList, times(2)).get(0);

    }

    @Test
    @DisplayName("El metodo devolvere true estupidamente")
    void canISpyThere() {

        assertThat(cupCake.canISpyThere()).isTrue();
    }

    @ParameterizedTest
    @ArgumentsSource(CupCakeArgumentsProvider.class)
    @DisplayName("Usando 2 listas parametrizadas con 3 ingredientes esperados")
    void masFrutas(final List<Ingredient> ingredientsList, Ingredient ingredientExpected, Ingredient ingredient2Expected,
                   Ingredient ingredient3Expected) {

        when(this.ingredientList.get(0)).thenReturn(ingredientsList.get(0));
        when(this.ingredientList.get(1)).thenReturn(ingredientsList.get(1));
        when(this.ingredientList.get(2)).thenReturn(ingredientsList.get(2));

        assertNotNull(ingredientsList);
        assertThat(ingredientExpected).isEqualTo(cupCake.getIngredients().get(0));
        assertThat(ingredient2Expected).isEqualTo(cupCake.getIngredients().get(1));
        assertThat(ingredient3Expected).isEqualTo(cupCake.getIngredients().get(2));

        assertThat(ingredientList)
                .usingRecursiveComparison()
                .isEqualTo(cupCake.getIngredients());

        verify(ingredientList, times(1)).get(0);

    }

}