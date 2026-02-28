package org.example.xiangqi.gui.v1;

import org.example.xiangqi.core.Game;
import org.example.xiangqi.core.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGameGuiV1 extends Game<BoardGuiV1, CapturedLineGuiV1> {
    public static final int CELL_SIZE = 50;
    public static final int PADDING = 20;
    public static final int PIECE_RADIUS = 20;
    public static final int BORDER_THICKNESS = 4;


    private final int[] selectedPiecePos = new int[]{-1, -1};  // {row, col}
    public final List<int[]> possibleMoves = new ArrayList<>();

    public ChessGameGuiV1(BoardGuiV1 board, CapturedLineGuiV1 redCapturedLine, CapturedLineGuiV1 blackCapturedLine) {
        super(board, redCapturedLine, blackCapturedLine);
    }

    public boolean isNotEmpty(int row, int col) {
        return !board.isEmptyAt(row, col);
    }

    public boolean pieceSelected() {
        return selectedPiecePos[0] != -1 && selectedPiecePos[1] != -1;
    }

    public void paint(Graphics g, int canvasWidth, int canvasHeight) {
        board.updateOrigin(canvasWidth, canvasHeight);
        // Paint board with selected piece indicator
        if (pieceSelected()) {
            board.paint(g, selectedPiecePos[0], selectedPiecePos[1]);
        } else {
            board.paint(g);
        }
        capturedRedPieceLine.paint(g);
        capturedBlackPieceLine.paint(g);
        System.out.println("pieceSelected: " + pieceSelected() + ", selectedPos: (" + selectedPiecePos[0] + ", " + selectedPiecePos[1] + ")");
        if (pieceSelected()) {
            drawPossibleMoves(g);
        }
    }


    public int[] getSelectedCell() {
        return selectedPiecePos;
    }




    public int[] getClickedCell(int x, int y) {
        return board.getCellFromPixel(x, y);
    }

    public void setSelectedCell(int row, int col) {
        if (!BoardGuiV1.isInvalidPosition(row, col) && !board.isEmptyAt(row, col)) {
            selectedPiecePos[0] = row;
            selectedPiecePos[1] = col;
        } else {
            selectedPiecePos[0] = -1;
            selectedPiecePos[1] = -1;
        }

    }

    public void updatePossibleMoves() {
        possibleMoves.clear();
        int row = selectedPiecePos[0];
        int col = selectedPiecePos[1];
        System.out.println("updatePossibleMoves called for position: (" + row + ", " + col + ")");
        if (!BoardGuiV1.isInvalidPosition(row, col)) {
            Piece piece = board.getPieceAt(row, col);
            System.out.println("Piece at position: " + piece);
            if (piece != null) {
                try {
                    List<int[]> moves = board.getPossibleMoves(row, col);
                    possibleMoves.addAll(moves);
                    System.out.println("Found " + moves.size() + " possible moves");
                } catch (Exception e) {
                    System.out.println("Error getting possible moves: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }


    public int[] getPixelPosition(int targetRow, int targetCol) {
        return board.getCellCenter(targetRow, targetCol);
    }

    private void drawPossibleMoves(Graphics g) {
        System.out.println("drawPossibleMoves called with " + possibleMoves.size() + " moves");
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int[] move : possibleMoves) {
            int targetRow = move[0];
            int targetCol = move[1];
            int[] pixelPos = getPixelPosition(targetRow, targetCol);
            System.out.println("Drawing move at: (" + targetRow + ", " + targetCol + ") -> pixel (" + pixelPos[0] + ", " + pixelPos[1] + ")");

            // Draw semi-transparent green circle for possible moves
            g2d.setColor(new Color(0, 255, 0, 100)); // Semi-transparent green
            g2d.fillOval(pixelPos[0] - PIECE_RADIUS / 3, pixelPos[1] - PIECE_RADIUS / 3,
                         (PIECE_RADIUS / 3) * 2, (PIECE_RADIUS / 3) * 2);

            // Draw border
            g2d.setColor(new Color(0, 180, 0, 150));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(pixelPos[0] - PIECE_RADIUS / 3, pixelPos[1] - PIECE_RADIUS / 3,
                         (PIECE_RADIUS / 3) * 2, (PIECE_RADIUS / 3) * 2);
        }
    }
}
