package org.example.chinesechess;

import org.example.engine.DrawableEntity;

import java.awt.*;

public class ChessBoard implements DrawableEntity {
    private static final int ROWS = 10;
    private static final int COLS = 9;

    // All position markers (row, col) - exact positions for piece placement
    private static final int[][] MARKER_POSITIONS = {
            // Top cannon positions
            {2, 1}, {2, 7},

            // Top soldier positions (row 3)
            {3, 0}, {3, 2}, {3, 4}, {3, 6}, {3, 8},

//            // Middle positions around river (rows 4-5)
//            {4, 1}, {4, 3}, {4, 5}, {4, 7},
//            {5, 1}, {5, 3}, {5, 5}, {5, 7},

            // Bottom soldier positions (row 6)
            {6, 0}, {6, 2}, {6, 4}, {6, 6}, {6, 8},

            // Bottom cannon positions
            {7, 1}, {7, 7}
    };

    private int x, y, width, height;
    private Color bg, lineColor, markerColor;
    private int lineThickness = 2;
    private int padding = 20;
    private int borderThickness = 4;
    private int cellSize = 50;

    public ChessBoard(int x, int y, Color bg, Color lineColor, Color markerColor) {
        this.x = x;
        this.y = y;
        this.bg = bg;
        this.lineColor = lineColor;
        this.markerColor = markerColor;
        this.width = (COLS - 1) * cellSize + 2 * padding + borderThickness; // Add border and line thickness to width
        this.height = (ROWS - 1) * cellSize + 2 * padding + borderThickness; // Add border and line thickness to height
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Draw background
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, width, height);

        // Draw chessboard background (wood texture or color)
        g2d.setColor(bg);
        g2d.fillRect(x + borderThickness / 2 + padding, y + borderThickness / 2 + padding,
                width - borderThickness - padding * 2, height - borderThickness - padding * 2);

        // Draw border
        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(borderThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        g2d.drawRect(x, y, width, height);

        // Draw grid lines
        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));

        // Draw horizontal lines (exactly 10 rows = 11 lines)
        for (int row = 0; row < ROWS; row++) {
            int py = y + borderThickness / 2 + padding + row * cellSize;
            g2d.drawLine(x + borderThickness / 2 + padding, py, x + width - borderThickness / 2 - padding, py);
        }

        // Draw vertical lines above
        for (int col = 0; col < COLS; col++) {
            int px = x + borderThickness / 2 + padding + col * cellSize;
            g2d.drawLine(px, y + borderThickness / 2 + padding, px, y + borderThickness / 2 + padding + 4 * cellSize);
        }

        // Draw vertical lines at bottom
        for (int col = 0; col < COLS; col++) {
            int px = x + borderThickness / 2 + padding + col * cellSize;
            g2d.drawLine(px, y + borderThickness / 2 + padding + cellSize * 5, px, y + borderThickness / 2 + padding + cellSize * (ROWS - 1));
        }

        // Draw diagonal lines in the palace areas
        int palaceStartX = x + borderThickness / 2 + padding + 3 * cellSize;
        int palaceEndX = x + borderThickness / 2 + padding + 5 * cellSize;
        int palaceTopY = y + borderThickness / 2 + padding;
        int palaceBottomY = y + borderThickness / 2 + padding + 2 * cellSize;
        // Top palace diagonals
        g2d.drawLine(palaceStartX, palaceTopY, palaceEndX, palaceBottomY);
        g2d.drawLine(palaceEndX, palaceTopY, palaceStartX, palaceBottomY);
        // Bottom palace diagonals
        int palaceBottomStartY = y + borderThickness / 2 + padding + cellSize * 7;
        int palaceBottomEndY = y + borderThickness / 2 + padding + cellSize * 9;
        g2d.drawLine(palaceStartX, palaceBottomStartY, palaceEndX, palaceBottomEndY);
        g2d.drawLine(palaceEndX, palaceBottomStartY, palaceStartX, palaceBottomEndY);

        // Draw position markers (for piece placement)
        drawPositionMarkers(g2d);

        // Draw two vertical lines in the river area
        g2d.drawLine(x + borderThickness / 2 + padding, y + borderThickness / 2 + padding + cellSize * 4, x + borderThickness / 2 + padding, y + borderThickness / 2 + padding + cellSize * 5);
        g2d.drawLine(x + borderThickness / 2 + padding + cellSize * 8, y + borderThickness / 2 + padding + cellSize * 4, x + borderThickness / 2 + padding + cellSize * 8, y + borderThickness / 2 + padding + cellSize * 5);
    }


    private void drawPositionMarkers(Graphics2D g) {
        g.setColor(markerColor);
        int markerSize = 10;

        for (int[] pos : MARKER_POSITIONS) {
            int row = pos[0];
            int col = pos[1];
            int centerX = x + borderThickness / 2 + padding + col * cellSize;
            int centerY = y + borderThickness / 2 + padding + row * cellSize;
            if (col == 0) {
                // Left edge marker
                drawMarker(g, centerX + markerSize / 2, centerY, markerSize, -1);
            } else if (col == COLS - 1) {
                // Right edge marker
                drawMarker(g, centerX - markerSize / 2, centerY, markerSize, 1);
            } else {
                // Normal marker
                 drawMarker(g, centerX, centerY, markerSize, 0);
            }
        }

    }

    private void drawMarker(Graphics2D g, int centerX, int centerY, int markerSize, int onBorder) {
        g.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Draw corner marks at 45 degrees
        int cornerDist = (int) (markerSize * 0.7);
        int cornerSize = (int) (markerSize * 0.4);

        if (onBorder != -1) {
            // Top-left corner
            g.drawLine(centerX - cornerDist, centerY - cornerDist - cornerSize,
                    centerX - cornerDist, centerY - cornerDist);
            g.drawLine(centerX - cornerDist - cornerSize, centerY - cornerDist,
                    centerX - cornerDist, centerY - cornerDist);

            // Bottom-left corner
            g.drawLine(centerX - cornerDist, centerY + cornerDist + cornerSize,
                    centerX - cornerDist, centerY + cornerDist);
            g.drawLine(centerX - cornerDist - cornerSize, centerY + cornerDist,
                    centerX - cornerDist, centerY + cornerDist);
        }

        if (onBorder != 1) {
            // Top-right corner
            g.drawLine(centerX + cornerDist, centerY - cornerDist - cornerSize,
                    centerX + cornerDist, centerY - cornerDist);
            g.drawLine(centerX + cornerDist + cornerSize, centerY - cornerDist,
                    centerX + cornerDist, centerY - cornerDist);

            // Bottom-right corner
            g.drawLine(centerX + cornerDist, centerY + cornerDist + cornerSize,
                    centerX + cornerDist, centerY + cornerDist);
            g.drawLine(centerX + cornerDist + cornerSize, centerY + cornerDist,
                    centerX + cornerDist, centerY + cornerDist);
        }
    }
}
