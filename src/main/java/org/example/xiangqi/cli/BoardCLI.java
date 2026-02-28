package org.example.xiangqi.cli;

import org.example.xiangqi.core.Board;
import org.example.xiangqi.core.Piece;

public class BoardCLI extends Board {
    @Override
    public String toString() {
        return buildColumnHeader() +
                buildBorder() +
                buildBoard() +
                buildBorder();
    }

    private String buildColumnHeader() {
        StringBuilder header = new StringBuilder("  ");
        for (int j = 0; j < COLUMNS; j++) {
            header.append(j).append("    ");
        }
        return header.append("\n").toString();
    }

    private String buildBorder() {
        return " " + "#".repeat(COLUMNS * 5 - 1) + "\n";
    }

    private String buildBoard() {
        StringBuilder boardStr = new StringBuilder();
        for (int i = 0; i < Board.ROWS; i++) {
            boardStr.append(i).append("#");
            for (int j = 0; j < Board.COLUMNS; j++) {
                if (j > 0) {
                    boardStr.append("   ");
                }
                boardStr.append(getPieceSymbol(i, j));
            }
            boardStr.append("#\n");
        }
        return boardStr.toString();
    }

    private String getPieceSymbol(int row, int col) {
        Piece piece = board[row][col];
        if (piece != null) {
            return String.valueOf(piece.getType().getSymbol());
        }
        return isRiverRow(row) ? "--" : "++";
    }

    private boolean isRiverRow(int row) {
        return row == Board.RIVER_START || row == Board.RIVER_END;
    }

}


