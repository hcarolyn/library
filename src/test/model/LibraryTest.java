package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Library class
public class LibraryTest {
    private Library testLibrary;
    private String testLibName;

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


    @BeforeEach
    void setup() {
        testLibrary = new Library(testLibName);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testLibrary.getLibSize());
        assertEquals(testLibName, testLibrary.getLibName());
    }

    @Test
    public void testAddBook() {
        testLibrary.addBook(bkA);
        assertEquals(1, testLibrary.getLibSize());
        assertTrue(testLibrary.containsBook(bkATitle));

        testLibrary.addBook(bkB);
        assertEquals(2, testLibrary.getLibSize());
        assertTrue(testLibrary.containsBook(bkBTitle));
    }

    @Test
    public void testRemoveBookTrue() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        testLibrary.addBook(bkC);
        assertEquals(3, testLibrary.getLibSize());
        assertTrue(testLibrary.containsBook(bkCTitle));

        Boolean removeBookCStatus = testLibrary.removeBook(bkCTitle);
        assertFalse(testLibrary.containsBook(bkCTitle));
        assertTrue(removeBookCStatus);
        assertEquals(2, testLibrary.getLibSize());

        Boolean removeBookAStatus = testLibrary.removeBook(bkATitle);
        assertFalse(testLibrary.containsBook(bkATitle));
        assertTrue(removeBookAStatus);
        assertEquals(1, testLibrary.getLibSize());
    }

    @Test
    public void testRemoveBookFalse() {
        Boolean removeEmptyLib = testLibrary.removeBook(bkCTitle);
        assertFalse(removeEmptyLib);
        assertEquals(0, testLibrary.getLibSize());

        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        assertEquals(2, testLibrary.getLibSize());

        Boolean removeBookDStatus = testLibrary.removeBook(bkDTitle);
        assertFalse(removeBookDStatus);
        assertEquals(2, testLibrary.getLibSize());

    }

    @Test
    public void testContainsBook() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkD);
        testLibrary.addBook(bkC);

        assertTrue(testLibrary.containsBook(bkATitle));
        assertTrue(testLibrary.containsBook(bkDTitle));
        assertFalse(testLibrary.containsBook(bkBTitle));
        assertFalse(testLibrary.containsBook("Testing testing!"));
    }

    @Test
    public void testLibBookInfoSingle() {
        testLibrary.addBook(bkA);
        String expectedLibInfo = bkATitle + " by " + bkAAuthor + " about " + bkAGenre + " with a rating of nothing";
        assertEquals(expectedLibInfo, testLibrary.libBookInfo(testLibrary.getArrayLib()));
    }

    @Test
    public void testLibBookInfoMultiple() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        bkB.changeRating(3);

        String expectedAInfo = bkATitle + " by " + bkAAuthor + " about " + bkAGenre + " with a rating of nothing";
        String expectedBInfo = bkBTitle + " by " + bkBAuthor + " about " + bkBGenre + " with a rating of 3";
        String expectedOverallInfo = expectedAInfo + ", " + expectedBInfo;
        assertEquals(expectedOverallInfo, testLibrary.libBookInfo(testLibrary.getArrayLib()));
    }

    @Test
    public void testReplaceRating() {
        testLibrary.addBook(bkD);
        Book bookD = testLibrary.getArrayLib().get(0);
        assertEquals(bkD, bookD);
        testLibrary.addBook(bkC);
        Book bookC = testLibrary.getArrayLib().get(1);
        assertEquals(bkC, bookC);

        assertEquals(0, bookD.getRating());
        testLibrary.replaceRating(bkDTitle, 3);
        assertEquals(3, bookD.getRating());
        testLibrary.replaceRating(bkDTitle, 5);
        assertEquals(5, bookD.getRating());

        assertEquals(0, bookC.getRating());
        testLibrary.replaceRating(bkCTitle, 5);
        assertEquals(5, bookC.getRating());
    }

    @Test
    public void testReplaceReadStatus() {
        testLibrary.addBook(bkB);
        Book bookB = testLibrary.getArrayLib().get(0);
        testLibrary.addBook(bkA);
        Book bookA = testLibrary.getArrayLib().get(1);

        assertFalse(bookB.getReadStatus());
        testLibrary.replaceReadStatus(bkBTitle, true);
        assertTrue(bookB.getReadStatus());
        testLibrary.replaceReadStatus(bkBTitle, false);
        assertFalse(bookB.getReadStatus());

        assertFalse(bookA.getReadStatus());
        testLibrary.replaceReadStatus(bkATitle, true);
        assertTrue(bookA.getReadStatus());
        testLibrary.replaceReadStatus(bkATitle, false);
        assertFalse(bookA.getReadStatus());
    }

    @Test
    public void testSearchRatingSingleResult() {
        List<Integer> ratings = new ArrayList<>();
        ratings.add(1);
        ratings.add(2);
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        testLibrary.replaceRating(bkBTitle, 2);

        ArrayList<Book> searchResults = testLibrary.searchRating(ratings);
        assertEquals(1, searchResults.size());
        assertEquals(bkB, searchResults.get(0));
    }

    @Test
    public void testSearchRatingMultiResultSingleList() {
        List<Integer> ratings = new ArrayList<>();
        ratings.add(3);
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        testLibrary.addBook(bkC);
        testLibrary.replaceRating(bkBTitle, 3);
        testLibrary.replaceRating(bkCTitle, 3);

        ArrayList<Book> searchResults = testLibrary.searchRating(ratings);
        assertEquals(2, searchResults.size());
        assertEquals(bkB, searchResults.get(0));
        assertEquals(bkC, searchResults.get(1));
    }

    @Test
    public void testSearchRatingMultiResultMultiList() {
        List<Integer> ratings = new ArrayList<>();
        ratings.add(4);
        ratings.add(5);
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        testLibrary.addBook(bkC);
        testLibrary.addBook(bkD);
        testLibrary.replaceRating(bkATitle, 4);
        testLibrary.replaceRating(bkDTitle, 5);

        ArrayList<Book> searchResults = testLibrary.searchRating(ratings);
        assertEquals(2, searchResults.size());
        assertEquals(bkA, searchResults.get(0));
        assertEquals(bkD, searchResults.get(1));
    }

    @Test
    public void testSearchRatingNoResult() {
        List<Integer> ratings = new ArrayList<>();
        ratings.add(1);
        testLibrary.addBook(bkA);

        ArrayList<Book> searchResults = testLibrary.searchRating(ratings);
        assertEquals(0, searchResults.size());
    }

    @Test
    public void testSearchTitlesSingleResult() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkD);

        ArrayList<Book> searchResults = testLibrary.searchTitle(bkATitle);
        assertEquals(1, searchResults.size());
        assertEquals(bkA, searchResults.get(0));
    }

    @Test
    public void testSearchTitlesNoResult() {
        testLibrary.addBook(bkC);
        testLibrary.addBook(bkD);

        ArrayList<Book> searchResults = testLibrary.searchTitle(bkATitle);
        assertEquals(0, searchResults.size());
    }

    @Test
    public void testSearchGenreSingleResult() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);

        ArrayList<Book> searchResults = testLibrary.searchGenre(bkBGenre);
        assertEquals(1, searchResults.size());
        assertEquals(bkB, searchResults.get(0));
    }

    @Test
    public void testSearchGenreMultiResult() {
        testLibrary.addBook(bkB);
        testLibrary.addBook(bkC);
        testLibrary.addBook(bkD);

        ArrayList<Book> searchResults = testLibrary.searchGenre(bkCGenre);
        assertEquals(2, searchResults.size());
        assertEquals(bkC, searchResults.get(0));
        assertEquals(bkD, searchResults.get(1));
    }

    @Test
    public void testSearchGenreNoResult() {
        testLibrary.addBook(bkA);
        ArrayList<Book> searchResults = testLibrary.searchGenre(bkBGenre);
        assertEquals(0, searchResults.size());
    }

    @Test
    public void testSearchUnreadSingleResult() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkC);
        testLibrary.replaceReadStatus(bkATitle, true);

        ArrayList<Book> searchResults = testLibrary.searchUnreadBooks();
        assertEquals(1, searchResults.size());
        assertTrue(searchResults.contains(bkC));
        assertFalse(searchResults.contains(bkA));
    }

    @Test
    public void testSearchUnreadMultiResult() {
        testLibrary.addBook(bkA);
        testLibrary.addBook(bkB);
        testLibrary.addBook(bkC);
        testLibrary.replaceReadStatus(bkBTitle, true);

        ArrayList<Book> searchResults = testLibrary.searchUnreadBooks();
        assertEquals(2, searchResults.size());
        assertTrue(searchResults.contains(bkC));
        assertFalse(searchResults.contains(bkB));
        assertTrue(searchResults.contains(bkA));
    }


    @Test
    public void testSearchUnreadNoResult() {
        testLibrary.addBook(bkD);
        testLibrary.replaceReadStatus(bkDTitle, true);

        ArrayList<Book> searchResults = testLibrary.searchUnreadBooks();
        assertEquals(0, searchResults.size());
        assertFalse(searchResults.contains(bkD));
    }

}

