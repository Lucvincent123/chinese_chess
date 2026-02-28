package org.example.xiangqi.cli;

import org.example.xiangqi.core.Game;

public class ChessGameCLI extends Game {
    public ChessGameCLI(BoardCLI board, CapturedLineCLI capturedRedPieceLine, CapturedLineCLI capturedBlackPieceLine) {
        super(board, capturedRedPieceLine, capturedBlackPieceLine);
    }

    @Override
    public String toString() {
        return capturedRedPieceLine + "\n" +
                board +
                capturedBlackPieceLine;
    }


}
