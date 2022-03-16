package ui;

import model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookPanel extends JPanel implements ActionListener {
    private JTextField title;
    private String bookTitle;
    private model.Book book;

    public Book getBook() {
        return book;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void changeVisibility(Boolean bool) {
        setVisible(bool);
    }

    public DeleteBookPanel() {
        super();
        setLayout(new FlowLayout());
        JButton btn = new JButton("Delete Book");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        title = new JTextField("Title", 7);
        add(title);
        add(btn);
        setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            this.bookTitle = this.title.getText();
        }
    }
}
