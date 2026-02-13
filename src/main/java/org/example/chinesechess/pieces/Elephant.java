package org.example.chinesechess.pieces;

import java.awt.*;

public class Elephant extends Piece {
    public Elephant(int row, int col, PieceColor color) {
        super(row, col, color);
        this.name = color == PieceColor.RED ? "相" : "象";  // Tượng (đỏ) hoặc tượng (đen)
    }

    @Override
    public String getType() {
        return "Elephant";
    }

    @Override
    public void draw(Graphics g) {
        drawPieceCircle(g, getPixelX(), getPixelY());
        drawText(g, getPixelX(), getPixelY(), name);
    }
}

