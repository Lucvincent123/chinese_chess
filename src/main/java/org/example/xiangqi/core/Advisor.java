package org.example.xiangqi.core;

public class Advisor extends Piece {
    private static final int PALACE_COL_MIN = 3;
    private static final int PALACE_COL_MAX = 5;
    private static final int RED_PALACE_ROW_MIN = 7;
    private static final int RED_PALACE_ROW_MAX = 9;
    private static final int BLACK_PALACE_ROW_MIN = 0;
    private static final int BLACK_PALACE_ROW_MAX = 2;

    public Advisor(PieceType type) {
        super(type);
    }

    @Override
    public boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return isInPalace(toRow, toCol) && isDiagonalMove(fromRow, fromCol, toRow, toCol);
    }

    @Override
    public boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    private boolean isInPalace(int row, int col) {
        boolean inColumn = col >= PALACE_COL_MIN && col <= PALACE_COL_MAX;
        if (type == PieceType.RED_ADVISOR) {
            return inColumn && row >= RED_PALACE_ROW_MIN && row <= RED_PALACE_ROW_MAX;
        } else {
            return inColumn && row >= BLACK_PALACE_ROW_MIN && row <= BLACK_PALACE_ROW_MAX;
        }
    }

    private boolean isDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
        return Math.abs(toRow - fromRow) == 1 && Math.abs(toCol - fromCol) == 1;
    }
}
