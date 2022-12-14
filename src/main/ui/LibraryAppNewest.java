package ui;

import ui.exceptions.BookNotAddedException;
import exceptions.BookNotFoundException;
import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// A class with the newest library app
public class LibraryAppNewest extends JFrame {
    private static final String JSON_STORE = "./data/library.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1500;

    private Library lib;

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

    // EFFECTS: sets up the library app
    public LibraryAppNewest() {
        super("Library App");

        initializeFields();
        initializeGraphics();
    }

    // EFFECTS: returns library
    public Library getLibrary() {
        return lib;
    }


    // MODIFIES: this
    // EFFECT: initializes fields
    public void initializeFields() {
        lib = new Library("Library App");
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

    // MODIFIES: this
    // EFFECTS: sets up graphics
    public void initializeGraphics() {
        setLayout(new BorderLayout());
        addCloseLog();

        setLocationRelativeTo(null);
        setVisible(true);

        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(booksPanel, BorderLayout.CENTER);
        this.add(filterPanel, BorderLayout.WEST);
        this.add(chartPanel, BorderLayout.EAST);
    }

    // EFFECTS: prints out log when window closes
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void addCloseLog() {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                lib.printLog();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: sets up chart with rating distribution
    public void setUpChart() {
        chartPanel.addBar(Color.gray, numberRatings(1));
        chartPanel.addBar(Color.blue, numberRatings(2));
        chartPanel.addBar(Color.orange, numberRatings(3));
        chartPanel.addBar(Color.red, numberRatings(4));
        chartPanel.addBar(Color.pink, numberRatings(5));
    }

    // EFFECTS: returns the number of books with a rating
    public int numberRatings(int rating) {
        int returnRating = 0;

        for (Book b : lib.getArrayLib()) {
            if (b.getRating() == rating) {
                returnRating++;
            }
        }
        return returnRating;
    }

    // MODIFIES: this
    // EFFECTS: clear chart
    public void clearChart() {
        chartPanel.deleteBars();
    }

    // MODIFIES: this
    // EFFECTS: sets up all buttons
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

    // MODIFIES: this
    // EFFECTS: adds listeners to all buttons
    public void addListener() {
        addButton.addActionListener(e -> addButtonPressed());
        deleteButton.addActionListener(e -> deleteButtonPressed());
        loadButton.addActionListener(e -> loadButtonPressed());
        saveButton.addActionListener(e -> saveButtonPressed());
        changeRatingButton.addActionListener(e -> changeRatingButtonPressed());
        searchButton.addActionListener(e -> searchButtonPressed());
        resetButton.addActionListener(e -> resetButtonPressed());
    }

    // EFFECTS: sets up the rating change process
    public void changeRatingButtonPressed() {
        int index = booksPanel.getSelected();
        if (index < 0) {
            return;
        }
        editPopup(index);
    }

    // MODIFIES: this
    // EFFECTS: changes the rating of a provided book to a provided number
    public void editPopup(int index) {
        int rating = booksPanel.changeRating(index);
        if (rating > 0) {
            try {
                lib.searchIndex(index).changeRating(rating);
                clearChart();
                setUpChart();
            } catch (BookNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid index, try again",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new book to the library / book panel
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

    // MODIFIES: this
    // EFFECTS: removes book from book panel
    public void deleteButtonPressed() {
        int index = booksPanel.deleteBook();

        if (index >= 0) {
            try {
                lib.removeBook(lib.searchIndex(index).getTitle());
            } catch (BookNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                        "Invalid index, try again",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: saves library
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

    // MODIFIES: this
    // EFFECTS: reloads library
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

    // MODIFIES: this
    // EFFECTS: filters book based on user inputs
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

    // MODIFIES: this
    // EFFECTS: resets the filter
    public void resetButtonPressed() {
        booksPanel.noFilterBooks();
    }

}

