package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

// some methods are based on this website:
// https://stackoverflow.com/questions/29873878/creating-a-simple-bar-chart-in-java-reads-data-and-outputs-bar-graph


// A class that represents a bar chart with rating statistics
public class ChartPanel extends JPanel {
    JPanel chartPanel;
    private Map<Color, Integer> bars = new LinkedHashMap<Color, Integer>();

    // EFFECTS: creates a panel with bar chart and explanatory labels
    public ChartPanel() {
        chartPanel = new JPanel();
        JLabel oneLabel = new JLabel("Gray = 1");
        JLabel twoLabel = new JLabel("Blue = 2");
        JLabel threeLabel = new JLabel("Orange = 3");
        JLabel fourLabel = new JLabel("Red = 4");
        JLabel fiveLabel = new JLabel("Pink = 5");

        this.add(new JLabel("Rating distributions"));
        this.add(oneLabel);
        this.add(twoLabel);
        this.add(threeLabel);
        this.add(fourLabel);
        this.add(fiveLabel);
        this.setVisible(true);
    }

    // REQUIRES: valid color, value >= 0
    // MODIFIES: this
    // EFFECTS: creates and adds a new bar on chart
    public void addBar(Color color, int value) {
        bars.put(color, value);
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: changes bar dimensions and color, places bar
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int max = 0;
        for (int i : bars.values()) {
            if (max <= i) {
                max = i;
            }
        }
        int x = 1;

        int width = (getWidth() / bars.size()) - 2;

        for (Color color : bars.keySet()) {
            int value = bars.get(color);
            int height = (int) ((getHeight() - 5) * ((double) value / max));
            g.setColor(color);
            g.fillRect(x, getHeight() - height, width, height);
            x += (width + 10);
        }
    }

//    // EFFECTS: returns dimension of bars
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(bars.size() * 20 + 5, 50);
//    }

    // MODIFIES: this
    // EFFECTS: deletes bar on chart
    public void deleteBars() {
        bars.clear();
    }

}
