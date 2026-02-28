package org.example.xiangqi.core;

public class Chariot extends Piece {
    public Chariot(PieceType type) {
        super(type);
    }

    @Override
    public boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return isStraightLine(fromRow, fromCol, toRow, toCol) &&
               !isPathBlocked(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    @Override
    public boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    private boolean isStraightLine(int fromRow, int fromCol, int toRow, int toCol) {
        return fromRow == toRow || fromCol == toCol;
    }

    private boolean isPathBlocked(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromRow == toRow) {
            return isHorizontalPathBlocked(chessBoard, fromRow, fromCol, toCol);
        } else {
            return isVerticalPathBlocked(chessBoard, fromRow, toRow, fromCol);
        }
    }

    private boolean isHorizontalPathBlocked(Piece[][] chessBoard, int row, int fromCol, int toCol) {
        int step = (toCol - fromCol) > 0 ? 1 : -1;
        for (int col = fromCol + step; col != toCol; col += step) {
            if (chessBoard[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isVerticalPathBlocked(Piece[][] chessBoard, int fromRow, int toRow, int col) {
        int step = (toRow - fromRow) > 0 ? 1 : -1;
        for (int row = fromRow + step; row != toRow; row += step) {
            if (chessBoard[row][col] != null) {
                return true;
            }
        }
        return false;
    }
}
