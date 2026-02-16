package org.example.xiangqi.core;

public class Piece {
    protected final TypesOfPiece type;

    public Piece(TypesOfPiece type) {
        this.type = type;
    }

    public TypesOfPiece getType() {
        return type;
    }

    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return false;
    }

    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return false;
    }


}
