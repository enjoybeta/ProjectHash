package hash.application;

import org.junit.Test;

import java.util.ArrayList;

import hash.application.dataType.Ingredient;
import hash.application.managers.IngredientManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void validate_JUNIT() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void Test1() {
        IngredientManager mockedIM = mock(IngredientManager.class);

        Ingredient ing1 = new Ingredient("apple", 2, "box");
        mockedIM.addIngredient(ing1);

        ArrayList<Ingredient> tmpList = mockedIM.getList();
        assert (tmpList.size() == 1);
    }

}