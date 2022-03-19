package ui.panels;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {
    JPanel contentPane;

    public GraphicPanel() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0, 2));
    }

    public void addBook(BookPanel b) {
        contentPane.add(b);
    }

    public void removeBook(BookPanel b) {
        contentPane.remove(b);
    }


}
