// some tests are based on the EdX JsonSerializationDemo example!

package persistence;

import model.Book;
import model.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the JsonWriter class
public class JsonWriterTest extends TestBooks {

    @Test
    void testWriterInvalidFile() {
        try {
            Library lib = new Library("Library");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLibrary() {
        try {
            Library lib = new Library("Library");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            lib = reader.read();
            assertEquals(0, lib.getLibSize());
            assertEquals("Library", lib.getLibName());
            assertTrue(lib.getArrayLib().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNonEmptyLibrary() {
        try {
            Library lib = new Library("Library");
            lib.addBook(bkA);
            bkB.changeReadStatus(true);
            bkB.changeRating(3);
            lib.addBook(bkB);
            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyLibrary.json");
            writer.open();
            writer.write(lib);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyLibrary.json");
            lib = reader.read();
            List<Book> arrayLib = lib.getArrayLib();
            assertEquals("Library", lib.getLibName());
            assertEquals(2, lib.getLibSize());
            checkBookString("Cow Behaviour", "CS", "Gregor", arrayLib.get(0));
            checkBookOther(2022, false, 0, arrayLib.get(0));
            checkBookString("Percy Jackson", "Fantasy", "Rick Riordan", arrayLib.get(1));
            checkBookOther(2000, true, 3, arrayLib.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

