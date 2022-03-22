package ui.panels;

import javax.swing.*;

public class ButtonPanel extends JPanel {
    private JButton addButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton editButton;

    public ButtonPanel() {
        setUpAddButton();
        setUpDeleteButton();
        setUpLoadButton();
        setUpSaveButton();
        setUpEditButton();
        setVisible(true);
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    private void newButton(JButton button, String label, int x, int y, int sizeX, int sizeY) {
        button.setText(label);
        button.setLocation(x, y);
        button.setSize(sizeX, sizeY);
        add(button);
    }

    private void setUpEditButton() {
        editButton = new JButton();
        newButton(editButton, "Edit Book", 800, 50, 125, 50);
    }

    private void setUpAddButton() {
        addButton = new JButton();
        newButton(addButton, "Add Book", 25, 50, 125, 50);
    }

    private void setUpDeleteButton() {
        deleteButton = new JButton();
        newButton(deleteButton, "Delete Book", 200, 50, 125, 50);
    }

    private void setUpLoadButton() {
        loadButton = new JButton();
        newButton(loadButton, "Load Library", 350, 50, 125, 50);
    }

    private void setUpSaveButton() {
        saveButton = new JButton();
        newButton(saveButton, "Save Library", 500, 50, 125, 50);
    }


//        selected book - if you click on the button, then things will happen?,
//        for each book, i want to add a button,
//                put it in a different place on screen titles and authors there, books.get()



}
