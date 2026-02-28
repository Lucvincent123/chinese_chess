package org.example.xiangqi.core;

public class General extends Piece {
    public General(PieceType type) {
        super(type);
    }

    @Override
    public boolean canMove(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (type == PieceType.RED_GENERAL) {
            if (toRow > 2 || toCol < 3 || toCol > 5) {
                return false; // Red general must stay in the palace
            }
        } else { // BLACK_GENERAL
            if (toRow < 7 || toCol < 3 || toCol > 5) {
                return false; // Black general must stay in the palace
            }
        }
        return Math.abs(toRow - fromRow) + Math.abs(toCol - fromCol) == 1; // General can only move one step orthogonally
    }

    @Override
    public boolean canCapture(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        return isFacingGeneral(chessBoard, fromRow, fromCol, toRow, toCol) || canMove(chessBoard, fromRow, fromCol, toRow, toCol);
    }

    private boolean isFacingGeneral(Piece[][] chessBoard, int fromRow, int fromCol, int toRow, int toCol) {
        if (fromCol != toCol) {
            return false; // Generals must be in the same column to face each other
        }
        int step = (toRow - fromRow) > 0 ? 1 : -1;
        for (int row = fromRow + step; row != toRow; row += step) {
            Piece pieceBlocking = chessBoard[row][fromCol];
            if (pieceBlocking != null && pieceBlocking.getType().getName() != PieceName.GENERAL) {
                return false; // There is a piece blocking the line of sight
            }
        }
        return true; // Generals are facing each other
    }
}
