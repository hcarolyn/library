package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Book class
class BookTest {
    Book book;
    String bookTitle = "TestBook";
    String bookGenre = "TestGenre";
    String bookAuthor = "TestAuthor";
    int bookYear = 2022;

    @BeforeEach
    public void setup() {
        this.book = new Book(bookTitle, bookGenre, bookAuthor, bookYear);
    }

    @Test
    public void testBook() {
        assertEquals(bookTitle, this.book.getTitle());
        assertEquals(bookGenre, this.book.getGenre());
        assertEquals(bookAuthor, this.book.getAuthor());
        assertEquals(0, this.book.getRating());
        assertEquals(bookYear, this.book.getDatePublished());
        assertFalse(this.book.getReadStatus());
    }

    @Test
    public void testChangeRating() {
        this.book.changeRating(1);
        assertEquals(1, this.book.getRating());

        this.book.changeRating(5);
        assertEquals(5, this.book.getRating());

        this.book.changeRating(3);
        assertEquals(3, this.book.getRating());
    }

    @Test
    public void testChangeReadStatus() {
        this.book.changeReadStatus(false);
        assertFalse(this.book.getReadStatus());

        this.book.changeReadStatus(true);
        assertTrue(this.book.getReadStatus());
    }

    @Test
    public void testBookInfo() {
        String expectBookInfo = "TestBook by TestAuthor about TestGenre with a rating of 0";
        assertEquals(expectBookInfo, book.bookInfo(book));

        Book bookB = new Book("Trust the Natural Recursion", "CS", "Gregor", 2021);
        bookB.changeRating(5);
        String expectBookBInfo = "Trust the Natural Recursion by Gregor about CS with a rating of 5";
        assertEquals(expectBookBInfo, bookB.bookInfo(bookB));
    }

}
