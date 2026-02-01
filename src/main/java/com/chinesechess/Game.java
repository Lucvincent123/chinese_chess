package com.chinesechess;

public class Game {
    private final Board board;
    private final User player1;
    private final User player2;
    private User currentPlayer;

    public Game(User player1, User player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1.getColor().equals("Red") ? player1 : player2;
    }

    public void start() {
        // Game loop logic
        board.display();
    }

    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public User getCurrentPlayer() {
        return currentPlayer;
    }
}
