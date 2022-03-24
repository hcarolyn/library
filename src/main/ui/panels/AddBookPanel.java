package ui.panels;

import javax.swing.*;

// A class that represents information for an adding book panel
public class AddBookPanel {
    JTextField title = new JTextField();
    JTextField author = new JTextField();
    JTextField year = new JTextField();
    JTextField genre = new JTextField();
    JTextField rating = new JTextField();

    Object[] bookOptions = {
            "Title: ", title,
            "Author: ", author,
            "Year: ", year,
            "Genre: ", genre,
    };

    // EFFECTS: returns book inputs for user
    public Object getBookOptions() {
        return bookOptions;
    }

    // EFFECTS: returns user title input
    public String getTitle() {
        return title.getText();
    }

    // EFFECTS: returns user author input
    public String getAuthor() {
        return author.getText();
    }

    // EFFECTS: returns user year input
    public String getYear() {
        return year.getText();
    }

    // EFFECTS: returns user genre input
    public String getGenre() {
        return genre.getText();
    }

    // EFFECTS: returns user rating input
    public String getRating() {
        return rating.getText();
    }

}
