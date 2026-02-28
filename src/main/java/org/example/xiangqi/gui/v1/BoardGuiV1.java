package org.example.xiangqi.gui.v1;

import org.example.xiangqi.core.Board;
import org.example.xiangqi.core.PieceColor;

import java.awt.*;

public class BoardGuiV1 extends Board {
    // Board visualization constants
    public static final Color boardColor = new Color(252, 177, 60);
    public static final Color lineColor = new Color(0, 0, 0);
    public static final Color borderColor = new Color(207, 92, 1);
    public static final Color PIECE_BG = new Color(254, 218, 166);

    public static final int CELL_SIZE = 50;
    public static final int PADDING = 20;
    public static final int PIECE_RADIUS = 20;
    public static final int BORDER_THICKNESS = 4;
    public static final int LINE_THICKNESS = 2;
    public static final int BOARD_WIDTH = (COLUMNS - 1) * CELL_SIZE + 2 * PADDING + BORDER_THICKNESS;
    public static final int BOARD_HEIGHT = (ROWS - 1) * CELL_SIZE + 2 * PADDING + BORDER_THICKNESS;

    private int originX = 50;
    private int originY = 50;

    public BoardGuiV1() {
        super();
    }

    public void updateOrigin(int canvasWidth, int canvasHeight) {
        originX = (canvasWidth - BOARD_WIDTH) / 2;
        originY = (canvasHeight - BOARD_HEIGHT) / 2;
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void paint(Graphics g) {
        drawBoard(g);
        drawPieces(g);
    }

    public void paint(Graphics g, int selectedRow, int selectedCol) {
        drawBoard(g);
        drawPieces(g);
        if (selectedRow >= 0 && selectedCol >= 0) {
            drawSelectedPiece(g, selectedRow, selectedCol);
        }
    }

    private void drawBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Draw board background
        g2d.setColor(borderColor);
        g2d.fillRect(originX, originY, BOARD_WIDTH, BOARD_HEIGHT);

        // Draw inner board
        g2d.setColor(boardColor);
        g2d.fillRect(originX + BORDER_THICKNESS / 2 + PADDING, originY + BORDER_THICKNESS / 2 + PADDING,
                BOARD_WIDTH - BORDER_THICKNESS - PADDING * 2, BOARD_HEIGHT - BORDER_THICKNESS - PADDING * 2);

        // Draw border
        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(BORDER_THICKNESS, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        g2d.drawRect(originX, originY, BOARD_WIDTH, BOARD_HEIGHT);

        // Draw grid lines
        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(LINE_THICKNESS, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));

        // Draw horizontal lines (exactly 10 rows = 11 lines)
        for (int row = 0; row < ROWS; row++) {
            int py = originY + BORDER_THICKNESS / 2 + PADDING + row * CELL_SIZE;
            g2d.drawLine(originX + BORDER_THICKNESS / 2 + PADDING, py, originX + BOARD_WIDTH - BORDER_THICKNESS / 2 - PADDING, py);
        }

        // Draw vertical lines above
        for (int col = 0; col < COLUMNS; col++) {
            int px = originX + BORDER_THICKNESS / 2 + PADDING + col * CELL_SIZE;
            g2d.drawLine(px, originY + BORDER_THICKNESS / 2 + PADDING, px, originY + BORDER_THICKNESS / 2 + PADDING + 4 * CELL_SIZE);
        }

        // Draw vertical lines at bottom
        for (int col = 0; col < COLUMNS; col++) {
            int px = originX + BORDER_THICKNESS / 2 + PADDING + col * CELL_SIZE;
            g2d.drawLine(px, originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 5, px, originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * (ROWS - 1));
        }

        // Draw diagonal lines in the palace areas
        int palaceStartX = originX + BORDER_THICKNESS / 2 + PADDING + 3 * CELL_SIZE;
        int palaceEndX = originX + BORDER_THICKNESS / 2 + PADDING + 5 * CELL_SIZE;
        int palaceTopY = originY + BORDER_THICKNESS / 2 + PADDING;
        int palaceBottomY = originY + BORDER_THICKNESS / 2 + PADDING + 2 * CELL_SIZE;
        // Top palace diagonals
        g2d.drawLine(palaceStartX, palaceTopY, palaceEndX, palaceBottomY);
        g2d.drawLine(palaceEndX, palaceTopY, palaceStartX, palaceBottomY);
        // Bottom palace diagonals
        int palaceBottomStartY = originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 7;
        int palaceBottomEndY = originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 9;
        g2d.drawLine(palaceStartX, palaceBottomStartY, palaceEndX, palaceBottomEndY);
        g2d.drawLine(palaceEndX, palaceBottomStartY, palaceStartX, palaceBottomEndY);

        g2d.drawLine(originX + BORDER_THICKNESS / 2 + PADDING, originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 4, originX + BORDER_THICKNESS / 2 + PADDING, originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 5);
        g2d.drawLine(originX + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 8, originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 4, originX + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 8, originY + BORDER_THICKNESS / 2 + PADDING + CELL_SIZE * 5);

        drawPositionMarkers(g2d);
    }

