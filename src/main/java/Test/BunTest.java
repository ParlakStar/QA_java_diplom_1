package Test;
import praktikum.Bun;
import org.junit.Test;
import praktikum.Burger;
import praktikum.Ingredient;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BunTest {

    @Test
    public void testGetName() {
        String name = "Sesame Bun";
        float price = 1.50f;
        Bun bun = new Bun(name, price);

        String result = bun.getName();

        assertEquals(name, result);
    }

    @Test
    public void testGetPrice() {
        String name = "Sesame Bun";
        float price = 1.50f;
        Bun bun = new Bun(name, price);

        float result = bun.getPrice();

        assertEquals(price, result, 0.001); // Using delta for float comparison
    }

    @Test
    public void testConstructor() {
        String name = "Sesame Bun";
        float price = 1.50f;
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.001); // Using delta for float comparison
    }

    @Test
    public void testBunWithMock() {
        // Оставьте моки из предыдущего кода без изменений
        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Mocked Bun");
        when(bunMock.getPrice()).thenReturn(2.00f);

        // Теперь исправляем импорт ассертов и проверяем тесты
        assertEquals("Mocked Bun", bunMock.getName());
        assertEquals(2.00f, bunMock.getPrice(), 0.001); // Using delta for float comparison
    }
    @Test
    public void testBurgerWithIngredients() {
        // Создаем булочку
        Bun bun = new Bun("Sesame Bun", 1.50f);

        // Создаем ингредиенты
        Ingredient lettuce = new Ingredient("Lettuce", IngredientType.VEGETABLE, 0.50f);
        Ingredient cheese = new Ingredient("Cheese", IngredientType.ADD_ON, 1.00f);

        // Создаем бургер и добавляем ингредиенты
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(lettuce);
        burger.addIngredient(cheese);

        // Проверяем цену бургера с ингредиентами
        float expectedPrice = bun.getPrice() * 2 + lettuce.getPrice() + cheese.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.001); // Using delta for float comparison
    }
}


