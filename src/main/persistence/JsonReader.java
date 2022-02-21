// some methods are based on methods in the EdX JsonSerializationDemo provided in class

package persistence;

import model.Book;
import model.Library;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads a library from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads library from file and returns it
    //          throws IOException if an error occurs reading data from the file
    public Library read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Library lib = new Library(name);
        addLibrary(lib, jsonObject);
        return lib;
    }

    // MODIFIES: lib
    // EFFECTS: parses books from JSON object and adds them to library
    private void addLibrary(Library lib, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(lib, nextBook);
        }
    }

    // MODIFIES: lib
    // EFFECTS: parses book from JSON object and adds it to library
    private void addBook(Library lib, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String genre = jsonObject.getString("genre");
        String author = jsonObject.getString("author");
        int rating = jsonObject.getInt("rating");
        int datePublished = jsonObject.getInt("date published");
        boolean readStatus = jsonObject.getBoolean("read status");

        Book book = new Book(title, genre, author, datePublished);
        book.changeRating(rating);
        book.changeReadStatus(readStatus);
        lib.addBook(book);
    }
}
