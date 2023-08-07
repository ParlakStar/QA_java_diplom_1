import org.junit.Test;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger prepareBurgerWithIngredients(Database mockDatabase) {
        List<Bun> buns = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        // Set the correct price for the bun
        buns.add(new Bun("black bun", 100));

        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));

        // Nastronim mock-object for returning predefined data
        when(mockDatabase.availableBuns()).thenReturn(buns);
        when(mockDatabase.availableIngredients()).thenReturn(ingredients);

        Burger burger = new Burger();
        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(4));
        burger.addIngredient(ingredients.get(3));
        burger.addIngredient(ingredients.get(5));
        burger.moveIngredient(2, 1);
        burger.removeIngredient(3);

        return burger;
    }


    @Test
    public void testSetBuns() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);
        assertEquals("Неверная булочка", bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        assertEquals("Неверное количество ингредиентов в бургере", 1, burger.ingredients.size());
        assertEquals("Неверный ингредиент в списке", ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        // Удаляем первый ингредиент по индексу 0
        burger.removeIngredient(0);

        // Проверяем, что количество ингредиентов стало 1
        assertEquals("Неверное количество ингредиентов после удаления", 1, burger.ingredients.size());

        // Проверяем, что удаленный ингредиент больше не содержится в списке
        assertEquals("Удаленный ингредиент всё еще присутствует в списке", false, burger.ingredients.contains(ingredient1));

        // Проверяем, что оставшийся ингредиент находится на первой позиции
        assertEquals("Оставшийся ингредиент не находится на первой позиции", ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Перемещаем ингредиент с индексом 0 на индекс 2
        burger.moveIngredient(0, 2);

        // Проверяем, что ингредиенты поменялись местами
        assertEquals("Ингредиенты не поменялись местами", ingredient2, burger.ingredients.get(0));
        assertEquals("Ингредиенты не поменялись местами", ingredient3, burger.ingredients.get(1));
        assertEquals("Ингредиенты не поменялись местами", ingredient1, burger.ingredients.get(2));
    }

    @Test
    public void testGetPrice() {
        // Создаем мок-объект для интерфейса Database
        Database mockDatabase = mock(Database.class);

        Burger burger = prepareBurgerWithIngredients(mockDatabase);

        float expectedPrice = 700.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt() {
        Database mockDatabase = mock(Database.class);
        Burger burger = prepareBurgerWithIngredients(mockDatabase);

        // Ожидаемая цена
        double expectedPrice = 700.0;

        // Фактическая цена
        double actualPrice = burger.getPrice();

        // Сравниваем значения с погрешностью
        assertEquals(expectedPrice, actualPrice, 0.1);

        // Сравниваем ожидаемый и фактический чек с обрезанием лишних пробелов
        String expectedReceipt = "(==== black bun ====)" + System.lineSeparator() +
                "= sauce sour cream =" + System.lineSeparator() +
                "= filling cutlet =" + System.lineSeparator() +  // Updated line
                "= filling dinosaur =" + System.lineSeparator() +
                "(==== black bun ====)" + System.lineSeparator() +
                System.lineSeparator() +
                "Price: 700,000000" + System.lineSeparator(); // Updated line

        String actualReceipt = burger.getReceipt();

        // Print the receipts
        System.out.println("Expected Receipt:");
        System.out.println(expectedReceipt);
        System.out.println("Actual Receipt:");
        System.out.println(actualReceipt);

        // Compare receipts after trimming white spaces
        assertEquals(expectedReceipt.trim(), actualReceipt.trim());
    }
}
