package ui.panels;

import javax.swing.*;

// A class representing a panel with buttons
public class ButtonPanel extends JPanel {
    private JButton addButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton changeRatingButton;

    // EFFECTS: sets up and adds buttons on panel
    public ButtonPanel() {
        setUpAddButton();
        setUpDeleteButton();
        setUpLoadButton();
        setUpSaveButton();
        setUpRatingButton();
        setVisible(true);
    }

    // EFFECTS: returns change rating button
    public JButton getChangeRatingButton() {
        return changeRatingButton;
    }

    // EFFECTS: returns add book button
    public JButton getAddButton() {
        return addButton;
    }

    // EFFECTS: returns delete book button
    public JButton getDeleteButton() {
        return deleteButton;
    }

    // EFFECTS: returns load library button
    public JButton getLoadButton() {
        return loadButton;
    }

    // EFFECTS: returns save library button
    public JButton getSaveButton() {
        return saveButton;
    }

    // REQUIRES: valid inputs (button, string, 4 integers)
    // MODIFIES: this
    // EFFECTS: adds provided button at provided location with provided label
    private void newButton(JButton button, String label, int x, int y, int sizeX, int sizeY) {
        button.setText(label);
        button.setLocation(x, y);
        button.setSize(sizeX, sizeY);
        add(button);
    }

    // MODIFIES: this
    // EFFECTS: creates a new change rating button
    private void setUpRatingButton() {
        changeRatingButton = new JButton();
        newButton(changeRatingButton, "Change Rating", 800, 50, 125, 50);
    }

    // MODIFIES: this
    // EFFECTS: creates a new add book button
    private void setUpAddButton() {
        addButton = new JButton();
        newButton(addButton, "Add Book", 25, 50, 125, 50);
    }

    // MODIFIES: this
    // EFFECTS: creates a new delete book button
    private void setUpDeleteButton() {
        deleteButton = new JButton();
        newButton(deleteButton, "Delete Book", 200, 50, 125, 50);
    }

    // MODIFIES: this
    // EFFECTS: creates a new load library button
    private void setUpLoadButton() {
        loadButton = new JButton();
        newButton(loadButton, "Load Library", 350, 50, 125, 50);
    }

    // MODIFIES: this
    // EFFECTS: creates a new save library button
    private void setUpSaveButton() {
        saveButton = new JButton();
        newButton(saveButton, "Save Library", 500, 50, 125, 50);
    }

}
