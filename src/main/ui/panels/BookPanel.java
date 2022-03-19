package ui.panels;

import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BookPanel extends JPanel {
    // add change, delete, all that book stuff here too?
    private Book book;

    private JLabel title;
    private JLabel author;
    private JLabel genre;
    private JLabel datePublished;

    public Book getBook() {
        return book;
    }

    public String getTitle() {
        return title.getText();
    }

    public String getAuthor() {
        return author.getText();
    }

    public String getGenre() {
        return genre.getText();
    }

    public void changeVisibility(Boolean bool) {
        setVisible(bool);
    }


    public BookPanel(Book b) {
        super();
        setLayout(new FlowLayout());
        title = new JLabel(b.getTitle());
        author = new JLabel(b.getAuthor());
        genre = new JLabel(b.getGenre());
        datePublished = new JLabel(Integer.toString(b.getDatePublished()));

        add(title);
        add(author);
        setVisible(true);
    }


}
