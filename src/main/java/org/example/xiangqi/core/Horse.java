package org.example.xiangqi.core;

public class Horse extends Piece {
    public Horse(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);

        if (!isLShapedMove(rowDiff, colDiff)) {
            return false;
        }

        return !isBlockedByPiece(chessBoard, fromRow, fromCol, toRow, toCol, rowDiff);
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    private boolean isLShapedMove(int rowDiff, int colDiff) {
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    private boolean isBlockedByPiece(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol, int rowDiff) {
        if (rowDiff == 2) {
            // Moving 2 rows: check if vertical path is blocked
            int blockingRow = fromRow + (toRow > fromRow ? 1 : -1);
            return chessBoard[blockingRow][fromCol] != null;
        } else {
            // Moving 2 columns: check if horizontal path is blocked
            int blockingCol = fromCol + (toCol > fromCol ? 1 : -1);
            return chessBoard[fromRow][blockingCol] != null;
        }
    }
}
