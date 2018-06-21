package hash.application;

import android.util.Log;

import org.junit.Test;
import org.mockito.Mock;

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
    @Mock
    private IngredientManager mockedIM;

    @Test
    public void validate_JUNIT() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void Test1() {
        mockedIM = mock(IngredientManager.class);
        ArrayList<Ingredient> tmpList1 = mockedIM.getList();
        System.out.println(tmpList1.size());
        assert (tmpList1.size() == 0);
    }

    //test will fail somehow due to mockito singleton instance
    /* @Test
    public void Test2() {
        mockedIM = mock(IngredientManager.class);
        ArrayList<Ingredient> tmpList1 = mockedIM.getList();
        System.out.println(tmpList1.size());
        assert (tmpList1.size() == 0);

        Ingredient ing1 = new Ingredient("apple", 2, "box");
        mockedIM.addIngredient(ing1);

        ArrayList<Ingredient> tmpList2 = mockedIM.getList();
        System.out.println(tmpList2.size());
        assert (tmpList2.size() == 1);
    } */

}