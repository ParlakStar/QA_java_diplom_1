import org.junit.Test;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void testGetPrice() {
        // Создаем мок-объект для интерфейса Database
        Database mockDatabase = mock(Database.class);

        // Подготавливаем данные, которые будут возвращены мок-объектом
        List<Bun> buns = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        buns.add(new Bun("black bun", 100));
        buns.add(new Bun("white bun", 200));
        buns.add(new Bun("red bun", 300));

        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));

        // Настроим мок-объект для возвращения предопределенных данных
        when(mockDatabase.availableBuns()).thenReturn(buns);
        when(mockDatabase.availableIngredients()).thenReturn(ingredients);

        Burger burger = new Burger();

        // Теперь используем сохраненные списки buns и ingredients для настройки тестовых данных
        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));
        burger.moveIngredient(2, 1);
        burger.removeIngredient(3);

        float expectedPrice = 700.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }
    @Test
    public void testGetReceipt() {
        Database mockDatabase = mock(Database.class);

        // Подготавливаем данные, которые будут возвращены мок-объектом
        List<Bun> buns = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        buns.add(new Bun("black bun", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));

        // Настроим мок-объект для возвращения предопределенных данных
        when(mockDatabase.availableBuns()).thenReturn(buns);
        when(mockDatabase.availableIngredients()).thenReturn(ingredients);

        Burger burger = new Burger();
        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));

        // Ожидаемая цена
        double expectedPrice = 400.0;

        // Фактическая цена
        double actualPrice = burger.getPrice();

        // Сравниваем значения с погрешностью
        assertEquals(expectedPrice, actualPrice, 0.1);

        // Выведем фактический чек в консоль
        String actualReceipt = burger.getReceipt();
        System.out.println("Фактический чек:");
        System.out.println(actualReceipt);

        // Сравним ожидаемую цену с фактической ценой с погрешностью 0.1
        assertEquals(expectedPrice, actualPrice, 0.1);
    }
}