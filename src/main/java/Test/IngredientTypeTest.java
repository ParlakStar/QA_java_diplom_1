package Test;
import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testEnumValues() {
        IngredientType sauce = IngredientType.SAUCE;
        IngredientType filling = IngredientType.FILLING;

        // Проверяем, что перечисление содержит ожидаемые значения
        assertEquals(2, IngredientType.values().length);
        assertEquals(sauce, IngredientType.valueOf("SAUCE"));
        assertEquals(filling, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void testEnumToString() {
        IngredientType sauce = IngredientType.SAUCE;
        IngredientType filling = IngredientType.FILLING;

        // Проверяем, что перечисление правильно переопределяет метод toString()
        assertEquals("SAUCE", sauce.toString());
        assertEquals("FILLING", filling.toString());
    }
}