    private void drawPositionMarkers(Graphics2D g2d) {
        int markerSize = 6;
        int[][] markerPositions = {
                {2, 1}, {2, 7}, {3, 0}, {3, 2}, {3, 4}, {3, 6}, {3, 8},
                {7, 1}, {7, 7}, {6, 0}, {6, 2}, {6, 4}, {6, 6}, {6, 8}
        };
        for (int[] pos : markerPositions) {
            int row = pos[0];
            int col = pos[1];
            int centerX = originX + BORDER_THICKNESS / 2 + PADDING + col * CELL_SIZE;
            int centerY = originY + BORDER_THICKNESS / 2 + PADDING + row * CELL_SIZE;
            drawMarker(g2d, centerX, centerY, markerSize, (col == 0) ? -1 : (col == COLUMNS - 1) ? 1 : 0);
        }
    }

    private void drawMarker(Graphics2D g, int centerX, int centerY, int markerSize, int onBorder) {
        g.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Draw corner marks at 45 degrees
        int cornerDist = (int) (markerSize * 0.7);
        int cornerLen = markerSize;

        if (onBorder != -1) {
            // Top-left corner
            g.drawLine(centerX - cornerDist, centerY - cornerDist - cornerLen,
                    centerX - cornerDist, centerY - cornerDist);
            g.drawLine(centerX - cornerDist - cornerLen, centerY - cornerDist,
                    centerX - cornerDist, centerY - cornerDist);

            // Bottom-left corner
            g.drawLine(centerX - cornerDist, centerY + cornerDist + cornerLen,
                    centerX - cornerDist, centerY + cornerDist);
            g.drawLine(centerX - cornerDist - cornerLen, centerY + cornerDist,
                    centerX - cornerDist, centerY + cornerDist);
        }

        if (onBorder != 1) {
            // Top-right corner
            g.drawLine(centerX + cornerDist, centerY - cornerDist - cornerLen,
                    centerX + cornerDist, centerY - cornerDist);
            g.drawLine(centerX + cornerDist + cornerLen, centerY - cornerDist,
                    centerX + cornerDist, centerY - cornerDist);

            // Bottom-right corner
            g.drawLine(centerX + cornerDist, centerY + cornerDist + cornerLen,
                    centerX + cornerDist, centerY + cornerDist);
            g.drawLine(centerX + cornerDist + cornerLen, centerY + cornerDist,
                    centerX + cornerDist, centerY + cornerDist);
        }
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (!isEmptyAt(row, col)) {
                    int[] center = getCellCenter(row, col);
                    g.setColor(PIECE_BG);
                    g.fillOval(center[0] - PIECE_RADIUS, center[1] - PIECE_RADIUS, PIECE_RADIUS * 2, PIECE_RADIUS * 2);
                    Color pieceColor = (board[row][col].getType().getColor() == PieceColor.RED) ? new Color(200, 50, 50) : new Color(50, 50, 50);
                    g.setColor(pieceColor);
                    g.setFont(new Font("SimSun", Font.BOLD, 24));
                    String symbol = Character.toString(board[row][col].getType().getSymbol());
                    FontMetrics metrics = g.getFontMetrics();
                    int textX = center[0] - metrics.stringWidth(symbol) / 2;
                    int textY = center[1] - (metrics.getAscent() + metrics.getDescent()) / 2 + metrics.getAscent();
                    g.drawString(symbol, textX, textY);
                    g.setColor(lineColor);

                    g.drawOval(center[0] - PIECE_RADIUS, center[1] - PIECE_RADIUS, PIECE_RADIUS * 2, PIECE_RADIUS * 2);
                    g.drawOval(center[0] - PIECE_RADIUS + 3, center[1] - PIECE_RADIUS + 3, (PIECE_RADIUS - 3) * 2, (PIECE_RADIUS - 3) * 2);
                }
            }
        }
    }

    private void drawSelectedPiece(Graphics g, int selectedRow, int selectedCol) {
        Graphics2D g2d = (Graphics2D) g;
        int[] center = getCellCenter(selectedRow, selectedCol);

        // Draw highlight circle with yellow/gold color
        g2d.setColor(new Color(255, 215, 0, 150)); // Semi-transparent gold
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawOval(center[0] - PIECE_RADIUS - 2, center[1] - PIECE_RADIUS - 2,
                     (PIECE_RADIUS + 2) * 2, (PIECE_RADIUS + 2) * 2);

        // Draw inner highlight
        g2d.setColor(new Color(255, 215, 0, 100));
        g2d.drawOval(center[0] - PIECE_RADIUS - 5, center[1] - PIECE_RADIUS - 5,
                     (PIECE_RADIUS + 5) * 2, (PIECE_RADIUS + 5) * 2);
    }

    public int[] getCellCenter(int row, int col) {
        int x = originX + BORDER_THICKNESS / 2 + PADDING + col * CELL_SIZE;
        int y = originY + BORDER_THICKNESS / 2 + PADDING + row * CELL_SIZE;
        return new int[]{x, y};
    }

    public int[] getCellFromPixel(int x, int y) {
        // Find the closest cell by checking distance from pixel to each cell center
        int closestRow = -1;
        int closestCol = -1;
        double minDistance = Double.MAX_VALUE;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int[] center = getCellCenter(row, col);
                double distance = Math.sqrt(Math.pow(x - center[0], 2) + Math.pow(y - center[1], 2));

                // Check if this cell is closer and within reasonable distance (PIECE_RADIUS)
                if (distance < minDistance && distance <= PIECE_RADIUS + 5) {
                    minDistance = distance;
                    closestRow = row;
                    closestCol = col;
                }
            }
        }

        System.out.println("Clicked pixel: (" + x + ", " + y + ") -> Calculated cell: (" + closestRow + ", " + closestCol + "), distance: " + minDistance);
        return new int[]{closestRow, closestCol};
    }


}
