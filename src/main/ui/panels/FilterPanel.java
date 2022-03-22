package ui.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FilterPanel extends JPanel {
    private JTextField searchInput;
    private JComboBox<String> searchType;
    private JButton searchButton;
    private JButton resetButton;

    public FilterPanel() {
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(0, 150, 200, 500);

        searchType = new JComboBox<String>(new String[] {
                "Title", "Genre", "Rating"});
        this.add(searchType);

        searchInput = new JTextField();
        this.add(searchInput);

        searchButton = new JButton();
        searchButton.setText("Search Books");
        searchButton.setLocation(10, 200);
        searchButton.setSize(125, 50);
        this.add(searchButton);

        resetButton = new JButton();
        resetButton.setText("Reset Search");
        resetButton.setLocation(0, 250);
        resetButton.setSize(125, 50);
        this.add(resetButton);

        TitledBorder border = new TitledBorder("Search");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        this.setBorder(border);
    }

    public String getSearchType() throws NullPointerException {
        return searchType.getSelectedItem().toString();
    }

    public String getSearchInput() {
        return searchInput.toString();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

}
