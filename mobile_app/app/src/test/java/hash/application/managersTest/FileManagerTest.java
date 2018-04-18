package hash.application.managersTest;

import org.junit.Test;

import java.io.File;

import hash.application.managers.FileManager;

import static junit.framework.Assert.*;

public class FileManagerTest {
    @Test
    public void writeFileTest() {
        FileManager file = new FileManager(new File("./"), "test.txt");
        assertTrue(file.checkFile());
        file.writeFile("test\n");
        String a = file.readFile();
        assertTrue(a.equals("test\n"));
        file.writeFile("NULL a b c \r");
        a = file.readFile();
        assertTrue(a.equals("NULL a b c \r"));
    }
}
