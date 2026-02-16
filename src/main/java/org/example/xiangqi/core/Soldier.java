package org.example.xiangqi.core;

public class Soldier extends Piece {
    private static final int RIVER_ROW_RED = 5;
    private static final int RIVER_ROW_BLACK = 4;

    public Soldier(PieceType type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (type == PieceType.RED_SOLDIER) {
            return canMoveRedSoldier(fromRow, fromCol, toRow, toCol);
        } else {
            return canMoveBlackSoldier(fromRow, fromCol, toRow, toCol);
        }
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    private boolean canMoveRedSoldier(int fromRow, int fromCol, int toRow, int toCol) {
        if (isForwardMove(toRow, fromRow, toCol, fromCol, 1)) {
            return true;
        }
        return fromRow >= RIVER_ROW_RED && isLateralMove(toRow, fromRow, toCol, fromCol);
    }

    private boolean canMoveBlackSoldier(int fromRow, int fromCol, int toRow, int toCol) {
        if (isForwardMove(toRow, fromRow, toCol, fromCol, -1)) {
            return true;
        }
        return fromRow <= RIVER_ROW_BLACK && isLateralMove(toRow, fromRow, toCol, fromCol);
    }

    private boolean isForwardMove(int toRow, int fromRow, int toCol, int fromCol, int direction) {
        return toRow == fromRow + direction && toCol == fromCol;
    }

    private boolean isLateralMove(int toRow, int fromRow, int toCol, int fromCol) {
        return toRow == fromRow && Math.abs(toCol - fromCol) == 1;
    }
}
