package ui;

import exceptions.BookNotAddedException;
import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryAppNewest extends JFrame {
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
    private JButton searchButton;
    private JButton changeRatingButton;
    private JButton resetButton;

    private BooksPanel booksPanel;
    private ButtonPanel buttonPanel;
    private AddBookPanel addBookPanel;
    private FilterPanel filterPanel;
    private ChartPanel chartPanel;

    public LibraryAppNewest() {
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

        booksPanel = new BooksPanel();
        buttonPanel = new ButtonPanel();
        filterPanel = new FilterPanel();
        chartPanel = new ChartPanel();

        this.buttonPanel.setBounds(0, 0, 750, 100);
        this.booksPanel.setBounds(0, 150, WIDTH, HEIGHT);

        setUpButtons();
        setUpChart();
    }

    public void initializeGraphics() {
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(booksPanel, BorderLayout.CENTER);
        this.add(filterPanel, BorderLayout.WEST);
        this.add(chartPanel, BorderLayout.EAST);
    }

    public void setUpChart() {
        for (int i = 0; i < 6; i++) {
            chartPanel.addBar(Integer.toString(i), numberRatings(i));
        }
    }

    public int numberRatings(int rating) {
        int returnRating = 0;

        for (Book b : lib.getArrayLib()) {
            if (b.getRating() == rating) {
                returnRating++;
            }
        }
        return returnRating;
    }

    public void clearChart() {
        chartPanel.deleteBars();
    }

    public void setUpButtons() {
        addButton = buttonPanel.getAddButton();
        deleteButton = buttonPanel.getDeleteButton();
        loadButton = buttonPanel.getLoadButton();
        saveButton = buttonPanel.getSaveButton();
        changeRatingButton = buttonPanel.getChangeRatingButton();
        searchButton = filterPanel.getSearchButton();
        resetButton = filterPanel.getResetButton();

        addListener();
    }

    public void addListener() {
        addButton.addActionListener(e -> addButtonPressed());
        deleteButton.addActionListener(e -> deleteButtonPressed());
        loadButton.addActionListener(e -> loadButtonPressed());
        saveButton.addActionListener(e -> saveButtonPressed());
        changeRatingButton.addActionListener(e -> changeRatingButtonPressed());
        searchButton.addActionListener(e -> searchButtonPressed());
        resetButton.addActionListener(e -> resetButtonPressed());
    }

    public void changeRatingButtonPressed() {
        int index = booksPanel.getSelected();
        if (index < 0) {
            return;
        }
        editPopup(index);
    }

    public void editPopup(int index) {
        int rating = booksPanel.changeRating(index);
        if (rating > 0) {
            lib.searchIndex(index).changeRating(rating);
            clearChart();
            setUpChart();
        }
    }

    public void addButtonPressed() {
        addBookPanel = new AddBookPanel();

        int result = JOptionPane.showConfirmDialog(null, addBookPanel.getBookOptions(),
                "Add Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                booksPanel.addBook(addBookPanel.getTitle(), addBookPanel.getAuthor(),
                        addBookPanel.getYear(), addBookPanel.getGenre());

                Book tempBook = new Book(addBookPanel.getTitle(), addBookPanel.getGenre(), addBookPanel.getAuthor(),
                        Integer.parseInt(addBookPanel.getYear()));
                tempBook.setIndex(booksPanel.panelSize() - 1);

                lib.addBook(tempBook);
            } catch (BookNotAddedException e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid year",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid inputs",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void deleteButtonPressed() {
        int index = booksPanel.deleteBook();

        if (index >= 0) {
            lib.removeBook(lib.searchIndex(index).getTitle());
        }
    }

    public void saveButtonPressed() {
        try {
            jsonWriter.open();
            jsonWriter.write(lib);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this,
                    "Saved " + lib.getLibName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to save to file: " + JSON_STORE,
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void loadButtonPressed() {
        try {
            lib = jsonReader.read();
            booksPanel.loadBooks(lib.getArrayLib());
            clearChart();
            setUpChart();
            JOptionPane.showMessageDialog(this,
                    "Loaded " + lib.getLibName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to read from file: " + JSON_STORE,
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void searchButtonPressed() {
        try {
            booksPanel.filterBooks(filterPanel.getSearchInput(), filterPanel.getSearchType());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this,
                    "No type selected",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void resetButtonPressed() {
        booksPanel.noFilterBooks();
    }




}

