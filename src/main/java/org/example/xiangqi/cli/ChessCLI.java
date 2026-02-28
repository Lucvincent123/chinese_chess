package org.example.xiangqi.cli;

import java.util.Scanner;

public class ChessCLI {
    public ChessCLI() {
        Scanner scanner = new Scanner(System.in);
        ChessGameCLI chessGame = new ChessGameCLI(new BoardCLI(), new CapturedLineCLI(true), new CapturedLineCLI(false));
        chessGame.initialize();
        boolean running = true;
        while (running) {
            System.out.println(chessGame);
            System.out.print("Enter move (e.g., 'move 0 0 0 1' to move piece from (0,0) to (0,1), or 'exit' to quit): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                running = false;
            } else {
                String[] parts = input.split(" ");
                if (parts.length == 5 && parts[0].equalsIgnoreCase("move")) {
                    try {
                        int fromRow = Integer.parseInt(parts[1]);
                        int fromCol = Integer.parseInt(parts[2]);
                        int toRow = Integer.parseInt(parts[3]);
                        int toCol = Integer.parseInt(parts[4]);
                        if (chessGame.movePiece(fromRow, fromCol, toRow, toCol)) {
                            System.out.println("Move successful!");
                        } else {
                            System.out.println("Invalid move. Try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format. Please enter the move in the correct format.");
                    }
                } else {
                    System.out.println("Invalid command. Please enter a valid move or 'exit' to quit.");
                }


            }

        }
        scanner.close();
    }
}
