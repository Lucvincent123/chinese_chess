package org.example.chinesechess.pieces;

import org.example.engine.DrawableEntity;
import java.awt.*;

public abstract class Piece implements DrawableEntity {
    protected int row, col;  // Vị trí trên bàn cờ (hàng, cột)
    protected PieceColor color;  // Màu quân cờ (đỏ hoặc đen)
    protected int size;  // Kích thước quân cờ
    protected String name;  // Tên quân cờ
    protected boolean isSelected = false;  // Trạng thái được chọn
    protected boolean isCaptured = false;  // Trạng thái bị bắt

    // Board position constants
    protected static final int BOARD_X = 50;
    protected static final int BOARD_Y = 50;
    protected static final int PADDING = 20;
    protected static final int BORDER_THICKNESS = 4;
    protected static final int CELL_SIZE = 50;

    public enum PieceColor {
        RED, BLACK
    }



    public Piece(int row, int col, PieceColor color) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.size = 40;  // Kích thước mặc định
    }

    public abstract String getType();

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    public PieceColor getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    protected int getPixelX() {
        return BOARD_X + BORDER_THICKNESS / 2 + PADDING + col * CELL_SIZE;
    }

    protected int getPixelY() {
        return BOARD_Y + BORDER_THICKNESS / 2 + PADDING + row * CELL_SIZE;
    }

    @Override
    public abstract void draw(Graphics g);

    // Phương thức vẽ quân cờ cơ bản
    protected void drawPieceCircle(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ hình tròn ngoài
        if (color == PieceColor.RED) {
            g2d.setColor(new Color(200, 50, 50));  // Đỏ tươi
        } else {
            g2d.setColor(new Color(50, 50, 50));   // Đen tuyền
        }
        g2d.fillOval(x - size / 2, y - size / 2, size, size);

        // Vẽ border
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x - size / 2, y - size / 2, size, size);

        // Vẽ highlight nếu được chọn
        if (isSelected) {
            g2d.setColor(new Color(255, 255, 0, 128)); // Vàng bán trong suốt
            g2d.fillOval(x - size / 2 - 5, y - size / 2 - 5, size + 10, size + 10);
        }


        // Vẽ nền sáng bên trong
        g2d.setColor(new Color(255, 200, 200));
        g2d.fillOval(x - size / 2 + 3, y - size / 2 + 3, size - 6, size - 6);
    }

    // Phương thức vẽ text trên quân cờ
    protected void drawText(Graphics g, int x, int y, String text) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        // Try multiple fonts for Chinese character support
        Font font = new Font("SimSun", Font.BOLD, 18);
        // Fallback fonts if SimSun is not available
        String[] fontNames = {"SimSun", "Microsoft YaHei", "Noto Sans CJK SC", "DejaVu Sans"};
        for (String fontName : fontNames) {
            Font testFont = new Font(fontName, Font.BOLD, 18);
            if (!testFont.getName().equals("Dialog")) {
                font = testFont;
                break;
            }
        }

        g2d.setFont(font);

        // All text is white for better visibility
        g2d.setColor(Color.WHITE);

        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y - (metrics.getAscent() + metrics.getDescent()) / 2 + metrics.getAscent();

        g2d.drawString(text, textX, textY);
    }

    public void setCaptured(boolean captured) {
        isCaptured = captured;
    }
}

