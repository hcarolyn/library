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
    private Map<Color, Integer> bars = new LinkedHashMap<Color, Integer>();

    public ChartPanel() {
        chartPanel = new JPanel();
        JLabel noLabel = new JLabel("Blue = No Rating");
        noLabel.setBackground(Color.blue);
        JLabel oneLabel = new JLabel("Gray = 1");
        oneLabel.setBackground(Color.lightGray);
        JLabel twoLabel = new JLabel("Cyan = 2");
        twoLabel.setBackground(Color.cyan);
        JLabel threeLabel = new JLabel("Orange = 3");
        threeLabel.setBackground(Color.orange);
        JLabel fourLabel = new JLabel("Red = 4");
        fourLabel.setBackground(Color.red);
        JLabel fiveLabel = new JLabel("Pink = 5");
        fiveLabel.setBackground(Color.pink);

        this.add(noLabel);
        this.add(oneLabel);
        this.add(twoLabel);
        this.add(threeLabel);
        this.add(fourLabel);
        this.add(fiveLabel);
        this.setVisible(true);
    }

    public void addBar(Color color, int value) {
        bars.put(color, value);
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

        for (Color color : bars.keySet()) {
            int value = bars.get(color);
            int height = (int) ((getHeight() - 5) * ((double) value / max));
            g.setColor(BAR_COLOUR);
            g.fillRect(x, getHeight() - height, width, height);
            x += (width + 10);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 14 + 2, 50);
    }

    public void deleteBars() {
        bars.clear();
    }

}
