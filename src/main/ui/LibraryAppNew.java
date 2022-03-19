package ui;

import exceptions.BookNotAddedException;
import exceptions.BookNotDeletedException;
import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryAppNew extends JFrame {
    private static final String JSON_STORE = "./data/library.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1500;

    private Library lib;
    private ArrayList<Book> books;

    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton addButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JButton saveButton;

    private LibraryMainPanel mainPanel;
    private AddBookPanel addBookAB;
    private DeleteBookPanel deleteBookAB;
    private GraphicPanel graphicPanel;


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
        this.mainPanel = new LibraryMainPanel();
        this.mainPanel.setBounds(0, 0, WIDTH, 1500);
        this.graphicPanel = new GraphicPanel();
        this.graphicPanel.setBounds(0, 125, WIDTH, 1375);
        this.addBookAB = new AddBookPanel();
        this.addBookAB.setBounds(0, 125, WIDTH, 1375);
        this.deleteBookAB = new DeleteBookPanel();
        this.deleteBookAB.setBounds(0, 125, WIDTH, 1375);

        setUpButtons();
    }

    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        this.add(addBookAB);
        this.add(deleteBookAB);
        this.add(mainPanel);
        this.add(graphicPanel);
    }

    public void setUpButtons() {
        addButton = mainPanel.getAddButton();
        deleteButton = mainPanel.getDeleteButton();
        loadButton = mainPanel.getLoadButton();
        saveButton = mainPanel.getSaveButton();

        addDeleteListener();
        saveLoadListener();
    }

    public void addDeleteListener() {
//        addButton.addActionListener(e -> {
//            try {
//                addButtonPressed();
//            } catch (BookNotAddedException n) {
//                /// ADD SOMETHING HERE
//            }
//        });
//
        addButton.addActionListener(e -> {
            addButtonPressed();
        });

        deleteButton.addActionListener(e -> {
            try {
                deleteButtonPressed();
            } catch (BookNotDeletedException n) {
                // ADD SOMETHING HERE
            }
        });
    }

    public void saveLoadListener() {
        loadButton.addActionListener(e -> loadButtonPressed());

        saveButton.addActionListener(e -> saveButtonPressed());
    }

    //throws BookNotAddedException
    public void addButtonPressed() {
        int size = lib.getLibSize();
        addBookAB.changeVisibility(true);
        Book toAddBook = addBookAB.getBook();
        lib.addBook(toAddBook);
//        if (lib.getLibSize() == size) {
//            throw new BookNotAddedException();
//        }
        addBookAB.changeVisibility(false);
        addBook(toAddBook);
        // if this doesn't change size, throw new exception?
    }

    public void deleteButtonPressed() throws BookNotDeletedException {
        int size = lib.getLibSize();
        deleteBookAB.changeVisibility(true);
        Book toDeleteBook = lib.searchTitle(deleteBookAB.getBookTitle()).get(0);
        lib.removeBook(deleteBookAB.getBookTitle());
//        if (lib.getLibSize() == size) {
//            throw new BookNotDeletedException();
//        }
        deleteBookAB.changeVisibility(false);
        deleteBook(toDeleteBook);
    }

    public void saveButtonPressed() {
        try {
            jsonWriter.open();
            jsonWriter.write(lib);
            jsonWriter.close();
            System.out.println("Saved " + lib.getLibName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
        // UPDATE MAIN GRAPHICS TO DISPLAY SOME KIND OF MESSAGE HERE
    }

    public void loadButtonPressed() {
        try {
            lib = jsonReader.read();
            System.out.println("Loaded " + lib.getLibName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        BookPanel bookPanel;

        for (Book b : lib.getArrayLib()) {
            bookPanel = new BookPanel(b);
            graphicPanel.add(bookPanel);
        }
    }

    public void addBook(Book b) {
        BookPanel book = new BookPanel(b);
        graphicPanel.addBook(book);
    }

    public void deleteBook(Book b) {
        BookPanel book = new BookPanel(b);
        graphicPanel.removeBook(book);
    }


    // add a search panel, add book panel stuff, figure out display message panel

    // when you click delete, search button comes up, can delete

    // add the book stuff (for each one, i want to add an action listener
    // for (Book b : books)

}
