package org.example.xiangqi.core;

import java.util.Objects;

public class Advisor extends Piece {
    public Advisor(TypesOfPiece type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (type == TypesOfPiece.RED_ADVISOR) {
            return toRow >= 7 && toRow <= 9 && toCol >= 3 && toCol <= 5 && Math.abs(toRow - fromRow) == 1 && Math.abs(toCol - fromCol) == 1;
        } else { // BLACK_ADVISOR
            return toRow >= 0 && toRow <= 2 && toCol >= 3 && toCol <= 5 && Math.abs(toRow - fromRow) == 1 && Math.abs(toCol - fromCol) == 1;
        }
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return canMove(chessBoard, fromRow, fromCol, toRow, toCol) && !Objects.equals(chessBoard[toRow][toCol].getType().getColor(), type.getColor());
    }
}
