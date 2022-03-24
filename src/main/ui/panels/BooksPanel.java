package ui.panels;

import ui.exceptions.BookNotAddedException;
import model.Book;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

// A class representing a panel of books (in the form of a table)
public class BooksPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable panel;
    private JComboBox optionList;

    public final String[] columnNames = {
            "Title", "Author", "Year", "Genre", "Rating (1 - 5)"
    };


    // EFFECTS: creates a new, non-editable table of books and adds it to a panel
    public BooksPanel() {
        this.tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        panel = new JTable(tableModel);
        panel.getTableHeader().setFont(new Font("Arial", Font.ITALIC, 20));
        panel.getTableHeader().setBackground(Color.white);
        panel.setRowHeight(24);
        panel.setFont(new Font("Arial", Font.PLAIN, 15));
        add(new JScrollPane(panel));
        setVisible(true);
        panel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // MODIFIES: this
    // EFFECTS: adds an option box with rating options and returns user selection
    private int setUpRating() {
        Object[] options = {"Cancel", "One", "Two", "Three", "Four", "Five"};
        optionList = new JComboBox(options);
        int result = JOptionPane.showConfirmDialog(null, optionList,
                "Change Rating", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    // EFFECTS: returns user rating for a book
    public int changeRating(int selected) {
        int result = setUpRating();
        if (result == JOptionPane.OK_OPTION) {
            switch (optionList.getSelectedItem().toString()) {
                case "Cancel":
                    return -1;
                case "One":
                    tableModel.setValueAt("1", selected, 4);
                    return 1;
                case "Two":
                    tableModel.setValueAt("2", selected, 4);
                    return 2;
                case "Three":
                    tableModel.setValueAt("3", selected, 4);
                    return 3;
                case "Four":
                    tableModel.setValueAt("4", selected, 4);
                    return 4;
                case "Five":
                    tableModel.setValueAt("5", selected, 4);
                    return 5;
            }
        }
        return -1;
    }

    // REQUIRES: book entry (ex: valid string for name, author, genre)
    // MODIFIES: this
    // EFFECTS: if valid book, adds book to table
    public void addBook(String name, String author, String year, String genre) throws BookNotAddedException {
        if (0 < Integer.parseInt(year) && Integer.parseInt(year) < 2022) {
            Object[] tempObject = {
                    name, author, year, genre, "No rating"
            };

            tableModel.addRow(tempObject);
        } else {
            throw new BookNotAddedException();
        }
    }

    // EFFECTS: returns number of books in the table
    public int panelSize() {
        return tableModel.getRowCount();
    }

    // MODIFIES: this
    // EFFECTS: if valid entry, deletes selected row/book
    public int deleteBook() {
        int selected = panel.getSelectedRow();
        if (selected >= 0) {
            tableModel.removeRow(selected);
        }
        return selected;
    }

    // EFFECTS: returns row that user selected, returns -1 if invalid row
    public int getSelected() {
        int selected = panel.getSelectedRow();
        if (selected < 0) {
            return -1;
        }
        return selected;
    }

    // REQUIRES: valid array list of books
    // MODIFIES: this
    // EFFECTS: adds a row in table for every book in provided array
    public void loadBooks(ArrayList<Book> books) {
        int i = 0;
        for (Book b : books) {
            String year = Integer.toString(b.getDatePublished());
            String rating;

            if (b.getRating() == 0) {
                rating = "No rating";
            } else {
                rating = Integer.toString(b.getRating());
            }

            Object[] tempObject = {
                    b.getTitle(), b.getAuthor(), year, b.getGenre(), rating
            };

            tableModel.addRow(tempObject);
            b.setIndex(i);
            i++;
        }
    }

    // REQUIRES: valid search input and search type
    // MODIFIES: this
    // EFFECTS: filters the books in the table based on inputs and hides non-valid books
    public void filterBooks(String searchInput, String searchType) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.tableModel);
        panel.setRowSorter(sorter);

        if (searchType.equals("Title")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchInput, 0));
        } else if (searchType.equals("Genre")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchInput, 3));
        } else if (searchType.equals("Rating")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchInput, 4));
        }

    }

    // MODIFIES: this
    // EFFECTS: takes away the filter in the table, displays all books in library
    public void noFilterBooks() {
        this.panel.setRowSorter(null);
    }

}
