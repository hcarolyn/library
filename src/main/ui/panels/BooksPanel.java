package ui.panels;

import exceptions.BookNotAddedException;
import model.Book;

import javax.swing.*;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.beans.Visibility;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class BooksPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable panel;
    private JComboBox optionList;

    public final String[] columnNames = {
            "Title", "Author", "Year", "Genre", "Rating (1 - 5)"
    };


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

    private int setUp() {
        Object[] options = {"Cancel", "One", "Two", "Three", "Four", "Five"};
        optionList = new JComboBox(options);
        int result = JOptionPane.showConfirmDialog(null, optionList,
                "Change Rating", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public int changeRating(int selected) {
        int result = setUp();
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

    public JTable getPanel() {
        return panel;
    }

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

    public int panelSize() {
        return tableModel.getRowCount();
    }

    public int deleteBook() {
        int selected = panel.getSelectedRow();
        if (selected >= 0) {
            tableModel.removeRow(selected);
        }

        return selected;
    }

    public int getSelected() {
        int selected = panel.getSelectedRow();
        if (selected < 0) {
            return -1;
        }
        return selected;
    }


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

    public void noFilterBooks() {
        this.panel.setRowSorter(null);
    }

}
