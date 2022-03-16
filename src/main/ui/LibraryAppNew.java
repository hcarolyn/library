package ui;

import exceptions.BookNotAddedException;
import exceptions.BookNotDeletedException;
import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryAppNew extends JFrame {
    private static final String JSON_STORE = "./data/library.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private Library lib;
    private ArrayList<Book> books;

    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton addButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JButton saveButton;

    private AddBookPanel addBookAB;
    private DeleteBookPanel deleteBookAB;


    public LibraryAppNew() {
        super("Library App");
        initializeFields();
        initializeGraphics();

    }

    public void initializeFields() {
        lib = new Library("Library App");
        books = lib.getArrayLib();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.addBookAB = new AddBookPanel();
        this.deleteBookAB = new DeleteBookPanel();

    }

    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        this.add(addBookAB);
        this.add(deleteBookAB);
    }

    public void addListener() throws BookNotAddedException {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addButtonPressed();
                } catch (BookNotAddedException n) {
                    /// ADD SOMETHING HERE
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteButtonPressed();
                } catch (BookNotDeletedException n) {
                    // ADD SOMETHING HERE
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    public void addButtonPressed() throws BookNotAddedException {
        int size = lib.getLibSize();
        addBookAB.changeVisibility(true);
        Book toAddBook = addBookAB.getBook();
        lib.addBook(toAddBook);
        if (lib.getLibSize() == size) {
            throw new BookNotAddedException();
        }
        addBookAB.changeVisibility(false);
        // NEED TO UPDATE MAIN GRAPHICS HERE
        // if this doesn't change size, throw new exception?
    }

    public void deleteButtonPressed() throws BookNotDeletedException {
        int size = lib.getLibSize();
        deleteBookAB.changeVisibility(true);
        lib.removeBook(deleteBookAB.getBookTitle());
        if (lib.getLibSize() == size) {
            throw new BookNotDeletedException();
        }
        deleteBookAB.changeVisibility(false);
        // UPDATE MAIN GRAPHICS TO DELETE BOOK HERE
    }

    public void saveButtonPressed() {

    }

    public void loadButtonPressed() {

    }

    // add an add book panel, delete book popup, and
    // when you click delete, search button comes up, can delete

    // add the book stuff (for each one, i want to add an action listener
    // for (Book b : books)
    // add button and display


//    public class LabelChanger extends JFrame implements ActionListener {
//        private JLabel label;
//        private JTextField field;
//
//        public LabelChanger() {
//            super("The title");
//            setDefaultCloseOperation(EXIT_ON_CLOSE);
//            setPreferredSize(new Dimension(400, 90));
//            ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
//            setLayout(new FlowLayout());
//            JButton btn = new JButton("Change");
//            btn.setActionCommand("myButton");
//            btn.addActionListener(this); // Sets "this" object as an action listener for btn
//            // so that when the btn is clicked,
//            // this.actionPerformed(ActionEvent e) will be called.
//            // You could also set a different object, if you wanted
//            // a different object to respond to the button click
//            label = new JLabel("flag");
//            field = new JTextField(5);
//            add(field);
//            add(btn);
//            add(label);
//            pack();
//            setLocationRelativeTo(null);
//            setVisible(true);
//            setResizable(false);
//        }
//
//        //This is the method that is called when the the JButton btn is clicked
//        public void actionPerformed(ActionEvent e) {
//            if (e.getActionCommand().equals("myButton")) {
//                label.setText(field.getText());
//            }
//        }
//
//        public static void main(String[] args) {
//            new LabelChanger();
//        }
//    }


}
