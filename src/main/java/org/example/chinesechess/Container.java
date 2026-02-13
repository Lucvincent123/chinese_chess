package org.example.chinesechess;

import org.example.chinesechess.pieces.Piece;
import org.example.engine.GameContainer;
import org.example.engine.GameWindow;

import java.awt.Color;

public class Container extends GameContainer {
    private Board board;

    public Container(GameWindow window, int targetFPS, int targetUPS) {
        super(window, targetFPS, targetUPS);

        // Create chess board
        ChessBoard chessBoard = new ChessBoard(50, 50, Color.ORANGE, Color.BLACK, Color.BLACK);
        this.window.getCanvas().addEntity(chessBoard);

        // Create game board and add all pieces
        this.board = new Board();
        Piece[] pieces = board.getAllPieces();
        for (Piece piece : pieces) {
            this.window.getCanvas().addEntity(piece);
        }
    }

    public Board getBoard() {
        return board;
    }
}
