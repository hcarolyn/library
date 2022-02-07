package model;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class Library {
    ArrayList<Book> library;
    List<BookInSeries> bookInSeries;

    // EFFECTS: creates a new library containing books
    public Library() {
        library = new ArrayList<Book>();
    }

    // EFFECTS: returns size of the library
    public int getLibSize() {
        return library.size();
    }

    // REQUIRES: valid book
    // MODIFIES: this
    // EFFECTS: adds a book to the library
    public void addBook(Book book) {
        this.library.add(book);
    }

//    // MODIFIES: this
//    // EFFECTS: if book exists in library, returns true and removes book. if not, returns false
//    public Boolean removeBook(Book book) {
//        if (this.library.contains(book)) {
//            this.library.remove(book);
//            return true;
//        }
//        return false;
//    }

    // EFFECTS: if this book exists in library, returns true. if not, returns false
//    public Boolean containsBook(Book book) {
//        if (library.contains(book)) {
//            return true;
//        }
//        return false;
//    }

    // MODIFIES: this
    // EFFECTS: if book exists in library, returns true and removes book. if not, returns false
    public Boolean removeBook(String title) {
        for (Book b : library) {
            if (title.equalsIgnoreCase(b.getTitle())) {
                library.remove(b);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: if this book exists in library, returns true. if not, returns false
    public Boolean containsBook(String title) {
        for (Book b : library) {
            if (title.equalsIgnoreCase(b.getTitle())) {
                return true;
            }
        }
        return false;
    }


    // EFFECTS: returns the information of all books in the provided library
    public String libBookInfo(ArrayList<Book> lib) {
        String bookTitles = " ";
        for (Book b : lib) {
            bookTitles = bookTitles + ", " + b.bookInfo(b);
        }
        return trim(bookTitles);
    }

    // MODIFIES: this
    //?????????????
    public void replaceRating(String title, int rating) {
        for (Book b : library) {
            if (title.equalsIgnoreCase(b.getTitle())) {
                b.changeRating(rating);
            }
        }
    }

    // MODIFIES: this
    //?????????????
    public void replaceReadStatus(String title, Boolean status) {
        for (Book b : library) {
            if (title.equalsIgnoreCase(b.getTitle())) {
                b.changeReadStatus(status);
            }
        }
    }

    // MODIFIES: this
    //?????????????
    public ArrayList<Book> returnArrayLib() {
        ArrayList<Book> returnList = new ArrayList<>();
        for (Book b : library) {
            returnList.add(b);
        }
        return returnList;
    }


    // EFFECTS: returns a list with all books of certain ratings
    public ArrayList<Book> searchRating(List<Integer> ratings) {
        ArrayList<Book> searchResult = new ArrayList<>();
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
            if (title.equalsIgnoreCase(b.getTitle())) {
                searchResult.add(b);
            }
        }
        return searchResult;
    }

    // EFFECTS: returns a list with all books fitting the genre
    public ArrayList<Book> searchLibByGenre(String genre) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book b : library) {
            if (genre.equalsIgnoreCase(b.getGenre())) {
                searchResult.add(b);
            }
        }
        return searchResult;
    }

    // EFFECTS: returns a list with all unread books
    public ArrayList<Book> searchUnreadBooks() {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book b : library) {
            if (!b.getReadStatus()) {
                searchResult.add(b);
            }
        }
        return searchResult;
    }


}
