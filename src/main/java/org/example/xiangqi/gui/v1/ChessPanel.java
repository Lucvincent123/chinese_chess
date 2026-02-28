package org.example.xiangqi.gui.v1;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import  java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessPanel extends JPanel {
    private final ChessGameGuiV1 chessGame;
    private final int[][] possibleMoves = new int[0][0];

    public ChessPanel(ChessGameGuiV1 chessGame) {
        this.chessGame = chessGame;
        this.chessGame.initialize();
        setBackground(new Color(255, 228, 196));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBoardClick(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        chessGame.paint(g, panelWidth, panelHeight);
    }

    private void handleBoardClick(int x, int y) {
        int[] clickedCell = chessGame.getClickedCell(x, y);
        int cellRow = clickedCell[0];
        int cellCol = clickedCell[1];

        // Invalid cell position
        if (cellRow < 0 || cellCol < 0) {
            chessGame.setSelectedCell(-1, -1);
            repaint();
            return;
        }

        // Check if a piece is already selected
        int[] selectedCell = chessGame.getSelectedCell();
        if (selectedCell[0] == -1 || selectedCell[1] == -1) {
            // No piece selected yet - select a piece
            if (chessGame.isNotEmpty(cellRow, cellCol)) {
                chessGame.setSelectedCell(cellRow, cellCol);
                System.out.println("Selected piece at: (" + cellRow + ", " + cellCol + ")");
                chessGame.updatePossibleMoves();
                System.out.println("Possible moves: " + chessGame.possibleMoves.size());
                repaint();
            }
        } else {
            // A piece is already selected - try to move it
            int fromRow = selectedCell[0];
            int fromCol = selectedCell[1];

            if (fromRow == cellRow && fromCol == cellCol) {
                // Clicked on the same piece - deselect it
                chessGame.setSelectedCell(-1, -1);
                repaint();
                return;
            }

            // Try to move the piece
            boolean moveSuccess = chessGame.movePiece(fromRow, fromCol, cellRow, cellCol);
            if (moveSuccess) {
                System.out.println("Moved piece from (" + fromRow + ", " + fromCol + ") to (" + cellRow + ", " + cellCol + ")");
                chessGame.setSelectedCell(-1, -1); // Clear selection after successful move
            } else {
                System.out.println("Move failed! Attempting to select piece at (" + cellRow + ", " + cellCol + ")");
                // Move failed - try to select the new piece if there is one
                if (chessGame.isNotEmpty(cellRow, cellCol)) {
                    chessGame.setSelectedCell(cellRow, cellCol);
                    chessGame.updatePossibleMoves();
                }
            }
            repaint();
        }
    }

}
