package org.example.chinesechess.pieces;

import java.awt.*;

public class King extends Piece {
    public King(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "帥" : "將";  // Sư (đỏ) hoặc Tướng (đen)
    }

    @Override
    public String getType() {
        return "King";
    }

    @Override
    public void draw(Graphics g) {
        if (!this.isCaptured) {

            drawPieceCircle(g, getPixelX(), getPixelY());
            drawText(g, getPixelX(), getPixelY(), name);
        }
    }
}

