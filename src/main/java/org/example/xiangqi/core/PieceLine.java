package org.example.xiangqi.core;

public class PieceLine {
    public static final int MAX_PIECES = 16;
    protected final Piece[] line = new Piece[MAX_PIECES];
    protected int count = 0;
    protected final boolean isRed;

    public PieceLine(boolean isRed) {
        this.isRed = isRed;
    }

    public void addPiece(Piece piece) {
        if (count < MAX_PIECES) {
            if (piece == null || piece.getType().getColor() != (isRed ? PieceColor.RED : PieceColor.BLACK)) {
                throw new IllegalArgumentException("Cannot add a null piece.");
            }
            line[count] = piece;
            count++;
        } else {
            throw new IllegalStateException("Cannot capture more than " + MAX_PIECES + " pieces.");
        }
    }

}
