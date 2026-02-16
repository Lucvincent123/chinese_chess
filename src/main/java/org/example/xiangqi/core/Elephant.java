package org.example.xiangqi.core;

public class Elephant extends Piece {
    private static final int RIVER_ROW = 5;
    private static final int DIAGONAL_DISTANCE = 2;

    public Elephant(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canCrossRiver(toRow) && isDiagonalMove(fromRow, fromCol, toRow, toCol) &&
               isMiddlePointEmpty(chessBoard, fromRow, toRow, fromCol, toCol);
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    private boolean canCrossRiver(int toRow) {
        if (type == PieceType.RED_ELEPHANT) {
            return toRow <= RIVER_ROW; // Red Elephant cannot cross the river
        } else {
            return toRow >= RIVER_ROW; // Black Elephant cannot cross the river
        }
    }

    private boolean isDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
        return Math.abs(toRow - fromRow) == DIAGONAL_DISTANCE &&
               Math.abs(toCol - fromCol) == DIAGONAL_DISTANCE;
    }

    private boolean isMiddlePointEmpty(Piece[][] chessBoard, int fromRow, int toRow, int fromCol, int toCol) {
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        return chessBoard[midRow][midCol] == null;
    }
}
