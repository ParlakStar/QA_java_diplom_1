package Test;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void testGetPrice() {
        float price = 1.50f;
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Chicken", price);

        float result = ingredient.getPrice();

        assertEquals(price, result, 0.001); // Using delta for float comparison
    }

    @Test
    public void testGetName() {
        String name = "Lettuce";
        Ingredient ingredient = new Ingredient(IngredientType.VEGETABLE, name, 0.50f);

        String result = ingredient.getName();

        assertEquals(name, result);
    }

    @Test
    public void testGetType() {
        IngredientType type = IngredientType.SAUCE;
        Ingredient ingredient = new Ingredient(type, "Hot Sauce", 0.75f);

        IngredientType result = ingredient.getType();

        assertEquals(type, result);
    }
}
