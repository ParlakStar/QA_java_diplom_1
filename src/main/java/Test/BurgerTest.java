package Test;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void testGetPrice() {
        Bun bun = new Bun("Sesame Bun", 1.50f);
        Ingredient lettuce = new Ingredient("Lettuce", IngredientType.VEGETABLE, 0.50f);
        Ingredient cheese = new Ingredient("Cheese", IngredientType.ADD_ON, 1.00f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(lettuce);
        burger.addIngredient(cheese);

        // Создаем моки объектов Bun и Ingredient
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(1.50f);

        Ingredient lettuceMock = mock(Ingredient.class);
        when(lettuceMock.getPrice()).thenReturn(0.50f);

        Ingredient cheeseMock = mock(Ingredient.class);
        when(cheeseMock.getPrice()).thenReturn(1.00f);

        // Устанавливаем моки вместо реальных объектов
        burger.bun = bunMock;
        burger.ingredients.set(0, lettuceMock);
        burger.ingredients.set(1, cheeseMock);

        float result = burger.getPrice();

        assertEquals(3.00f, result, 0.001); // Using delta for float comparison
    }

    @Test
    public void testGetReceipt() {
        Bun bun = new Bun("Sesame Bun", 1.50f);
        Ingredient lettuce = new Ingredient("Lettuce", IngredientType.VEGETABLE, 0.50f);
        Ingredient cheese = new Ingredient("Cheese", IngredientType.ADD_ON, 1.00f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(lettuce);
        burger.addIngredient(cheese);

        String result = burger.getReceipt();
        String expectedReceipt = "(==== Sesame Bun ====)\n" +
                "= vegetable Lettuce =\n" +
                "= add_on Cheese =\n" +
                "(==== Sesame Bun ====)\n" +
                "\n" +
                "Price: 3.000000\n";

        assertEquals(expectedReceipt, result);
    }
}
