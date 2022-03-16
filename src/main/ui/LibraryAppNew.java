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
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private LibraryMainPanel mainPanel;
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
        this.mainPanel = new LibraryMainPanel();
        this.addBookAB = new AddBookPanel();
        this.deleteBookAB = new DeleteBookPanel();

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
    }

    public void saveLoadListener() {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadButtonPressed();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonPressed();
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
        // UPDATE MORE MAIN GRAPHICS (also can i add sout messages?)
    }

    // add a search panel, add book panel stuff, figure out display message panel

    // when you click delete, search button comes up, can delete

    // add the book stuff (for each one, i want to add an action listener
    // for (Book b : books)
    // add button and display


}
