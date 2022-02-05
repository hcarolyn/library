package model;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class Library {
    List<Book> library;
    List<Series> seriesList;

    // EFFECTS: creates a new library containing books
    public Library() {
        this.library = new ArrayList<Book>();
    }

    public int getLibSize() {
        return library.size();
    }

    // MODIFIES: this
    // EFFECTS: adds a book to the library
    public void addBook(Book book) {
        this.library.add(book);
    }

    // MODIFIES: this
    // EFFECTS: if book exists in library, returns true and removes book. if not, returns false
    public Boolean removeBook(Book book) {
        if (this.library.contains(book)) {
            this.library.remove(book);
            return true;
        }
        return false;
    }

    // EFFECTS: returns the information of all books in the provided library
    public String libBookInfo(ArrayList<Book> library) {
        String bookTitles = " ";
        for (Book b : library) {
            bookTitles = bookTitles + ", " + b.bookInfo(b);
        }
        return trim(bookTitles);
    }


    // EFFECTS: returns a list with all books of certain ratings
    public ArrayList<Book> searchRating(List<Integer> ratings) {
        ArrayList<Book> searchResult = new ArrayList<Book>();
        for (Book b : library) {
            for (Integer r : ratings) {
                if (b.getRating() == r) {
                    searchResult.add(b);
                }
            }
        }
        return searchResult;
    }

    // EFFECTS: returns a list with all books fitting the title
    public ArrayList<Book> searchLibByTitles(String title) {
        ArrayList<Book> searchResult = new ArrayList<Book>();
        for (Book b : library) {
            if (title == b.getTitle()) {
                searchResult.add(b);
            }
        }
        return searchResult;
    }

    // EFFECTS: returns a list with all books fitting the genre
    public ArrayList<Book> searchLibByGenre(String genre) {
        ArrayList<Book> searchResult = new ArrayList<Book>();
        for (Book b : library) {
            if (genre == b.getGenre()) {
                searchResult.add(b);
            }
        }
        return searchResult;
    }

    // EFFECTS: returns a list with all unread books
    public ArrayList<Book> searchUnreadBooks() {
        ArrayList<Book> searchResult = new ArrayList<Book>();
        for (Book b : library) {
            if (!b.getReadStatus()) {
                searchResult.add(b);
            }
        }
        return searchResult;
    }


}
