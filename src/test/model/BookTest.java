package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book;

    @BeforeEach
    public void setup() {
        this.book = new Book("TestBook", "TestGenre", "TestAuthor", 2022);
    }

    @Test
    public void testBook() {
        assertEquals("TestBook", this.book.getTitle());
        assertEquals("TestGenre", this.book.getGenre());
        assertEquals("TestAuthor", this.book.getAuthor());
        assertEquals(0, this.book.getRating());
        assertEquals(2002, this.book.getDatePublished());
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

// NEED TO ADD TESTS AFTERWARDS


}
