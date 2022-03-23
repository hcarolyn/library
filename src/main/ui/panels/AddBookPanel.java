package ui.panels;

import javax.swing.*;

public class AddBookPanel {
    JPanel addBookPanel;
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

    public AddBookPanel() {
        addBookPanel = new JPanel();
    }

    public Object getBookOptions() {
        return bookOptions;
    }

    public String getTitle() {
        return title.getText();
    }

    public String getAuthor() {
        return author.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public String getGenre() {
        return genre.getText();
    }

    public String getRating() {
        return rating.getText();
    }

}
