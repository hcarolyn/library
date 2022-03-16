package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryAppMainScreen extends JPanel {
    private JButton addButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JButton saveButton;

    private void newButton(JButton button, String label, int x, int y, int sizeX, int sizeY) {
        button.setText(label);
        button.setLocation(x, y);
        button.setSize(sizeX, sizeY);
        add(button);
    }

    private void setUpAddButton() {
        addButton = new JButton();
        newButton(addButton, "Add Book", 25, 50, 50, 50);
    }

    private void setUpDeleteButton() {
        deleteButton = new JButton();
        newButton(deleteButton, "Delete Book", 100, 50, 50, 50);
    }

    private void setUpLoadButton() {
        loadButton = new JButton();
        newButton(loadButton, "Load Library", 170, 50, 50, 50);
    }

    private void setUpSaveButton() {
        saveButton = new JButton();
        newButton(saveButton, "Save Library", 250, 50, 50, 50);
    }




//        selected book - if you click on the button, then things will happen?,
//        for each book, i want to add a button,
//                put it in a different place on screen titles and authors there, books.get()



}
