package org.example.chinesechess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.example.chinesechess.pieces.Piece;

public class Input implements MouseListener {
    private final Board board;

    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int[] pos = board.screenToBoard(e.getX(), e.getY());
        int row = pos[0];
        int col = pos[1];
        if (board.getSelectedPiece() == null) {
            // No piece selected, try to select a piece
            Piece piece = board.getPiece(row, col);
            if (piece != null) {
                board.setSelectedPiece(piece);
            }
        } else {
            // A piece is already selected, try to move it
            Piece selectedPiece = board.getSelectedPiece();
            if (board.isValidMove(selectedPiece, row, col)) {
                board.movePiece(selectedPiece, row, col);
            }
            board.setSelectedPiece(null);  // Deselect after attempting move
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
