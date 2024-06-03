package gui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ChartPanel extends JPanel {
 @Override
 protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     g.setColor(Color.BLACK);
     g.drawRect(50, 50, 200, 150);
 }
}