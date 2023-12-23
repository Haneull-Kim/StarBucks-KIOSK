package kiosk6;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColorChangePanel extends JPanel {
    private JLabel colorLabel;
    private JSlider[] sliders;

    public ColorChangePanel() {
        setLayout(new FlowLayout());

        colorLabel = new JLabel(" BANNER ");
        sliders = new JSlider[3];

        for (int i = 0; i < sliders.length; i++) {
            sliders[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
            sliders[i].setPaintLabels(true);
            sliders[i].setPaintTicks(true);
            sliders[i].setPaintTrack(true);
            sliders[i].setMajorTickSpacing(50);
            sliders[i].setMinorTickSpacing(10);
            sliders[i].addChangeListener(new MyChangeListener());
            add(sliders[i]);
        }

        sliders[0].setForeground(Color.RED);
        sliders[1].setForeground(Color.GREEN);
        sliders[2].setForeground(Color.BLUE);

        int r = sliders[0].getValue();
        int g = sliders[1].getValue();
        int b = sliders[2].getValue();

        colorLabel.setOpaque(true);
        colorLabel.setBackground(new Color(r, g, b));
        add(colorLabel);
    }

    class MyChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int r = sliders[0].getValue();
            int g = sliders[1].getValue();
            int b = sliders[2].getValue();
            colorLabel.setBackground(new Color(r, g, b));
        }
    }
}