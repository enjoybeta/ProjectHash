package hash.application.managersTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.io.File;
import java.util.ArrayList;

import hash.application.dataType.Ingredient;
import hash.application.managers.IngredientManager;

import static org.junit.Assert.*;

public class IngredientManagerTest {
    // Get the singleton first
    IngredientManager manager = IngredientManager.INSTANCE;

    @Before
    public void runBeforeTestMethod() {
        File file = new File("./");
        manager.initFromFile(file);
    }

    // Test the addIngredient function and some get functions
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
        assertEquals(ingredients.size(), 3);
    }

    // Test the removeIngredientByName function
    @Test
    public void removeIngredientByNameTest() {
        Ingredient a = new Ingredient("Potato",-1, "kilogram");
        Ingredient b = new Ingredient("Mushroom", 20, "box");
        Ingredient c = new Ingredient("Harry", 2, "sjw");
        manager.addIngredient(a);
        manager.addIngredient(b);
        manager.addIngredient(c);
        assertTrue(manager.removeIngredientByName("Potato"));
        Boolean x = manager.findIngredientByName("Potato");
        assertFalse(manager.findIngredientByName("Potato"));
        assertTrue(manager.findIngredientByName("Mushroom"));
        assertTrue(manager.findIngredientByName("Harry"));
        assertFalse(manager.findIngredientByName("NULL"));
        ArrayList<String> names = manager.getNameList();
        assertTrue(names.contains("Mushroom"));
        assertTrue(names.contains("Harry"));
        assertEquals(names.size(), 2);
        assertTrue(manager.removeIngredientByName("Mushroom"));
        assertFalse(manager.removeIngredientByName("NULL"));
        assertFalse(manager.findIngredientByName("NULL"));
    }
}
