package hash.application.managersTest;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;

import hash.application.dataType.Ingredient;
import hash.application.managers.IngredientManager;

import static org.junit.Assert.*;

public class IngredientManagerTest {
    // Get the singleton first
    IngredientManager manager = IngredientManager.INSTANCE;
    @Test
    public void addIngredientTest() {
        Ingredient a = new Ingredient("Potato",-1, "kilogram");
        Ingredient b = new Ingredient("Mushroom", 20, "box");
        Ingredient c = new Ingredient("Harry", 2, "sjw");
        manager.addIngredient(a);
        manager.addIngredient(b);
        manager.addIngredient(c);
        assertTrue(manager.findIngredientByName("Potato"));
        assertTrue(manager.findIngredientByName("Mushroom"));
        assertTrue(manager.findIngredientByName("Harry"));
        ArrayList<String> names = manager.getNameList();
        assertTrue(names.contains("Potato"));
        assertTrue(names.contains("Mushroom"));
        assertTrue(names.contains("Harry"));
        assertEquals(names.size(), 3);
        ArrayList<Ingredient> ingredients = manager.getList();
        assertTrue(ingredients.contains(a));
        assertTrue(ingredients.contains(b));
        assertTrue(ingredients.contains(c));
    }
}
