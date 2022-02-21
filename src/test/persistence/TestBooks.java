package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Class with general test methods and books
public class TestBooks {
    // Sample books for testing
    String bkATitle = "Cow Behaviour";
    String bkAGenre = "CS";
    String bkAAuthor = "Gregor";
    int bkADate = 2022;
    Book bkA = new Book(bkATitle, bkAGenre, bkAAuthor, bkADate);

    String bkBTitle = "Percy Jackson";
    String bkBGenre = "Fantasy";
    String bkBAuthor = "Rick Riordan";
    int bkBDate = 2000;
    Book bkB = new Book(bkBTitle, bkBGenre, bkBAuthor, bkBDate);

    String bkCTitle = "Are cats evil?";
    String bkCGenre = "Non-fiction";
    String bkCAuthor = "Concerned citizen";
    int bkCDate = 1;
    Book bkC = new Book(bkCTitle, bkCGenre, bkCAuthor, bkCDate);

    String bkDTitle = "Cow behaviour and more animals";
    String bkDGenre = "Non-fiction";
    String bkDAuthor = "Paul Carter";
    int bkDDate = 978;
    Book bkD = new Book(bkDTitle, bkDGenre, bkDAuthor, bkDDate);

    // EFFECTS: compares expected/provided information with actual book information
    protected void checkBookString(String name, String genre, String auth, Book book) {
        assertEquals(name, book.getTitle());
        assertEquals(genre, book.getGenre());
        assertEquals(auth, book.getAuthor());
    }

    // EFFECTS: compares expected/provided information with actual book information
    protected void checkBookOther(int yr, Boolean readStat, int rate, Book book) {
        assertEquals(yr, book.getDatePublished());
        assertEquals(readStat, book.getReadStatus());
        assertEquals(rate, book.getRating());
    }

}
