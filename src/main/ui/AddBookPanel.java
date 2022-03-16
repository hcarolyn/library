package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Book;

public class AddBookPanel extends JPanel implements ActionListener {
    private JTextField title;
    private JTextField datePublished;
    private JTextField genre;
    private JTextField author;
    private Book book;

    public Book getBook() {
        return book;
    }

    public void changeVisibility(Boolean bool) {
        setVisible(bool);
    }

    public AddBookPanel() {
        super();
        setLayout(new FlowLayout());
        JButton btn = new JButton("Add Book");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        title = new JTextField("Title", 7);
        genre = new JTextField("Genre", 5);
        author = new JTextField("Author", 7);
        datePublished = new JTextField("Publishing year", 3);
        add(title);
        add(genre);
        add(author);
        add(datePublished);
        add(btn);
        setVisible(false);
    }

    @Override
    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            String title = this.title.getText();
            String genre = this.genre.getText();
            String author = this.author.getText();
            int datePublished = Integer.parseInt(this.datePublished.getText());
            this.book = new Book(title, genre, author, datePublished);
        }
    }
}
