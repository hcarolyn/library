package model;

// Represents a book with a title, a genre, an author, a published date, and a "read" status
public class Book {
    private String title;
    private String genre;
    private String author;
    private int rating; // from 1 to 5, with 0 representing no rating
    private int datePublished;
    private boolean readStatus;

    // REQUIRES: title, genre, and author are not empty strings
    //           datePublished is a valid year (2022 >= datePublished >= 1 AD)
    // EFFECTS: constructs a book with a title, genre, author, publishing year, no rating, and non-read status (false)
    public Book(String title, String genre, String author, int datePublished) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.rating = 0;
        this.datePublished = datePublished;
        this.readStatus = false;
    }

    // EFFECTS: gets title
    public String getTitle() {
        return title;
    }

    // EFFECTS: gets genre
    public String getGenre() {
        return genre;
    }

    // EFFECTS: gets author
    public String getAuthor() {
        return author;
    }

    // EFFECTS: gets rating
    public int getRating() {
        return rating;
    }

    // EFFECTS: gets year published
    public int getDatePublished() {
        return datePublished;
    }

    // EFFECTS: gets read status
    public boolean getReadStatus() {
        return readStatus;
    }

    // REQUIRES: integer between [1, 5]
    // MODIFIED: this
    // EFFECTS: changes the rating of the book
    public void changeRating(int rating) {
        this.rating = rating;
    }

    // REQUIRES: boolean
    // MODIFIED: this
    // EFFECTS: changes the read status of the book
    public void changeReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public String bookInfo(Book book) {
        String bookInfo = " ";
        bookInfo = book.title + " by " + book.getAuthor() + " about " + book.getGenre();
        return bookInfo;
    }

}
