package org.example.xiangqi.core;

public class General extends Piece {
    public General(TypesOfPiece type) {
        super(type);
    }

    @Override
    boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (toRow < 0 || toRow > 9 || toCol < 0 || toCol > 8) {
            return false; // Out of bounds
        }
        if (type == TypesOfPiece.RED_GENERAL) {
            if (toRow < 7 || toCol < 3 || toCol > 5) {
                return false; // Red general must stay in the palace
            }
        } else { // BLACK_GENERAL
            if (toRow > 2 || toCol < 3 || toCol > 5) {
                return false; // Black general must stay in the palace
            }
        }
        return (Math.abs(toRow - fromRow) == 1 && toCol == fromCol) ||
               (Math.abs(toCol - fromCol) == 1 && toRow == fromRow); // Move one step orthogonally
    }

    @Override
    boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromCol == toCol) {
            if (chessBoard[toRow][toCol].getType() == TypesOfPiece.RED_GENERAL && this.type == TypesOfPiece.BLACK_GENERAL) {
                return true; // Can capture the opponent's general
            }
            if (chessBoard[toRow][toCol].getType() == TypesOfPiece.BLACK_GENERAL && this.type == TypesOfPiece.RED_GENERAL) {
                return true; // Can capture the opponent's general
            }
        }

        return canMove(chessBoard, fromRow, fromCol, toRow, toCol) && !chessBoard[toRow][toCol].getType().getColor().equals(this.type.getColor());
    }
}
