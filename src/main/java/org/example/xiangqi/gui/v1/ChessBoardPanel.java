package org.example.xiangqi.gui.v1;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class ChessBoardPanel extends JPanel {

    public ChessBoardPanel() {
        setBackground(new Color(255, 228, 196));
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        System.out.println("ChessBoardPanel size: " + w + "x" + h);
    }
}
