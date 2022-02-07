package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {
    private Library testLibrary;

    // some example books for tests
    String bkATitle = "Cow Behaviour";
    String bkAGenre = "CS";
    String bkAAuthor = "Gregor";
    int bkARating;
    int bkADate = 2022;
    boolean bkAStatus;
    Book bkA = new Book(bkATitle, bkAGenre, bkAAuthor, bkADate);

    String bkBTitle = "Percy Jackson";
    String bkBGenre = "Fantasy";
    String bkBAuthor = "Rick Riordan";
    int bkBRating;
    int bkBDate = 2000;
    boolean bkBStatus;
    Book bkB = new Book(bkBTitle, bkBGenre, bkBAuthor, bkBDate);

    String bkCTitle = "Are cats evil?";
    String bkCGenre = "Non-fiction";
    String bkCAuthor = "Concerned citizen";
    int bkCRating;
    int bkCDate = 1;
    boolean bkCStatus;
    Book bkC = new Book(bkCTitle, bkCGenre, bkCAuthor, bkCDate);

    String bkDTitle = "Cow behaviour and more animals";
    String bkDGenre = "Non-fiction";
    String bkDAuthor = "Rick Riordan";
    int bkDRating;
    int bkDDate = 978;
    boolean bkDStatus;
    Book bkD = new Book(bkDTitle, bkDGenre, bkDAuthor, bkDDate);


    @BeforeEach
    void setup() {
        testLibrary = new Library();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testLibrary.getLibSize());
    }

    @Test
    public void testAddBook() {
        testLibrary.addBook(bkA);
        assertEquals(1, testLibrary.getLibSize());

        testLibrary.addBook(bkB);
        assertEquals(2, testLibrary.getLibSize());

    }


    //    // REQUIRES: non-empty string
//    // MODIFIES: this
//    // EFFECTS: if book exists in library, returns true and removes book. if not, returns false
//    public Boolean removeBook(String title) {
//        for (Book b : library) {
//            if (title.equalsIgnoreCase(b.getTitle())) {
//                library.remove(b);
//                return true;
//            }
//        }
//        return false;
//    }
//
    @Test
    public void testRemoveBook() {

    }
//    // REQUIRES: non-empty string
//    // EFFECTS: if this book exists in library, returns true. if not, returns false
//    public Boolean containsBook(String title) {
//        for (Book b : library) {
//            if (title.equalsIgnoreCase(b.getTitle())) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Test
    public void testContainsBookTrue() {
        // test both lower and upper base books?
        // test part of the title?

    }

    @Test
    public void testContainsBookFalse() {

    }


    //    // REQUIRES: a non-empty array list with books
//    // EFFECTS: returns the information of all books in the provided library in the form of a string
//    public String libBookInfo(ArrayList<Book> lib) {
//        String bookTitles = " ";
//        for (Book b : lib) {
//            bookTitles = bookTitles + ", " + b.bookInfo(b);
//        }
//        String trimmedBookTitles = bookTitles.trim().substring(1).trim();
//        return trimmedBookTitles;
//    }
//
    @Test
    public void testLibBookInfoSingle() {

    }

    @Test
    public void testLibBookInfoMultiple() {

    }

    //    // REQUIRES: a rating (integer between [1, 5]), valid book title in library
//    // MODIFIES: this
//    // EFFECTS: replaces the rating of a specified book in the library
//    public void replaceRating(String title, int rating) {
//        for (Book b : library) {
//            if (title.equalsIgnoreCase(b.getTitle())) {
//                b.changeRating(rating);
//            }
//        }
//    }
//
    @Test
    public void testReplaceRating() {
        // test boundaries? idk if really needed tho

    }

    //    // REQUIRES: valid library book title, boolean (t/f)
//    // MODIFIES: this
//    // EFFECTS: replaces the read status of a specified book in the library
//    public void replaceReadStatus(String title, Boolean status) {
//        for (Book b : library) {
//            if (title.equalsIgnoreCase(b.getTitle())) {
//                b.changeReadStatus(status);
//            }
//        }
//    }
    @Test
    public void testReplaceReadStatus() {
        // need to test both F/T
    }


    //    // EFFECTS: returns an array list with the same books as the library object
//    public ArrayList<Book> returnArrayLib() {
//        ArrayList<Book> returnList = new ArrayList<>();
//        for (Book b : library) {
//            returnList.add(b);
//        }
//        return returnList;
//    }
    @Test
    public void testReturnArrayLib() {

    }
//    // REQUIRES: non-empty list of valid ratings (integer between [1, 5])
//    // EFFECTS: returns an array list with all books of certain ratings
//    public ArrayList<Book> searchRating(List<Integer> ratings) {
//        ArrayList<Book> searchResult = new ArrayList<>();
//        for (Book b : library) {
//            for (Integer r : ratings) {
//                if (b.getRating() == r) {
//                    searchResult.add(b);
//                }
//            }
//        }
//        return searchResult;
//    }

    @Test
    public void testSearchRatingSingle() {

    }

    @Test
    public void testSearchRatingMultiple() {

    }

    @Test
    public void testSearchRatingNothing() {

    }

    //
//    // REQUIRES: non-empty string
//    // EFFECTS: returns a list with all books fitting the title
//    public ArrayList<Book> searchLibByTitles(String title) {
//        ArrayList<Book> searchResult = new ArrayList<>();
//        for (Book b : library) {
//            if (title.equalsIgnoreCase(b.getTitle())) {
//                searchResult.add(b);
//            }
//        }
//        return searchResult;
//    }
//
    @Test
    public void testSearchTitlesSingle() {

    }

    @Test
    public void testSearchTitlesMultiple() {

    }

    @Test
    public void testSearchTitlesNothing() {

    }

    //    // REQUIRES: non-empty string
//    // EFFECTS: returns a list with all books fitting the genre
//    public ArrayList<Book> searchLibByGenre(String genre) {
//        ArrayList<Book> searchResult = new ArrayList<>();
//        for (Book b : library) {
//            if (genre.equalsIgnoreCase(b.getGenre())) {
//                searchResult.add(b);
//            }
//        }
//        return searchResult;
//    }
//
    @Test
    public void testSearchGenreSingle() {

    }

    @Test
    public void testSearchGenreMultiple() {

    }

    @Test
    public void testSearchGenreNothing() {

    }
//    // EFFECTS: returns a list with all unread books
//    public ArrayList<Book> searchUnreadBooks() {
//        ArrayList<Book> searchResult = new ArrayList<>();
//        for (Book b : library) {
//            if (!b.getReadStatus()) {
//                searchResult.add(b);
//            }
//        }
//        return searchResult;
//    }

    @Test
    public void testSearchUnreadSingle() {

    }

    @Test
    public void testSearchUnreadMultiple() {

    }

    @Test
    public void testSearchUnreadNothing() {

    }


    // 12 methods in total - do all 12 tomorrow!!
    // will prob take 2 hours

}
