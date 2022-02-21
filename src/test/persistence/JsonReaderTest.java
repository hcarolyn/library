// some tests are based on the EdX JsonSerializationDemo example!

package persistence;

import model.Book;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the JsonReader class
public class JsonReaderTest extends TestBooks {

    @Test
    void testReaderNonExistFile() {
        JsonReader reader = new JsonReader("./data/ill\0testNonExistFile.json");
        try {
            Library lib = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // should pass
        }
    }

    @Test
    void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals("Library", lib.getLibName()); // what is the default name?
            assertEquals(0, lib.getLibSize());
        } catch (IOException e) {
            // should this fail or not?@?@?
        }
    }

    @Test
    void testReaderNonEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyLibrary.json");
        try {
            Library lib = reader.read();
            assertEquals("Library", lib.getLibName());
            assertEquals(2, lib.getLibSize());
            ArrayList<Book> arrayLib = lib.getArrayLib();
            checkBookString("Cow Behaviour", "CS", "Gregor", arrayLib.get(0));
            checkBookOther(2022, false, 0, arrayLib.get(0));
            checkBookString("Percy Jackson", "Fantasy", "Rick Riordan", arrayLib.get(1));
            checkBookOther(2000, true, 3, arrayLib.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
