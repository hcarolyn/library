package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

// some methods are based on this website:
// http://helpdesk.objects.com.au/java/how-to-display-bar-chart-using-swing

public class ChartPanel extends JPanel {
    JPanel chartPanel;
    private static final Color BAR_COLOUR = Color.blue;
    private int[] values;
    private Map<String, Integer> bars = new LinkedHashMap<String, Integer>();

    public ChartPanel() {
        chartPanel = new JPanel();
    }

    public void addBar(String rating, int value) {
        bars.put(rating, value);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int max = 0;
        for (int i : bars.values()) {
            max = Math.max(max, i);
        }
        int x = 1;
        int width = (getWidth() / bars.size()) - 2;

        for (String rating : bars.keySet()) {
            int value = bars.get(rating);
            int height = (int) ((getHeight() - 5) * ((double) value / max));
            g.setColor(BAR_COLOUR);
            g.fillRect(x, getHeight() - height, width, height);
            x += (width + 3);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 14 + 2, 50);
    }

    public void deleteBars() {
        bars.clear();
    }



//    private static final int SCALE = 250;
//    private static final int WIDTH = 40;
//
//    private int total;
//    private int[] value;
//
//    private String[] labels = {
//            "No rating", "One",
//            "Two", "Three",
//            "Four", "Five"
//    };
//    private String title = "Rating Distribution";
//
//    public ChartPanel(int[] iv) {
//        value = iv;
//        total = IntStream.of(iv).sum();
//    }


}
