package hash.application.managersTest;

import org.junit.Test;

import hash.application.managers.WebManager;

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
        String data1 = manager.searchPrecise({"keyword": "beef"});
        String data2 = manager.searchPrecise({"keyword": "beef"});
        String data3 = manager.searchPrecise({"keyword": "milk"});
        assertTrue(data1.equals(data2));
        assertFalse(data1.equals(data3));
    }

     @Test
    public void searchCoarseTest() {
        String data1 = manager.searchCoarse({"numberofserving": "4", "having": ["onion", "beef"], "not having": ["egg"]});
        String data2 = manager.searchCoarse({"numberofserving": "4", "having": ["onion", "beef"], "not having": ["egg"]});
        String data3 = manager.searchCoarse({"numberofserving": "6", "having": ["onion", "beef"], "not having": ["egg"]});
        String data4 = manager.searchCoarse({"numberofserving": "6", "having": ["pepper", "chicken"], "not having": ["peanut"]});
        assertTrue(data1.equals(data2));
        assertFalse(data1.equals(data3));
        assertFalse(data3.equals(data4));
    }

}
