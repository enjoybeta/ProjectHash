package hash.application.managersTest;

import org.junit.Test;

import java.util.ArrayList;

import hash.application.dataType.SearchCoarse;
import hash.application.dataType.SearchPrecise;
import hash.application.managers.WebManager;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class WebManagerTest {
    // Get the singleton first
    WebManager manager = WebManager.INSTANCE;
    /*
    Since for now we cannot foresee the return value from the server, we can
    only test the return content twice to make sure these functions work as intended.
     */
    @Test
    public void getToday1Test() {
        String data1 = manager.getToday1();
        String data2 = manager.getToday1();
        assertTrue(data1.equals(data2));
    }

    @Test
    public void getToday2Test() {
        String data1 = manager.getToday2();
        String data2 = manager.getToday2();
        assertTrue(data1.equals(data2));
    }

    @Test
    public void getToday3Test() {
        String data1 = manager.getToday3();
        String data2 = manager.getToday3();
        assertTrue(data1.equals(data2));
    }

    @Test
    public void getToday4Test() {
        String data1 = manager.getToday4();
        String data2 = manager.getToday4();
        assertTrue(data1.equals(data2));
    }

    /*
    For the two search functions, we specify a test data in server.
    When we search for these words, the server will always return the same content.
     */
    @Test
    public void searchPreciseTest() {
        String data1 = manager.searchPrecise(new SearchPrecise("beef"));
        String data2 = manager.searchPrecise(new SearchPrecise("beef"));
        String data3 = manager.searchPrecise(new SearchPrecise("milk"));
        assertTrue(data1.equals(data2));
        assertFalse(data1.equals(data3));
    }

     @Test
    public void searchCoarseTest() {
        String data1 = manager.searchCoarse(new SearchCoarse(4, new ArrayList<String>(asList("onion", "beef")), new ArrayList<String>(asList("egg"))));
        String data2 = manager.searchCoarse(new SearchCoarse(4, new ArrayList<String>(asList("onion", "beef")), new ArrayList<String>(asList("egg"))));
        String data3 = manager.searchCoarse(new SearchCoarse(6, new ArrayList<String>(asList("onion", "beef")), new ArrayList<String>(asList("egg"))));
        String data4 = manager.searchCoarse(new SearchCoarse(6, new ArrayList<String>(asList("pepper", "chicken")), new ArrayList<String>(asList("peanut"))));
        assertTrue(data1.equals(data2));
        assertFalse(data1.equals(data3));
        assertFalse(data3.equals(data4));
    }

}
