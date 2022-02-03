package model;

// Represents a book with a title, a genre, an author, a published date, and a "read" status
public class Book {
    private String title;
    private String genre;
    private String author;
    private int rating; // from 1 to 5, 6 represents not having a rating yet
    private int datePublished;
    private boolean readStatus;

    // REQUIRES: title, genre, and author are not empty strings
    //           datePublished is a valid year (2022>= datePublished >= 1500)
    //           readStatus is either true or false
    // EFFECTS: constructs a book with a title, genre, author, publishing year, no rating, and non-read status
    public Book(String title, String genre, String author, int datePublished, boolean readStatus) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.rating = 6;
        this.datePublished = datePublished;
        this.readStatus = readStatus;
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


}
