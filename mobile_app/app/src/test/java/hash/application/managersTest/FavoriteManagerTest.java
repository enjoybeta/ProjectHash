package hash.application.managersTest;

import org.junit.Test;

import java.util.ArrayList;

import hash.application.dataType.Recipe;
import hash.application.managers.FavoriteManager;

import static junit.framework.Assert.*;

public class FavoriteManagerTest {
    FavoriteManager manager = FavoriteManager.INSTANCE;
    @Test
    public void addRecipeTest() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add("Ingredient1");
        ingredients.add("Ingredient2");
        ingredients.add("Ingredient3");
        Recipe a = new Recipe("Soup", "1", 2000, "example.com", 4, "NULL", "example.com", ingredients);
        ingredients = new ArrayList<String>();
        ingredients.add("Ingredienta");
        ingredients.add("Ingredientb");
        ingredients.add("Ingredientc");
        Recipe b = new Recipe("Soup", "1", 2000, "example.com", 4, "NULL", "example.com", ingredients);
        manager.addRecipe(a);
        manager.addRecipe(b);
    }
}
