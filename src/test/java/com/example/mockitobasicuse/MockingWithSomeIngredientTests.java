package com.example.mockitobasicuse;

import com.example.mockitobasicuse.classtotest.CupCake;
import com.example.mockitobasicuse.classtotest.Ingredient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing a CupCake with some ingredient ;)
 */
@ExtendWith(MockitoExtension.class)
class MockingWithSomeIngredientTests {

	private static final String INGREDIENT = "Strawberry";

	@Mock
	private Ingredient ingredient;

	@InjectMocks
	private CupCake cupCake;

	@Test
	void mockToReturnStrawberry() {
		Mockito.when(ingredient.getFlavor()).thenReturn(INGREDIENT);

		final String expectedFlavor = CupCake.INGREDIENT_FOR_CUPCAKE.concat(INGREDIENT);

		assertThat(expectedFlavor, is(cupCake.getIngredient()));
	}


}
