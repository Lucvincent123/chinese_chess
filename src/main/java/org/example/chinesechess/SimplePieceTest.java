package org.example.chinesechess;

import org.example.engine.DrawableEntity;
import java.awt.*;

public class SimplePieceTest implements DrawableEntity {
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Test rendering - draw some circles with text
        int[] xs = {150, 200, 250, 300, 350, 100, 150, 200};
        int[] ys = {150, 150, 150, 150, 150, 200, 200, 200};
        String[] texts = {"紅", "車", "馬", "象", "士", "將", "砲", "兵"};
        
        for (int i = 0; i < xs.length; i++) {
            // Draw circle
            g2d.setColor(new Color(200, 50, 50));
            g2d.fillOval(xs[i] - 20, ys[i] - 20, 40, 40);
            
            // Draw border
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(xs[i] - 20, ys[i] - 20, 40, 40);
            
            // Draw light inner circle
            g2d.setColor(new Color(255, 200, 200));
            g2d.fillOval(xs[i] - 17, ys[i] - 17, 34, 34);

            // Draw text
            Font font = new Font("SimSun", Font.BOLD, 14);
            g2d.setFont(font);
            g2d.setColor(Color.WHITE);
            FontMetrics metrics = g2d.getFontMetrics();
            String text = texts[i];
            int textX = xs[i] - metrics.stringWidth(text) / 2;
            int textY = ys[i] - (metrics.getAscent() + metrics.getDescent()) / 2 + metrics.getAscent();
            g2d.drawString(text, textX, textY);
        }
        
        // Draw simple text test
        g2d.setFont(new Font("Dialog", Font.BOLD, 16));
        g2d.setColor(Color.BLACK);
        g2d.drawString("TEST PIECE RENDERING", 100, 300);
        g2d.drawString("Red circles=Red pieces | See Chinese characters if font available", 100, 330);
        g2d.drawString("If you see circles above with text, rendering works!", 100, 360);

        // Test piece positions from board
        g2d.setFont(new Font("Dialog", Font.PLAIN, 12));
        g2d.drawString("PIECE POSITIONS (row, col):", 100, 400);
        g2d.drawString("Red King at (0,4) should be at pixel: (" + (50 + 2 + 20 + 4*50) + ", " + (50 + 2 + 20 + 0*50) + ")", 100, 420);
        g2d.drawString("Red Chariot at (0,0) should be at pixel: (" + (50 + 2 + 20 + 0*50) + ", " + (50 + 2 + 20 + 0*50) + ")", 100, 440);
    }
}

